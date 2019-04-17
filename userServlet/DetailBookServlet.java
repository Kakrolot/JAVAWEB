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
//��Ʒ�����ҳ��
public class DetailBookServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charaset=utf-8");
		//����dao��ķ���:��ѯһ�������ϸ��Ϣ
		BookDao dao = new BookDao();
		//�Ȼ�ȡһ�����isbn���
		String isbn = request.getParameter("isbn");
		//ʹ��dao.selectByIsbn()�ķ���
		Book  b = dao.selectByIsbn(isbn);
		//��book�����װ��request������
		request.setAttribute("book",b);
		//����Ƽ����鼮��Ϣ
		ArrayList<Book> list = dao.selectAll();
		ArrayList<Book> newList = new ArrayList<>();
		//ʹ������������4����
		for(int i=0;i<4;i++){
			int random = (int) (Math.random()*list.size());
			Book bo = list.get(random);
			newList.add(bo);
			list.remove(random);
			
		}
		request.setAttribute("newList", newList);
		//�ж��ղؼ��ǡ���ɫ�����ǡ���ɫ��
		CollectDao cdao = new CollectDao();
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		Collect col = cdao.selectByUidAndIsbn(uid, isbn);
		if(col != null){
			//��ʾ�Ѿ��ղ�62.png
			request.setAttribute("isCollect", "2");
		}else {
			//��ʾδ�ղ�6.png
			request.setAttribute("isCollect","");
		}
		
		
		//�����ת�� ��request��reponse����ת����detail.jspҳ��
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
}
