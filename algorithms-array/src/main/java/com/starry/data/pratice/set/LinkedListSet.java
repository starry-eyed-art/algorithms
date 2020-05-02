package com.starry.data.pratice.set;

import com.starry.data.pratice.linkedlist.LinkedList;

/**
 * @Description 链表Set
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 10:11 上午
 */
public class LinkedListSet<E> implements Set<E>  {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        // 先判断是否存在O(n),再添加O(1)
        if (!contains(e)) {
            linkedList.addFirst(e);
        }
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.remove(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
