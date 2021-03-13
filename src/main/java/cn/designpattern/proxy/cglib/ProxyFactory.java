package cn.designpattern.proxy.cglib;

import cn.designpattern.proxy.ProductService;
import cn.designpattern.proxy.ProductServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CgLib继承代理
 *
 * @author Jinhua
 * @date 2021/3/13下午9:58
 */
public class ProxyFactory implements MethodInterceptor {

    /**
     * 目标对象
     */
    private final Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 工具类
        Enhancer en = new Enhancer();
        // 设置父类
        en.setSuperclass(target.getClass());
        // 设置回调函数
        en.setCallback(this);
        // 产生代理对象
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 添加了代理方法
        if ("delPro".equals(method.getName())) {
            System.out.println("开启事务……");
            Object result = method.invoke(target, objects);
            System.out.println("提交事务……");
            return result;
        } else {    // 未添加代理方法
            return method.invoke(target, objects);
        }
    }

    public static void main(String[] args) {
        // 普通对象产生
        ProductService ps = new ProductServiceImpl();
        // 代理对象的产生
        ProductService proxy = (ProductService) new ProxyFactory(ps).getProxyInstance();
        System.out.println(proxy.getClass());

        proxy.addPro();
        System.out.println("---------");
        proxy.delPro();
    }
}
