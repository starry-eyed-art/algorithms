package com.starry.data.pratice.unionfind;

/**
 * @Description 通过将节点的父节点直接指向整棵树的根节点这种路径压缩的方式来优化并查集
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/23 3:21 下午
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int proot = find(p);
        int qroot = find(q);
        if (proot == qroot) {
            return;
        }
        if (rank[proot] < rank[qroot]) {
            parent[proot] = qroot;
        } else if (rank[qroot] < rank[proot]) {
            parent[qroot] = proot;
        } else {
            parent[proot] = qroot;
            rank[qroot] += 1;
        }
    }

    // 虽然在find上执行路径压缩会在前期牺牲部分性能，但是在大批量的查询时，后期的性能提升是巨大的，可以忽略这部分牺牲的性能
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p not correct");
        }
        if (p != parent[p]) {
            parent[p] = find(parent[p]);// 寻找父节点的父节点，一直往上找，直到找到根节点
        }
        // 这里是递归出口
        // 当p = parent[p]时，返回根节点的索引
        return parent[p];
    }
}
