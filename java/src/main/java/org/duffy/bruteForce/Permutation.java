package org.duffy.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Permutation {

    public void pr2559() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        long ret = 0;
        for (int i=0; i<k; i++) {
            ret += a[i];
        }
        for (int i=0; i<=n-k; i++) {
            int tmp = a[i];
            for (int j=1; j<k; j++)
                tmp += a[i+j];
            ret = Math.max(ret, tmp);
        }
        System.out.println(ret);
    }

    public void pr10819(int[] nums) {
        Arrays.sort(nums);
        int ret  = 0;
        do {
            int tmp = count(nums);
            ret = Math.max(ret, tmp);
        } while (nextPermutation(nums));

        System.out.println(ret);
    }

    public int count(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            sum += Math.abs(nums[i-1] - nums[i]);
        }
        return sum;
    }

    public void pr10971(int n, int[][] grid) {
        int ret = Integer.MAX_VALUE;
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = i;
        }

        do {
            boolean flag = true;
            int sum = 0;
            for (int i = 0; i < n-1; i++) {
                if (grid[d[i]][d[i+1]] == 0)
                    flag = false;
                else
                    sum += grid[d[i]][d[i+1]];
            }
            if (flag && grid[d[n-1]][d[0]] != 0) {
                sum += grid[d[n-1]][d[0]];
                if (ret > sum)
                    ret = sum;
            }
        } while (nextPermutation(d));

        System.out.println(ret);
    }

    public static boolean nextPermutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i])
            i--;
        if (i <= 0) return false;

        int j = a.length-1;
        while (a[j] <= a[i-1])
            j--;

        swap(a, i-1, j);

        j = a.length-1;
        while (i < j) {
            swap(a, i, j);
            i++; j--;
        }
        return true;
    }

    public static void swap(int[] a, int from, int to) {
        int tmp = a[from];
        a[from] = a[to];
        a[to] = tmp;
    }
}
