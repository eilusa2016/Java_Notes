package com.xjb.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {

	/**
	 * �����valu���ǻ��Q�����ļ����õ��Լ�ʹ�õ�cache1 ������һ�� ���յ� getTimestamp(String key2)����Ĳ���Ҫע��
	 * ��Ҫ��ע����������ͬ ����key = "#key2" ��Ϊʹ��spring el���ʽ
	 */
	@Cacheable(value = "cache1", key = "#key2")
	public String getTimestamp(String key2) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

	// �Զ��������ָ���ǿ���ͨ��Spring��EL���ʽ��ָ�����ǵ�key��
	// �����EL���ʽ����ʹ�÷������������Ƕ�Ӧ�����ԡ�
	// ʹ�÷�������ʱ���ǿ���ֱ��ʹ�á�#�����������ߡ�#p����index����
	// �����Ǽ���ʹ�ò�����Ϊkey��ʾ����

	@Cacheable(value = "cache1", key = "#id")
	public User find1(Integer id) {
		return null;
	}

	@Cacheable(value = "cache1", key = "#p0")
	public User find2(Integer id) {
		return null;
	}

	@Cacheable(value = "cache1", key = "#user.id")
	public User find3(User user) {
		return null;
	}

	@Cacheable(value = "cache1", key = "#p0.id")
	public User find4(User user) {
		return null;
	}

}
