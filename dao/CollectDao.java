package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.Collect;
import cn.tedu.javaweb.pojo.User;
import cn.tedu.javaweb.utils.C3P0;

//Dao层:数据访问层
//专门惊醒数据库的增删改查的操作
public class CollectDao {
	/*
	 * 1.查询数据出中是否已经收藏
	 */
	public Collect selectByUidAndIsbn(String uid,String isbn) {
		//定义一个collect对象
		Collect col = null;
		//返回一个collect对象
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_collect where uid = ? and book = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//如果存在这本书的收藏
				col=new Collect();
				col.setBook(rs.getString("book"));
				col.setUid(rs.getString("uid"));
				col.setRid(rs.getInt("rid"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return col;
		
	}
	/*
	 * 2.如果已收藏，点击【收藏按钮】时，进行取消收藏
	 */
	public void delete(String uid,String isbn) {
		/////////JDBC:C3P0///////////
		Connection conn = C3P0.getConnection();
		String sql = "delete from t_collect where uid = ? and book = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
	}
	
	/*
	 *3.如果未收藏，点击【收藏按钮】时,进行添加收藏 
	 */
	public void insert(String uid,String isbn) {
		Connection conn = C3P0.getConnection();
		String sql = "insert into t_collect values(default,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
	}
	//编写public方法
	public ArrayList<Collect> selectAllByUid(String uid) {
		ArrayList<Collect> list = new ArrayList<Collect>();
		Collect col = null;
		////////////////连接C3P0数据库///////////////
		Connection conn = C3P0.getConnection();
		String sql = "select *from t_collect where uid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				col = new Collect();
				col.setRid(rs.getInt("rid"));
				col.setUid(rs.getString("uid"));
				col.setBook(rs.getString("book"));
				list.add(col);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		
		return list;
	}
	public static void main(String[] args) {
		CollectDao dao = new CollectDao();
		String uid = "12345678901";
/*//		String isbn ="9787115130228";
//		Collect col = dao.selectByUidAndIsbn(uid,isbn);
//		System.out.println(col);
//		dao.delete(uid, isbn);
//		System.out.println("删除成功");
//		dao.insert(uid, isbn);
//		System.out.println("插入成功");
*/		
		ArrayList<Collect> col = dao.selectAllByUid(uid);
		for(Collect c:col){
			System.out.println(c);
		}
		
	}

}
