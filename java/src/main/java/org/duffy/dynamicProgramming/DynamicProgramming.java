package org.duffy.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DynamicProgramming {

    private long[] temp = new long[101];
    private int[] d = new int[1001];
    private Integer[] price;

    public long fibonacci(int a) {
        if (a <= 1)
            return 1;
        else {
            if (temp[a] != 0)
                return temp[a];
            temp[a] = fibonacci(a-1) + fibonacci(a-2);
            return temp[a];
        }
    }

    public int pr1463(int n) {
        if (n == 1)
            return 0;
        if (d[n] != 0)
            return d[n];

        d[n] = pr1463(n-1)+1;

        if (n%2 == 0) {
            int temp = pr1463(n/2) + 1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        if (n%3 == 0) {
            int temp = pr1463(n/3) + 1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }

        return d[n];
    }

    public int pr11726(int n) {
        if (n <= 1)
            return 1;
        if (d[n] != 0)
            return d[n];
        d[n] = pr11726(n-1) + pr11726(n-2);
        d[n] %= 10007;
        return d[n];
    }

    public int pr11727(int n) {
        if (n <= 1)
            return 1;
        if (d[n] != 0)
            return d[n];
        d[n] = pr11727(n-1) + pr11727(n-2) + pr11727(n-2);
        d[n] %= 10007;

        return d[n];
    }

    public int pr9095(int n) {
        if (n <= 1)
            return 1;
        else if (n==2)
            return 2;

        if (d[n] != 0)
            return d[n];

        d[n] = pr9095(n-3) + pr9095(n-2) + pr9095(n-1);
        return d[n];
    }

    public int pr11052(int n) {
        if (n == 1) return price[0];
        if(d[n] != 0) return d[n];

        for (int i=1; i<=n; i++) {
            int tmp = pr11052(n-i) + price[i-1];
            if (d[n] < tmp)
                d[n] = tmp;
        }
        return d[n];
    }

    public void pr15990() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dd = new long[100001][4];
        long mod = 1000000009L;

        for (int i=1; i<=100000; i++) {
            if (i-1 >= 0) {
                dd[i][1] = dd[i-1][2] + dd[i-1][3];
                if (i == 1)
                    dd[i][1] = 1;
            }
            if (i-2 >= 0) {
                dd[i][2] = dd[i-2][1] + dd[i-2][3];
                if (i == 2) dd[i][2] = 1;
            }
            if (i-3  >= 0) {
                dd[i][3] = dd[i-3][1] + dd[i-3][2];
                if (i == 3) dd[i][3] = 1;
            }
            dd[i][1] %= mod;
            dd[i][2] %= mod;
            dd[i][3] %= mod;
        }
        int n = Integer.valueOf(br.readLine());
        while (n-- > 0) {
            int t = Integer.valueOf(br.readLine());
            System.out.println((dd[t][1] + dd[t][2] + dd[t][3])%mod);
        }
    }

    public void pr10844() throws IOException {
        long mod = 1000000000L;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[][] d = new int[n+1][10];
        for (int i=1; i<10; i++)
            d[1][i] = 1;

        for (int i=2; i<=n; i++) {
            for (int j=0; j<10; j++) {
                d[i][j] = 0;
                if (j-1 >= 0)
                    d[i][j] += d[i-1][j-1];
                if (j+1 <= 9)
                    d[i][j] += d[i-1][j+1];
                d[i][j] %= mod;
            }
        }

        long result = 0;
        for (int i=-0; i<10; i++)
            result += d[n][i];

        System.out.println(result%mod);
    }

    public void pr2193() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        long[][] d = new long[n+2][2];
        d[1][1] = d[2][0] = 1;

        for (int i=3; i<=n; i++) {
            d[i][0] = d[i-1][0] + d[i-1][1];
            d[i][1] = d[i-1][0];
        }

        System.out.println(d[n][0] + d[n][1]);
    }

    public void pr11053() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        Integer[] input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        int[] d = new int[n+1];
        for (int i=0; i<n; i++) {
            d[i] = 1;
            for (int j=0; j<=i; j++) {
                if (input[j] < input[i] && d[i] < d[j]+1)
                    d[i] = d[j] + 1;
            }
        }

        int ans = d[0];
        for (int i=0; i<n; i++) {
            if (ans < d[i]) {
                ans = d[i];
            }
        }
        System.out.println(ans);
    }

    private Integer[] a;
    private int[] v;
    public void pr14002() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        a = Arrays.stream(br.readLine().split(" "))
                .map(Integer::new)
                .toArray(Integer[]::new);
        d = new int[n];
        v = new int[n];
        for (int i=0; i<n; i++) {
            d[i] = 1;
            v[i] = -1;
            for (int j=0; j<i; j++) {
                if (a[j] < a[i] && d[i] < d[j]+1) {
                    d[i] = d[j]+1;
                    v[i] = j;
                }
            }
        }
        int ans = d[0];
        int p = 0;
        for (int i=0; i<n; i++) {
            if (ans < d[i]) {
                ans = d[i];
                p = i;
            }
        }
        System.out.println(ans);
        print(p);
        System.out.println();
    }

    private void print(int p) {
        if (p == -1) return;
        print(v[p]);
        System.out.print(a[p] + " ");
    }
}
