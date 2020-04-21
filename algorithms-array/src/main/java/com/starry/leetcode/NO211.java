package com.starry.leetcode;

import java.util.TreeMap;

/**
 * @Description 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 示例:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 *
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/21 9:59 上午
 */
public class NO211 {

    private Node root;
    private int size;

    public NO211() {
        root = new Node();
        size = 0;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            // 在递归中，return 也是接收返回值的一种方式
            return search(node.next.get(c), word, index + 1);
        }
        for (Character key : node.next.keySet()) {
            if (search(node.next.get(key), word, index + 1)) {
                return true;
            }
        }
        return false;
    }

    private class Node{
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
