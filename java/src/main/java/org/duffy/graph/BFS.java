package org.duffy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    private int length, width;
    private int[][] visited;
    private int[][] grid;
    public int[] dx = new int[] {0, -1, 0, 1};
    public int[] dy = new int[] {1, 0, -1, 0};
    public Queue<Integer[]> queue = new LinkedList<>();

    public void pr2178() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        length = Integer.parseInt(buffer[0]);
        width = Integer.parseInt(buffer[1]);
        grid = new int[length][width];
        visited = new int[length][width];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().trim().split("");
            for (int j = 0; j < width; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        visited[0][0] = 1;
        queue.add(new Integer[]{0, 0});
        while (queue.size() > 0) {
            Integer[] bfr = queue.poll();
            int x = bfr[0];
            int y = bfr[1];

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (mx < 0 || my < 0 || width-1 < mx || length-1 < my) continue;
                if (0 < visited[my][mx]) continue;
                if (grid[my][mx] == 0) continue;

                visited[my][mx] = visited[y][x] + 1;
                queue.add(new Integer[]{mx, my});
            }
        }

        System.out.println(visited[length-1][width-1]);
    }
}
