/*
 * Copyright (c)    2020/8/15 下午4:07.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/test/Size.java
 * LastModified:    2020/8/15 下午4:07
 */

package com.test;

/**
 * @author Jinhua
 */
public enum Size {
    /**
     * 小码
     */
    SMALL("S"),
    /**
     * 中码
     */
    MEDIUM("M"),
    /**
     * 大码
     */
    LARGE("L"),
    /**
     * 加大码
     */
    EXTRA_LARGE("XL");

    /**
     * 缩写
     */
    private String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
