package com.itstyle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itstyle.util.CreatHtml;
/**
 * 
 *这个是要配置servlet来启动
 *
 */
public class CreatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CreatServlet(){
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("生成静态页面开始");
		CreatHtml.CreatAllHtml(request);
		System.out.println("生成静态页面结束");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript' type='text/javascript'>");
		out.println("window.location.href='" + request.getContextPath() + "/file/question.html'");
		out.println("</script>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
}
