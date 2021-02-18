/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/se/inner/member/Circle.java
 * LastModified:    2021/2/18 下午9:12
 */

package com.se.inner.member;

/**
 * 圆的定义
 *
 * @author Jinhua
 * @date 2021/2/18 21:12
 */
public class Circle {

    private double radius = 0;

    public static int count = 1;

    public Draw draw = null;

    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawShape();
    }

    public Draw getDrawInstance() {
        if (draw == null) {
            draw = new Draw();
        }
        return draw;
    }

    /**
     * 绘画方法类定义
     */
    public class Draw {

        private int count = 2;

        public void drawShape() {
            System.out.println(radius);
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        // 方式一
        Circle circle = new Circle(5);
        Circle.Draw draw = circle.new Draw();
        // 方式二
        Circle.Draw draw2 = circle.getDrawInstance();
    }

}
