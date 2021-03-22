package cn.designpattern.proxy.stat;

import cn.designpattern.proxy.ProductService;
import cn.designpattern.proxy.ProductServiceImpl;

/**
 * 静态代理对象实现
 *
 * @author Jinhua
 */
public class ProxyProductServiceImpl implements ProductService {

    /**
     * 被代理对象的引用
     */
    private final ProductService ps;

    public ProxyProductServiceImpl(ProductService ps) {
        this.ps = ps;
    }

    @Override
    public void addPro() {
        System.out.println("添加之前……开启事务");
        ps.addPro();
        System.out.println("添加之后……提交事务");
    }

    @Override
    public void delPro() {
        System.out.println("删除之前……开启事务");
        ps.delPro();
        System.out.println("删除之后……提交事务");
    }

    public static void main(String[] args) {
        ProductService ps = new ProductServiceImpl();
        ps.addPro();
        ps.delPro();
        System.out.println("_____________________");
        ProductService psp = new ProxyProductServiceImpl(ps);
        psp.addPro();
        psp.delPro();
    }
}
