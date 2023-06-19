package org.duffy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] graph = new int[y][x];
    }
}