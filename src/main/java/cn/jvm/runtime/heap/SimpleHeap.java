package cn.jvm.runtime.heap;

/**
 * 数组、对象、对象数组在堆中的创建<p>&emsp;
 * 通过字节码查看
 *
 * @author Jinhua
 * @date 2021/4/1 23:53
 */
public class SimpleHeap {

    private final int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        // 1. 对象，对应字节码指令【new】
        SimpleHeap sh1 = new SimpleHeap(1);
        SimpleHeap sh2 = new SimpleHeap(2);
        // 2. 数组，对应字节码指令【newarray】
        int[] arr = new int[10];
        // 3. 对象数组，对应字节码指令【anewarray】
        SimpleHeap[] simpleHeaps = new SimpleHeap[10];
    }

}
