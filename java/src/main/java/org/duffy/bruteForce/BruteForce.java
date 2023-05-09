package org.duffy.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

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

    public void pr1476() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[3];
        for (int i=0; i<3; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[] d = new int[3];
        d[0] = d[1] = d[2] = 1;
        int ret = 1;
        while (true) {
            if (d[0] == a[0] && d[1] == a[1] && d[2] == a[2]) {
                System.out.println(ret);
                break;
            }
            d[0] ++; d[1]++; d[2]++;
            if (d[0]%15 == 1) d[0] %= 15;
            if (d[1]%28 == 1) d[1] %= 28;
            if (d[2]%19 == 1) d[2] %= 19;
            ret++;
        }
    }

    public void pr1107() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] a = new boolean[10];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            a[x] = true;
        }
        int ret = Math.abs(n - 100);

        for (int i=0; i<1000000; i++) {
            int len = check1107(i, a);
            if (len > 0) {
                int press = Math.abs(n - i);
                ret = (press + len < ret)? press + len: ret;
            }
        }
        System.out.println(ret);
    }

    private int check1107(int n, boolean[] a) {
        int len = 0;
        if (n == 0)
            return a[0]? 0: 1;
        while(n > 0) {
            if (a[n%10])
                return 0;
            len++;
            n /= 10;
        }
        return len;
    }

    public void pr6064() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] line = br.readLine().split(" ");
            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);
            int x = Integer.valueOf(line[2])-1;
            int y = Integer.valueOf(line[3])-1;
            boolean flag = false;
            for (int k=x; k<n*m; k+=m) {
                if (k%n==y) {
                    flag = true;
                    System.out.println(k+1);
                    break;
                }
            }
            if (!flag)
                System.out.println(-1);
        }
    }
}
