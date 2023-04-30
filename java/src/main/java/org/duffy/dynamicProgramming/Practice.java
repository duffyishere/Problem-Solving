package org.duffy.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Practice {

    public void pr15988() throws IOException {
        final long mod = 1000000009L;
        final int max = 1000000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] d = new long[max+1];
        d[0] = 1;
        for (int i=1; i<=max; i++) {
            for (int j=1; j<4; j++) {
                if (i-j >= 0)
                    d[i] += d[i-j];
            }
            d[i] %= mod;
        }

        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            System.out.println(d[n]);
        }
    }

    public void pr1149() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n+1][3];
        int[][] price = new int[n+1][3];

        for (int i=1; i<=n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j=0; j<3; j++) {
                price[i][j] = Integer.parseInt(str[j]);
                if (i==1) {
                    d[i][j] = price[i][j];
                }
                else {
                    switch (j) {
                        case 0:
                            d[i][j] = d[i-1][1] < d[i-1][2]? d[i-1][1]: d[i-1][2];
                            break;
                        case 1:
                            d[i][j] += d[i-1][0] < d[i-1][2]? d[i-1][0]: d[i-1][2];
                            break;
                        case 2:
                            d[i][j] += d[i-1][0] < d[i-1][1]? d[i-1][0]: d[i-1][1];
                            break;
                    }
                    d[i][j] += price[i][j];
                }
            }
        }

        int rst = d[n][0];
        for (int i=0; i<3; i++) {
            if (d[n][i] < rst)
                rst = d[n][i];
        }

        System.out.println(rst);
    }

    public void pr1309() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[][] d = new int[n+1][3];
        d[0][0] = 1;
        for (int i=1; i<=n; i++) {
            d[i][0] = d[i-1][0] + d[i-1][1] + d[i-1][2];
            d[i][1] = d[i-1][0] + d[i-1][2];
            d[i][2] = d[i-1][0] + d[i-1][1];
            for (int j=0; j<3; j++) {
                d[i][j] %= 9901;
            }
        }

        System.out.println((d[n][0] + d[n][1] + d[n][2])%9901);
    }

    public void pr11057() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        final int mod = 10007;
        int[][] d = new int[n+1][10];
        for (int i=0; i<10; i++) d[1][i] = 1;
        for (int i=2; i<=n; i++) {
            for (int j=0; j<10; j++) {
                for (int k=0; k<=j; k++) {
                    d[i][j] += d[i-1][k];
                }
                d[i][j] %= mod;
            }
        }

        int rst = 0;
        for(int i=0; i<10; i++) {
            rst += d[n][i];
        }

        System.out.println(rst%mod);
    }

    public void pr11722() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[n+1];
        for (int i=n; i>=1; i--) {
            d[i] = 1;
            for (int j=n; j>=i; j--) {
                if (a[i] > a[j] && d[i] < d[j]+1)
                    d[i] = d[j]+1;
            }
        }

        int ret = d[1];
        for (int i=2; i<=n; i++)
            ret = ret < d[i]? d[i]: ret;
        System.out.println(ret);
    }

    public void pr11054() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] d1 = new int[n+1];
        for (int i=1; i<=n; i++) {
            d1[i] = 1;
            for (int j=1; j<i; j++) {
                if (a[i] > a[j] && d1[i] < d1[j]+1)
                    d1[i] = d1[j]+1;
            }
        }

        int[] d2 = new int[n+1];
        for (int i=n; i>=1; i--) {
            d2[i] = 1;
            for (int j=n; j>=i; j--) {
                if (a[i] > a[j] && d2[i] < d2[j]+1)
                    d2[i] = d2[j]+1;
            }
        }


        int ret = d1[1] + d2[1] - 1;
        for (int i=2; i<=n; i++) {
            ret = Math.max(ret, d1[i] + d2[i] - 1);
        }

        System.out.println(ret);
    }
}
