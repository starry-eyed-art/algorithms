package com.starry.pratice.segmenttree;

/**
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/30 8:51 上午
 */
public interface Merger<E> {

    E merge(E a, E b);
}
