package com.c3p0;

public class User {
	private int ID;
	private String Name;
	private String Password;
	private String Sex;
	private String Home;
	private String Info;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getHome() {
		return Home;
	}

	public void setHome(String home) {
		Home = home;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	@Override
	public String toString() {
		return "User{" +
				"ID=" + ID +
				", Name='" + Name + '\'' +
				", Password='" + Password + '\'' +
				", Sex='" + Sex + '\'' +
				", Home='" + Home + '\'' +
				", Info='" + Info + '\'' +
				'}';
	}
}
