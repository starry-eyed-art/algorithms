package com.starry.leetcode.interview;

import java.util.Stack;

/**
 * @Description 实现一个MyQueue类，该类用两个栈来实现一个队列。
 *
 *
 * 示例：
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明：
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/22 10:27 上午
 */
public class NO0304 {

    private Stack<Integer> stackA;
    private Stack<Integer> stackB;

    /** Initialize your data structure here. */
    public NO0304() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stackA.isEmpty()) {
            stackA.push(x);
            return;
        }
        // 加入之前先将A的元素全部转移到B，加入之后再从B转移回来
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
        stackA.push(x);
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stackA.isEmpty()) {
            return -1;
        }
        return stackA.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stackA.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackA.isEmpty();
    }
}
