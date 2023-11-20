package org.duffy.baekjoon.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Practice {
    int y, x;

    public void pr16929(int y, int x, char[][] grid) {
        this.y = y;
        this.x = x;
        this.grid16929 = grid;
        this.visited16929 = new boolean[y][x];
        this.counts16929 = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (dfs(i, j, 0)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    boolean[][] visited16929;
    char[][] grid16929;
    int[][] counts16929;

    public int[] dx = new int[] {0, -1, 0, 1};
    public int[] dy = new int[] {1, 0, -1, 0};

    public boolean dfs(int y, int x, int count) {
        if (visited16929[y][x]) {
            return count - counts16929[y][x] >= 4;
        }
        visited16929[y][x] = true;
        counts16929[y][x] = count;
        char color = grid16929[y][x];
        for (int i = 0; i < 4; i++) {
            int my = y + dy[i];
            int mx = x + dx[i];
            if (0 <= my && 0 <= mx && my < this.y && mx < this.x) {
                if (grid16929[my][mx] == color) {
                    if (dfs(my, mx, count + 1)) return true;
                }
            }
        }
        return false;
    }

    public void pr16947(int n, List<Integer>[] graph) {
        visited = new int[n];
        findCycle(graph, 0, 0);
        Queue<Integer> workQueue = new LinkedList<>();
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 2) {
                workQueue.add(i);
            }
            else
                count[i] = -1;
        }
        while (!workQueue.isEmpty()) {
            int now = workQueue.remove();
            for (int next: graph[now]) {
                if (count[next] == -1) {
                    count[next] = count[now] + 1;
                    workQueue.add(next);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();
    }

    // If it's a cycle, visited is 2.
    int[] visited;
    public int findCycle(List<Integer>[] graph, int now, int prev) {
        if (0 < visited[now]) return now;
        visited[now] = 1;
        for (Integer next: graph[now]) {
            if (next == prev) continue;
            int res = findCycle(graph, next, now);
            if (res == -2) return -2;
            if (0 <= res) {
                visited[now] = 2;
                if (res == now) return -2;
                else return res;
            }
        }

        return -1;
    }
}
