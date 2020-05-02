package com.starry.data.pratice.map;

/**
 * @Description 链表
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 6:34 下午
 */
public class KVLinkedList<K, V> {

    // 为什么需要dummyHead，解决头节点和非头节点插入逻辑不一致的问题
    private Node dummyHead;
    private int size;

    public KVLinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // O(1)
    public void addFirst(K k, V v) {
        add(0, k, v);
    }

    // O(n)
    public void addLast(K k, V v) {
        add(size, k, v);
    }

    // O(1)
    public V removeFirst() {
        return remove(0);
    }

    // O(n)
    public V removeLast() {
        return remove(size - 1);
    }

    // O(1)
    public V getFist() {
        return get(0);
    }

    // O(n)
    public V getLast() {
        return get(size - 1);
    }

    // 根据索引插入元素
    public void add(int index, K k, V v) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        // 遍历到index的前一个元素
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(k, v);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    // 根据索引寻找元素
    public V get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        return prev.v;
    }

    // 删除指定位置的元素
    public V remove(int index) {
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

        return indexNode.v;
    }

    // 修改元素
    public void update(K k, V v) {
        Node prev = dummyHead.next;
        while (prev != null) {
            if (prev.k.equals(k)) {
                prev.v = v;
            }
            prev = prev.next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(K k) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.k.equals(k)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public V remove(K k) {
        Node prev = dummyHead;
        while (prev.next != null) {
            prev = prev.next;
            if (prev.k.equals(k)) {
                Node retNode = prev;
                prev.next = prev.next.next;
                return retNode.v;
            }
        }
        return null;
    }

    public V getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.k.equals(key)) {
                return cur.v;
            }
            cur = cur.next;
        }
        return null;
    }

    private class Node {
        private K k;
        private V v;
        private Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        // 没有next的构造方法，一般此类节点会用于最后一个节点或者第一个节点，也有可能是先创建node后再指定next
        public Node(K k, V v) {
            this(k, v, null);
        }

        // 无参构造，一般用于创建DummyHead
        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return v.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
