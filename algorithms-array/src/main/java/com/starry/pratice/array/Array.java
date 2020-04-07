package com.starry.pratice.array;

/**
 * @Description 自定义数组
 * @Auther: starry
 * @Date: 2020/4/3 09:26
 */
public class Array<E> {

    // 存放数据的数组
    private E[] data;
    // 数组内真实存放元素的容量
    private int size;

    // 指定容量的构造方法
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    // 默认容量的构造方法，默认容量为10
    public Array() {
        data = (E[]) new Object[10];
    }

    // 从头部插入一个元素
    public void addFirst(E e) {
        addIndex(0, e);
    }

    // 从尾部插入一个元素
    public void addLast(E e) {
        addIndex(size, e);
    }

    // 从指定位置插入一个元素
    public void addIndex(int index, E e) {
        // 加入时允许index==size，是因为size指向数组最后一个元素的下一个位置
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index非法");
        }

        // 避免边界重复扩容/缩容的时间复杂度震荡问题
        if (size == data.length / 2) {
            resize(data.length * 2);
        }

        // 索引>=index，全部往后移动一位，空出index位置，当index==size时，不会进入此循环
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    // 根据索引查询元素
    public E find(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index非法");
        }
        return data[index];
    }

    // 根据元素查询索引，不存在返回-1
    public int find(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 查询最后一个元素
    public E findLast() {
        return find(size - 1);
    }

    // 查询第一个元素
    public E findFirst() {
        return find(0);
    }

    // 判断是否包含某个元素
    public boolean contains(E element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // 删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除指定元素
    public void remove(E e) {
        int i = find(e);
        if (i != -1) {
            remove(i);
        }
    }

    // 删除指定位置的元素
    public E remove(int index) {
        // 删除时不允许index==size，是因为在数组中data[size]必定是null的
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index非法");
        }
        E e = data[index];
        // index之后的元素往前移动一位，移动完成后，因为此时最后两个元素相同，故需要将最后一个元素置为空
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        // 避免边界值上的扩容/缩容引起的时间复杂度震荡
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return e;
    }

    // 数组扩容/缩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获得数组的大小
    public int getSize(){
        return size;
    }

    // 获得数组的容量
    public int getCapacity(){
        return data.length;
    }
}
