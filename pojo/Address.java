package cn.tedu.javaweb.pojo;

import java.util.Date;

public class Address {
	private int rid;
	private String uid;
	private String address;
	private Date added;
	private String receiver;
	private String rphone;
	@Override
	public String toString() {
		return "Address [rid=" + rid + ", uid=" + uid + ", address=" + address + ", added=" + added + ", receiver="
				+ receiver + ", rphone=" + rphone + "]";
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}

}
