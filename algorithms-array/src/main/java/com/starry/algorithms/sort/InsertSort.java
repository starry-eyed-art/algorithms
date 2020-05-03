package com.starry.algorithms.sort;

/**
 * @Description 插入排序，核心思想在于将当前的元素插入到前面的合适的位置中
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/3 11:46 上午
 */
public class InsertSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 和前面的元素相比较，如果前面的元素大，则和前面的元素交换位置
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    // 优化后的插入排序，相比sort避免每次都需要swap
    public static void sortBetter(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            for (int j = i; j > 0; j--) {
                // 和前面的元素相比较，如果前面的元素大，则前面的元素往后移动一位
                if (cur < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                } else {
                    // 如果前面的元素小，说明找到合适的位置
                    arr[j] = cur;
                    break;
                }
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        int jr = arr[j];
        int ir = arr[i];
        arr[j] = ir;
        arr[i] = jr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 111, 4};
        sortBetter(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
