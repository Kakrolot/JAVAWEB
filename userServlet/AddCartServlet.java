package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.AddCartDao;
import cn.tedu.javaweb.pojo.Cart;
import cn.tedu.javaweb.pojo.User;

public class AddCartServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ����ʽ
		request.setCharacterEncoding("UTF-8");
		//��ȡҳ��������url�Ĳ���
		String newcount = request.getParameter("count");
		String isbn = request.getParameter("product");
		User user = (User)request.getSession().getAttribute("user");
		String uid =user.getPhone();
		//��Ҫ����Dao��ķ���
		/* 1.�ж����ݿ����Ƿ��Ѿ����ڡ��ñ���Ĺ��ﳵ��Ϣ��
		 * 2.�����ԭ���Ѿ���ӹ����ﳵ������ô�Թ��ﳵ��Ϣ����
		 * 3.�����ԭ��û����ӹ����ﳵ������ô�Թ��ﳵ��Ϣ����
		 * 
		 */
		AddCartDao dao = new AddCartDao();
		Cart cart = dao.selectByUidAndBook(uid, isbn);
		if(cart!=null){//��ʾԭ���Ѿ���ӹ����ﳵ
			int oldcount = cart.getCount();
			int totalcount = oldcount+Integer.parseInt(newcount);
			dao.update(cart.getRid(), totalcount);
		}else {//ԭ��û����ӹ����ﳵ
			dao.insert(uid, isbn, Integer.parseInt(newcount));
		}
		//ʹ��IO�������ظ������һ���ַ������
		Writer out = response.getWriter();
		out.write("yes");
		out.close();
	}
}
