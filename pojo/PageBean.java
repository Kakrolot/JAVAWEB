package cn.tedu.javaweb.pojo;
//���еķ�ҳ���������ڸ�PageBean

import java.util.ArrayList;
//<T>:��ʾ���ͣ�
//����:PageBean<Book> PageBean<User>
public class PageBean<T> {
	//ͨ���������ֶΣ����Լ��������һҳ ��һҳ������
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿһҳ������
	//ͨ���������ֶΣ������βҳ������
	private int totalPage;//�ܹ�����Ҳ
	private int totalCount;//�������ݵ�����
	//��ҳ������:��Ҫ�������
	
	private ArrayList<T> pageList;
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", pageList=" + pageList + "]";
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<T> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<T> pageList) {
		this.pageList = pageList;
	}
	

}
