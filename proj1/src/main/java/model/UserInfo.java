package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserInfo implements Serializable {

	//
	public UserInfo() {
	}

	//
	private String name;

	private String yomi;

	private String zip;

	private String address;

	private String tel;

	private String email;

	// 
	public String getName() {
		return this.name;
	}

	public String getYomi() {
		return this.yomi;
	}

	public String getZip() {
		return this.zip;
	}

	public String getAddress() {
		return this.address;
	}

	public String getTel() {
		return this.tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

