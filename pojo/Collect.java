package cn.tedu.javaweb.pojo;
//Model层:数据模型层
//用于数据的封装
public class Collect {
	private Integer rid;//收藏夹的主键，s属于自增主键
	private String uid;//用户表的主键,用户的手机号
	private String book;//书籍的主键,书籍的isbn编号
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "Collect [rid=" + rid + ", uid=" + uid + ", book=" + book + "]";
	}
	
}
