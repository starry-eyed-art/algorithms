package com.starry.pratice.test;

import com.starry.pratice.ArrayStack;

/**
 * @Description
 * @Auther: xuxudong@cvte.com
 * @Date: 2020/4/3 19:15
 */
public class StackTest {

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        System.out.println(arrayStack);

        arrayStack.pop();
        System.out.println(arrayStack);

        System.out.println(arrayStack.peek());
    }
}
