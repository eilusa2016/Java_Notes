package com.servlet.user;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ע��WebServlet��������һ��Servlet
 * ����name����Servlet������,��ѡ
 * ����urlPatterns������ʵ�URL,����ʹ������value������ʵ�URL.(������ʵ�URL�Ǳ�ѡ����)
 */
@WebServlet(name="LoginServlet",urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname=req.getParameter("username");
		req.getSession().setAttribute("username", uname);
		resp.sendRedirect("chat.jsp");
		
	}
	
}
