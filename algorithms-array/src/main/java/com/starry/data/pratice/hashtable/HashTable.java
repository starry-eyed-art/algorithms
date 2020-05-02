package com.starry.data.pratice.hashtable;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description 哈希表的实现，通过链地址法解决哈希冲突，CRUD操作都为O(1)，实际上是O(downTol) ~ O(upTol)
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/2 12:02 下午
 */
public class HashTable<K, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private TreeMap<K, V>[] hashTable;
    private int size;
    private int capacityIndex = 0;// 初始容量索引
    private int upTol = 10;// 最大扩容忍耐度，假定HashTable的数组大小为M，元素数量为N，那么此时如果 N >= upTol * M时，执行扩容
    private int downTol = 2;// 最小缩容忍耐度，假定HashTable的数组大小为M，元素数量为N，那么此时如果 N <= downTol * M时，执行缩容

    public HashTable() {
        this.hashTable = new TreeMap[capacity[capacityIndex]];
        for (int i = 0; i < capacity[capacityIndex]; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        // & 0x7fffffff这一步去掉整数的符号
        return (key.hashCode() & 0x7fffffff) % capacity[capacityIndex];
    }

    private void resize(int newCapacityIndex) {
        TreeMap[] newHashTable = new TreeMap[capacity[newCapacityIndex]];
        for (int i = 0; i < capacity[newCapacityIndex]; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        // 遍历数组
        for (TreeMap<K, V> treeMap : hashTable) {
            // 遍历数组的每个查找表
            for (Map.Entry<K, V> entry : treeMap.entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                // 将key重新hash之后，放入到新数组的查找表中
                newHashTable[hash(key)].put(key, value);
            }
        }
        this.hashTable = newHashTable;
    }

    public void add(K key, V value) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        if (treeMap.containsKey(key)) {
            treeMap.put(key, value);
        } else {
            treeMap.put(key, value);
            size++;
        }
        if (capacityIndex + 1 < capacity.length && size >= upTol * capacity[capacityIndex]) {
            resize(++capacityIndex);
        }
    }

    public V remove(K key) {
        V val = hashTable[hash(key)].remove(key);
        size --;
        if (capacityIndex -1 >= 0 && size <= downTol * capacity[capacityIndex]) {
            resize(--capacityIndex);
        }
        return val;
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    public void set(K key, V value) {
        if (contains(key)) {
            hashTable[hash(key)].put(key, value);
        }
    }
}
