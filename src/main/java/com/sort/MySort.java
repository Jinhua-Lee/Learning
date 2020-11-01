package com.sort;

/**
 * @author Jinhua
 */
public class MySort {

    private static MySort mySort = new MySort();

    public static void main(String[] args) {
        MySort.getInstance().test();
    }

    private static MySort getInstance() {
        return mySort;
    }

    public void test() {
        int[] arr = {32, 43, 23, 13, 5};
//		insertSort(arr);
//        bubbleSort(arr);
//		quickSort(arr, 0, arr.length - 1);
//		swapSort(arr);
        shellSort(arr);
        for (int i : arr) {
            System.out.printf("%-4d", i);
        }
        System.out.println();
    }

    /**
     * 01_冒泡排序
     *      1) 每轮会选出最大的元素到末端
     *      2) 改进：增加一个变量标记该轮是否有交换过元素，若没有交换，则已经排好序
     * @param arr 待排序数组
     */
    public void bubbleSort(int[] arr) {
        int temp;
        // 外层表示循环次数，循环 length - 1次
        for (int i = 0; i < arr.length - 1; i++) {
            // 该轮是否排交换过的标志变量，若没改变，则表示没进行过交换，已经排好
            boolean swap =false;
            // 每一轮排序（外层），会将大的值排到数组末端， 所以内层每轮到的数组位置是越来越小（length - i - 1）
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * 02_简单选择排序
     * @param arr 待排序数组
     */
    public void selectSort(int[] arr) {
        int len = arr.length;
        // 外层循环：i即可表示，如果产生小值要放入的位置。
        for(int i = 0; i < len ; i++){
            int value = arr[i];
            int position = i;
            // 选出小的值，记录小值的位置
            for(int j = i+1; j < len; j++){
                if(arr[j] < value){
                    value = arr[j];
                    position = j;
                }
            }
            // 交换最小值和当前外层遍历的位置
            arr[position] = arr[i];
            arr[i] = value;
        }
    }

    /**
     * 03_直接插入排序
     * @param arr  待排序数组
     */
    public void insertSort(int[] arr) {
        int length = arr.length;
        // 存放待插入数据的变量
        int insertNum;
        // 从第二位开始依次遍历插入，第一个数不用插入
        for (int i = 1; i < length; i++) {
            insertNum = arr[i];
            int j = i - 1;
            // 从后往前倒序，遍历大于insertNum的数，依次往后移动。j最小值是0
            while (j >= 0 && arr[j] > insertNum) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 出循环前j--，则要插入的位置，需要取 [j + 1] 位置
            arr[j + 1] = insertNum;
        }
    }

    /**
     * 04_希尔排序
     *		1) 改进的直接插入排序；
     *		2) 增加了分组。按增量进行分组，组内仍使用直接插入排序
     *		3) 组内的直接插入排序找前一个元素，从固定移动1，改为指定步长
     * @param arr 待排序数组
     */
    public void shellSort(int[] arr){
        // 单独把数组长度拿出来，提高效率
        int length = arr.length;
        while(length > 0){
            // 增量公式，简单折半
            length = length / 2;
            // 第一层，是分组，每组执行插入排序
            for(int i = 0; i < length; i++){
                //	每组从第二个元素开始，按增量确定该组的元素
                for(int j = i + length; j < arr.length; j+= length){
                    // 要插入的元素
                    int value = arr[j];
                    // k为有序序列最后一位的位数
                    int k = j - length;

                    /*for (; k >= 0 && value < arr[k]; k -= length) {
                        arr[k + length] = arr[k];
                    }*/

                    // 从后往前，如果大于待插入值，则该值向后移动
                    while(k >= 0 && arr[k] > value) {
                        arr[k + length] = arr[k];
                        // 按步长向前移动
                        k -= length;
                    }
                    // 出循环前，k减去length，所以真正插入的位置，需要加上length
                    arr[k + length] = value;
                }
            }
        }
    }

    /**
     * 05_快速排序
     * @param arr 待排序数组
     * @param start 开始下标
     * @param end 结束下标
     */
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // 选基准值
            int baseNum= arr[start];
            // 记录中间值
            int midNum;
            int i=start;
            int j=end;
            do {
                while((arr[i] < baseNum) && i < end) {
                    i++;
                }
                while((arr[j] > baseNum) && j > start) {
                    j--;
                }
                if(i <= j){
                    midNum = arr[i];
                    arr[i] = arr[j];
                    arr[j] = midNum;
                    i++;
                    j--;
                }
            } while (i<=j);
            if (start < j) {
                quickSort(arr, start, j);
            }
            if (end > i){
                quickSort(arr, i, end);
            }
        }
    }



}
