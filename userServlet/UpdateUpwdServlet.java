package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;
@WebServlet("/user/page/updateUpwdServlet")
public class UpdateUpwdServlet extends HelloServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = (User)request.getSession().getAttribute("user");
		String npwd = request.getParameter("npwd");
		//调用Dao层方法
		UserDao dao = new UserDao();
		dao.updateUpwd(npwd, user);
		Writer out = response.getWriter();
		out.write("yes");
		out.close();
				
	}

}
