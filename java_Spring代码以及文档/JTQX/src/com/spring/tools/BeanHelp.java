package com.spring.tools;

import org.springframework.beans.BeansException;

public class BeanHelp extends BeanFactory {

	@SuppressWarnings("unchecked")
	public static <T extends Object> T getBean(String name) {

		try {
			return ((T) getFactory().getBean(name));
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 通过类型来获取bean
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends Object> T getBean(Class<T> clazz) {
		try {
			return getFactory().getBean(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
}
