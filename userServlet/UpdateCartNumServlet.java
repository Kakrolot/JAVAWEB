package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.User;
@WebServlet("/user/page/updateCartNumServlet")
public class UpdateCartNumServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置request的编码格式
		request.setCharacterEncoding("UTF-8");
		//因为是ajax请求,所以reponse的格式不需要设置成html
		//配置获取页面中的ajax参值
		Integer number = Integer.parseInt(request.getParameter("num"));
		Integer id = Integer.parseInt(request.getParameter("itemId"));
		
		User user = (User) request.getSession().getAttribute("user");
		//调用Dao层的方法:更新数据库的信息
		/*
		 * 1.根据rid，也就是cart购物车的主键，进行更新即可
		 */
		AddCartDao dao = new AddCartDao();
		dao.updateCarNum(id, number);
		
	}

}
