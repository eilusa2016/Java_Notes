package com.changwu.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaThread extends Thread {

	private IoSession session = null;
	private IoConnector connector = null;

	@Override
	public void run() {
		super.run();
		// TODO Auto-generated method stub]
		System.out.println("客户端链接开始...");
		connector = new NioSocketConnector();
		System.out.println(101);
		// 设置链接超时时间
		connector.setConnectTimeoutMillis(10000);
		System.out.println(102);
		// 添加过滤器
		// connector.getFilterChain().addLast("codec", new
		// ProtocolCodecFilter(new CharsetCodecFactory()));
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		System.out.println(110);
		connector.setHandler(new MinaClientHandler());
		System.out.println(111);
		//设置默认连接远程服务器的IP地址和端口
		connector.setDefaultRemoteAddress(new InetSocketAddress(ConstantUtil.OUT_MATCH_PATH, ConstantUtil.WEB_MATCH_PORT));
		// 监听客户端是否断线
		connector.addListener(new IoListener() {
			@Override
			public void sessionDestroyed(IoSession arg0) throws Exception {
				// TODO Auto-generated method stub
				super.sessionDestroyed(arg0);
				try {
					int failCount = 0;
					while (true) {
						Thread.sleep(5000);
						System.out.println(((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
								.getHostAddress());
						ConnectFuture future = connector.connect();
						System.out.println("断线2");
						future.awaitUninterruptibly();// 等待连接创建完成
						System.out.println("断线3");
						session = future.getSession();// 获得session
						System.out.println("断线4");
						if (session != null && session.isConnected()) {
							System.out.println("断线5");
							System.out.println("断线重连["
									+ ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress()
									+ ":" + ((InetSocketAddress) session.getRemoteAddress()).getPort() + "]成功");
							session.write("start");
							break;
						} else {
							System.out.println("断线重连失败---->" + failCount + "次");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		//开始连接
		try {
			System.out.println(112);
			ConnectFuture future = connector.connect();
			System.out.println(113);
			future.awaitUninterruptibly();// 等待连接创建完成
			System.out.println(114);
			session = future.getSession();// 获得session
			System.out.println(115);
			//判断是否连接服务器成功
			if (session != null && session.isConnected()) {
				session.write("start");
			} else {
				System.out.println("写数据失败");
			}

			System.out.println(11);
		} catch (Exception e) {
			System.out.println("客户端链接异常...");
		}
		System.out.println(118);
		if (session != null && session.isConnected()) {
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
			System.out.println("客户端断开111111...");
			// connector.dispose();//彻底释放Session,退出程序时调用不需要重连的可以调用这句话，也就是短连接不需要重连。长连接不要调用这句话，注释掉就OK。
		}

	}

}