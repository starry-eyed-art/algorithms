package com.starry.data.pratice.unionfind;

/**
 * @Description 并查集第三版，在第二版的基础上，增加了基于树的size的优化，在union时，会将size小的树指向size大的树
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/23 9:25 上午
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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

        // 必须加上
        if(proot == qroot)
            return;

        if (sz[proot] < sz[qroot]) {
            parent[proot] = qroot;
            sz[qroot] += sz[proot];
        } else {
            parent[qroot] = proot;
            sz[proot] += sz[qroot];
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
