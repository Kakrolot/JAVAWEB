package cn.tedu.javaweb.dao;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.Cart;
import cn.tedu.javaweb.pojo.CartAndBook;
import cn.tedu.javaweb.utils.C3P0;

public class AddCartDao {
	//1.根据uid,isbn,查询数据库
	public Cart selectByUidAndBook(String uid,String isbn) {
		//定义一个Cart对象
		Cart cart = null;
		//链接数据库
		Connection conn = C3P0.getConnection();
		String sql = "select *from t_cart where uid = ? and book = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				cart = new Cart();
				cart.setRid(rs.getInt("rid"));
				cart.setBook(rs.getString("book"));
				cart.setUid(rs.getString("uid"));
				cart.setCount(rs.getInt("count"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
		//返回一个Cart对象
		return cart;
	}
	//2.根据uid,isbn,rid,totalcount，更新数据库
	public void update(int rid,int totalcount) {
		//连接数据库
		Connection conn = C3P0.getConnection();
		String sql = "update t_cart set count=? where rid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, totalcount);
			ps.setInt(2, rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	//3.根据uid,isbn,count，插入数据库
	public void insert(String uid,String isbn,int count) {
		Connection conn = C3P0.getConnection();
		String sql = "insert into t_cart(uid,book,count) values (?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setString(2,isbn);
			ps.setInt(3,count);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	//根据uid查询全部购物车的方法
	public ArrayList<CartAndBook> selectAllByUid(String uid) {
		//定义一个集合对象
		ArrayList<CartAndBook> list = new ArrayList<CartAndBook>();
		//定义一个cart对象
		Cart cart = null;
		//定义一个Book对象
		Book book = null;
		//定义一个CartAndBook对象
		CartAndBook cab = null;
		Connection conn = C3P0.getConnection();
		String sql="select *from t_book tb  INNER JOIN t_cart tc ON tb.isbn=tc.book where tc.uid=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//1.创建cart对象，并进行赋值
				cart = new Cart();
				cart.setRid(rs.getInt("rid"));
				cart.setBook(rs.getString("book"));
				cart.setUid(rs.getString("uid"));
				cart.setCount(rs.getInt("count"));
				//2.创建book对象，并进行赋值
				book = new Book();
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getInt("edition"));
				book.setFormat(rs.getString("form"));
				book.setFormat(rs.getString("format"));
				book.setIsbn(rs.getString("isbn"));
				book.setPackaging(rs.getString("packaging"));
				book.setPages(rs.getInt("pages"));
				book.setPress(rs.getString("press"));
				book.setPrice(rs.getDouble("price"));
				book.setPublished(rs.getDate("published"));
				book.setTitle(rs.getString("title"));
				book.setWords(rs.getInt("words"));
				//3.创建cartAndBook对象，并进行赋值
				cab = new CartAndBook();
				cab.setCart(cart);
				cab.setBook(book);
				//4.把cab对象存入到list集合中
				list.add(cab);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return list;
	}
    //【加减号更新数据库】
	public void updateCarNum(int rid,int number) {
		//连接数据库
		Connection conn = C3P0.getConnection();
		String sql = "update t_cart set count = ? where rid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, number);
			ps.setInt(2, rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	public void deleteCart(String id) {
		//连接数据库
		Connection conn = C3P0.getConnection();
		String sql = "delete from t_cart where rid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
	}
	public static void main(String[] args) {
		AddCartDao dao = new AddCartDao();
		ArrayList<CartAndBook> list = dao.selectAllByUid("13083537444");
		for(CartAndBook cab:list){
			System.out.println(cab);
		}
	}
	

	
}
