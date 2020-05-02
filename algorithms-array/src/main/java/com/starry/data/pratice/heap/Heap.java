package com.starry.data.pratice.heap;

/**
 * @Description 堆抽象接口
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/15 10:20 上午
 */
public interface Heap<E> {

    int size();

    boolean isEmpty();

    void add(E e);

    int parent(int index);

    int leftChild(int parentIndex);

    int rightChild(int parentIndex);

    E extract();

    E findMaxOrMin();

    void heapify(E[] arrs);

    E replace(E e);
}
