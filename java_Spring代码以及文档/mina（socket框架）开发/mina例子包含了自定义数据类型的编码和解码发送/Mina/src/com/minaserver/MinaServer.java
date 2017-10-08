package com.minaserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.minacodec.ProtocalFactory;
import com.minacodec.ProtocalPack;
import com.minafilter.MyClientFilter;
import com.minafilter.MyServerFilter;
/**
 * 服务端
 * @author Administrator
 *
 */
public class MinaServer {
	static int PORT = 7080;
	static IoAcceptor ioAcceptor;
	/**
	 * //获得过滤器
			 //ioAcceptor.getFilterChain();
			 //得到会话的配置信息
			 //ioAcceptor.getSessionConfig();
			 //完成关闭的时候  调用的方法
			 //ioAcceptor.dispose();
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//构造指定线程数量
		ioAcceptor = new NioSocketAcceptor(10);
		
		// 设置编码过滤器
//		ioAcceptor.getFilterChain().addLast(
//				"dcodec",
//				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//						.forName("utf-8"), LineDelimiter.WINDOWS.getValue(),
//						LineDelimiter.WINDOWS.getValue())));
		//添加自定义的解码器工厂
		ioAcceptor.getFilterChain().addLast("coderc", 
				new ProtocolCodecFilter(new ProtocalFactory(Charset.forName("UTF-8"))));
		
		
		//添加自定义过滤器  这个实在handler之前使用这个过滤器
		//ioAcceptor.getFilterChain().addFirst("myServerFilter",new MyServerFilter());
		// 设置缓冲区
		ioAcceptor.getSessionConfig().setReadBufferSize(1024);
		// 设置 超时时间 10 毫秒： session并没有处理任何的请求，并且这个时间超过了你设定的 session 进入空闲状态的那个时间点
		ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		// 设置处理业务的handler
		ioAcceptor.setHandler(new MyHandler());
		// 绑定端口号
		 ioAcceptor.bind(new InetSocketAddress(PORT));
		 
		 //ioAcceptor.dispose();
		System.out.println("Mina Server  starting");

	}
}

/**
 * 自己的handler
 */
class MyHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught="+cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
//		String mess = (String) message;
//		System.out.println("接收到：" + mess);
		//现在要接收对象
		ProtocalPack pack=(ProtocalPack)message;
		System.out.println("接收到：" + pack.toString());
		session.write(pack);
//		if(mess.equals("quit"))
//			session.close(true);//客户端这里退出
		
//		Date date = new Date();
//		session.write(date);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
		//session.close(true);//立马关闭  就从长连接编程短链接 如果客户端谢了dispose  那么就会直接关闭掉通道
		//super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
		//super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		//解决关闭后  仍然链接的问题 不会再进入TIME_WAIT状态了
//		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   
//	    cfg.setReceiveBufferSize(2 * 1024 * 1024);   
//	    cfg.setReadBufferSize(2 * 1024 * 1024);   
//	    cfg.setKeepAlive(true);   
//	    cfg.setSoLinger(0); //这个是根本解决问题的设置
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
		//super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
		//super.sessionOpened(session);
	}

}
