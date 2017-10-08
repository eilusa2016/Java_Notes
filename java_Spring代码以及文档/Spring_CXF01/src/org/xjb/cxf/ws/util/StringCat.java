package org.xjb.cxf.ws.util;

import java.util.ArrayList;
import java.util.List;

import org.xjb.cxf.ws.domain.Cat;

/**
 * 自定义的用来适配的类
 * @date 2016年9月28日
 */
public class StringCat {

	private List<Entry> entries=new ArrayList<Entry>(); 
		
	/**
	 * 静态内部类
	 * key  - value 的类
	 */
	public static class Entry {
		private String key;
		private Cat value;

		public Entry() {
		}

		public Entry(String key, Cat value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Cat getValue() {
			return value;
		}

		public void setValue(Cat value) {
			this.value = value;
		}
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
