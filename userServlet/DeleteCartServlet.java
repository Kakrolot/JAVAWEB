package cn.tedu.javaweb.userServlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.User;
@WebServlet("/user/page/deleteCartServlet")
public class DeleteCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		//根据rid删除数据
		//首先获得user的用户信息
		User user = (User) request.getSession().getAttribute("user");
		String rid = request.getParameter("itemId");
		//调用dao层的方法
		AddCartDao dao = new AddCartDao();
		dao.deleteCart(rid);
		request.getRequestDispatcher("allCartServlet").forward(request, response);
	}

}
