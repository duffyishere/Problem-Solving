package org.duffy.math;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MathProblem {

    public void pr1978() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int cnt = 0;
        List<Integer> nums = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for (int num: nums) {
            if (this.isPrimeNumber(num))
                cnt++;
        }
        System.out.println(cnt);

    }

    private boolean isPrimeNumber(int num) {
        if (num < 2)
            return false;
        for (int i=2; i*i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public void pr1929() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.valueOf(st.nextToken());
        int n = Integer.valueOf(st.nextToken());
        boolean[] check = new boolean[n+1];
        check[0] = check[1] = true;
        for (int i=2; i*i<=n; i++) {
            if (!check[i]) {
                for (int j=i*i; j<=n; j+=i) {
                    check[j] = true;
                }
            }
        }
        for (int i=m; i<=n; i++) {
            if (!check[i]) {
                System.out.println(i);
            }
        }
    }

    public void pr6588() throws IOException {
        int MAX = 1000000;
        boolean[] check = new boolean[MAX+1];
        List<Integer> primeNum = new ArrayList<>();
        int cnt = 0;
        check[0] = check[1] = true;
        for (int i=2; i*i<=MAX; i++) {
            if (!check[i]) {
                primeNum.add(i);
                cnt++;
                for (int j=i*2; j<=MAX; j+=i)
                    check[j] = true;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            int num = Integer.valueOf(br.readLine());
            if (num == 0) break;
//            if (num <= 4 || num %2 == 1) continue;
            for (int i=0; i<cnt; i++) {
                int p = primeNum.get(i);
                if (!check[num - p]) {
                    bw.write(num+" = "+p+" + "+ Integer.valueOf(num - p)+"\n");
                    bw.flush();
                    break;
                }
            }
        }
    }
}
