package com.starry.pratice.queue;

/**
 * @Description 循环的精髓在于 (i+1) % data.length来实现索引上的循环
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/3 8:23 下午
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int size;
    private int front;// 循环队列队首索引
    private int tail;// 循环队列队尾索引

    public LoopQueue(int capacity) {
        // capacity + 1 是因为在循环队列中必定有一个位置是空闲的
        data = (E[]) new Object[capacity + 1];
        size = 0;
        tail = 0;
        front = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 循环队列已经满载的判断条件 (tail + 1) % data.length = front
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        // 从尾部插入
        data[tail] = e;
        size++;
        // 插入元素 tail往后移动一位
        tail = (tail + 1) % data.length;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E datum = data[front];
        data[front] = null;
        size--;
        front = (front + 1) % data.length;
        // 缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return datum;
    }

    @Override
    public E getFront() {
        return data[0];
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
