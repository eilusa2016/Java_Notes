package com.servlet.entity;

import java.util.ArrayList;
import java.util.List;

public class JsonMessage {
	private String type;
	private String mess;
	private String who;
	private String witch;
	private String ip;
	private List<String> nameList=new ArrayList<String>();
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public String getWitch() {
		return witch;
	}
	public void setWitch(String witch) {
		this.witch = witch;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	
}
