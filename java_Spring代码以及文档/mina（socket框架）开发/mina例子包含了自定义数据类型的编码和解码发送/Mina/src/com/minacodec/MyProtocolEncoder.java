package com.minacodec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;

import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
/**
 * 自定义编码
 * @author Administrator
 *
 */
public class MyProtocolEncoder extends ProtocolEncoderAdapter {

	private final Charset charset;
	public MyProtocolEncoder(Charset charset) {
		this.charset=charset;
	}
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ProtocalPack pack=(ProtocalPack)message;
		IoBuffer buffer=IoBuffer.allocate(pack.getLength());
		buffer.setAutoExpand(true);//自动增长
		buffer.putInt(pack.getLength());
		buffer.put(pack.getFlag());
		if(pack.getContent()!=null){
			buffer.put(pack.getContent().getBytes());
		}
		buffer.flip();
		out.write(buffer);//发送出去
	}



}
