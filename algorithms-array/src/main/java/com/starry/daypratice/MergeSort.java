package com.starry.daypratice;

public class MergeSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] newArr = new int[arr.length];
        for (int i = l; i <= r; i++) {
            newArr[i - l] = arr[i];
        }

        int i = l - l;
        int j = mid + 1 - l;
        for (int k = 0; k < newArr.length; k++) {
            if (i > mid - l) {
                arr[k] = newArr[j];
                j++;
            } else if (j > r - l) {
                arr[k] = newArr[i];
                i++;
            } else if (newArr[i] <= newArr[j]) {
                arr[k] = newArr[i];
                i++;
            } else if (newArr[i] > newArr[j]) {
                arr[k] = newArr[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 111, 4, 12, 3, 7, 6};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
