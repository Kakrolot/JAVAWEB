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
//商品详情的页面
public class DetailBookServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charaset=utf-8");
		//调用dao层的方法:查询一本书的详细信息
		BookDao dao = new BookDao();
		//先获取一本书的isbn编号
		String isbn = request.getParameter("isbn");
		//使用dao.selectByIsbn()的方法
		Book  b = dao.selectByIsbn(isbn);
		//把book对象封装到request对象中
		request.setAttribute("book",b);
		//添加推荐的书籍信息
		ArrayList<Book> list = dao.selectAll();
		ArrayList<Book> newList = new ArrayList<>();
		//使用随机数随机出4本书
		for(int i=0;i<4;i++){
			int random = (int) (Math.random()*list.size());
			Book bo = list.get(random);
			newList.add(bo);
			list.remove(random);
			
		}
		request.setAttribute("newList", newList);
		//判断收藏夹是【黄色】还是【白色】
		CollectDao cdao = new CollectDao();
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		Collect col = cdao.selectByUidAndIsbn(uid, isbn);
		if(col != null){
			//表示已经收藏62.png
			request.setAttribute("isCollect", "2");
		}else {
			//表示未收藏6.png
			request.setAttribute("isCollect","");
		}
		
		
		//请求的转发 把request和reponse对象转发到detail.jsp页面
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
}
