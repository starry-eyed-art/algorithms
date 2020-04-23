package com.starry.pratice.unionfind;

/**
 * @Description 并查集顶层接口
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/22 9:03 上午
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}