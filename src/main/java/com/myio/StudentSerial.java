package com.myio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 模拟序列化和反序列化
class StudentSerial implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private static int count = 0;
	private transient String password;

	public StudentSerial(String name, String password) {
		System.out.println("进入构造方法");
		this.name = name;
		this.password = password;
		count++;
	}

	@Override
	public String toString() {
		return "StudentSerial{" +
				"name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String dir = "E:/IOTest/";
		String filename = "StudentSerial.txt";
		File f = new File(dir, filename);
		StudentSerial s1 = new StudentSerial("李金华", "5206");
		StudentSerial s2 = new StudentSerial("高亚文", "5247");

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(s1);
		oos.writeObject(s2);
		oos.flush();
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		try {
			StudentSerial s3 = (StudentSerial)ois.readObject();
			StudentSerial s4 = (StudentSerial)ois.readObject();
			System.out.println(s3.toString());
			System.out.println(s4.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
	}
}
