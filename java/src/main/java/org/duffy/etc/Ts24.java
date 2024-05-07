package org.duffy.etc;

import java.util.Arrays;

public class Ts24 {
    public boolean solution3(String amountText) {
        int n = amountText.length();
        if (1 < n && amountText.charAt(0) == 0) return false;
        if (amountText.charAt(0) == ',' || amountText.charAt(n - 1) == ',') return false;

        int idx = -1;
        for (int i = 0; i < n; i++) {
            char ch = amountText.charAt(i);
            if (!Character.isDigit(ch) && ch != ',') return false;

            if (ch == ',') {
                if (idx == -1 || i == idx) {
                    idx = i + 4;
                } else {
                    return false;
                }
            }
            if (n <= idx - 1) {
                return false;
            }
        }
        return true;
    }

    public int solution2(String s) {
        int max = -1;
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == s.charAt(i + 2)) {
                int num = Integer.parseInt(s.substring(i, i + 3));
                max = Math.max(max, num);
            }
        }
        return max;
    }

    public int solution1(int[] levels) {
        Arrays.sort(levels);
        int idx = levels.length - levels.length / 4;
        if (levels.length < 4) {
            return -1;
        }
        return levels[idx];
    }
}
