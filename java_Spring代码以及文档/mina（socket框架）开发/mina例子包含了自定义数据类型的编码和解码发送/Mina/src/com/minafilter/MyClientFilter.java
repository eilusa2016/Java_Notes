package com.minafilter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
/**
 * 客户端的过滤器
 * @author Administrator
 *
 */
public class MyClientFilter extends IoFilterAdapter {
	/**
	 * 收到消息
	 */
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		nextFilter.messageReceived(session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		nextFilter.messageSent(session, writeRequest);
	}
}
