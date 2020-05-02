package com.starry.data.leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/5 9:45 下午
 */
public class NO203 {

    public ListNode removeElements(ListNode head, int val) {
        return notRecursive(head, val);
    }

    // 非递归求解简化版
    private ListNode notRecursiveSimplified(ListNode head, int val) {
        // 创建虚拟节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            // 浮标从虚拟节点开始往后移动，先获取下一个节点next
            ListNode next = prev.next;
            // 判断next的值是否满足，满足的话删除next，此时不需要移动浮标，因为下一个元素已经变更
            if (next.val == val) {
                prev.next = prev.next.next;
            } else {
                // 不满足浮标继续往后移动一位
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    // 非递归求解
    private ListNode notRecursive(ListNode head, int val) {

        // 创建虚拟节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            // 浮标从虚拟节点开始往后移动，先获取下一个节点next
            ListNode next = prev.next;
            // 判断next的值是否满足，满足的话删除next，此时不需要移动浮标，因为下一个元素已经变更
            if (next.val == val) {
                prev.next = next.next;
                next.next = null;
            } else {
                // 不满足浮标继续往后移动一位
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    /**
     * 递归其实就是把一个符号不断地赋值给一个数据结构中的每个元素，比如这个案例中，每个元素都会成为head，当这个元素的值等于我们想要删除的元素时，就通过head=?的方式
     * 删除这个元素。如果不等于则把head传递给下一个元素，直到被传递的元素为null
     *
     * 因此，我们在写递归时，首先明确要传递的符号是什么？接着明确传递的终止条件是什么？最后明确符号是如何在数据结构中进行传递的。
     */
    // 递归求解
    private ListNode recursive(ListNode head, int val) {
        if (head == null) {
            return null; // 求解最基础的问题
        }
        if (head.val == val) { // 将原问题拆解为更小的问题，其实就是 head + 除了head之外的链表
            // 对head直接赋值相当于就是把head直接删掉
            head = removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
        }
        return head;
    }


    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
