package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.filter.AutoLoginFilter;
import cn.tedu.javaweb.pojo.User;

public class UserLoginServlet extends HttpServlet{
	//编写处理请求的service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//设置编码格式
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//通过request对象，后去登陆的用户名和密码
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		//使用io流，输出到浏览器的信息
		//获取页面中验证码文本框输入的内容
		String valistr = request.getParameter("valistr");
		//获取session中存放的验证码(图片上的验证码)
		String code = (String) request.getSession().getAttribute("code");
		//匹配验证码 不匹配时不能登陆
		if (!code.equalsIgnoreCase(valistr)) {
			request.setAttribute("showResult", "验证码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		
		UserDao usd = new UserDao();
		User user = usd.selectByUnameAndUpwd(uname, upwd);
		if (user!=null) {//该用户可以登录
			//response.sendRedirect("index.html");
			//使用session把user对象记录下来

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			String remname = (String) request.getParameter("remname");
			if ("true".equals(remname)) {
				Cookie cookie = new Cookie("remname", URLEncoder.encode(uname,"utf-8"));
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
			}else {
				//取消是使用新的cookie代替原来的cookie
				Cookie cookie = new Cookie("remname", "");
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			//将密码存储到cookie中
			
			//key:"user"表示程序员的自定义名字
			//value:user对象 表示用户的当前登陆信息
			String autologin = request.getParameter("autologin");
			//自动登陆的设置
			if ("true".equals(request.getParameter("autologin"))) {
				//字符串的拼接
				Cookie cookie = new Cookie("autologin", URLEncoder.encode(uname,"utf-8")+"#"+upwd);
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(3600*24*30);
				response.addCookie(cookie);
				
			}
			
			
			
			
			request.getRequestDispatcher("allBookServlet").forward(request,response);
		}else {//该用户的用户名或者密码错误
			response.sendRedirect("login.jsp");
		}
		
		
		
	}

}
