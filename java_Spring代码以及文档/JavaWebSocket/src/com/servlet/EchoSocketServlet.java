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
 * ����ʹ��ע�� @ServerEndpoint �������Ҫ �ӿڷ�ʽ�Ļ� ʵ�� Endpoint ����ӿ�
 * 
 * ע�⣺����ÿ�������Ƕ����� �����̰߳�ȫ ����ľ�̬�����ļ��� ֻ������������������ʱ��Ĳ�������ͬ�Ķ���
 * 
 * @author Administrator
 */
@ServerEndpoint("/websocket/echoAnnotation")
public class EchoSocketServlet {

	// ��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
	private static int onlineCount = 0;

	// concurrent�����̰߳�ȫSet��
	// �������ÿ���ͻ��˶�Ӧ��MyWebSocket����
	// ��Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	private static CopyOnWriteArraySet<EchoSocketServlet> webSocketSet = new CopyOnWriteArraySet<EchoSocketServlet>();
	/**
	 * �����˵ļ���
	 */
	private static CopyOnWriteArrayList<String> chatNames = new CopyOnWriteArrayList<>();
	// �Ƽ������ַ�ʽ�洢
	private static Map<String, EchoSocketServlet> webSocketSetMap = new ConcurrentHashMap<String, EchoSocketServlet>();
	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;
	private String  LoginName;//ȫ����Ϣ
	public Session getSession() {
		return session;
	}

	/**
	 * @Onpen ��ʾ ͨ������ʱ��ִ�е���Ӧ������
	 * @param session
	 *            ����һ�λỰ
	 */
	@OnOpen
	public void open(Session session) {
		this.session = session;
		webSocketSet.add(this);
		webSocketSetMap.put(session.getId(), this);
		addOnlineCount();// ����һ������
		// session ����һ�� socket���ӡ� ���Խ������ݣ� Ҳ���Է������ݡ�
		// ȡ���ʺź�����ַ���,������ʹ�������ʽ�ò���
		// String queryString=session.getQueryString();
		// if(queryString!=null){
		// if(!queryString.equals(""))
		// System.out.println("������"+queryString);
		// }
		System.out.println("���ӽ����ɹ�������" + session.getId());
		// System.out.println("webSocketSetMap:length=" +
		// webSocketSetMap.size());
	}

	/**
	 * �յ��ͻ�����Ϣ����õķ���
	 * 
	 * @param message
	 *            �ͻ��˷��͹�������Ϣ
	 * @param session
	 *            ��ѡ�Ĳ���
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
	 * �㲥
	 * @param message
	 */
	private void broadCast(String message) {
		// Ⱥ����Ϣ Ҳ�������ɹ��˵���
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
	// session.getBasicRemote().sendText("��Ҳ�á�����");
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
		subOnlineCount();// ����������1
		
		//���¹㲥
		chatNames.remove(this.LoginName);
		JsonMessage mess=new JsonMessage();
		mess.setType("all");
		mess.setWho(LoginName);
		mess.setWitch("users");
		mess.setNameList(chatNames);
		broadCast(new Gson().toJson(mess).toString());
		System.out.println(session.getId() + "session �ر���");
	}

	@OnError
	public void onError(Throwable error) {
		// ���´���ʡ��...
		error.printStackTrace();
	}

	/**
	 * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ����� ��������и��ô� spring���ʱ�� ���ԴӺ������������Ϣ��ǰ��
	 * �����Ŀ�����о�
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		// ͬ�� ���Ƽ�һ�η�����
		// this.session.getBasicRemote().sendText(message);
		// �첽 �Ƽ�ʹ��
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
