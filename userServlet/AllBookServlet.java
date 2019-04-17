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
		//����dao��ķ�������ɲ�ѯ�鼮�Ĳ���
		/*BookDao dao = new BookDao();
		ArrayList<Book> list = dao.selectAll();
		//�����ݴ��뵽request������
		request.setAttribute("books",list);*/
		//ҳ����ת
		//��ȡҳ���е��������:currentPage  / pageSize
		
		String currentPage = request.getParameter("currentPage");//��ǰҳ
		String pageSize = request.getParameter("pageSize");
		/*
		 * ��Ҫ����ҵ���߼��� ר�Ŵ����ҳ��ҵ��
		 * 1.����һ���ӿ�:BookService(ҵ��Ľӿ�)
		 * 2.����һ��ʵ����:BookServiceImpl(ҵ���ʵ����)
		 */
		BookService bs = new BookServiceImpl();
		//�����˵���
		PageBean<Book> pg = bs.selectBookByPage(currentPage, pageSize);
		request.setAttribute("pg", pg);
		request.setAttribute("books", pg.getPageList());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
