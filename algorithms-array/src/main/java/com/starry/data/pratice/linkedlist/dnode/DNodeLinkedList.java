package com.starry.data.pratice.linkedlist.dnode;

/**
 * @Description 双向链表
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/7 12:56 下午
 */
public class DNodeLinkedList<E> {

    private DNode dummyHead;
    private int size;

    public DNodeLinkedList() {
        this.dummyHead = new DNode();
    }

    // 在指定位置插入元素
    public void add(int index, E e) {

    }

    private class DNode {
        private E e;
        private DNode next;
        private DNode prev;

        public DNode(E e, DNode next, DNode prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        // 没有next的构造方法，一般此类节点会用于最后一个节点或者第一个节点，也有可能是先创建node后再指定next
        public DNode(E e) {
            this(e, null, null);
        }

        // 无参构造，一般用于创建DummyHead
        public DNode() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
