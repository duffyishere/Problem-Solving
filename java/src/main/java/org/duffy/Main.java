package org.duffy;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] a = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);

        if (nextPermutation(a)) {
            for (int i=0; i<n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
        else
            System.out.println(-1);
    }
}