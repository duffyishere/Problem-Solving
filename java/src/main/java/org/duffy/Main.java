package org.duffy;

import org.duffy.leet_code.ListNode;
import org.duffy.leet_code.TreeNode;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Main main = new Main();
    }

    public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int num: nums) {
            if (num != 0) nums[pos++] = num;
        }
        while (pos < nums.length) {
            nums[pos++] = 0;
        }
    }
}