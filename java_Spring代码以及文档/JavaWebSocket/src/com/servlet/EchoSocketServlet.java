package com.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.servlet.entity.JsonMessage;

/**
 * 这里使用注解 @ServerEndpoint 这个很重要 接口方式的话 实现 Endpoint 这个接口
 * 
 * 注意：这里每个请求都是独立的 所以线程安全 下面的静态变量的集合 只不过是来做多个请求的时候的并发处理不同的对象
 * 
 * @author Administrator
 */
@ServerEndpoint("/websocket/echoAnnotation")
public class EchoSocketServlet {

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，
	// 用来存放每个客户端对应的MyWebSocket对象。
	// 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<EchoSocketServlet> webSocketSet = new CopyOnWriteArraySet<EchoSocketServlet>();
	/**
	 * 在线人的集合
	 */
	private static CopyOnWriteArrayList<String> chatNames = new CopyOnWriteArrayList<>();
	// 推荐用这种方式存储
	private static Map<String, EchoSocketServlet> webSocketSetMap = new ConcurrentHashMap<String, EchoSocketServlet>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	private String  LoginName;//全局信息
	public Session getSession() {
		return session;
	}

	/**
	 * @Onpen 表示 通道建立时，执行的响应方法。
	 * @param session
	 *            代表一次会话
	 */
	@OnOpen
	public void open(Session session) {
		this.session = session;
		webSocketSet.add(this);
		webSocketSetMap.put(session.getId(), this);
		addOnlineCount();// 增加一个链接
		// session 代表一次 socket连接。 可以接收数据， 也可以返回数据。
		// 取到问号后面的字符串,不建议使用这个方式拿参数
		// String queryString=session.getQueryString();
		// if(queryString!=null){
		// if(!queryString.equals(""))
		// System.out.println("参数："+queryString);
		// }
		System.out.println("连接建立成功！！！" + session.getId());
		// System.out.println("webSocketSetMap:length=" +
		// webSocketSetMap.size());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void echoTextMessage(Session session, String message, boolean last) {
		System.out.println(message);
		// String nameId=session.getId();
		Gson gson = new Gson();
		JsonMessage mess = gson.fromJson(message, JsonMessage.class);
		// String messages="";
		if (mess != null) {
			if (mess.getType().equals("open")) {
				if (!chatNames.contains(mess.getWho()))
					LoginName=mess.getWho();
					chatNames.add(mess.getWho());
			}
		}
		mess.setNameList(chatNames);
		broadCast(gson.toJson(mess).toString());
	}
	/**
	 * 广播
	 * @param message
	 */
	private void broadCast(String message) {
		// 群发消息 也可以做成过滤单发
		for (EchoSocketServlet item : webSocketSet) {
			try {

				item.sendMessage(message);
			} catch (IOException e) {
				try {
					session.close();
				} catch (IOException e1) {
					// Ignore
				}
			}
		}

	}

	// @OnMessage
	// public void receive(Session session, String msg){
	// System.out.println(msg);
	//
	// try {
	// session.getBasicRemote().sendText("你也好。。。");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// @OnMessage
	// public void echoBinaryMessage(Session session, ByteBuffer bb,
	// boolean last) {
	// try {
	// if (session.isOpen()) {
	// session.getBasicRemote().sendBinary(bb, last);
	// }
	// } catch (IOException e) {
	// try {
	// session.close();
	// } catch (IOException e1) {
	// // Ignore
	// }
	// }
	// }
	//
	// /**
	// * Process a received pong. This is a NO-OP.
	// * @param pm Ignored.
	// */
	// @OnMessage
	// public void echoPongMessage(PongMessage pm) {
	// // NO-OP
	// }
	//

	@OnClose
	public void close(Session session) {
		webSocketSet.remove(this);
		webSocketSetMap.remove(session.getId());
		subOnlineCount();// 在线人数减1
		
		//重新广播
		chatNames.remove(this.LoginName);
		JsonMessage mess=new JsonMessage();
		mess.setType("all");
		mess.setWho(LoginName);
		mess.setWitch("users");
		mess.setNameList(chatNames);
		broadCast(new Gson().toJson(mess).toString());
		System.out.println(session.getId() + "session 关闭啦");
	}

	@OnError
	public void onError(Throwable error) {
		// 以下代码省略...
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 这个方法有个好处 spring结合时候 可以从后端主动发送信息到前端
	 * 其他的框架再研究
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		// 同步 不推荐一次发送完
		// this.session.getBasicRemote().sendText(message);
		// 异步 推荐使用
		this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		onlineCount--;
	}
}
