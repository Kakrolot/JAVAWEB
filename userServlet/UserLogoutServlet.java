package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�ǳ�����
@WebServlet("/user/page/userLogoutServlet")
public class UserLogoutServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�˳�ʱ����session
		request.getSession().setAttribute("user", null);
		//�����Զ���¼��cookie
		Cookie cookie = new Cookie("autologin","" );
		cookie.setPath(request.getContextPath()+"/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//�˳�ʱʹ���ض��򣬶�λ��login.jsp
		response.sendRedirect("login.jsp");
	}

}