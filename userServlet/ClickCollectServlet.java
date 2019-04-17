package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CollectDao;
import cn.tedu.javaweb.pojo.Collect;

//控制层:controller
public class ClickCollectServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取页面中url路径的参数
		String uid = request.getParameter("userId");
		String isbn = request.getParameter("product");
		/*
		 * 1.查询数据库中是否已经收藏
		 * 2.如果已收藏,点击收藏按钮时，进行取消收藏
		 * 3.如果未收藏,点击收藏按钮时，进行添加收藏
		 */
		CollectDao dao = new CollectDao();
		//定义一个collect对象，获取收藏信息
		Collect col = dao.selectByUidAndIsbn(uid, isbn);
		if (col !=null) {
			//表示原来已收藏，再次点击时取消
			dao.delete(uid, isbn);
		}else {
			//表示原来未收藏，再次点击时添加
			dao.insert(uid, isbn);
		}
		//使用IO流:完成ajax结果的返回
		Writer out = response.getWriter();
		out.write("yes");
		out.close();
	}
}
