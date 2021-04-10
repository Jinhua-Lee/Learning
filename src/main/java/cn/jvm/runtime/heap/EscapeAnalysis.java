package cn.jvm.runtime.heap;

/**
 * 逃逸分析的情况
 * <p>
 * 如何快速判断是否发生逃逸：new的对象是否有可能在方法外被调用。
 *
 * @author Jinhua
 * @date 2021/4/10 21:43
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    /**
     * 1. 方法返回{@link EscapeAnalysis} 对象，发生逃逸
     *
     * @return Object
     */
    public EscapeAnalysis getInstance() {
        return obj == null ? new EscapeAnalysis() : obj;
    }

    /**
     * 2. 为成员变量赋值，发生逃逸
     * 思考：如果当前引用声明为static，是否会发生逃逸？仍然会
     */
    public void setObj() {
        this.obj = new EscapeAnalysis();
    }

    /**
     * 对象作用域仅在方法中，未发生逃逸
     */
    public void use1() {
        EscapeAnalysis escape = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     */
    public void use2() {
        EscapeAnalysis escape = getInstance();
    }

}
