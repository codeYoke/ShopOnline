package com.fjh.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.biz.GoodsBiz;
import com.biz.impl.GoodsBizImpl;
import com.fjh.Goods;
import com.fjh.dao.GoodsDao;
import com.fjh.dao.impl.GoodsDaoImpl;


/**
 * Servlet implementation class SearchServlet
 */

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsBiz bookbiz = null;
	private GoodsDao bookdao = null;
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bookdao = new GoodsDaoImpl();
		bookbiz = new GoodsBizImpl();
		bookbiz.setBookdao(bookdao);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int countByName = 0;
		String currentPage = request.getParameter("currentPage");//获取当前页
		int no = currentPage == null?1:Integer.parseInt(currentPage);//如果当前页为空，则默认为1，否则转化为相应的int
		
//		String currentSearchPage = request.getParameter("currentSearchPage");
		
		String bookname = request.getParameter("keywords");//获取你输入的书名	
		
		String keywords = bookname;
		List<Map<String, Object>> books = null;
		if(bookname==null || bookname.equals("") || bookname.equals("null")){
			books=bookbiz.findAll(3, no);//查询所有。3：代表每页显示的条数，no：当前显示页面
			request.setAttribute("totalPage", (bookbiz.count()/3 + 1));//将总页面数存入request
			
		} else {
		
			countByName = bookbiz.findGoodsByName(bookname);//记录模糊查询的总记录数
			books = bookbiz.findGoodsByName(bookname,3, no);//根据书名模糊查询出数据
			//System.out.println(books.size());
			request.setAttribute("totalPage", (countByName/3 + 1));//将总页面数存入request
		}
		request.setAttribute("keywords", keywords);
		System.out.println(keywords);
		request.setAttribute("books", books);//将查询出的数据存入request
		request.setAttribute("current", no);//将当前页存入request	
		request.getRequestDispatcher("main.jsp").forward(request, response);//页面转发
		
	}

}
