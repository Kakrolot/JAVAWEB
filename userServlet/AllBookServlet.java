package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;
import cn.tedu.javaweb.service.BookService;
import cn.tedu.javaweb.service.BookServiceImpl;

public class AllBookServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用dao层的方法，完成查询书籍的操作
		/*BookDao dao = new BookDao();
		ArrayList<Book> list = dao.selectAll();
		//把数据存入到request对象中
		request.setAttribute("books",list);*/
		//页面跳转
		//获取页面中的请求参数:currentPage  / pageSize
		
		String currentPage = request.getParameter("currentPage");//当前页
		String pageSize = request.getParameter("pageSize");
		/*
		 * 需要调用业务逻辑层 专门处理分页的业务
		 * 1.定义一个接口:BookService(业务的接口)
		 * 2.定义一个实现类:BookServiceImpl(业务的实现类)
		 */
		BookService bs = new BookServiceImpl();
		//发生了调用
		PageBean<Book> pg = bs.selectBookByPage(currentPage, pageSize);
		request.setAttribute("pg", pg);
		request.setAttribute("books", pg.getPageList());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
