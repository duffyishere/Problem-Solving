package org.duffy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken()) - 1;
        }
    }
}