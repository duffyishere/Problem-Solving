package org.duffy;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] a = new int[10];
    private static boolean c[] = new boolean[10];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        System.out.println(sb);
    }
}