package com.starry.algorithms.sort;

/**
 * @Description 双路快排，目的是解决大量重复元素时会出现的时间复杂度退化为O(n^2)的问题
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/9 7:46 上午
 */
public class QuickSortTwoWays {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    // j记录大于v这一端下一个要扫描的位置，停在整个数组最后一个小于等于V的位置
    // i记录小于v这一端下一个要扫描的位置，停在整个数组第一个大于等于V的位置
    private static int partition(int[] arr, int l, int r) {

        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (true) {
            // 此处不能是arr[i] <= v，因为如果加上=时，处理{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}这种数组时会退化成O(n^2)
            while (i <= r && arr[i] < v) {
                i ++;
            }
            // 此处不能是arr[i] <= v，因为如果加上=时，处理{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}这种数组时会退化成O(n^2)
            while (j >= l + 1 && arr[j] > v) {
                j --;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i ++;
            j --;
        }
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
