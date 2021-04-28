package cn.jvm.stringtable;

/**
 * Intern方法的版本细节，
 * 1) JDK版本间有不同；
 * 2) Main方法和单元测试方法中不同
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/28 9:53
 */
public class StringIntern {

    /**
     * intern的使用，不同版本结果不同（JDK 7将字符串常量池移至堆空间）<p>&emsp;
     * 1) JDK 6及以前；
     * 2) JDK 7+：
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        String s1 = new String("1");
        // 调用intern方法前，字符串常量池和堆空间中都存在了"1"
        s1.intern();
        String s2 = "1";
        // JDK 6 -> false
        // JDK 7+ -> false
        System.out.println(s1 == s2);

        // s3 指向 new String("11")，执行完后字符串常量池中不存在"11"
        String s3 = new String("1") + new String("1");
        // 字符串常量池中生成"11"
        // 在JDK 6中，创建一个新的对象"11"；
        // 在JDK 7+中，
        s3.intern();
        // s4 指向 上一行代码生成的字符串常量池中"11"的地址
        String s4 = "11";
        // JDK 6 -> false
        // jDK 7+ -> true
        System.out.println(s3 == s4);
    }
}
