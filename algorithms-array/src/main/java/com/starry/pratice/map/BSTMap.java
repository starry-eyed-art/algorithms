package com.starry.pratice.map;

import com.starry.pratice.set.FileOperation;

import java.util.ArrayList;

/**
 * @Description 基于BST的Map
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 2:40 下午
 */
public class BSTMap<K extends Comparable, V> implements Map<K, V> {

    private KVBST<K, V> bst;

    public BSTMap() {
        bst = new KVBST<>();
    }

    @Override
    public void add(K key, V value) {
        bst.addNode(key, value);
    }

    @Override
    public V remove(K key) {
        V v = this.get(key);
        if (v != null) {
            bst.remove(key);
        }
        return v;
    }

    @Override
    public boolean contains(K key) {
        return bst.contains(key);
    }

    @Override
    public V get(K key) {
        return bst.getNodeVal(key);
    }

    @Override
    public void set(K key, V newValue) {
        bst.set(key, newValue);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("/Users/xuyang/IdeaProjects/starry/algorithms/algorithms-array/src/main/resources/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
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
