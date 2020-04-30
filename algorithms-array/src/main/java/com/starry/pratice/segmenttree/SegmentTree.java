package com.starry.pratice.segmenttree;

import org.omg.CORBA.Object;

/**
 * @Description 线段树 线段树是平衡二叉树但不是满二叉树
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/28 8:18 上午
 */
public class SegmentTree<E> {

    private E[] tree;// 用于表示线段树
    private E[] data;// 用于暂存生成线段树的数据内容
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger merger) {
        this.merger = merger;
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            arr[i] = data[i];
        }
        this.tree = (E[]) new Object[4 * arr.length];// 当线段树的原始数据区间为[1,n]时，实际需要4n空间的数组才能存放对应的数据。
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 表示在treeIndex这个位置建立以l和r为左右区间的根节点的线段树，l和r对应data中某个值的索引
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    public E query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在treeIndex这个根节点[l....r]的线段树中，查找[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightChild, mid + 1, r, queryL, queryR);
        }
        if (queryR <= mid) {
            return query(leftChild, l, mid, queryL, queryR);
        }

        // 范围分为左子树和右子树两个部分，所以此时[queryL，queryR]分为[queryL...mid]和[mid+1....queryR]两个部分
        // 左子树是在左节点中，此时左节点的区间为[l....mid]，寻找queryL到mid范围的数据。
        E left = query(leftChild, l, mid, queryL, mid);
        E right = query(rightChild, mid + 1, r, mid + 1, queryR);

        return merger.merge(left, right);
    }


    public int getSize() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }


    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

}
