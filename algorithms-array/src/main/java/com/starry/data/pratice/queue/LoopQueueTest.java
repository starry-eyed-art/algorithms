package com.starry.data.pratice.queue;

/**
 * @Description 循环队列测试
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/3 8:24 下午
 */
public class LoopQueueTest {

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>(10);
        loopQueue.enqueue(1);
        loopQueue.enqueue(2);
        loopQueue.enqueue(3);
        loopQueue.enqueue(4);
        loopQueue.enqueue(5);
        loopQueue.enqueue(6);
        loopQueue.enqueue(7);
        loopQueue.enqueue(8);
        loopQueue.enqueue(9);
        loopQueue.enqueue(10);

        System.out.println(loopQueue);

        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();

        System.out.println(loopQueue);
    }
}
