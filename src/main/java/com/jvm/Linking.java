/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/Linking.java
 * LastModified:    2020/9/29 下午10:32
 */

package com.jvm;

/**
 * 链接过程模拟
 *      加载 -> 链接 -> 初始化
 * @author Jinhua
 * @date 2020/9/29 22:32
 */
public class Linking {

    /**
     * 类变量（static）的链接（验证 -> 准备 -> 解析）过程
     * Prepare阶段，a = 0
     * Initial阶段，a = 1
     */
    private static int a  = 1;
}
