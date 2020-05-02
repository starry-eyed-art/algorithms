package com.starry.data.leetcode;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @Description 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/14 4:04 下午
 */
public class NO350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        // 先将nums1的内容加入到map中，并将出现的次数记录到value中
        TreeMap<Integer, Integer> numMap = new TreeMap<>();
        for (int i : nums1) {
            Integer times = numMap.get(i);
            if (times == null) {
                numMap.put(i, 1);
            } else {
                numMap.put(i, ++times);
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 遍历nums2
        for (int i : nums2) {
            // 获得i在nums1中出现的次数
            Integer times = numMap.get(i);
            // 首先次数减一，如果此时次数大于0，就加入到list中，否则不加入
            if (times != null) {
                times = times - 1;
                if (times >= 0) {
                    arrayList.add(i);
                }
                numMap.put(i, times);
            }
        }
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            ret[i] = arrayList.get(i);
        }
        return ret;
    }

}
