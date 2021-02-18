/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/se/inner/member/local/People.java
 * LastModified:    2021/2/18 下午9:41
 */

package com.se.inner.local;

/**
 * 局部内部类模拟
 *
 * @author Jinhua
 * @date 2021/2/18 21:41
 */
public class People {

    public People() {
    }
}

/**
 * 男人类
 */
class Man {

    public Man() {
    }

    /**
     * 获取女人的方法
     * <p>
     * 女人类，作为方法内部的类
     *
     * @return 女人
     */
    public People getWoman() {
        class Woman extends People {

            private final Integer age;

            public Woman(Integer age) {
                this.age = age;
            }
        }
        return new Woman(20);
    }
}
