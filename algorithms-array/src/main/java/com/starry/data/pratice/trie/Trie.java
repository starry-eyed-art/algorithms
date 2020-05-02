package com.starry.data.pratice.trie;

import java.util.TreeMap;

/**
 * @Description 字典树
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/21 9:27 上午
 */
public class Trie {

    private Node root;
    private int size;// Trie中单词的数量

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next.get(c) == null) {
                return false;
            }
        }
        return true;
    }

    private class Node {
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
