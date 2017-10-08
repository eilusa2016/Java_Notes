package org.xjb.cxf.ws.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.xjb.cxf.ws.domain.Cat;
import org.xjb.cxf.ws.util.StringCat;

/**
 * 处理map的xml映射的适配器
 * cxf是不能处理map集合的
 * <p>Title:MapXMlAdapter</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月28日
 */
public class MapXMlAdapter extends XmlAdapter<StringCat, Map<String, Cat>> {
	/**
	 * StringCat :自定义的可以处理的类型
	 * Map<String, Cat> ：cxf无法处理转换的类型
	 */
	/**
	 * 将map还原的方法
	 */
	@Override
	public Map<String, Cat> unmarshal(StringCat v) throws Exception {
		Map<String, Cat> map=new HashMap<String, Cat>();
		for(StringCat.Entry entry:v.getEntries()){
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	/**
	 * 将map转换成StringCat的方法
	 */
	@Override
	public StringCat marshal(Map<String, Cat> v) throws Exception {
		StringCat stringCat=new StringCat();
		stringCat.setEntries(new ArrayList<StringCat.Entry>());
		for(String str:v.keySet()){
			StringCat.Entry entry=new StringCat.Entry();
			entry.setKey(str);
			entry.setValue(v.get(str));
			stringCat.getEntries().add(entry);
		}
		return stringCat;
	}
	


}
