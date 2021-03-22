package cn.designpattern.proxy;

/**
 * 公用接口 -> 产品集成的相关方法
 *
 * @author Jinhua
 */
public interface ProductService {

    /**
     * 定义添加商品的方法
     */
    void addPro();

    /**
     * 定义删除商品的方法
     */
    void delPro();
}
