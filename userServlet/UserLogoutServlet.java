package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//登出功能
@WebServlet("/user/page/userLogoutServlet")
public class UserLogoutServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//退出时消除session
		request.getSession().setAttribute("user", null);
		//消除自动登录的cookie
		Cookie cookie = new Cookie("autologin","" );
		cookie.setPath(request.getContextPath()+"/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//退出时使用重定向，定位到login.jsp
		response.sendRedirect("login.jsp");
	}

}
