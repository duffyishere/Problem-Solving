package org.duffy.code_forces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solutions {
    public void pr1698_B() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (0 < t--) {
            br.readLine();
            String a = br.readLine();
            String b = br.readLine();
            int idx = 0;

            for (int i = 0; i < b.length(); i++) {
                if (a.charAt(idx) == b.charAt(i)) {
                    idx++;
                }
                if (idx == a.length()) break;
            }
            System.out.println(idx);
        }
    }

    public void pr1968_A() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            int max = 0, res = 0;
            for (int y = 1; y < x; y++) {
                int gcd = gcd_1968(x, y) + y;
                if (max < gcd) {
                    max = gcd;
                    res = y;
                }
            }
            System.out.println(res);
        }
    }

    private int gcd_1968(int big, int small) {
        if (small == 0) return big;
        else return gcd_1968(small, big % small);
    }
}
