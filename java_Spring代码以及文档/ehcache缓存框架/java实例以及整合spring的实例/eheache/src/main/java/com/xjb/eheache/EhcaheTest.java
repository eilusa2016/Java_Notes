package com.xjb.eheache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcaheTest {
	public static void main(String[] args) {
		//根据配置文件创建cache管理器 第一种
		//CacheManager cacheManager=CacheManager.create("src/main/resources/ehcache.xml");
		//第二种  个人喜欢第二种
		URL url = EhcaheTest.class.getResource("/ehcache.xml");
		CacheManager cacheManager = new CacheManager(url);
		
		//获取配置文件中配置好的Cache标签的缓存
		Cache cache1=cacheManager.getCache("cache1");
		//存元素  element
		net.sf.ehcache.Element element=new Element("key1", "第一个cache元素");
		cache1.put(element);;
		
		//获取  根据key获取
		net.sf.ehcache.Element element2=cache1.get("key1");
		System.out.println(element2);
		cache1.flush();//刷新缓存
		
		cacheManager.shutdown();//关闭缓存管理器  释放内存
	}
}
