package cn.alg.sort;

/**
 * 排序的学习
 *
 * @author Jinhua
 */
@SuppressWarnings("unused")
public class MySort {

    private static final MySort MY_SORT = new MySort();

    public static void main(String[] args) {
        MySort.getInstance().test();
    }

    private static MySort getInstance() {
        return MY_SORT;
    }

    public void test() {
        int[] arr = {17, 13, 2, 5, 11, 3, 7, 23};
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSort(arr);
//		quickSort(arr, 0, arr.length - 1);
//        heapSort(arr);
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.printf("%-4d", i);
        }
        System.out.println();
    }

    /**
     * 交换指定下标的两个数
     *
     * @param index1 第一个数下标
     * @param index2 第二个数下标
     */
    private void swapInt(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 01_冒泡排序
     * <p>
     * 1) 每轮会选出最大的元素到末端
     * <p>
     * 2) 改进：增加一个变量标记该轮是否有交换过元素，若没有交换，则已经排好序
     *
     * @param arr 待排序数组
     */
    public void bubbleSort(int[] arr) {
        // 外层表示循环次数，循环 length - 1次
        for (int i = 0; i < arr.length - 1; i++) {
            // 该轮是否排交换过的标志变量，若没改变，则表示没进行过交换，已经排好
            boolean swap = false;
            // 每一轮排序（外层），会将大的值排到数组末端， 所以内层每轮到的数组位置是越来越小（length - i - 1）
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap = true;
                    swapInt(arr, j, j + 1);
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * 02_简单选择排序
     *
     * @param arr 待排序数组
     */
    public void selectSort(int[] arr) {
        int len = arr.length;
        // 外层循环：i即可表示，如果产生小值要放入的位置。
        for (int i = 0; i < len; i++) {
            int value = arr[i];
            int position = i;
            // 选出小的值，记录小值的位置
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < value) {
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
     *
     * @param arr 待排序数组
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
     * <p>
     * 1) 改进的直接插入排序；
     * <p>
     * 2) 增加了分组。按增量进行分组，组内仍使用直接插入排序
     * <p>
     * 3) 组内的直接插入排序找前一个元素，从固定移动1，改为指定步长
     *
     * @param arr 待排序数组
     */
    public void shellSort(int[] arr) {
        int length = arr.length;
        while (length > 0) {
            // 增量公式，简单折半
            length = length / 2;
            // 第一层，是分组，每组执行插入排序
            for (int i = 0; i < length; i++) {
                //	每组从第二个元素开始，按增量确定该组的元素
                for (int j = i + length; j < arr.length; j += length) {
                    // 要插入的元素
                    int value = arr[j];
                    // k为有序序列最后一位的位数
                    int k = j - length;

                    /*for (; k >= 0 && value < arr[k]; k -= length) {
                        arr[k + length] = arr[k];
                    }*/

                    // 从后往前，如果大于待插入值，则该值向后移动
                    while (k >= 0 && arr[k] > value) {
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
     * <p>
     * 1) 初始调用，从0到length - 1
     * <p>
     * 2) 基准值选取的三种方式：
     * <p>
     * a. 选start下标位置元素或end位置的元素 -> 易产生劣质的分割
     * <p>
     * b. 选start到end范围内随机位置的元素 -> 不易产生劣质的分割
     * <p>
     * c. 选start, end, (end - start) / 2，三个位置元素中，值大小排在中间的元素 -> 算出中值会费一定时间
     *
     * @param arr   待排序数组
     * @param start 开始下标
     * @param end   结束下标
     */
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // 选基准值，随机
            int baseNum = arr[start + Math.round(end - start)];

            int i = start;
            int j = end;
            // 当两个游标没有重合
            while (i < j) {
                // 从左开始找大于于基准值的下标
                while (arr[i] < baseNum && i < end) {
                    i++;
                }
                // 从右边开始找小于基准值的下标
                while (arr[j] > baseNum && j > start) {
                    j--;
                }
                if (i <= j) {
                    // 两个值做交换（位置相等时候不用换，保证稳定性）
                    if (i < j) {
                        swapInt(arr, i, j);
                    }
                    // 从下一个位置开始继续找
                    i++;
                    j--;
                }
            }
            // 利用递归，开始分治
            if (start < j) {
                quickSort(arr, start, j);
            }
            if (end > i) {
                quickSort(arr, i, end);
            }
        }
    }

    /**
     * 06_堆排序 -> 改进的对简单选择排序；
     * <p>
     * 1) 最后一个非叶子节点下标
     * <p>
     * a) 按数组长度 lastNonLeaf = length / 2 - 1;
     * <p>
     * b) 按下标 lastNonLeaf = (length - 1) / 2;
     * <p>
     * 2) 按大顶堆的结构，二叉树父节点的值大于左右孩子节点的值。
     * <p>
     * a) 需要在每次构建堆，保证节点与子节点构成大顶堆
     * <p>
     * b) 最大元素向堆顶（最小）移动，所以必须从最后一个非叶子节点开始
     * <p>
     * 3) 每个节点构建完堆，则将堆尾值与堆顶值的最大值交换。
     *
     * @param arr 待排序数组
     */
    public void heapSort(int[] arr) {
        int length = arr.length;

        // 循环必须从后往前，因为要将大的元素向堆顶移动，堆顶索引最小
        for (int last = length - 1; last >= 0; last--) {
            // 构建大顶堆每次进入循环last自减一，去除最大的堆尾节点
            buildTopMaxHeap(arr, last);
            // 将堆顶的最大值与堆尾元素交换
            swapInt(arr, 0, last);
        }

    }

    /**
     * 将指定位置的元素构建成大顶堆
     *
     * @param arr       待建为大顶堆的数组
     * @param lastIndex 结束索引（可取到）
     */
    private void buildTopMaxHeap(int[] arr, int lastIndex) {
        // 最后一个非叶子节点下标
        int lastNonLeaf = (lastIndex - 1) / 2;
        for (int cur = lastNonLeaf; cur >= 0; cur--) {

            // 若当前节点有孩子
            if (cur * 2 + 1 <= lastIndex) {

                // 存储左右节点孩子中较大的一个的下标，初始为左孩子
                int biggerIndex = cur * 2 + 1;

                // 若存在右孩子
                if (biggerIndex < lastIndex) {
                    // 如果右孩子值更大
                    if (arr[biggerIndex] < arr[biggerIndex + 1]) {
                        biggerIndex++;
                    }
                }

                // 如果父节点的值更小
                if (arr[cur] < arr[biggerIndex]) {
                    swapInt(arr, cur, biggerIndex);
                }
            }
        }
    }


    /**
     * 07_归并排序
     * <p>
     * 1) 运用递归，将相邻两个有序数组合并；
     * <p>
     * 2) 当两个数组元素都大于1时候，再分别将两个数组再划分为两个数组；
     * <p>
     * 3) 递归到每个数组大小为1时候，开始回溯；
     * <p>
     * 4) 缓存数组一开始就在外面创建，减少递归中创建的消耗
     *
     * @param arr     待排数组
     * @param left    待排数组的起始位置
     * @param right   待排数组终点位置
     */
    public void mergeSort(int[] arr, int left, int right) {
        // 当数组元素不为1时候，继续划分；否则可以进行归并
        if (left != right) {
            int middle = (left + right) / 2;
            // 分别排序左右两边，再归并起来
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    /**
     * 将两个相邻数的有序数组合并为一个有序数组的方法
     * <p>
     * 1) middle用来分割两个有序数组，该位置的元素属于左边或右边，归并需要定义清楚，通常由于除法运算向下取整，定义为属于第一个数组;
     * <p>
     * 2) 需要额外的数组空间 right -left + 1，由于该额外空间在每次归并只使用一次，且归并到最后是原数组大小，所以大小直接分配为原数组大小，在递归外部创建;
     * <p>
     * 3) 每次归并排好序后放入原来的位置。
     * <p>
     * 4) 归并的难点在于将两个序列合并为一个有序序列，此处需要想清楚过程，否则容易导致数组下标越界：
     * <p>
     * a. 当两边都有值的时候，将值比较后一次填入缓存数组；
     * <p>
     * b. 执行完上面的循环，只有一边有值了，将另一边剩下的位置数组全部放到缓存数组接着的位置。
     *
     * @param arr     待排数组
     * @param left    待排数组的起始位置
     * @param middle  待排数组中间位置，归属于左边
     * @param right   待排数组终点位置
     */
    private void merge(int[] arr, int left, int middle, int right) {
        int[] tempArr = new int[arr.length];
        // 分别是左边数组，右边数组，缓存数组的游标
        int i = left;
        int j = middle + 1;
        int k = left;
        // 左右两部分值都还未取完时
        while (i <= middle && j <= right) {

            if (arr[i] <= arr[j]) {
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }
        // 当左边或者右边值被取完时候，将另一边的值全部赋值到缓存数组
        while (i <= middle) {
            tempArr[k++] = arr[i++];
        }
        while (j <= right) {
            tempArr[k++] = arr[j++];
        }
        // 将缓存数组的值放回原来数组的对应位置
        for (k = left; k <= right; k++) {
            arr[k] = tempArr[k];
        }
    }

}
