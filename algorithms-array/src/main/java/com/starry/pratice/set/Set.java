package com.starry.pratice.set;

/**
 * @Description 集合顶层接口 使用BST和链表作为底层实现集合，比较两者的时间复杂度
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 9:09 上午
 */
public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
