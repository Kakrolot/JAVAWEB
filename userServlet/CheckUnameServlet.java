package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
/*
 * ר����������Ĵ������Ӧ
 */
public class CheckUnameServlet extends HttpServlet{
	//��д������ڵķ���
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request��reponse�ı����ʽ
		request.setCharacterEncoding("UTF-8");
		//��ʹ��ajaxʱ����Ҫ�Ľ����һ���ַ���
		//�����������html�ĸ�ʽ����ô�е�������ͻ���Ϊyes��һ��Html��ǩ���¸�ʽ��ƥ��
		//����,������Ҫ��responseע�͵�
		//response.setContentType("text/html;charset=utf-8");
		//��ȡҳ���еĲ���:uname
		//�˴���uname�ֶΣ�ָ����ajax�е�data:'uname'+ֵ;
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
		//������û��Ѿ�����ʱ:ʹ��io������������һ���ַ���
		
	
	}

}
