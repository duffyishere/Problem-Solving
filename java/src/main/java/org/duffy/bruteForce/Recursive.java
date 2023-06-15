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
}
