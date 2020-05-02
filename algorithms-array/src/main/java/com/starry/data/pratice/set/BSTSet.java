package com.starry.data.pratice.set;

import com.starry.data.pratice.bst.BST;

/**
 * @Description 二分搜索树集合
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 9:11 上午
 */
public class BSTSet<E extends Comparable> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.addNode(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
