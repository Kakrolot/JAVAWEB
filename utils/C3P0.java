package cn.tedu.javaweb.utils;
//���ӳؼ���:�Զ����տ��е����ӵ����ӳ���

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//����һ��c3p0�����ӳ�
public class C3P0{
	private static ComboPooledDataSource dataSource = null;
    //ʹ�����ӳصĹ��췽������������ļ��ļ���
	static{
		dataSource = new ComboPooledDataSource("mysql");
	}
	//��ȡһ�����ݿ������
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			//System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//�ر�һ�����ݿ�����:��������Դ�ͷŵ����ӳ���
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
