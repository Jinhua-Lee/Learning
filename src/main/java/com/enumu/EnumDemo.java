/*
 * Copyright (c)    2020/8/8 下午3:22.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/enumu/EnumDemo.java
 * LastModified:    2020/8/8 下午3:22
 */

package com.enumu;

/**
 * 枚举测试类
 * @author Jinhua
 * @date 2020/8/8 15:22
 * @version 1.0
 */
public class EnumDemo {

    public static void main(String[] args) {
        int len = Color.values().length;
        Color color = Color.getColor(len);
        switch (color) {
            case RED:
                System.out.println("select " + "RED");
                break;
            case GREEN:
                System.out.println("select " + "GREEN");
                break;
            case BLUE:
                System.out.println("select " + "BLUE");
                break;
            case YELLOW:
                System.out.println("select " + "YELLOW");
                break;
            default:
                System.out.println("select " + "unknow!!");
                break;
        }
    }

}
