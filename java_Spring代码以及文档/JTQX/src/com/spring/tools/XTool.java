package com.spring.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanAccessLanguageException;
import org.apache.commons.lang.StringUtils;



import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Service
public class XTool {
	
	/**
	 * 判断是否是数值
	 * @param str
	 * @return
	 */
	public  Boolean IsNumic(String str){
		if(str==null){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		Boolean flag=pattern.matcher(str).matches();
		return flag;
	}
	
	
	private  String remoteIp = null;
	protected  String getRemoteIp(HttpServletRequest request ){
        if (remoteIp==null || remoteIp.length()==0)
        {
            remoteIp = request.getHeader("x-forwarded-for");
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp= request.getHeader("X-Real-IP");
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getHeader("Proxy-Client-IP");
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getHeader("WL-Proxy-Client-IP");
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getHeader("HTTP_CLIENT_IP");
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getRemoteAddr();
            }
            if (remoteIp == null ||remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
               remoteIp= request.getRemoteHost();
            }
        }
        return remoteIp;
    }
	
	
	
	
	public  JSONArray NormalFormatBeanToJsonArray(List<?> list){
		JSONArray array=new JSONArray();
		for(Object pav:list){
			JSONObject obj=JSONObject.fromObject(pav);
			array.add(obj);
		}
		return array;
	}
	
	public  Object JsonToBean(String jsonString,Class<?> bean){
		Object object=new Object();
		JSONObject json=JSONObject.fromObject(jsonString);
		if(jsonString!=null){
			object=JSONObject.toBean(json, bean);
		}
		
		return object;
	}
	
	
	public  JSONObject NormalBeanToJson(Object obj){
		JSONObject object=new JSONObject();
		if(obj!=null){
			object=JSONObject.fromObject(obj);
		}
		return object;
	}
	/**
	 * 得到Cookies
	 * @param cookiename
	 * @param request
	 * @param response
	 * @return
	 */
	public  Cookie GetCookie(String cookiename,Cookie[] cookies){
		Cookie cookie=null;
//		Cookie[] cookies=request.getCookies();
		for(Cookie cook:cookies){
			String name=cook.getName();
			if(name.equals(cookiename)){
				cookie=cook;
				break;
			}
		}
		return cookie;
	}
	
	
	@SuppressWarnings("unchecked")
	public  <T> T  JsonStringToBean(String jsonString, Class<T> pojoCalss){
		Object pojo=null; 
		try {
			JSONObject object=JSONObject.fromObject(jsonString);
			pojo=JSONObject.toBean(object, pojoCalss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T)pojo;
	}
	
	/**
	 * 将一个对象装换成一个Json对象
	 * @param obj
	 * @return
	 */
	public    JSONObject ObjectConvertToJson(Object obj){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(obj, config);
	}
	
	public   JSONObject ObjectListConvertToJson(List<?> list){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(list, config);
	}
	
	
	public    JSONArray ObjectListConvertToJsonarray(List<?> list){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(list, config);
	}
	
	
	public   HashMap<String, Object> ReflectToMap(Class<?> cls,Object ob) throws IllegalArgumentException, IllegalAccessException{
		HashMap<String, Object> map=new HashMap<String, Object>();
		Field[] fields=cls.getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			map.put(field.getName(), field.get(ob));
		}
		return map;
	}
	
	
	public  <T extends Object>List<T> getArrayListT(){
		List<T> list=new ArrayList<T>();
		return list;
		
	}
}
