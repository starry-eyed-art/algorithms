package com.starry.data.pratice.map;

/**
 * @Description 映射 使用BST和链表作为底层实现映射，分别对原有的BST和LinkedList做改造，然后比较两者的时间复杂度
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 2:38 下午
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();

}
