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
		//����request�ı����ʽ
		request.setCharacterEncoding("UTF-8");
		//��Ϊ��ajax����,����reponse�ĸ�ʽ����Ҫ���ó�html
		//���û�ȡҳ���е�ajax��ֵ
		Integer number = Integer.parseInt(request.getParameter("num"));
		Integer id = Integer.parseInt(request.getParameter("itemId"));
		
		User user = (User) request.getSession().getAttribute("user");
		//����Dao��ķ���:�������ݿ����Ϣ
		/*
		 * 1.����rid��Ҳ����cart���ﳵ�����������и��¼���
		 */
		AddCartDao dao = new AddCartDao();
		dao.updateCarNum(id, number);
		
	}

}
