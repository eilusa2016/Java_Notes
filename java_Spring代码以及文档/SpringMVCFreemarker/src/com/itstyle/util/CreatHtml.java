package com.itstyle.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.itstyle.model.Question;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
/**
 * 
 * @author 科帮网(www.52itstyle.com)
 *
 */
public class CreatHtml {
     public static void CreatAllHtml(HttpServletRequest request){
    	    Map<String,Object> root = new HashMap<String,Object>(); 
    	    String path = request.getSession().getServletContext().getContextPath();
    	    String templatePath = request.getSession().getServletContext().getRealPath("/file");
    	    String templateName = "question.flt";
			String targetHtmlPath =request.getSession().getServletContext().getRealPath("/file")+Constants.SF_FILE_SEPARATOR+"question.html";
			Question question = new Question(1,"我是中国人");
			root.put("question", question);   
			root.put("path" , path);
			crateHTML(root, templatePath, templateName, targetHtmlPath);
     }
     public static void crateHTML(Map<String,Object> data,String templatePath,String templateName,String targetHtmlPath){
    	 Configuration freemarkerCfg = new Configuration();  
 		//加载模版  
 		Writer out = null;
 		try {  
 			//设置要解析的模板所在的目录，并加载模板文件  
 			freemarkerCfg.setDirectoryForTemplateLoading(new File(templatePath));
 			//设置包装器，并将对象包装为数据模型  
 			freemarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
 		    //指定模版路径  
 		    Template template = freemarkerCfg.getTemplate(templateName,"UTF-8");  
 		    template.setEncoding("UTF-8");  
 		    //静态页面路径  
 		    FileOutputStream fos = new FileOutputStream(targetHtmlPath);  
 		    out = new OutputStreamWriter(fos,"UTF-8");  
 		    //合并数据模型与模板  
 	        template.process(data, out);  
 		} catch (Exception e) {  
 		    e.printStackTrace();  
 		}finally{
 			 try {
 				 out.flush();
 				 out.close();  
 			} catch (IOException e) {
 				e.printStackTrace();
 			}  
 		}
  	}
     
}
