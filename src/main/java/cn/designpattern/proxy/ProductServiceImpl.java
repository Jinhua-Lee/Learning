package cn.designpattern.proxy;

/**
 * 普通对象的实现
 *
 * @author Jinhua
 */
public class ProductServiceImpl implements ProductService {
    /**
     * 定义添加商品的方法
     */
    @Override
    public void addPro() {
        System.out.println("添加商品");
    }

    /**
     * 定义删除商品的方法
     */
    @Override
    public void delPro() {
        System.out.println("删除商品");
    }
}
