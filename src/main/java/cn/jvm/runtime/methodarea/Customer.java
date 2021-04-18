package cn.jvm.runtime.methodarea;

/**
 * 测试对象实例化过程<p>
 *     1) 加载类元信息
 *     2) 内存分配检查
 *     3) 并发安全问题
 *     4) 零值初始化
 *     5) 设置对象头
 *     6) 显式初始化 / 构造代码块初始化 / 构造方法初始化
 *
 * 给对象的属性赋值的操作：<p>&emsp;
 * ①属性默认初始化；<p>&emsp;
 * ②显式初始化；<p>&emsp;
 * ③代码块中的初始化；<p>&emsp;
 * ④构造器初始化；
 *
 * @author Jinhua
 * @date 2021/4/18 21:25
 */
public class Customer {

    /**
     * 显式赋值
     */
    int id = 1001;
    /**
     * 代码块赋值
     */
    String name;
    /**
     * 构造器赋值
     */
    Account account;

    {
        name = "匿名用户";
    }

    public Customer() {
        account = new Account();
    }
}

class Account {
}
