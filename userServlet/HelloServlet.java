package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//1.继承
public class HelloServlet extends HttpServlet {
	//重写父类中的service()方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request 专门处理请求
		//reponse负责相应给浏览器结果
		//功能:前台发送了一个hello请求
		//功能:后台需要处理这个请求
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h3>欢迎来到这里</h3>");
		out.print("<h1>喵喵喵</h1>");
		out.close();
	}

}
