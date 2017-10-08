package com.xjb.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {

	/**
	 * 这里的valu就是花黁配置文件配置的自己使用的cache1 这里有一点 狗日的 getTimestamp(String key2)这里的参数要注意
	 * 需要和注解中属性相同 比如key = "#key2" 因为使用spring el表达式
	 */
	@Cacheable(value = "cache1", key = "#key2")
	public String getTimestamp(String key2) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

	// 自定义策略是指我们可以通过Spring的EL表达式来指定我们的key。
	// 这里的EL表达式可以使用方法参数及它们对应的属性。
	// 使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”。
	// 下面是几个使用参数作为key的示例。

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
