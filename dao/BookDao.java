package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.utils.C3P0;

public class BookDao {
	//��ѯȫ����������Ϣ������װ��list������
	public ArrayList<Book> selectAll() {
		ArrayList<Book> list = new ArrayList<Book>();
		//1.���ݿ������ 
		Connection conn = C3P0.getConnection();
		//2.��дsql���
		String sql = "select *from t_book";
		try {
			//3.��ȡ�������
			PreparedStatement ps = conn.prepareStatement(sql);
			//4.ִ�в�ѯ����
			ResultSet rs = ps.executeQuery();
			//5.���������
			while(rs.next()){
				Book book = new Book();
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
				list.add(book);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return list;
	}
	//����isbn����ѯ��Ʒ������ҳ
	//����:isbn;����ֵ:book����
	public Book selectByIsbn(String isbn) {
		Book book = null;
		//�������ݿ�
		Connection conn = C3P0.getConnection();
		//��дsql���
		String sql = "select *from t_book where isbn=?";
		//����������
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,isbn);
			ResultSet rs = ps.executeQuery();
			//�ж�rs��������Ƿ������Ϣ
			while(rs.next()){
				book = new Book();
				//�ֶθ�ֵ
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
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return book;
		
	}
	public ArrayList<Book> selectBookByPages(int startRow,int pageSize) {
		ArrayList<Book> list = new ArrayList<Book>();
		Book book = null;
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_book limit ?,?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, pageSize);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				book = new Book();
				//Ϊ12���ֶθ�ֵ
				book.setAuthor(res.getString("author"));
				book.setEdition(res.getInt("edition"));
				
				book.setFormat(res.getString("format"));
				book.setIsbn(res.getString("isbn"));
				book.setPackaging(res.getString("packaging"));
				book.setPages(res.getInt("pages"));
				book.setPress(res.getString("press"));
				book.setPrice(res.getDouble("price"));
				book.setPublished(res.getDate("published"));
				book.setTitle(res.getString("title"));
				book.setWords(res.getInt("words"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0.closeConnection(conn);
		}
		
		return list;
	}
	public void insert(Book book) {
		Connection con = C3P0.getConnection();
		String sql = "insert into "
				+ "t_book(isbn,title,author,price,press,edition,published,pages,words,packaging,format,form) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, book.getIsbn());
			sta.setString(2, book.getTitle());
			sta.setString(3, book.getAuthor());
			sta.setDouble(4, book.getPrice());
			sta.setString(5, book.getPress());
			sta.setInt(6, book.getEdition());
			sta.setDate(7, new java.sql.Date(book.getPublished().getTime()));
			sta.setInt(8, book.getPages());
			sta.setInt(9, book.getWords());
			sta.setString(10, book.getPackaging());
			sta.setString(11, book.getFormat());
			sta.setString(12, book.getForm());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			C3P0.closeConnection(con);
		}
	}


	public static void main(String[] args) {
		BookDao dao = new BookDao();
		String isbn = "9787111213826";
		Book b = dao.selectByIsbn(isbn);
		System.out.println(b);
	}

}
