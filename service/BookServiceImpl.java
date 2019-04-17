package cn.tedu.javaweb.service;

import java.util.ArrayList;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//ҵ���߼���ľ���ʵ����
//�ɳ���Ա�ֶ���д����������ҵ��
public class BookServiceImpl implements BookService{

	@Override
	public PageBean<Book> selectBookByPage(String currenPage, String pageSize) {
		//����һ����ҳ����
		PageBean<Book> pageBean = new PageBean<Book>();
		//��װ��ǰ��ҳ��
		if (currenPage==null) {
			currenPage = "1";//Ĭ����ҳ
		}
		int i_currenPage = Integer.parseInt(currenPage);
		pageBean.setCurrentPage(i_currenPage);
		if (pageSize==null) {
			pageSize = "8";//Ĭ��ÿһҳ8����
		}
		int i_pageSize = Integer.parseInt(pageSize);
		
		//pageBean����:��װ�鼮��������
		/*
		 * 1.��ѯdao��ķ�����selectAll(),��ȡȫ������
		 * 2.����List����,��ȡlist.size()����������
		 */
		BookDao dao = new BookDao();
		ArrayList<Book> books = dao.selectAll();
		int i_totalCount = books.size();
		pageBean.setTotalCount(i_totalCount);
		//pageBean����:��װ��ҳ��
		//��ʽ:��ҳ�� = ����������ÿһҳ������
		int i_totalPages = (int) Math.floor(i_totalCount/i_pageSize)+1;
		pageBean.setTotalPage(i_totalPages);
		//pageBean����:��װ��ҳ������
		//��Ҫ����dao��ķ���
		//��ʽ:��һҳ:0-7
		 //   �ڶ�ҳ:8-15
		//    ����ҳ:16-23
		int startRow=(i_currenPage-1)*i_pageSize;
		ArrayList<Book> list = dao.selectBookByPages(startRow, i_pageSize);
		pageBean.setPageList(list);
		
		return pageBean;
	}

}
