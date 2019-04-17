package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.pojo.User;

//control层
public class AllCartServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("UTF-8");
		//设置reponse的编码格式
		response.setContentType("text/html;charset=UTF-8");
		//获取页面中的参数 从session 中得到user的对象信息
		User user = (User)request.getSession().getAttribute("user");
		//调用dao层的方法查询全部的购物车信息
		/*
		 * 1.根据uid，查出该用户下所有的cart对象以及对应的book对象
		 * 2.把获取的结果，封装到ArrayList<BookAndCart> 集合中
		 */
		String uid = user.getPhone();
		AddCartDao dao = new AddCartDao();
		ArrayList<CartAndBook> list = dao.selectAllByUid(uid);
		request.setAttribute("newList", list);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
