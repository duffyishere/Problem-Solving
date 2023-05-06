package org.duffy.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BruteForce {

    public void pr2309() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[9];
        int sum = 0;
        for (int i=0; i<9; i++) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }

        Arrays.sort(a);
        for (int i=0; i<9; i++) {
            for (int j=i+1; j<9; j++) {
                if (sum - a[i] - a[j] == 100) {
                    for (int k=0; k<9; k++) {
                        if (k == i || k == j) continue;
                        System.out.println(a[k]);
                    }
                    System.exit(0);
                }
            }
        }
    }

    public void pr3085() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] a = new char[n][n];
        for (int i=0; i<n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int ret = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (j+1 < n ) {
                    char tmp = a[i][j];
                    a[i][j] = a[i][j+1];
                    a[i][j+1] = tmp;

                    ret = Math.max(ret, check3085(a));

                    tmp = a[i][j];
                    a[i][j] = a[i][j+1];
                    a[i][j+1] = tmp;
                }
                if (i+1 < n) {
                    char tmp = a[i][j];
                    a[i][j] = a[i+1][j];
                    a[i+1][j] = tmp;

                    ret = Math.max(ret, check3085(a));

                    tmp = a[i][j];
                    a[i][j] = a[i+1][j];
                    a[i+1][j] = tmp;
                }
            }
        }
        System.out.println(ret);
    }

    private int check3085(char[][] a) {
        int ret = 1;
        int l = a.length;
        for (int i=0; i<l; i++) {
            // 가로 비교
            int tmp = 1;
            for (int j=1; j<l; j++) {
                if (a[i][j] == a[i][j-1]) tmp++;
                else tmp = 1;
                ret = Math.max(ret, tmp);
            }
            // 세로 비교
            tmp = 1;
            for (int j=1; j<l; j++) {
                if (a[j][i] == a[j-1][i]) tmp++;
                else tmp = 1;
                ret = Math.max(ret, tmp);
            }
        }

        return ret;
    }
}
