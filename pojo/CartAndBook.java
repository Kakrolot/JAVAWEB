package cn.tedu.javaweb.pojo;
//Model��:����ģ��
public class CartAndBook {
	private Cart cart;//���ﳵ��Ϣ
	private Book book;//�鼮��Ϣ
	
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
