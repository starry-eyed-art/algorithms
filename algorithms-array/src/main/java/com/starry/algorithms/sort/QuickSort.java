package com.starry.algorithms.sort;

/**
 * @Description 快速排序
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/30 6:59 下午
 */
public class QuickSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    swap(arr, j ,i);
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
        int[] arr = new int[]{1,3,4,2,111,4};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
