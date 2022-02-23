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
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(4, l3);
        ListNode l1 = new ListNode(2, l2);

        ListNode r3 = new ListNode(4);
        ListNode r2 = new ListNode(6, r3);
        ListNode r1 = new ListNode(5, r2);

        ListNode listNode = addTwoNumbers(l1, r1);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. 预置头结点
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        boolean upto = false;
        // 2. 只要有一个不为null都行，如果其中一个为null，则当作0处理，所以取或
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int realSum = upto ? v1 + v2 + 1 : v1 + v2;
            int curPos;
            if (realSum >= 10) {
                upto = true;
                curPos = realSum % 10;
            } else {
                upto = false;
                curPos = realSum;
            }
            cur.next = new ListNode(curPos);
            // 特别重要，当不为空的时候才移动
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 处理最后的进位问题
        if (upto) {
            cur.next = new ListNode(1);
        }
        return head.next;
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

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
