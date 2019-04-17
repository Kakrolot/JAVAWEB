package cn.tedu.javaweb.service;

import java.util.ArrayList;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//业务逻辑层的具体实现类
//由程序员手动编写，处理具体的业务
public class BookServiceImpl implements BookService{

	@Override
	public PageBean<Book> selectBookByPage(String currenPage, String pageSize) {
		//定义一个分页对象
		PageBean<Book> pageBean = new PageBean<Book>();
		//封装当前的页数
		if (currenPage==null) {
			currenPage = "1";//默认首页
		}
		int i_currenPage = Integer.parseInt(currenPage);
		pageBean.setCurrentPage(i_currenPage);
		if (pageSize==null) {
			pageSize = "8";//默认每一页8本书
		}
		int i_pageSize = Integer.parseInt(pageSize);
		
		//pageBean对象:封装书籍的总数量
		/*
		 * 1.查询dao层的方法：selectAll(),获取全部数据
		 * 2.根据List集合,获取list.size()即是总数量
		 */
		BookDao dao = new BookDao();
		ArrayList<Book> books = dao.selectAll();
		int i_totalCount = books.size();
		pageBean.setTotalCount(i_totalCount);
		//pageBean对象:封装总页数
		//公式:总页数 = 总数量处以每一页的数量
		int i_totalPages = (int) Math.floor(i_totalCount/i_pageSize)+1;
		pageBean.setTotalPage(i_totalPages);
		//pageBean对象:封装分页的数据
		//需要调用dao层的方法
		//公式:第一页:0-7
		 //   第二页:8-15
		//    第三页:16-23
		int startRow=(i_currenPage-1)*i_pageSize;
		ArrayList<Book> list = dao.selectBookByPages(startRow, i_pageSize);
		pageBean.setPageList(list);
		
		return pageBean;
	}

}
