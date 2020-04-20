package com.starry.leetcode.interview;

/**
 * @Description 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *  
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/20 6:58 下午
 */
public class NO24 {

    // 迭代
    public ListNode reverseList(ListNode head) {
        ListNode next = null;
        while (head != null) {
            ListNode newNode = new ListNode(head.val);
            newNode.next = next;
            next = newNode;
            head = head.next;
        }
        return next;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList2(head.next);
        ListNode cycleNode = newNode;
        while (cycleNode.next != null) {
            cycleNode = cycleNode.next;
        }
        head.next = null;
        cycleNode.next = head;
        return newNode;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
