package com.se.stream;

import lombok.ToString;

import java.io.*;

/**
 * 模拟序列化和反序列化
 *
 * @author Jinhua
 */
@ToString
public class StudentSerial implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 域字段
     */
    private final String name;

    /**
     * 静态常量
     */
    private static int count = 0;

    /**
     * 非序列化属性
     */
    private final transient String password;

    public StudentSerial(String name, String password) {
        System.out.println("进入构造方法");
        this.name = name;
        this.password = password;
        count++;
    }


    public static void main(String[] args) throws IOException {
        String dir = "D:/IOTest/";
        String filename = "StudentSerial.txt";
        File f = new File(dir, filename);
        if (!f.exists()) {
            if (!f.createNewFile()) {
                return;
            }
        }
        StudentSerial s1 = new StudentSerial("李金华", "5206");
        StudentSerial s2 = new StudentSerial("高亚文", "5247");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(s1);
        oos.writeObject(s2);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        try {
            StudentSerial s3 = (StudentSerial) ois.readObject();
            StudentSerial s4 = (StudentSerial) ois.readObject();
            System.out.println(s3.toString());
            System.out.println(s4.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
    }
}
