package com.collection;

import java.util.*;

public class Student {
	private String name;
	private int age;
	private String sex;
	private int classNo;
	public Student(String name, int age, String sex, int classNo) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.classNo = classNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public static void main(String[] args) {
		Set<Student> set = new TreeSet<>(new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				// 先名字升序
				int num1 = s1.getName().compareTo(s2.getName());
				// 其次班级号升序
				int num2 = num1 == 0 ? s1.getClassNo() - s2.getClassNo() : num1;
				// 再其次性别升序
				int num3 = num2 == 0 ? s1.getSex().compareTo(s2.getSex()) : num2;
				// 最后年纪升序
				int num4 = num3 == 0 ? s1.getAge() - s2.getAge() : num3;
				return num4;
			}
		});
		set.add(new Student("李金华", 21, "男", 1503));
		set.add(new Student("李文凯", 21, "男", 1501));
		set.add(new Student("高亚文", 20, "女", 1502));
		for (Student student : set) {
			System.out.println(student.getName() + "-----" + student.getAge()
				+ "-----" + student.getSex() + "-----" + student.getClassNo());
		}

		Map<Integer, Student> is = new HashMap<>();
		Integer i = 0;
		for (Student student : set) {
			is.put(i++, student);
		}

		Set<Map.Entry<Integer, Student>> set3 = is.entrySet();
		for (Map.Entry<Integer, Student> entry : set3) {
			Integer key = entry.getKey();
			Student value = entry.getValue();
			System.out.println("Key:" + key  + "-----Value:"+ value.getName() + "-----" + value.getAge()
				+ "-----" + value.getSex() + "-----" + value.getClassNo());
		}
	}
}
