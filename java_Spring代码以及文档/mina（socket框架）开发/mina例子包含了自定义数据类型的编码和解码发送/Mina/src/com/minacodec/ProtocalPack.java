package com.minacodec;

import java.io.Serializable;

/**
 * 自定义信息的包体
 * @author Administrator
 *
 */
public class ProtocalPack implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int length;
	private byte flag;
	private String content;
	public ProtocalPack() {
		
	}
	
	public ProtocalPack(byte flag, String content) {
		this.length = (content==null)?0:(content.getBytes().length+5);
		this.flag = flag;
		this.content = content;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public byte getFlag() {
		return flag;
	}
	public void setFlag(byte flag) {
		this.flag = flag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ProtocalPack [length=" + length + ", flag=" + flag + ", content=" + content + "]";
	}
	
	
}
