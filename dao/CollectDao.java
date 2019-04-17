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

//Dao��:���ݷ��ʲ�
//ר�ž������ݿ����ɾ�Ĳ�Ĳ���
public class CollectDao {
	/*
	 * 1.��ѯ���ݳ����Ƿ��Ѿ��ղ�
	 */
	public Collect selectByUidAndIsbn(String uid,String isbn) {
		//����һ��collect����
		Collect col = null;
		//����һ��collect����
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_collect where uid = ? and book = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {//��������Ȿ����ղ�
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
	 * 2.������ղأ�������ղذ�ť��ʱ������ȡ���ղ�
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
	 *3.���δ�ղأ�������ղذ�ť��ʱ,��������ղ� 
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
	//��дpublic����
	public ArrayList<Collect> selectAllByUid(String uid) {
		ArrayList<Collect> list = new ArrayList<Collect>();
		Collect col = null;
		////////////////����C3P0���ݿ�///////////////
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
//		System.out.println("ɾ���ɹ�");
//		dao.insert(uid, isbn);
//		System.out.println("����ɹ�");
*/		
		ArrayList<Collect> col = dao.selectAllByUid(uid);
		for(Collect c:col){
			System.out.println(c);
		}
		
	}

}
