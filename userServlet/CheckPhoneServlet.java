package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;

public class CheckPhoneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDao dao = new UserDao();
		String phone = request.getParameter("phone");
		PrintWriter pWriter = response.getWriter();
		boolean flag = dao.ajaxCheck("phone",phone);
		if (flag) {
			pWriter.write("yes");
		}else {
			pWriter.write("no");
		}
		pWriter.close();
	}
}
