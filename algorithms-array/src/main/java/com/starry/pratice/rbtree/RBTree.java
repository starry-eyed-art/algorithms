package com.starry.pratice.rbtree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description 红黑树实现（基于23树思想实现，左倾红黑树）
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/25 4:08 下午
 */
public class RBTree<K extends Comparable, V> {

    private Node root;
    private int size;

    public RBTree() {
        size = 0;
        root = null;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            size++;
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.value = value;
        }

        if (!isRed(node.left) && isRed(node.right)) {
            node = lefRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    public V minimum() {
        return minimum(root).value;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public V maximum() {
        return maximum(root).value;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public V remove(K key) {
        return remove(root, key).value;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            Node minimum = minimum(node.right);
            node = removeMin(node.right);
            minimum.left = node.left;
            minimum.right = node.right;
            node.left = node.right = null;
            return node;
        }
    }

    public void levelOrder() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node remove = queue.remove();
            System.out.println(remove.key);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }
        }
    }

    public V get(K key) {
        if (contains(key)) {
            return get(root, key).value;
        }
        return null;
    }

    public Node getNode(K key) {
        if (contains(key)) {
            return get(root, key);
        }
        return null;
    }

    private Node get(Node node, K key) {
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        }
        if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        }
        return true;
    }

    public void set(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        }
    }

    // 红黑树旋转翻转逻辑
    //      B:x        B:x       B:z             R:z
    //     /   -->    /   -->   /   \    -->    /   \
    //    R:y        R:z       R:y  R:x        B:y  B:x
    //     \        /
    //     R:z    R:y
    public Node lefRotate(Node y) {
        //   node                     x
        //  /   \     左旋转         /  \
        // T1   x   --------->   node   T3
        //     / \              /   \
        //    T2 T3            T1   T2
        Node x = y.right;
        Node t2 = x.left;

        x.left = y;
        y.right = t2;

        x.color = y.color;
        y.color = RED;

        return x;
    }

    public Node rightRotate(Node y) {
        //     node                   x
        //    /   \     右旋转       /  \
        //   x    T2   ------->   y   node
        //  / \                       /  \
        // y  T1
        Node x = y.left;
        Node t1 = x.right;

        x.right = y;
        y.left = t1;

        x.color = y.color;
        y.color = RED;

        return x;
    }

    public void flipColors(Node node) {
        node.color = RED;
        node.left.color = node.right.color = BLACK;
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private boolean color = RED;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

}
