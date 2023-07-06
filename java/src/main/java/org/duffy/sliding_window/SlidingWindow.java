package org.duffy.sliding_window;

public class SlidingWindow {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = 0;
        int minLen = len + 1;

        while (end < len) {
            target -= nums[end++];
            while (target <= 0) {
                minLen = Math.min(minLen, end - start);
                target += nums[start++];
            }
        }

        return minLen % (len + 1);
    }
}
