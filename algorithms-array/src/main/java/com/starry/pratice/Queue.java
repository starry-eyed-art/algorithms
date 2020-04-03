package com.starry.pratice;

/**
 * @Description 队列接口
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/3 8:24 下午
 */
public interface Queue<E> {

    // 获取队列中的元素数量
    int getSize();

    // 判断队列是否为空
    boolean isEmpty();

    // 入队
    void enqueue(E e);

    // 出队
    E dequeue();

    // 获取第一个元素
    E getFront();
}
