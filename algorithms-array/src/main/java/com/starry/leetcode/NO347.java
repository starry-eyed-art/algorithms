package com.starry.leetcode;

import com.starry.pratice.array.Array;

import java.util.*;

/**
 * @Description 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/20 9:00 上午
 */
public class NO347 {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (freqMap.get(nums[i]) == null) {
                freqMap.put(nums[i], 1);
            } else {
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 将频次低的元素放到前面，因为实现了堆来实现优先队列，所以新加入的元素默认会放在堆的最后的位置，然后执行shitUp。
                // shitUp时，o1是新加入的元素，o2是原先的元素，因此，如果新加入的元素小于原先的元素，则交换位置。
                return freqMap.get(o1) - freqMap.get(o2);
            }
        });
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry.getKey());
            } else {
                Integer min = pq.peek();
                if (entry.getValue() > freqMap.get(min)) {
                    pq.remove();
                    pq.add(entry.getKey());
                }
            }
        }
        ArrayList<Integer> retList = new ArrayList<>();
        for (Integer node : pq) {
            retList.add(node);
        }
        return retList;
    }

    public static void main(String[] args) {
        List<Integer> integers = topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
        System.out.println(integers);
    }
}
