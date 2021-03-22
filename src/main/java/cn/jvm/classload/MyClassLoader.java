package cn.jvm.classload;

/**
 * 自定义用户类加载器<p>&emsp;
 * ==> 继承自抽象的ClassLoader
 *
 * @author Jinhua
 * @date 2020/10/13 21:52
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] result = getClassFromCustomPath(name);
        if (result == null) {
            throw new ClassNotFoundException("未找到指定路径" + name);
        } else {
            return defineClass(name, result, 0, result.length);
        }
    }

    /**
     * 1) 从自定义路径中加载指定类的二进制流，细节略<p>
     * 2) 如果指定了路径的字节码文件进行加密，则需要在此方法中进行解密操作
     *
     * @param name 路径
     * @return 类的二进制流
     */
    private byte[] getClassFromCustomPath(String name) {

        return null;
    }
}
