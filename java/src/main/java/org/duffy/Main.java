package org.duffy;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] a = new int[n+1][3];
        for (int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ret = 1000*1000+1;
        int[][] d = new int[n+1][3];
        for (int k=0; k<3; k++) {
            for (int j=0; j<3; j++) {
                if (k == j)
                    d[1][j] = a[1][j];
                else
                    d[1][j] = 1000*1000+1;
            }
            for (int i=2; i<=n; i++) {
                d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + a[i][0];
                d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + a[i][1];
                d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + a[i][2];
            }
            for (int j=0; j<=2; j++) {
                if (j == k) continue;
                ret = Math.min(ret, d[n][j]);
            }
        }

        System.out.println(ret);
    }
}
