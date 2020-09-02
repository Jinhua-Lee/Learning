/*
 * Copyright (c)    2020/8/8 下午3:21.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/enumu/Color.java
 * LastModified:    2020/8/8 下午3:21
 */

package com.enumu;

import java.util.Random;

/**
 * 颜色枚举类实例
 * @author Jinhua
 * @date 2020/8/8 15:21
 * @version 1.0
 */
public enum Color {
    /**
     * 红色
     */
    RED("red color", 0),

    /**
     * 绿色
     */
    GREEN("green color", 1),

    /**
     * 蓝色
     */
    BLUE("blue color", 2),

    /**
     * 黄色
     */
    YELLOW("yellow color", 3);

    Color(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * 颜色名称
     */
    private String name;

    /**
     * 颜色枚举值
     */
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Color getColor(int max) {
        Random random = new Random(System.currentTimeMillis());
        int num = random.nextInt(max);
        switch (num) {
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            default:
                return Color.YELLOW;
        }
    }
}
