package org.duffy.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Permutation {

    public void pr2559() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Integer[] a = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        long ret = 0;
        for (int i=0; i<k; i++) {
            ret += a[i];
        }
        for (int i=0; i<=n-k; i++) {
            int tmp = a[i];
            for (int j=1; j<k; j++)
                tmp += a[i+j];
            ret = Math.max(ret, tmp);
        }
        System.out.println(ret);
    }
}
