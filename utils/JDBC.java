package cn.tedu.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//ר�����ڷ������ݿ�,�򿪺͹ر����ݿ������
public class JDBC {
	//�Զ��巽��:�����ݿ������
	public static Connection getConnection() {
		//����һ�����ݿ�����
		Connection conn = null;
		//����mysql��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/teduweb?characterEncoding=utf-8";
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("���ݿ����ӳɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		//�������ݿ�����
		return conn;
		
	}
	



}
