package com.starry.pratice.bst;

import java.util.Random;

/**
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/10 11:27 上午
 */
public class BSTTest {

    public static void main(String[] args) {
        BST<Integer> myBst = new BST<>(100);
        myBst.addNode(1);
//        myBst.addNode(101);
//        myBst.addNode(19);
//        myBst.addNode(13123);
//        myBst.addNode(17);
//        myBst.addNode(1000);
//        myBst.addNode(2);
//        myBst.preOrder();
//        System.out.println("-------------------------分割线-------------------------");
//        myBst.inOrder();
//        System.out.println("-------------------------分割线-------------------------");
//        myBst.postOder();
//        System.out.println("-------------------------分割线-------------------------");
//        System.out.println("-------------------------最大值:" + myBst.maximum());
//        System.out.println("-------------------------最小值:" + myBst.mininum());
//
//        System.out.println("-------------------------删除最大值-------------------------");
        myBst.removeMax();
        myBst.inOrder();

//        System.out.println("-------------------------删除最小值-------------------------");
//        myBst.removeMin();
//        myBst.inOrder();
//
//        System.out.println("-------------------------删除指定值-------------------------");
//        myBst.remove(100);
//        myBst.inOrder();
    }
}
