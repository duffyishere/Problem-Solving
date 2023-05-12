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
        go15651(0, n, m);
        System.out.println(sb);
    }

    private static void go15651(int index, int n, int m) {
        if (index == m) {
            for (int i=0; i<m; i++) {
                sb.append(a[i]);
                if (i != m-1)
                    sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=n; i++) {
            c[i] = true;
            a[index] = i;
            go15651(index+1, n, m);
            c[i] = false;
        }
    }
}