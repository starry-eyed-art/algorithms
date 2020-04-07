package com.starry.pratice.linkedlist;

/**
 * @Description 链表
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 6:34 下午
 */
public class LinkedList<E> {

    // 为什么需要dummyHead，解决头节点和非头节点插入逻辑不一致的问题
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // O(1)
    public void addFirst(E e) {
        add(0, e);
    }

    // O(n)
    public void addLast(E e) {
        add(size, e);
    }

    // O(1)
    public E removeFirst() {
        return remove(0);
    }

    // O(n)
    public E removeLast() {
        return remove(size - 1);
    }

    // O(1)
    public E getFist() {
        return get(0);
    }

    // O(n)
    public E getLast() {
        return get(size - 1);
    }

    // 根据索引插入元素
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        // 遍历到index的前一个元素
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    // 根据索引寻找元素
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        return prev.e;
    }

    // 删除指定位置的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node indexNode = prev.next;
        prev.next = indexNode.next;
        indexNode.next = null;

        size--;

        return indexNode.e;
    }

    // 修改元素
    public void update(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("update failed. Illegal index.");

        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.e = e;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

        // 无参构造，一般用于创建DummyHead
        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
