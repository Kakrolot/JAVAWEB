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
	//��д���������service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//���ñ����ʽ
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//ͨ��request���󣬺�ȥ��½���û���������
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		//ʹ��io������������������Ϣ
		//��ȡҳ������֤���ı������������
		String valistr = request.getParameter("valistr");
		//��ȡsession�д�ŵ���֤��(ͼƬ�ϵ���֤��)
		String code = (String) request.getSession().getAttribute("code");
		//ƥ����֤�� ��ƥ��ʱ���ܵ�½
		if (!code.equalsIgnoreCase(valistr)) {
			request.setAttribute("showResult", "��֤�벻��ȷ");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		
		UserDao usd = new UserDao();
		User user = usd.selectByUnameAndUpwd(uname, upwd);
		if (user!=null) {//���û����Ե�¼
			//response.sendRedirect("index.html");
			//ʹ��session��user�����¼����

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			String remname = (String) request.getParameter("remname");
			if ("true".equals(remname)) {
				Cookie cookie = new Cookie("remname", URLEncoder.encode(uname,"utf-8"));
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
			}else {
				//ȡ����ʹ���µ�cookie����ԭ����cookie
				Cookie cookie = new Cookie("remname", "");
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			//������洢��cookie��
			
			//key:"user"��ʾ����Ա���Զ�������
			//value:user���� ��ʾ�û��ĵ�ǰ��½��Ϣ
			String autologin = request.getParameter("autologin");
			//�Զ���½������
			if ("true".equals(request.getParameter("autologin"))) {
				//�ַ�����ƴ��
				Cookie cookie = new Cookie("autologin", URLEncoder.encode(uname,"utf-8")+"#"+upwd);
				cookie.setPath(request.getContextPath()+"/");
				cookie.setMaxAge(3600*24*30);
				response.addCookie(cookie);
				
			}
			
			
			
			
			request.getRequestDispatcher("allBookServlet").forward(request,response);
		}else {//���û����û��������������
			response.sendRedirect("login.jsp");
		}
		
		
		
	}

}
