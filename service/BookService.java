package cn.tedu.javaweb.service;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

public interface BookService {
	/*
	 * ���ݾ���ҵ��ȥ����
	 * һ����˵:������ɾ�ò�ķ�������
	 * ��Щ������dao��ķ���һһ��Ӧ
	 * 
	 */
	//ר�����ڷ�ҳ��ѯ�ķ���
	//����:currentPage/pageSize
	//����ֵ����:��Ҫ������ҳ��������ȥ��ȡֵ
	public PageBean<Book> selectBookByPage(String currenPage,String pageSize);

}
