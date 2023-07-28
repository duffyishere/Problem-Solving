package org.duffy;

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Main main = new Main();
    }

    private void mergeSort(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if (left + 1 == right)
            return;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = mid;

        for (int i = left; i < right; i++) {
            if (rightIndex == right) {
                tmp[i] = nums[leftIndex++];
            }
            else if (leftIndex == mid) {
                tmp[i] = nums[rightIndex++];
            }
            else if (nums[leftIndex] < nums[rightIndex]) {
                tmp[i] = nums[leftIndex++];
            }
            else {
                tmp[i] = nums[rightIndex++];
            }
        }

        for (int i = left; i < right; i++)
            nums[i] = tmp[i];
    }
}