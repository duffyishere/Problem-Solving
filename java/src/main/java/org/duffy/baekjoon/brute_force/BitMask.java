package org.duffy.baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BitMask {
    public void pr11723(int n, BufferedReader br) throws IOException {
        int s = 0;
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String keyword = st.nextToken();
            int num;
            switch (keyword) {
                case "add":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    s = (s | 1 << num);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    s = (s & ~(1 << num));
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    int res = (s & (1 << num));
                    if (res == 0)
                        sb.append(0 + "\n");
                    else
                        sb.append(1 + "\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    s = (s ^ (1 << num));
                    break;
                case "all":
                    s = (1 << 20) - 1;
                    break;
                case "empty":
                    s = 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}
