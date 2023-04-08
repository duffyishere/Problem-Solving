package org.duffy.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Practice {

    public long factorial(int num, long sum) {
        if (num == 0 || num == 1)
            return sum;
        else {
            return factorial(num-1, num * sum);
        }
    }

    public void pr9613() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            List<Integer> num = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            long sum = 0;
            for (int i=1; i<=num.get(0); i++) {
                for (int j=i+1; j<=num.get(0); j++) {
                    sum += gcd(num.get(i), num.get(j));
                }
            }

            System.out.println(sum);
        }
    }

    private int gcd(int a, int b) {
        return b==0? a: gcd(b, a%b);
    }

    public void pr17087() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int [n];
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            a[i] = Math.abs(x-s);
        }

        int result = a[0];
        for (int i=1; i<n; i++) {
            result = gcd(result, a[i]);
        }

        System.out.println(result);
    }
}
