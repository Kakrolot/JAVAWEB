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
	//1.����uid,isbn,��ѯ���ݿ�
	public Cart selectByUidAndBook(String uid,String isbn) {
		//����һ��Cart����
		Cart cart = null;
		//�������ݿ�
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
		
		//����һ��Cart����
		return cart;
	}
	//2.����uid,isbn,rid,totalcount���������ݿ�
	public void update(int rid,int totalcount) {
		//�������ݿ�
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
	//3.����uid,isbn,count���������ݿ�
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
	//����uid��ѯȫ�����ﳵ�ķ���
	public ArrayList<CartAndBook> selectAllByUid(String uid) {
		//����һ�����϶���
		ArrayList<CartAndBook> list = new ArrayList<CartAndBook>();
		//����һ��cart����
		Cart cart = null;
		//����һ��Book����
		Book book = null;
		//����һ��CartAndBook����
		CartAndBook cab = null;
		Connection conn = C3P0.getConnection();
		String sql="select *from t_book tb  INNER JOIN t_cart tc ON tb.isbn=tc.book where tc.uid=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//1.����cart���󣬲����и�ֵ
				cart = new Cart();
				cart.setRid(rs.getInt("rid"));
				cart.setBook(rs.getString("book"));
				cart.setUid(rs.getString("uid"));
				cart.setCount(rs.getInt("count"));
				//2.����book���󣬲����и�ֵ
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
				//3.����cartAndBook���󣬲����и�ֵ
				cab = new CartAndBook();
				cab.setCart(cart);
				cab.setBook(book);
				//4.��cab������뵽list������
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
    //���Ӽ��Ÿ������ݿ⡿
	public void updateCarNum(int rid,int number) {
		//�������ݿ�
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
		//�������ݿ�
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
