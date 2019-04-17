package cn.tedu.javaweb.utils;
//连接池技术:自动回收空闲的连接到连接池中

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//定义一个c3p0的连接池
public class C3P0{
	private static ComboPooledDataSource dataSource = null;
    //使用连接池的构造方法，完成配置文件的加载
	static{
		dataSource = new ComboPooledDataSource("mysql");
	}
	//获取一个数据库的连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			//System.out.println("数据库连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭一个数据库连接:把连接资源释放到连接池中
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
