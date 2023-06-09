package org.duffy;

import java.io.*;
import java.util.*;

public class Main {

    public static int height, width;
    public static int[][] grid, visited;
    public static int[] dx = new int[] {0, -1, 0, 1};
    public static int[] dy = new int[] {1, 0, -1, 0};
    public static Queue<Integer[]> queue = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        height = Integer.parseInt(buffer[0]);
        width = Integer.parseInt(buffer[1]);
        grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            buffer = br.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                int num = Integer.parseInt(buffer[j]);
                grid[i][j] = num;
            }
        }
    }
}