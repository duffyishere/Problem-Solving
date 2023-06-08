package org.duffy;

import java.io.*;
import java.util.*;

public class Main {

    public static int length, width;
    public static int[][] grid, visited;
    public static int[] dx = new int[] {0, -1, 0, 1};
    public static int[] dy = new int[] {1, 0, -1, 0};
    public static Queue<Integer[]> queue = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
    }
}