package cn.tedu.javaweb.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;
@WebFilter("/*")
public class AutoLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁过滤器");
		
	}
	

	//核心部分由程序员编写
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1.未登录才自动登录
		
		//拦截并后去requst对象
		//查看session会话当中是否有user对象
		//如果没有session对象，实现自动登录
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null){
			//2.只有带自动登陆cookie才能自动登陆
			Cookie[] cookies = req.getCookies();
			Cookie autoCookie = null;
			if (cookies!=null) {
				for(Cookie cookie:cookies){
					if("autologin".equals(cookie.getName())){
						autoCookie = cookie;
						break;
					}
				}
			}
			//不为空才能获取用户与密码
			if (autoCookie != null) {
				//3.只有cookie中的用户名密码都正确才能自动登录
				//split拆分字符串
				String username = URLDecoder.decode(autoCookie.getValue(),"UTF-8").split("#")[0];
				String password = URLDecoder.decode(autoCookie.getValue(),"UTF-8").split("#")[1];
				
				UserDao dao = new UserDao();
				User user = dao.selectByUnameAndUpwd(username, password);
				System.out.println("自动登陆的用户名:"+username);
				if(user!=null){
					//符合以上三个条件,自动登陆
					req.getSession().setAttribute("user",user);
				}
			}
		}
		//4.放行请求
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化过滤器");
		
	}

}
