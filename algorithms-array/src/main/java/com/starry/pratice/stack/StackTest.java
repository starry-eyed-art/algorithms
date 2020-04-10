package com.starry.pratice.stack;

/**
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/7 12:57 下午
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
