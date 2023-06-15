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
}
