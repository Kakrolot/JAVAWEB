package cn.tedu.javaweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.tedu.javaweb.pojo.Address;
import cn.tedu.javaweb.utils.C3P0;

public class AddressDao {
	public void insertAddress(Address address) {
		//连接数据库
		Connection conn = C3P0.getConnection();
		String sql = "insert into t_address(uid,address,added,receiver,rphone)values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address.getUid());
			ps.setString(2, address.getAddress());
			ps.setDate(3,new Date(address.getAdded().getTime()));
			ps.setString(4, address.getReceiver());
			ps.setString(5, address.getRphone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0.closeConnection(conn);
		}
	}

}
