package cn.tedu.javaweb.userServlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.User;
@WebServlet("/user/page/deleteCartServlet")
public class DeleteCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ����ʽ
		request.setCharacterEncoding("UTF-8");
		//����ridɾ������
		//���Ȼ��user���û���Ϣ
		User user = (User) request.getSession().getAttribute("user");
		String rid = request.getParameter("itemId");
		//����dao��ķ���
		AddCartDao dao = new AddCartDao();
		dao.deleteCart(rid);
		request.getRequestDispatcher("allCartServlet").forward(request, response);
	}

}
