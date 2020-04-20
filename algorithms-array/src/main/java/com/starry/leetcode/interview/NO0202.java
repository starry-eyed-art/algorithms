package com.starry.leetcode.interview;

/**
 * @Description 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * <p>
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 * <p>
 * 给定的 k 保证是有效的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/20 10:33 上午
 */
public class NO0202 {
    public int kthToLast(ListNode head, int k) {
        // 快慢指针求解
        ListNode target = head;
        while (head != null) {
            k--;
            if (k < 0) {
                target = target.next;
            }
            head = head.next;
        }
        return target.val;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
