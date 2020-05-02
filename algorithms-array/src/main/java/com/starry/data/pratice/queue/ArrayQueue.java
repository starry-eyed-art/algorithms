package com.starry.data.pratice.queue;

import com.starry.data.pratice.array.Array;

/**
 * @Description 数组队列 FIFO 队首出队，队尾入队
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/3 8:23 下午
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        // 注意这里的时间复杂度为O(n)，将通过循环队列改为O(1)
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.findFirst();
    }
}
