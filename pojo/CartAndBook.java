package cn.tedu.javaweb.pojo;
//Model层:数据模型
public class CartAndBook {
	private Cart cart;//购物车信息
	private Book book;//书籍信息
	
	@Override
	public String toString() {
		return "CartAndBook [cart=" + cart + ", book=" + book + "]";
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}
