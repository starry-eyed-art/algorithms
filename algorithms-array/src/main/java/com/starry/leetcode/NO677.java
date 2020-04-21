package com.starry.leetcode;

import java.util.Set;
import java.util.TreeMap;

/**
 * @Description 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * <p>
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * <p>
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/21 10:35 上午
 */
public class NO677 {

    private Node root;
    private int size;

    private class Node {
        private int val;
        private TreeMap<Character, Node> next;

        public Node(int val) {
            this.val = val;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    /**
     * Initialize your data structure here.
     */
    public NO677() {
        root = new Node();
        size = 0;
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (char c : key.toCharArray()) {
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.val = val;
        size++;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        int sum = cur.val;
        for (Character key : cur.next.keySet()) {
            sum += sum(cur.next.get(key));
        }
        return sum;
    }

    // 每个节点分别统计自己及其所有子节点的值的总和
    private int sum(Node node) {
        if (node.next.size() == 0) {
            return node.val;
        }
        int val = node.val;
        for (Character newKey : node.next.keySet()) {
            val += sum(node.next.get(newKey));
        }
        return val;
    }
}
