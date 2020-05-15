package com.starry.daypratice;

/**
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/13 4:58 下午
 */
public class QuicksortThreeWays {


    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // partition
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        int v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i++, ++lt);
            } else if (arr[i] > v) {
                swap(arr, i, --gt);
            } else {
                i ++;
            }
        }
        swap(arr, l, lt);
        sort(arr, l, lt - 1);
        sort(arr, gt, r);
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
