package com.starry.pratice.map;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 拥有Key和Value的二分搜索树
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/10 8:40 上午
 */
public class KVBST<K extends Comparable, V> {

    // 2. 定义根节点和size
    private Node root;
    private int size;

    // 3. 定义BST构造函数
    public KVBST(K k, V v) {
        this.root = new Node(k, v);
        size = 1;
    }

    public KVBST() {
        size = 0;
    }

    // 4. 定义添加节点方法
    public void addNode(K k, V v) {
        this.root = addNode(root, k, v);
    }

    /**
     * 递归符号的传递规则：如果要加入的元素大于当前元素，则往右传递，否则往左传递
     * 递归传递的符号：往右传递则是node.right，否则是node.left
     * 递归传递的终止条件：当传递的元素本身为空时，传递结束
     */
    // 递归增加节点
    private Node addNode(Node node, K k, V v) {
        // 递归终止条件，node为null表示e找到了自己的位置，因此构建node并返回，从而挂接到BST上
        if (node == null) {
            size++;
            return new Node(k, v);
        }
        if (k.compareTo(node.k) > 0) {
            // > 当前节点 挂接到右子树
            node.right = addNode(node.right, k, v);
        } else if (k.compareTo(node.k) < 0) {
            // < 当前节点，挂接到左子树
            node.left = addNode(node.left, k, v);
        } else {
            node.v = v;
        }
        return node;
    }

    // 5. 寻找最小节点
    public V mininum() {
        return mininum(root).v;
    }

    private Node mininum(Node node) {
        // 一直向左移动，直到某个节点的左节点为null，那么此时这个节点即为最小节点
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
    }

    // 6. 寻找最大节点
    public V maximum() {
        return maximum(root).v;
    }

    private Node maximum(Node node) {
        // 一直向右移动，直到某个节点的右节点为null，那么此时这个节点即为最大节点
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 7. 遍历：无论是前序遍历，中序遍历还是后序遍历，遍历的方向都是left->right，区别只在于打印节点的时机
    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.v);
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
        System.out.println(node.v);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOder() {
        postOder(root);
    }

    private void postOder(Node node) {
        if (node == null) {
            return;
        }
        postOder(node.left);
        postOder(node.right);
        System.out.println(node.v);
    }

    // 8、删除最小节点
    public V removeMin() {
        Node retNode = mininum(root);
        root = removeMin(root);
        return retNode.v;
    }

    // 此方法最后返回的是删除后的根节点，比如传入的是root，那么调用者应该也应该用root来接收；传入的是node.left，就是node.left = removeMin(node.left)
    // 找到最小的节点MIN，将MIN的右孩子(不管是否为null)挂接为MIN的父节点的左子树
    private Node removeMin(Node node) {
        // 一直寻找左孩子，如果节点N左孩子L为空，说明找到最小值了，将N的右孩子R返回给N的上一层节点P，从而P的左孩子变成R
        if (node.left == null) {
            Node right = node.right;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 9、删除最大节点
    public V removeMax() {
        Node retNode = maximum(root);
        root = removeMax(root);
        return retNode.v;
    }

    // 此方法最后返回的是删除后的根节点，比如传入的是root，那么调用者应该也应该用root来接收；传入的是node.left，就是node.left = removeMin(node.left)
    // 找到最大的节点MAX，将MAX的左孩子（无论是否为null）挂接为MAX的父节点的右子树
    private Node removeMax(Node node) {
        // 一直寻找右孩子，如果节点N右孩子R为空，说明找到最大值了，将N的左孩子L返回给N的上一层节点P，从而P的右孩子变成L
        if (node.right == null) {
            Node left = node.left;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 10、删除指定节点
    public void remove(K k) {
        root = remove(root, k);
    }

    private Node remove(Node node, K k) {
        if (k.compareTo(node.k) > 0) {
            node.right = remove(node.right, k);
        } else if (k.compareTo(node.k) < 0) {
            node.left = remove(node.left, k);
        } else {
            if (node.left == null) {
                Node right = node.right;
                node = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node = null;
                size--;
                return left;
            }
            // 删除并返回右子树中的最小节点作为后继节点，并将其替换到被删除元素的位置上，然后返回给父节点
            Node successor = mininum(node.right);
            node.right = removeMin(node.right);
            successor.left = node.left;
            successor.right = node.right;
            return successor;
        }
        return node;
    }


    // 11、层序遍历
    public void levelOrder() {
        levelOrder(root);
    }

    // 利用队列的方式来实现层序遍历
    private void levelOrder(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.v);

            // 利用队列先进先出的特性完成层序遍历
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    // 12、根据Key获取元素
    public Node getNode(K key) {
        return getNode(root, key);
    }

    public V getNodeVal(K key) {
        return getNode(root, key).v;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.k) > 0) {
            return getNode(node.right, key);
        }
        if (key.compareTo(node.k) < 0) {
            return getNode(node.left, key);
        }
        return node;
    }

    public boolean contains(K k) {
        return contains(root, k);
    }

    private boolean contains(Node node, K k) {
        if (node == null) {
            return false;
        }
        if (k.compareTo(node.k) > 0) {
            return contains(node.right, k);
        }
        if (k.compareTo(node.k) < 0) {
            return contains(node.left, k);
        }
        return true;
    }

    // 更新数据的值
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            return;
        }
        node.v = newValue;
    }

    // 1. 定义树的节点结构
    private class Node {
        private K k;
        private V v;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
