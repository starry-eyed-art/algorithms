package com.starry.pratice.test;

import com.starry.pratice.Array;

/**
 * @Description
 * @Auther: xuxudong@cvte.com
 * @Date: 2020/4/3 10:07
 */
public class ArrayTest {

    public static void main(String[] args) {
        Array<Integer> integerArray = new Array<>(2);

        integerArray.addLast(1);
        integerArray.addLast(2);
        System.out.println(integerArray);
        integerArray.addLast(3);
        System.out.println(integerArray);

        integerArray.removeLast();
        System.out.println(integerArray);
        integerArray.removeFirst();
        System.out.println(integerArray);
    }
}
