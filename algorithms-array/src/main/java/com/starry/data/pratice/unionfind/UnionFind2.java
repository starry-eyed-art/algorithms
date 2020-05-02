package com.starry.data.pratice.unionfind;

/**
 * @Description 使用QuickUnion实现的并查集，将union的操作时间复杂度降低为O(h)
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/22 2:20 下午
 */
public class UnionFind2 implements UF {

    private int parent[];// 数组索引代表的是元素，数组值代表的是该数组元素的父节点的索引，比如parent[1] = 2，代表的是1的父节点是2
    private int size;

    public UnionFind2(int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        // 将两个节点连接到一起，步骤是将p的根节点指向q的根节点
        int proot = find(p);
        int qroot = find(q);
        if (find(p) == find(q)) {
            return;
        }
        parent[proot] = qroot;// 这里parent[qroot] = proot也可以
    }

    // 寻找该节点的根节点(根节点的索引和数组中存储的元素相同，即根节点的父节点就是自己)
    private int find(int p){
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException("p not correct");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
