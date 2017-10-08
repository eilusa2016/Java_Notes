package com.xjb.eheache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcaheTest {
	public static void main(String[] args) {
		//���������ļ�����cache������ ��һ��
		//CacheManager cacheManager=CacheManager.create("src/main/resources/ehcache.xml");
		//�ڶ���  ����ϲ���ڶ���
		URL url = EhcaheTest.class.getResource("/ehcache.xml");
		CacheManager cacheManager = new CacheManager(url);
		
		//��ȡ�����ļ������úõ�Cache��ǩ�Ļ���
		Cache cache1=cacheManager.getCache("cache1");
		//��Ԫ��  element
		net.sf.ehcache.Element element=new Element("key1", "��һ��cacheԪ��");
		cache1.put(element);;
		
		//��ȡ  ����key��ȡ
		net.sf.ehcache.Element element2=cache1.get("key1");
		System.out.println(element2);
		cache1.flush();//ˢ�»���
		
		cacheManager.shutdown();//�رջ��������  �ͷ��ڴ�
	}
}
