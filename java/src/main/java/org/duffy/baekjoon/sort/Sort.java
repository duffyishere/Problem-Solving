package org.duffy.baekjoon.sort;

import java.util.Queue;

public class Sort {
    public void pr11728(Queue<Integer> q1, Queue<Integer> q2) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (q1.isEmpty()) {
                while (!q2.isEmpty()) {
                    sb.append(q2.remove() + " ");
                }
                break;
            }
            if (q2.isEmpty()) {
                while (!q1.isEmpty()) {
                    sb.append(q1.remove() + " ");
                }
                break;
            }

            int n1 = q1.peek();
            int n2 = q2.peek();
            if (n2 < n1) {
                sb.append(q2.remove() + " ");
            }
            else {
                sb.append(q1.remove() + " ");
            }
        }

        System.out.println(sb);
    }

    public void pr2751(int n, int[] nums) {
        this.nums = nums;
        this.temp = new int[n];
        mergeSort(0, n);

        StringBuffer sb = new StringBuffer();
        for (int num: nums)
            sb.append(num + "\n");

        System.out.println(sb);
    }

    public void mergeSort(int start, int end) {
        if (start + 1 == end) return;
        int mid = (start + end) / 2;

        mergeSort(start, mid);
        mergeSort(mid, end);
        merge(start, end);
    }

    int[] nums;
    int[] temp;
    public void merge(int start, int end) {
        int mid = (start + end) / 2;
        int leftIndex = start;
        int rightIndex = mid;
        for (int i = start; i < end; i++) {
            if (rightIndex == end)
                temp[i] = nums[leftIndex++];
            else if (leftIndex == mid)
                temp[i] = nums[rightIndex++];
            else if (nums[leftIndex] <= nums[rightIndex])
                temp[i] = nums[leftIndex++];
            else
                temp[i] = nums[rightIndex++];
        }

        for (int i = start; i < end; i++)
            nums[i] = temp[i];
    }

    public void mergerSort_2(int start, int end) {
        if (start + 1 == end) return;
        int mid = (start + end) / 2;
        mergerSort_2(start, mid);
        mergerSort_2(mid, end);
        merge_2(start, end);
    }

    public void merge_2(int start, int end) {
        int mid = (start + end) / 2;
        int leftIndex = start;
        int rightIndex = end;
        for (int i = start; i < end; i++) {
            if (rightIndex == end)
                temp[i] = nums[leftIndex++];
            else if (leftIndex == end)
                temp[i] = nums[rightIndex++];
            else if (nums[leftIndex] <= nums[rightIndex])
                temp[i] = nums[leftIndex++];
            else
                temp[i] = nums[rightIndex++];
        }

        for (int i = start; i < end; i++) {
            nums[i] = temp[i];
        }
    }


    final int MAX = 1000000;
    int[] frequency = new int[MAX * 2 + 100];
    public void countingSort() {
        for (int num: nums)
            frequency[num + MAX] += 1;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < frequency.length; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                sb.append(i - MAX + "\n");
            }
        }

        System.out.println(sb);
    }

    public float findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int sum = n1 + n2;
        int[] joined = new int[sum];

        int i = 0, j = 0, k = 0;

        while (i <= n1 && j <= n2) {
            if (i == n1) {
                while (j < n2) joined[k++] = nums2[j++];
                break;
            }
            else if (j == n2) {
                while (i < n1) joined[k++] = nums1[i++];
                break;
            }

            if (nums1[i] < nums2[j])
                joined[k++] = nums1[i++];
            else
                joined[k++] = nums2[j++];
        }

        if (sum % 2 == 0)
            return (float) (joined[sum / 2 - 1] + joined[sum / 2]) / 2;
        return joined[sum / 2];
    }
}
