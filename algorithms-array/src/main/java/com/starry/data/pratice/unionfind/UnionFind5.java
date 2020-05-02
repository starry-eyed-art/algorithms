package com.starry.data.pratice.unionfind;

/**
 * @Description 使用路径压缩进一步优化并查集
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/23 9:58 上午
 */
public class UnionFind5 implements UF {

    private int[] parent;
    // 路径压缩后因为节省开销的原因，所以并不会维护rank的变化，因此rank并不能真实反馈树的高度，这也是为什么叫做rank，而不是叫做depth或者height的原因。
    // 这里的rank更代表的是一种高度的排名，rank越大，排名越高。
    private int[] rank;

    public UnionFind5(int size) {
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
        // 判断p和q的根节点的高度从而来决定union的方向
        int proot = find(p);
        int qroot = find(q);
        if (rank[proot] < rank[qroot]) {
            parent[proot] = qroot;
        } else if (rank[qroot] < rank[proot]) {
            parent[qroot] = proot;
        } else {
            parent[qroot] = proot;
            rank[qroot] += 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p not correct");
        }
        while (p != parent[p]) {
            // 在find操作中执行路径压缩，具体方法是将某个节点的父节点变更为父节点的父节点。比如A->B->C变更为A->C，B->C从而实现树的高度降低
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
