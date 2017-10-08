package com.minacodec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
/**
 * 解码工厂
 * @author Administrator
 *
 */
public class ProtocalFactory implements ProtocolCodecFactory {

	final MyProtacolDecodec decoder;
	final MyProtocolEncoder encoder;
	public ProtocalFactory(Charset charset) {
		encoder=new MyProtocolEncoder(charset);
		decoder=new MyProtacolDecodec(charset);
	}
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return  decoder;
	}

}
