package org.duffy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        main.n = n;
        main.prices = new int[n+1];
        main.days = new int[n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            main.days[i] = Integer.parseInt(st.nextToken());
            main.prices[i] = Integer.parseInt(st.nextToken());
        }
        main.go14501(1, 0);
        System.out.println(main.ret);
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