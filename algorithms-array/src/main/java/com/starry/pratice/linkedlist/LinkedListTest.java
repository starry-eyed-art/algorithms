package com.starry.pratice.linkedlist;

/**
 * @Description 链表测试
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 9:59 下午
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);

        System.out.println(linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();

        System.out.println(linkedList);
    }
}
