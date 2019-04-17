package cn.tedu.javaweb.pojo;

public class Cart {
	private Integer rid;
	private String uid;
	private String book;
	private Integer count;
	
	@Override
	public String toString() {
		return "Cart [rid=" + rid + ", uid=" + uid + ", book=" + book + ", count=" + count + "]";
	}
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	

}
