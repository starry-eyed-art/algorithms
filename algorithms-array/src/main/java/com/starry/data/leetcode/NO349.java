package com.starry.data.leetcode;

import java.util.TreeSet;

/**
 * @Description 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 4:08 下午
 */
public class NO349 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        TreeSet<Integer> ret = new TreeSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                ret.add(i);
            }
        }
        int i = 0;
        int[] retArr = new int[ret.size()];
        for (Integer integer : ret) {
            retArr[i] = integer;
            i ++;
        }
        return retArr;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4,9,5};
        int[] arr2 = new int[]{9,4,9,8,4};
        intersection(arr1, arr2);
    }
}
