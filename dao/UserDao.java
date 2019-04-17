package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;

import cn.tedu.javaweb.pojo.User;
import cn.tedu.javaweb.utils.C3P0;
import cn.tedu.javaweb.utils.JDBC;


//专门用于数据库的表操作(CRUD):增删改查
//dao:数据访问层
//表:t_user(其中有五个字段)
public class UserDao {
	
	//查询数据库的操作
	public void selectAll() {
		//1.获取并打开数据库连接
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "select *from t_user";
		//3.获取执行sql语句的对象
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//4.为语句对象赋值
		//5.执行sql语句并获得结果集
			ResultSet rs = ps.executeQuery();
			//循环遍历结果集
			while(rs.next()){
				String uname = rs.getString("uname");
				System.out.println(uname);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//新增数据库的操作
	public void insert(String phone,String uname,String upwd,String email,int role) {
		//1.获取数据库的连接
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "insert into t_user values (?,?,?,?,?)";
		//3.后去执行sql语句的对象
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//为语句对象赋值
		//4.执行sql语句
			ps.setString(1,phone);
			ps.setString(2,uname);
			ps.setString(3, upwd);
			ps.setString(4, email);
			ps.setInt(5, role);
			ps.executeUpdate();
			System.out.println("插入数据成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//修改数据库的操作
	public void updata(String uname,String phone) {
		//1.建立数据库连接
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "UPDATE t_user SET uname = ? WHERE phone= ? ";
		//3.sql语句对象
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,uname);
			ps.setString(2,phone);
			//4.执行sql语句
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//删除数据库的操作
	public void delete(String phone) {
		//1.建立数据库连接
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "delete from t_user where phone=?";
		//3.sql 语句对象
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.executeUpdate();
			System.out.println("数据删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	public boolean selectBynameAndUpwd(String uname,String upwd) {
//		boolean answer=false;
//		//1.建立数据库连接
//		Connection conn = JDBC.getConnection();
//		//2.编写sql语句
//		String sql = "select upwd from t_user where uname=?";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			//查询的到结果集
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
	//专门用于检验用户是否存在
	//参数:uname和upwd
	//返回值类型:user类型，用于记录当前的用户信息
	public User selectByUnameAndUpwd(String uname,String upwd) {
		//定义一个user对象
		User user = null;
		//访问数据库，查询数据库
		Connection conn = JDBC.getConnection();
		String sql = "select *from t_user where uname=? and upwd=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upwd);
			//使用结果集,接受sql语句的执行jieguo
			ResultSet rs = ps.executeQuery();
			//对结果集，进行user对象的封装
			if(rs.next()){//如果存在数据,内马尔
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
		//返回一个user对象
		return user;
	}
	//编写一个ajaxCheck方法
	public boolean ajaxCheckUname(String uname) {
		//定义boolean一个判断用户名
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
		//返回一个boolean类型
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
//		//1.连接数据库
//		Connection conn = JDBC.getConnection();
//		String sql = "UPDATE t_user SET upwd = ? WHERE uname = ? ";
//		return end;
//		
//	}
	//检验密码是否正确的方法
	public User ajaxCheckUpwd(String upwd,String phone,int role) {
		User user = null;
		Connection conn =JDBC.getConnection();
		//编写sql语句
		String sql = "select *from t_user where phone = ? and upwd=? and role = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, upwd);
			ps.setInt(3,role);
			//5.执行sql语句
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
	
	//修改密码的dao方法
	public void updateUpwd(String npwd,User user) {
		//获取数据库的连接
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
