package com.starry.algorithms.sort;

/**
 * @Description 归并排序，时间复杂度为O(nlogn)
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/5/7 12:31 下午
 */
public class MergeSort {

    // 自下而上的归并排序
    public static void mergeSortBottomUp(int[] arr) {
        int n = arr.length;
        for (int sz = 1; sz < n; sz += sz) {// sz: 1,2,4,8
            for (int i = 0; i + sz < n; i += sz * 2) {// 一次跨过两个sz的长度，比如从0开始且sz为2时，对[0,1]和[2,3]进行merge操作
                // 对arr数组中的[i, i+sz-1]和[i+sz,i+sz+sz-1]进行merge操作
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    // 自上而下的归并排序
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        // 对数组进行二分，直到l和r相等，也就是只有一个元素
        if (l == r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        // 因为[l,mid]和[mid+1,r]都是有序的，因此，如果arr[mid]<=arr[mid+1]，说明两个数组合并后仍然是有序的，无需执行merge操作
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }
    }

    /**
     * 1. 将数组中从l到r的部分拷贝到一个新数组中暂存
     * 2. 将新数组根据mid分为两部分，[0, mid + 1 -l] [mid + 1 -l , r - l]
     * 3. 遍历数组，每次都从新数组的两部分中选择最小的元素，放入当前数组的位置中
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] newArr = new int[arr.length];
        for (int i = l; i <= r; i++) {
            newArr[i - l] = arr[i];
        }
        int i = l - l;
        int j = mid + 1 - l;
        for (int k = l; k <= r; k++) {
            if (i + l > mid) {
                arr[k] = newArr[j];
                j++;
            } else if (j + l > r) {
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
//        int[] arr = new int[]{1, 3, 4, 2, 111, 12, 1, 34, 212, 99, 4};
//        mergeSortBottomUp(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }
        System.out.println(1 + (2 -1) /2);
    }
}
