package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CookieTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =request.getCookies();
		Cookie remCookie = null;
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if("remname".equals(cookie.getName())){
					remCookie = cookie;
				}
			}
		}
		String username = "";
		if(remCookie != null){
			username = remCookie.getValue();
			username = URLDecoder.decode(username,"UTF-8");
		}
		
	}

}
