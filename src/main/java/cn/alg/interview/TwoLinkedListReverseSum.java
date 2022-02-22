package cn.alg.interview;

/**
 * 两个链表
 * 2 -> 4 -> 3
 * 5 -> 6 -> 4
 * 逆序求和，输出逆序链表:
 * 7 -> 0 -> 8
 *
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/2/23 1:23
 */
public class TwoLinkedListReverseSum {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int realSum = l1.val + l2.val;
        boolean upto = realSum >= 10;
        ListNode curNode = new ListNode(upto ? realSum - 10 : realSum);
        ListNode res = curNode;
        // 1. 当两个链表相等下标位置都有元素的时候，相加，判断是否进位
        while (l1.next != null && l2.next != null) {
            realSum = l1.val + l2.val;
            // 如果上一位进位，则加一
            int curSum = upto ? realSum + 1 : realSum;
            // 加入结点，并设置标识位：下一个结点是否需要进位
            if (curSum >=10) {
                upto = true;
                curNode = new ListNode(curSum - 10);
            } else {
                upto = false;
                curNode = new ListNode(curSum);
            }

            curNode = curNode.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        // 2. 当其中一个链表完成后，需要判断进位表示，并且另外一个链表若有元素，则仍要判断进位标识
        // TODO: 2022/2/23

        return res;
    }
}


class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}
