package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//1.�̳�
public class HelloServlet extends HttpServlet {
	//��д�����е�service()����
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request ר�Ŵ�������
		//reponse������Ӧ����������
		//����:ǰ̨������һ��hello����
		//����:��̨��Ҫ�����������
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h3>��ӭ��������</h3>");
		out.print("<h1>������</h1>");
		out.close();
	}

}
