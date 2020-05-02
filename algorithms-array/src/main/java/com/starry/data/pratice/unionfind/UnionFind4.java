package com.starry.data.pratice.unionfind;

/**
 * @Description 并查集第四版，基于rank的优化，rank[i]代表i作为根节点代表的树的高度
 * 基于size优化时，有可能出现节点数量大，但是深度低和节点数量小，深度高的两棵树合并的情况，此时反而会使树的深度变大
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/23 9:26 上午
 */
public class UnionFind4 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
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

        if(proot == qroot)
            return;
        // 根据两个元素所在树的rank不同判断合并方向，将rank低的集合合并到rank高的集合上
        if (rank[proot] < rank[qroot]) {
            parent[proot] = qroot; // 这里proot和qroot的树的高度都没有发生改变，因此不需要操作rank数组
        } else if (rank[qroot] < rank[proot]) {
            parent[qroot] = proot; // 这里proot和qroot的树的高度都没有发生改变，因此不需要操作rank数组
        } else {
            parent[proot] = qroot; // 当proot的树高度和qroot树高度相等时，将proot的父节点编程qroot，之后qroot的树高度需要加一
            rank[qroot] += 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p not correct");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
