package com.starry.daypratice;

/**
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/12 10:21 下午
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
        sort(arr, l, p-1);// 将l写成了0
        sort(arr, p + 1, r);// 将p+1写成了p
    }

    private static int partition(int[] arr, int l, int r) {
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );

        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i ++;
            }
            while (j >= l + 1 && arr[j] > v) {
                j --;// 错写成j++
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, j, l);
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
