package com.starry.data.pratice.heap;

import com.starry.data.pratice.array.Array;

/**
 * @Description 最小堆
 * @Auther: xuxudong@cvte.com
 * @Date: 2020/4/18 09:09
 */
public class MinHeap<E extends Comparable> implements Heap<E> {

    private Array<E> array;

    public MinHeap() {
        this.array = new Array<>();
    }

    public MinHeap(E[] arrs) {
        this.array = new Array<>(arrs);
        heapify(arrs);
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

    private void shitUp(int index) {
        // 最小堆。如果父节点大于子节点，则进行元素交换
        while (parent(index) >= 0 && array.find(parent(index)).compareTo(array.find(index)) > 0) {
            array.swap(parent(index), index);
            index = parent(index);
        }
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

    @Override
    public E extract() {
        array.swap(0, array.getSize() - 1);
        E ret = array.removeLast();
        shitDown(0);
        return ret;
    }

    private void shitDown(int index) {
        // 获取节点的子元素中最小的元素，和当前节点比较，如果小于当前节点，则做元素交换
        while (leftChild(index) < array.getSize()) {
            int j = leftChild(index);
            if (j + 1 < array.getSize() && array.find(j).compareTo(array.find(j + 1)) > 0) {
                j++;
            }
            if (array.find(index).compareTo(array.find(j)) > 0) {
                array.swap(index, j);
            }
            index = j;
        }
    }

    @Override
    public E findMaxOrMin() {
        if (array.getSize() == 0) {
            throw new IllegalArgumentException("minHeap is empty");
        }
        return array.find(0);
    }

    @Override
    public void heapify(E[] arrs) {
        for (int i = parent(array.getSize() - 1); i >= 0; i--) {
            shitDown(i);
        }
    }

    @Override
    public E replace(E e) {
        E min = array.findFirst();
        array.set(0, e);
        shitDown(0);
        return min;
    }
}
