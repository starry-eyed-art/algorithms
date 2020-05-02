package com.starry.data.pratice.unionfind;

/**
 * @Description 使用Quick Find的并查集，此实现中union的时间复杂度为O(n)，isConnected的时间复杂度为O(1)
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/22 9:04 上午
 */
public class UnionFind1 implements UF {

    private int[] id;// 数组索引代表的是元素的值，数组值代表的是该元素是位于哪一个集合中，比如id[0] == id[2]，即0和2在同一个集合中
    private int size;

    public UnionFind1(int size) {
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;// 初始化时每个元素都在不同的集合中
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
        // 遍历所有元素，当数组的值等等于q对应的数组的值时，将该索引上的数组值变更为p对应的数组值
        int pBelongListIndex = find(p);
        int qBelongListIndex = find(q);
        for (int i = 0; i < size; i++) {
            if (find(i) == qBelongListIndex) {
                id[i] = pBelongListIndex;
            }
        }
    }

    private int find(int p) {
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException("p not correct");
        }
        return id[p];
    }
}
