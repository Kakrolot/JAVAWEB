package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

public class UserRegistServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		//����dao�㷽��,���ݷ��ʲ�
		UserDao dao = new UserDao();
		//����Insert����,��ע����Ϣ�������ݿ�
		dao.insert(phone, uname, upwd, email,0);
		//ʹ���ض���
		response.sendRedirect("login.html");
		

	}

}
