package com.minaclient;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.minacodec.ProtocalFactory;
import com.minacodec.ProtocalPack;
import com.minafilter.MyClientFilter;
/**
 * 客户端
 * @author Administrator
 *
 */
public class MinaClient {
	static String host = "127.0.0.1";
	static int PORT = 7080;
	final static int fil=100;//消息长度
	static long start=0;
	static long counter=0;
	public static void main(String[] args) {
		IoSession session=null;
		IoConnector ioConnector = new NioSocketConnector();
		//链接超时设置
		ioConnector.setConnectTimeoutMillis(30000);
		//过滤器
//		ioConnector.getFilterChain().addLast(
//				"dcodec",
//				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//						.forName("utf-8"), LineDelimiter.WINDOWS.getValue(),
//						LineDelimiter.WINDOWS.getValue())));
		
//		//添加自定义的解码器工厂
		ioConnector.getFilterChain().addLast("coderc", 
						new ProtocolCodecFilter(new ProtocalFactory(Charset.forName("UTF-8"))));
		
		//添加自定义过滤器 这个实在handler之前使用这个过滤器
		//ioConnector.getFilterChain().addFirst("myClientFilter",new MyClientFilter());
		
		//业务处理的handler
		ioConnector.setHandler(new ClientHandler());
		//设置 超时时间 10 毫秒
		ioConnector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		//链接
		ConnectFuture connectFuture=ioConnector.connect(new InetSocketAddress(host, PORT));
		//监听事件
		connectFuture.addListener(new IoFutureListener<ConnectFuture>() {

			@Override
			public void operationComplete(ConnectFuture future) {
				if(future.isConnected()){
					IoSession session=future.getSession();
					System.out.println("以链接开始发送数据");
					SendData(session);
				}
				
			}
		});
		
		
		//等待连接
		connectFuture.awaitUninterruptibly();
		
		session=connectFuture.getSession();
		//session.write("quit");
		session.getCloseFuture().awaitUninterruptibly();//等待连接关闭
		//完成关闭的时候  调用的方法
		//彻底释放Session,退出程序时调用不需要重连的可以调用
		//也就是短连接不需要重连。长连接不要调用这句话，注释掉就OK。
		ioConnector.dispose();
	}
	/**
	 * 发送消息
	 * @param session
	 */
	public static void SendData(IoSession session){
		for(int i=0;i<fil;i++){
			String content="watchman:"+i;
			ProtocalPack pack=new ProtocalPack((byte)i, content);
			session.write(pack);
			//System.out.println("制作了数据="+pack.toString());
		}
	}
}




class ClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
//		String mess=(String)message;
//		System.out.println("客户端接受到的数据:"+mess);
		//session.close();
		ProtocalPack pack=(ProtocalPack)message;
		System.out.println("客户端接受到的数据:"+pack.toString());
		
	}

//	@Override
//	public void messageSent(IoSession session, Object message) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Client=messageSent");
//		//session.close();
//	}
//
//	@Override
//	public void sessionClosed(IoSession session) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Client=sessionClosed");
//	}
//
//	@Override
//	public void sessionCreated(IoSession session) throws Exception {
//		// TODO Auto-generated method stub
////		System.out.println("Client=sessionCreated");
////		//解决关闭后  仍然链接的问题 不会再进入TIME_WAIT状态了
////		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   
////        cfg.setReceiveBufferSize(2 * 1024 * 1024);   
////        cfg.setReadBufferSize(2 * 1024 * 1024);   
////        cfg.setKeepAlive(true);   
////        cfg.setSoLinger(0); //这个是根本解决问题的设置
//		
//		
//	}
//
//	@Override
//	public void sessionIdle(IoSession session, IdleStatus status)
//			throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Client=sessionIdle");
//	}
}
