package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
/*
 * 专门用于请求的处理和响应
 */
public class CheckUnameServlet extends HttpServlet{
	//重写服务入口的方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request和reponse的编码格式
		request.setCharacterEncoding("UTF-8");
		//当使用ajax时，需要的结果是一个字符串
		//如果这设置了html的格式，那么有的浏览器就会认为yes是一个Html标签导致格式不匹配
		//所以,我们需要把response注释掉
		//response.setContentType("text/html;charset=utf-8");
		//获取页面中的参数:uname
		//此处的uname字段，指的是ajax中的data:'uname'+值;
		String uname = request.getParameter("uname");
		UserDao dao  = new UserDao();
		
		/*boolean flag = dao.ajaxCheckUname(uname);
		
		if (flag) {
			
		    writer.write("yes");
		    
		}else {
			writer.write("no");
		}
		writer.close();*/
		boolean flag = dao.ajaxCheck("uname", uname);
		PrintWriter writer = response.getWriter();
		if(flag){
			writer.write("yes");
		}else {
			writer.write("no");
		}
		writer.close();
		
	
		/*String email = request.getParameter("email");
		System.out.println(email);
		String phone = request.getParameter("phone");
		System.out.println(phone);*/
		//如果该用户已经存在时:使用io流向浏览器输出一个字符串
		
	
	}

}
