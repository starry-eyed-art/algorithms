package com.starry.data.pratice.heap;

import com.starry.data.pratice.queue.Queue;

/**
 * @Description 基于最小堆的优先队列
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/20 8:59 上午
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MinHeap<E> minHeap;

    public PriorityQueue() {
        minHeap = new MinHeap<>();
    }

    @Override
    public int getSize() {
        return minHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        minHeap.add(e);
    }

    @Override
    public E dequeue() {
        return minHeap.extract();
    }

    @Override
    public E getFront() {
        return minHeap.findMaxOrMin();
    }
}
