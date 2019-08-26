package com.fjh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.UserBiz;
import com.biz.impl.UserBizImpl;
import com.fjh.UserInfo;
import com.fjh.dao.UserDao;
import com.fjh.dao.impl.UserDaoImpl;


/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = null;
	private UserBiz userBiz = null;
	UserInfo userInfo = null;

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    
    	userDao = new UserDaoImpl();
		userBiz = new UserBizImpl();
		userBiz.setUserDao(userDao);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");	//根据查看当前执行的动作时什么，（action有值，则检查用户名是否存在，null则注册）
		if(action == null) {
			register(request, response);
		} else {
			check(request, response);
		}
		
//		register(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//防止中文注册名乱码		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		userInfo = new UserInfo();
		userInfo.setUserName(username);
		userInfo.setPassword(password);
		userInfo.setEmail(email);
		System.out.println("注册状态："+userBiz.addUser(userInfo));
		if(!userBiz.addUser(userInfo)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert(\"注册失败！请联系管理员咨询\")");//反斜杠转义
			out.println("open(\"register.jsp\", \"_self\");");//重新打开新的页面, _self在原窗口打开
			out.println("</script>");
			out.close();
		}
		response.sendRedirect("register_success.jsp");//注册成功
		
	}
	
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean isExist = userBiz.userExist(username,userInfo);
		System.out.println("用户是否存在数据库："+isExist);
		if(isExist) {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("true");
			System.out.println("用户是否存在状态：true");
			response.getWriter().close();
		} else {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("false");
			System.out.println("用户是否存在状态：false");
			response.getWriter().close();
		}
	}

}
