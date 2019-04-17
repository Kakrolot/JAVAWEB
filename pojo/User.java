package cn.tedu.javaweb.pojo;
//数据模型:专门用于用户对象的封装
public class User {
	//user类的字段:来自与Mysql数据库中的t_user表
	//添加五个字段:对应着t_user表中的5个列名
	private String phone;//用户的主键,电话号码
	private String uname;
	private String upwd;
	private String email;
	private int role;//角色(0:消费者，1:管理员)
	//打印用户对象的信息:使用toString()方法
	
	
	@Override
	public String toString() {
		return "User [phone=" + phone + ", uname=" + uname + ", upwd=" + upwd + ", email=" + email + ", role=" + role
				+ "]";
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}
