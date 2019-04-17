package cn.tedu.javaweb.pojo;
//所有的分页，都依赖于该PageBean

import java.util.ArrayList;
//<T>:表示泛型！
//举例:PageBean<Book> PageBean<User>
public class PageBean<T> {
	//通过这两个字段，可以计算出，上一页 下一页的内容
	private int currentPage;//当前页
	private int pageSize;//每一页的数量
	//通过这两个字段，计算出尾页的内容
	private int totalPage;//总共多少也
	private int totalCount;//所有数据的数量
	//分页的数据:需要存放起来
	
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
