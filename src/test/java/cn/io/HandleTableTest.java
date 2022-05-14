package cn.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Arrays;

/**
 * 测试HandleTable<p>&emsp;
 * 由于它是内部类不便测试，所以直接将这个类复制出来。
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/14 21:01
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class HandleTableTest {

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试hash方法，以及索引")
    public void testSystemHash() {
        int sHash = System.identityHashCode(Student.class);
        int tHash = System.identityHashCode(Teacher.class);
        System.out.println("sHash = " + sHash);
        System.out.println("tHash = " + tHash);

        int hs = sHash & 0x7FFFFFFF;
        int ht = tHash & 0x7FFFFFFF;
        System.out.println("hs = " + hs);
        System.out.println("ht = " + ht);

        int si = hs % 10;
        int ti = ht % 10;
        System.out.println("si = " + si);
        System.out.println("ti = " + ti);
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "调试多次分配时，三个数组的情况")
    public void testReassignment() {
        HandleTable hTable = new HandleTable(10, (float) 3.00);

        // 分配，调试hash冲突
        assignMyClassWithJudge(hTable, Student.class);
        assignMyClassWithJudge(hTable, Teacher.class);
        assignMyClassWithJudge(hTable, Student.class);
    }

    private void assignMyClassWithJudge(HandleTable hTable, Class<?> aClass) {
        if (hTable.lookup(aClass) == -1) {
            hTable.assign(aClass);
        } else {
            System.out.println("已存在" + aClass.getSimpleName() + "，未分配");
        }
        hTable.printArray();
        System.out.println("==================");
    }

    private static class Student {
    }

    private static class Teacher {
    }

    private static class HandleTable {

        /* 对象句柄的数量 */
        private int size;
        /* 扩容阈值 */
        private int threshold;
        /* 加载因子 */
        private final float loadFactor;

        // 数组下标为键 -> 数组元素为值

        /* hash值 -> 候选句柄值 */
        private int[] spine;
        /* 候选句柄值 -> next 候选句柄值 */
        private int[] next;
        /* 句柄值 -> obj */
        private Object[] objs;

        HandleTable(int initialCapacity, float loadFactor) {
            this.loadFactor = loadFactor;
            spine = new int[initialCapacity];
            next = new int[initialCapacity];
            objs = new Object[initialCapacity];
            threshold = (int) (initialCapacity * loadFactor);
            clear();
        }

        int assign(Object obj) {
            if (size >= next.length) {
                growEntries();
            }
            if (size >= threshold) {
                growSpine();
            }
            insert(obj, size);
            return size++;
        }

        int lookup(Object obj) {
            // 为空则
            if (size == 0) {
                return -1;
            }
            // 先计算hash值
            int index = hash(obj) % spine.length;
            // 根据冲突链表，逐个找
            for (int i = spine[index]; i >= 0; i = next[i]) {
                if (objs[i] == obj) {
                    return i;
                }
            }
            return -1;
        }

        void clear() {
            Arrays.fill(spine, -1);
            Arrays.fill(objs, 0, size, null);
            size = 0;
        }

        int size() {
            return size;
        }

        private void insert(Object obj, int handle) {
            int index = hash(obj) % spine.length;
            System.out.println("index = " + index);
            objs[handle] = obj;
            next[handle] = spine[index];
            spine[index] = handle;
        }

        private void growSpine() {
            spine = new int[(spine.length << 1) + 1];
            threshold = (int) (spine.length * loadFactor);
            Arrays.fill(spine, -1);
            for (int i = 0; i < size; i++) {
                insert(objs[i], i);
            }
        }

        private void growEntries() {
            int newLength = (next.length << 1) + 1;
            int[] newNext = new int[newLength];
            System.arraycopy(next, 0, newNext, 0, size);
            next = newNext;

            Object[] newObjs = new Object[newLength];
            System.arraycopy(objs, 0, newObjs, 0, size);
            objs = newObjs;
        }

        private int hash(Object obj) {
            return System.identityHashCode(obj) & 0x7FFFFFFF;
        }

        private void printArray() {
            printSpine();
            printNext();
            printObjs();
        }

        private void printSpine() {
            System.out.print("spine: \t");
            for (int sp : spine) {
                System.out.print(sp + "\t");
            }
            System.out.println();
        }

        private void printNext() {
            System.out.print("next: \t");
            for (int nx : next) {
                System.out.print(nx + "\t");
            }
            System.out.println();
        }

        private void printObjs() {
            System.out.print("objs: \t");
            for (Object obj : objs) {
                System.out.print(obj + "\t");
            }
            System.out.println();
        }
    }
}
