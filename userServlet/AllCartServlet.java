package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.pojo.User;

//control��
public class AllCartServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request�ı����ʽ
		request.setCharacterEncoding("UTF-8");
		//����reponse�ı����ʽ
		response.setContentType("text/html;charset=UTF-8");
		//��ȡҳ���еĲ��� ��session �еõ�user�Ķ�����Ϣ
		User user = (User)request.getSession().getAttribute("user");
		//����dao��ķ�����ѯȫ���Ĺ��ﳵ��Ϣ
		/*
		 * 1.����uid��������û������е�cart�����Լ���Ӧ��book����
		 * 2.�ѻ�ȡ�Ľ������װ��ArrayList<BookAndCart> ������
		 */
		String uid = user.getPhone();
		AddCartDao dao = new AddCartDao();
		ArrayList<CartAndBook> list = dao.selectAllByUid(uid);
		request.setAttribute("newList", list);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
