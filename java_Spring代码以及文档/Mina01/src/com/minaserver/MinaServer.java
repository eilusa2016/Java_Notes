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
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	static int PORT = 7080;
	static IoAcceptor ioAcceptor;

	public static void main(String[] args) throws IOException {
		ioAcceptor = new NioSocketAcceptor();
		// 设置编码过滤器
		ioAcceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("utf-8"), LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));
		// 设置缓冲区
		ioAcceptor.getSessionConfig().setReadBufferSize(1024);
		// 设置 超时时间 10 毫秒： session并没有处理任何的请求，并且这个时间超过了你设定的 session 进入空闲状态的那个时间点
		ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		// 设置处理业务的handler
		ioAcceptor.setHandler(new MyHandler());
		// 绑定端口号
		 ioAcceptor.bind(new InetSocketAddress(PORT));

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
//		／／List
//		ArrayList
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String mess = (String) message;
		System.out.println("接收到：" + mess);
		if(mess.equals("quit"))
			//客户端这里退出
			session.close(true);
		
		Date date = new Date();
		session.write(date);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
		//super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
		//super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
		//super.sessionCreated(session);
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
