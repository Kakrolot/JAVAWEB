package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;

import cn.tedu.javaweb.pojo.User;
import cn.tedu.javaweb.utils.C3P0;
import cn.tedu.javaweb.utils.JDBC;


//ר���������ݿ�ı����(CRUD):��ɾ�Ĳ�
//dao:���ݷ��ʲ�
//��:t_user(����������ֶ�)
public class UserDao {
	
	//��ѯ���ݿ�Ĳ���
	public void selectAll() {
		//1.��ȡ�������ݿ�����
		Connection conn = JDBC.getConnection();
		//2.��дsql���
		String sql = "select *from t_user";
		//3.��ȡִ��sql���Ķ���
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//4.Ϊ������ֵ
		//5.ִ��sql��䲢��ý����
			ResultSet rs = ps.executeQuery();
			//ѭ�����������
			while(rs.next()){
				String uname = rs.getString("uname");
				System.out.println(uname);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//�������ݿ�Ĳ���
	public void insert(String phone,String uname,String upwd,String email,int role) {
		//1.��ȡ���ݿ������
		Connection conn = JDBC.getConnection();
		//2.��дsql���
		String sql = "insert into t_user values (?,?,?,?,?)";
		//3.��ȥִ��sql���Ķ���
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//Ϊ������ֵ
		//4.ִ��sql���
			ps.setString(1,phone);
			ps.setString(2,uname);
			ps.setString(3, upwd);
			ps.setString(4, email);
			ps.setInt(5, role);
			ps.executeUpdate();
			System.out.println("�������ݳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//�޸����ݿ�Ĳ���
	public void updata(String uname,String phone) {
		//1.�������ݿ�����
		Connection conn = JDBC.getConnection();
		//2.��дsql���
		String sql = "UPDATE t_user SET uname = ? WHERE phone= ? ";
		//3.sql������
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,uname);
			ps.setString(2,phone);
			//4.ִ��sql���
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//ɾ�����ݿ�Ĳ���
	public void delete(String phone) {
		//1.�������ݿ�����
		Connection conn = JDBC.getConnection();
		//2.��дsql���
		String sql = "delete from t_user where phone=?";
		//3.sql ������
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.executeUpdate();
			System.out.println("����ɾ���ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	public boolean selectBynameAndUpwd(String uname,String upwd) {
//		boolean answer=false;
//		//1.�������ݿ�����
//		Connection conn = JDBC.getConnection();
//		//2.��дsql���
//		String sql = "select upwd from t_user where uname=?";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			//��ѯ�ĵ������
//			ps.setString(1, uname);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()){
//				String pwd = rs.getString("upwd");
//				if (pwd.equals(upwd)) {
//					answer=true;
//				}else {
//					answer=false;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return answer;
//	}
	//ר�����ڼ����û��Ƿ����
	//����:uname��upwd
	//����ֵ����:user���ͣ����ڼ�¼��ǰ���û���Ϣ
	public User selectByUnameAndUpwd(String uname,String upwd) {
		//����һ��user����
		User user = null;
		//�������ݿ⣬��ѯ���ݿ�
		Connection conn = JDBC.getConnection();
		String sql = "select *from t_user where uname=? and upwd=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upwd);
			//ʹ�ý����,����sql����ִ��jieguo
			ResultSet rs = ps.executeQuery();
			//�Խ����������user����ķ�װ
			if(rs.next()){//�����������,�����
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ��user����
		return user;
	}
	//��дһ��ajaxCheck����
	public boolean ajaxCheckUname(String uname) {
		//����booleanһ���ж��û���
		boolean flag = false;
		////////////////JDBC:c3p0///////////////////
		Connection conn = C3P0.getConnection();
		String sql = "select *from t_user where uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		//����һ��boolean����
		return flag;
	}
	public boolean ajaxCheck(String sqlthing,String eString) {
		boolean flag = false;
		Connection conn = C3P0.getConnection();
		String sql = "select * from t_user where "+sqlthing+" = ?";
//		String sql = "select * from t_user where ? = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, eString);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return flag;
	}
	
	
	
//	public boolean ChangePassword(String upwd ) {
//		boolean end = false;
//		//1.�������ݿ�
//		Connection conn = JDBC.getConnection();
//		String sql = "UPDATE t_user SET upwd = ? WHERE uname = ? ";
//		return end;
//		
//	}
	//���������Ƿ���ȷ�ķ���
	public User ajaxCheckUpwd(String upwd,String phone,int role) {
		User user = null;
		Connection conn =JDBC.getConnection();
		//��дsql���
		String sql = "select *from t_user where phone = ? and upwd=? and role = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, upwd);
			ps.setInt(3,role);
			//5.ִ��sql���
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
		return user;
		
		
	}
	
	//�޸������dao����
	public void updateUpwd(String npwd,User user) {
		//��ȡ���ݿ������
		Connection conn = C3P0.getConnection();
		String sql = "update t_user set upwd = ? where phone = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, npwd);
			ps.setString(2, user.getPhone());
			int row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
	UserDao dao = new UserDao();
	/*boolean flag = dao.ajaxCheckUname("liuyi11");
	System.out.println(flag);*/
	boolean flag = dao.ajaxCheck("uname","liuyi11");
	System.out.println(flag);
	}
	
}
