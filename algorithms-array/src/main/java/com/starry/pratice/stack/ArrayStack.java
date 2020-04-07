package com.starry.pratice.stack;

import com.starry.pratice.array.Array;

/**
 * @Description 栈的实现
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/3 8:23 下午
 */
public class ArrayStack<E> {

    // 使用数组来作为栈的内部实现
    public Array<E> array;

    // 指定容量的构造
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    // 默认容量的构造方法
    public ArrayStack() {
        array = new Array<>();
    }

    // 往栈中推进去一个元素
    public void push(E element) {
        array.addLast(element);
    }

    // 栈顶弹出一个元素
    public E pop() {
        return array.removeLast();
    }

    // 查看栈顶的元素
    public E peek() {
        return array.findLast();
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 获取元素数量
    public int getSize() {
        return array.getSize();
    }

    // 获得栈容量大小
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.find(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
