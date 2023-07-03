package org.duffy.sort;

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
}
