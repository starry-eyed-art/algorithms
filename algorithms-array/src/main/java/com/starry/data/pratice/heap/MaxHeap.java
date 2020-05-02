package com.starry.data.pratice.heap;

import com.starry.data.pratice.array.Array;

/**
 * @Description 最大堆
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/15 10:22 上午
 */
public class MaxHeap<E extends Comparable> implements Heap<E> {

    private Array<E> array;

    public MaxHeap() {
        this.array = new Array<>();
    }

    public MaxHeap(E[] arr) {
        heapify(arr);
    }

    @Override
    public int size() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void add(E e) {
        array.addLast(e);
        shitUp(array.getSize() - 1);
    }

    // 将任意一个数组整理成最大堆的形状
    public void heapify(E[] data) {
        array = new Array<>(data);
        for (int i = parent(array.getSize()-1); i >= 0 ; i--) {
            shitDown(i);
        }
    }

    // 将最大值替换为另一个值
    public E replace(E e) {
        E ret = array.findFirst();
        array.set(0, e);
        shitDown(0);
        return ret;
    }

    private void shitUp(int index) {
        while (parent(index) >= 0 && array.find(index).compareTo(array.find(parent(index))) > 0) {
            array.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void shitDown(int index) {
        while (leftChild(index) < array.getSize()) {
            int lc = leftChild(index);
            E e = array.find(lc);
            if (lc + 1 < array.getSize() && e.compareTo(array.find(lc + 1)) < 0) {
                lc++;
            }
            if (array.find(lc).compareTo(array.find(index)) <= 0) {
                break;
            }
            array.swap(lc, index);
            index = lc;
        }
    }

    @Override
    public E extract() {
        array.swap(0, array.getSize() - 1);
        E ret = array.removeLast();
        shitDown(0);
        return ret;
    }

    @Override
    public E findMaxOrMin() {
        if (array.getSize() == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        return array.find(0);
    }

    @Override
    public int parent(int index) {
        return (index - 1) / 2;
    }

    @Override
    public int leftChild(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    @Override
    public int rightChild(int parentIndex) {
        return parentIndex * 2 + 2;
    }
}
