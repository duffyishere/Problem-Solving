package org.duffy;

public class Main {
    public static void main(String args[]) {
        Main main = new Main();
        main.numDecodings("2101");
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; 0 <= i; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            dp[i] = dp[i + 1];
            if (i < n - 1 && (s.charAt(i) == '1'|| s.charAt(i) == '2'&& s.charAt(i+1) < '7')) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }
}