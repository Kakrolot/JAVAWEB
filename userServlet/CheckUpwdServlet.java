package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;

@WebServlet("/user/page/checkUpwdServlet")
public class CheckUpwdServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		//��ȡҳ�����Ϣ
		String upwd = request.getParameter("upwd");
		//����Dao��
		UserDao dao = new UserDao();
		User result = dao.ajaxCheckUpwd(upwd, user.getPhone(), user.getRole());
		//�ж�
		Writer out = response.getWriter();
		if (result!=null) {
			out.write("yes");
		}else {
			out.write("no");
			
		}
		out.close();
		
	}
	

}
