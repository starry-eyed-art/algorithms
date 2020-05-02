package com.starry.data.pratice.linkedlist;

import com.starry.data.pratice.queue.Queue;

/**
 * @Description 基于链表形成的队列
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 9:55 下午
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;// 解决enqueue时间复杂度为O(n)的问题
    private int size;

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        // 没有next的构造方法，一般此类节点会用于最后一个节点或者第一个节点，也有可能是先创建node后再指定next
        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (size == 0) {
            head = new Node(e);
            head = tail;
        } else {
            Node node = new Node(e);
            tail.next = node;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) {
            throw new IllegalArgumentException("queue is empty");
        }

        // 先拷贝出head节点，然后将head指向head的下一个节点，最后将拷贝节点的next断开
        Node retNode = this.head;
        head = head.next;
        retNode.next = null;

        if (this.head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }
}
