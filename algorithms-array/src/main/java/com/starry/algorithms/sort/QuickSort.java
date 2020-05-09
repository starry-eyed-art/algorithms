package com.starry.algorithms.sort;

/**
 * @Description 快速排序
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/8 11:49 上午
 */
public class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int j = partition(arr, l, r);
        sort(arr, l, j - 1);
        sort(arr, j + 1, r);
    }

    // 将arr从[l,r]区间中的第一个元素确定为中间值v，其他元素大于等于v的放到v的右边，小于v的放到v的左边
    private static int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int j = l;// j指向比v小的数组中的最后一个元素，j++指向大于等于v的数组中的第一个元素
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr, j, i);
            }
        }
        // 将j和v互换位置，这样子v就处于合适的位置上
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int j, int i) {
        int jr = arr[j];
        int ir = arr[i];
        arr[j] = ir;
        arr[i] = jr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 111, 4, 12, 3, 7, 6};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
