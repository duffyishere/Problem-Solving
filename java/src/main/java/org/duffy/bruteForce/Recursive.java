package org.duffy.bruteForce;

import java.util.List;

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

    int[][] grid;
    public int go14889(int index, List<Integer> first, List<Integer> second) {
        if (first.size() > n/2) return -1;
        if (second.size() > n/2) return -1;
        /*
        정답인 경우
        if index == n:
            return diff;
         */
        if (index == n) {
            if (first.size() != n / 2) return -1;
            if (second.size() != n / 2) return -1;
            int t1 = 0;
            int t2 = 0;
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    if (i == j) continue;
                    t1 += grid[first.get(i)][first.get(j)];
                    t2 += grid[second.get(i)][second.get(j)];
                }
            }
            return Math.abs(t1 - t2);
        }

        // Else
        int ret = -1;
        first.add(index);
        int t1 = go14889(index + 1, first, second);
        if (ret == -1 || t1 != -1 && t1 < ret)
            ret = t1;
        first.remove(first.size()-1);

        second.add(index);
        int t2 = go14889(index + 1, first, second);
        if (ret == -1 || t2 != -1 && t2 < ret)
            ret = t2;
        second.remove(second.size() - 1);

        return ret;
    }

    public int go15661(int index, List<Integer> first, List<Integer> second) {
        if (n == index) {
            if (first.size() == 0) return -1;
            if (second.size() == 0) return -1;
            int team1 = 0;
            int team2 = 0;
            for (int i = 0; i < first.size(); i++) {
                for (int j = 0; j < first.size(); j++) {
                    if (i == j) continue;
                    team1 += grid[first.get(i)][first.get(j)];
                }
            }
            for (int i = 0; i < second.size(); i++) {
                for (int j = 0; j < second.size(); j++) {
                    if (i == j) continue;
                    team2 += grid[second.get(i)][second.get(j)];
                }
            }

            return Math.abs(team1 - team2);
        }

        int ret = -1;
        first.add(index);
        int team1 = go15661(index + 1, first, second);
        if (ret == -1 || team1 != -1 && team1 < ret)
            ret = team1;
        first.remove(first.size() - 1);

        second.add(index);
        int team2 = go15661(index + 1, first, second);
        if (ret == -1 || team2 != -1 && team2 < ret)
            ret = team2;
        second.remove(second.size() - 1);

        return ret;
    }
}
