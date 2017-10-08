package com.minacodec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 自定义解码器
 * @author Administrator
 *
 */
public class MyProtacolDecodec implements ProtocolDecoder {

	private final AttributeKey CONTEXT=new AttributeKey(this.getClass(), "context");
	private final Charset charset;
	private int maxPackLength=100;
	public MyProtacolDecodec() {
		this(Charset.defaultCharset());
	}
	public MyProtacolDecodec(Charset charset) {
		this.charset=charset;
	}
	public Context getContext(IoSession session){
		Context ctx=(Context)session.getAttribute(CONTEXT);
		if(ctx==null){
			ctx=new Context();
			session.setAttribute(CONTEXT,ctx);
		}
		return  ctx;
	}
	
	
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		final int packHeadLength=5;
		Context ctx=getContext(session);
		ctx.Append(in);
		
		IoBuffer buffer=ctx.getBuf();
		buffer.flip();
		while(buffer.remaining()>=packHeadLength){
			//可读
			buffer.mark();//标记
			int length=buffer.getInt();
			byte flag=buffer.get();//读取flag
			if(length<0||length>maxPackLength){
	//			超出长度无效
				buffer.reset();
				break;
			}else if(length>packHeadLength&&length-packHeadLength<=buffer.remaining()){
				//数据正常
				int olderLimit=buffer.limit();
				buffer.limit(buffer.position()+length-packHeadLength);
				String content=buffer.getString(ctx.getDecoder());
				buffer.limit(olderLimit);
				ProtocalPack pack=new ProtocalPack(flag, content);
				out.write(pack);//传数据
			}else{
				//半包结构
				buffer.clear();
				break;
			}
		}
		if(buffer.hasRemaining()){
			//还有数据
			//数据移到buffer的前面
			IoBuffer temp=IoBuffer.allocate(maxPackLength).setAutoExpand(true);
			temp.put(buffer);
			temp.flip();
			buffer.reset();
			buffer.put(temp);
		}else{
			//已经处理好了
			buffer.reset();
		}
		
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		Context ctx=(Context)session.getAttribute(CONTEXT);
		if(ctx!=null){
			session.removeAttribute(CONTEXT);
		}
	}
	public int getMaxPackLength() {
		return maxPackLength;
	}
	public void setMaxPackLength(int maxPackLength) {
		if(maxPackLength<0){
			throw new IllegalArgumentException("maxPackLength长度："+maxPackLength);
		}
		this.maxPackLength = maxPackLength;
	}
	/**
	 * 内部类
	 * 解码的山下文
	 * @author Administrator
	 *
	 */
	private class Context{
		private final CharsetDecoder decoder;
		private IoBuffer buf;
		private  Context() {
			decoder=charset.newDecoder();
			buf=IoBuffer.allocate(80).setAutoExpand(true);
		}
		/**
		 * 追加
		 * @param io
		 */
		public void Append(IoBuffer io){
			getBuf().put(io);
		}
		/**
		 * 重置
		 */
		public void Reset(){
			decoder.reset();
		}
		
		
		public IoBuffer getBuf() {
			return buf;
		}
		public void setBuf(IoBuffer buf) {
			this.buf = buf;
		}

		public CharsetDecoder getDecoder() {
			return decoder;
		}
		
	}
	
}
