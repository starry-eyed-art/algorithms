package com.starry.data.pratice.avl;

import com.starry.data.pratice.set.FileOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @Description AVL平衡树，通过旋转解决了BST在极端情况下可能退化成链表的情况，并且保证平衡性，即任意一个节点的左右子树高度差不超过1
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/24 11:59 上午
 */
public class AVLTree<K extends Comparable, V> {

    private Node root;
    private int size;

    public AVLTree() {
        size = 0;
    }

    // 添加节点
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            size++;
            return node;
        }
        if (key.compareTo(node.k) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.k) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.v = value;
        }

        // 维持高度
        // 为什么即使没有增加节点也是加一？因为1代表的是节点本身的高度，实际增加的是左右子树的高度，如果没有增加节点，那么其实左右子树的高度不变。新增节点后，左右子树的高度最大高度+1
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 维持平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 旋转
        // LL 新增的节点在需要旋转的节点的左节点的左节点
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 右旋转
            return rightRotate(node);
        }

        // RR 新增的节点在需要旋转的节点的右节点的右节点
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            // 左旋转
            return leftRotate(node);
        }

        // LR 新增的节点在需要旋转的节点的左节点的右节点
        if (balanceFactor > 1 && getBalanceFactor(node.left) <= 0) {
            // 先对x执行左旋转
            //     y              y
            //    /              /
            //   x         ->   z
            //    \            /
            //     z          x
            node.left = leftRotate(node.left);
            // 再对y执行右旋转
            return rightRotate(node);
        }

        // RL 新增的节点在需要旋转的节点的右节点的左节点
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 先对x执行右旋转
            //    y               y
            //     \               \
            //      x       ->      z
            //     /                 \
            //    z                   x
            node.right = rightRotate(node.right);
            // 再对y执行左旋转
            return leftRotate(node);
        }
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        resetHeight(y, x);
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t3 = x.left;

        x.left = y;
        y.right = t3;

        resetHeight(y, x);
        return x;
    }

    private void resetHeight(Node y, Node x) {
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
    }

    // 寻找最小节点
    public Node findMin() {
        return findMin(root);
    }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    // 寻找最大节点
    public Node findMax() {
        return findMax(root);
    }

    private Node findMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return findMax(node.right);
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.k);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.k);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node);
    }

    // 删除最小
    public Node removeMin() {
        size--;
        return removeMin(root);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大
    public Node removeMax() {
        size--;
        return removeMax(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除指定元素
    public Node remove(K key) {
        return remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode = null;
        if (key.compareTo(node.k) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else if (key.compareTo(node.k) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else {
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                retNode = left;
            } else if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                retNode = right;
            } else {
                Node min = findMin(node.right);
                Node successor = remove(node.right, min.k);
                successor.left = node.left;
                successor.right = node.right;
                node.left = null;
                node.right = null;
                size--;
                retNode = successor;
            }
        }

        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        int balanceFactor = getBalanceFactor(retNode);

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    // 层序遍历
    public void levelOrder() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node remove = queue.remove();
            System.out.println(remove.k);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }
        }
    }

    // 根据key获取值
    public V get(K key) {
        return getNode(root, key).v;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;

        if (key.equals(node.k))
            return node;
        else if (key.compareTo(node.k) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    // 包含某个key
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    // 更新某个数据的值
    public void set(K key, V value) {
        if (contains(key)) {
            set(root, key, value);
        }
    }

    private void set(Node node, K key, V value) {
        if (key.compareTo(node.k) == 0) {
            node.v = value;
        } else if (key.compareTo(node.k) > 0) {
            set(node.right, key, value);
        } else {
            set(node.left, key, value);
        }
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取size
    public int getSize() {
        return size;
    }

    // AVL 获取某个节点的高度
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    // AVL 获取某个节点的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // AVL 判断当前是否是BST
    public boolean isBST() {
        ArrayList<K> arrayList = new ArrayList<>();
        inOrder(root, arrayList);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1).compareTo(arrayList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> ks) {
        if (node == null) {
            return;
        }
        inOrder(node.left, ks);
        ks.add(node.k);
        inOrder(node.right, ks);
    }

    // AVL 判断当前是否是AVL
    public boolean isAVL() {
        return isAVL(root);
    }

    private boolean isAVL(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(node.balanceFactor) > 1) {
            return false;
        }
        return isAVL(node.left) && isAVL(node.right);
    }

    private class Node {
        private K k;
        private V v;
        private Node left;
        private Node right;
        private int height; // 节点的高度
        private int balanceFactor;// 节点的平衡因子，计算方式：left.height - right.height

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
            this.height = 1;
            this.balanceFactor = 0;
        }
    }


    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("/Users/xuyang/IdeaProjects/starry/algorithms/algorithms-array/src/main/resources/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isAVL());
        }

        System.out.println();
    }
}
