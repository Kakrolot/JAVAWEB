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

//������������ת�ղؼ�ҳ�����
public class AllCollectServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ����ʽ
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//ͨ��session��ȡ�û�����Ϣ
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		System.out.println(uid);
		//����dao��ķ���
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
