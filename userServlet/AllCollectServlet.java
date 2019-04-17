package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.dao.CollectDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.Collect;
import cn.tedu.javaweb.pojo.User;

//控制类用于跳转收藏夹页面的类
public class AllCollectServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//通过session获取用户的信息
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		System.out.println(uid);
		//调用dao层的方法
		CollectDao dao = new CollectDao();
		ArrayList<Collect> list = dao.selectAllByUid(uid);
		BookDao bDao = new BookDao();
		ArrayList<Book>  list2 = new ArrayList<Book>();
		for(int i=0;i<list.size();i++){
			list2.add(bDao.selectByIsbn(list.get(i).getBook()));
		}

		request.setAttribute("allList", list2);
		request.getRequestDispatcher("collect.jsp").forward(request, response);
	}

}
