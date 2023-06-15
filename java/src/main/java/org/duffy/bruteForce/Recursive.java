package org.duffy.bruteForce;

public class Recursive {

    public void pr9095(int n) {
        System.out.println(go9095(0, n));
    }

    public int go9095(int sum, int goal) {
        if (sum > goal) return 0;
        if (sum == goal) return 1;
        int ret = 0;
        for (int i = 1; i < 4; i++) {
            ret += go9095(sum+i, goal);
        }
        return ret;
    }

    public void go1759(int n, String[] strings, String password, int i) {
        if (password.length() == n) {
            if (check1759(password)) {
                System.out.println(password);
            }
            return;
        }
        if (strings.length <= i) return;
        go1759(n, strings, password+strings[i], i+1);
        go1759(n, strings, password, i+1);
    }

    private boolean check1759(String password) {
        int consonantCount = 0;
        int vowelCount = 0;
        for (char c: password.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vowelCount++;
            else
                consonantCount++;
        }

        return vowelCount >= 1 && consonantCount >= 2;
    }

    int[] prices;
    int[] days;
    int n;
    int ret = 0;

    public void go14501(int day, int price) {
        /*
        1. False
            if n < i:
                return;
        2. Answer
            if day == n:
                ret = max(ret, price);
                return;
        3. Next
            go(day+days[day], price+prices[day]); >> 일을 하는 경우
            go(day+1, price); >> 일을 하지 않는 경우
         */

        if (n+1 < day) return;
        if (n+1 == day) {
            ret = Math.max(ret, price);
            return;
        }

        go14501(day+days[day], price+prices[day]);
        go14501(day+1, price);
    }
}
