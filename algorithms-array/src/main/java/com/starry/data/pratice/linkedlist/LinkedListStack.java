package com.starry.data.pratice.linkedlist;

/**
 * @Description 基于队列的栈
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 9:50 下午
 */
public class LinkedListStack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        this.linkedList = new LinkedList<>();
    }

    // 元素推入栈中
    public void push(E e) {
        linkedList.addLast(e);
    }

    // 从栈顶推出元素
    public E pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        return linkedList.removeLast();
    }

    // 查看栈顶的元素
    public E peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        return linkedList.getLast();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
