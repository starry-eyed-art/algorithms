package com.starry.data.leetcode.interview;

/**
 * @Description 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 * <p>
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
        if (head == null || head.next == null)  // 递归终止条件
            return head;
        // 将head.next进行反转，反转后的链表的头结点返回给newHead
        // 因为递归返回时，第一个返回的是最后一个节点，此时newHead刚好就等于head.next，所以看起来后续没有对newhead做操作，其实一直在往newhead为头节点的链表后面加节点
        // 这样newHead其实就是以最后一个节点为头节点的反转之后的链表
        ListNode newHead = reverseList2(head.next);
        // 这里要注意：在链表进行反转以后，head.next仍然指着原先的head.next
        // 因为我们在reverseList(head.next)的过程中，没有动head!
        // 只不过此时，原先的head.next，同时是对head.next反转的尾节点
        // 也就是，现在的head.next，有两个节点的next指着它
        //
        // 比如：1->2->3->4->5->NULL
        // 如果head是2所在的节点的话，我们的这句递归调用，是反转以3为头节点的链表
        // 运行后的结果应该为：1->2->3<-4<-5，并且返回了5
        // 对以3为头结点的链表进行反转，结果为5->4->3->NULL，新的链表的头结点为5
        // 但是由于我们的递归过程和2没有关系，2依然指着3！（2是head，3是head.next）
        // 我们下面的任务，就是处理这个2(head)

        head.next.next = head; // 3(head.next)相应的next应该指向2(head)
        head.next = null;      // 2(head)的next赋值为空。

        return newHead;  // 返回newHead
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
