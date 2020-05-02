package com.starry.data.pratice.linkedlist;

/**
 * @Description 没有虚拟头节点的链表
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 6:33 下午
 */
public class LinkedListWithoutDummyHead<E> {

    private NodeWithoutDummyHead head;
    private int size;

    public LinkedListWithoutDummyHead() {
        head = null;
        size = 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        // 此处需要区分头节点和非头节点，通过DummyHead来避免
        if (size == 0) {
            head = new NodeWithoutDummyHead(e);
        } else {
            NodeWithoutDummyHead prev = head;
            // 因为是从头节点开始遍历，因此遍历数量为 index-1
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            NodeWithoutDummyHead node = new NodeWithoutDummyHead(e);
            node.next = prev.next;
            prev.next = node;

            size++;
        }
    }

    private class NodeWithoutDummyHead {
        private E e;
        private NodeWithoutDummyHead next;

        public NodeWithoutDummyHead(E e, NodeWithoutDummyHead next) {
            this.e = e;
            this.next = next;
        }

        // 没有next的构造方法，一般此类节点会用于最后一个节点或者第一个节点
        public NodeWithoutDummyHead(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
