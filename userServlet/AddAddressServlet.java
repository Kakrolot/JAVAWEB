package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddressDao;
import cn.tedu.javaweb.pojo.Address;
import cn.tedu.javaweb.pojo.User;

//添加地址的servlet
@WebServlet("/user/page/addAddressServlet")
public class AddAddressServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//读取页面中的数据
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String receiverPhone = request.getParameter("receiverPhone");
		
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		//数据封装
		Address add = new Address();
		add.setAddress(address);
		add.setAdded(new Date());
		add.setReceiver(receiver);
		add.setRphone(receiverPhone);
		add.setUid(uid);
		//调用Dao层
		AddressDao dao = new AddressDao();
		dao.insertAddress(add);
		
		Writer out = response.getWriter();
		out.write("yes");
		out.close();
	}

}
