package com.starry.pratice.map;

import com.starry.pratice.set.FileOperation;

import java.util.ArrayList;

/**
 * @Description 基于链表的MAP
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 3:44 下午
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private KVLinkedList<K, V> kvLinkedList;

    public LinkedListMap() {
        this.kvLinkedList = new KVLinkedList<>();
    }

    @Override
    public void add(K key, V value) {
        kvLinkedList.addFirst(key, value);
    }

    @Override
    public V remove(K key) {
        return kvLinkedList.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return kvLinkedList.contains(key);
    }

    @Override
    public V get(K key) {
        return kvLinkedList.getNode(key);
    }

    @Override
    public void set(K key, V newValue) {
        kvLinkedList.update(key, newValue);
    }

    @Override
    public int getSize() {
        return kvLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return kvLinkedList.isEmpty();
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("/Users/xuyang/IdeaProjects/starry/algorithms/algorithms-array/src/main/resources/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
