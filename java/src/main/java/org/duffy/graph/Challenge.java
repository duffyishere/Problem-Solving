package org.duffy.graph;

import java.util.*;

public class Challenge {
    public void pr16940(int n, List<Integer>[] graph, int[] sequence) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Queue<Integer> workQueue = new LinkedList<>();
        workQueue.add(0);
        visited[0] = true;
        int startIndex = 1;
        for (int i=0; i<n; i++) {
            if (workQueue.isEmpty()) {
                System.out.println(0);
                System.exit(0);
            }
            int now = workQueue.remove();
            if (now != sequence[i]) {
                System.out.println(0);
                System.exit(0);
            }
            int count = 0;
            for (int next : graph[now]) {
                if (visited[next] == false) {
                    parent[next] = now;
                    count += 1;
                }
            }
            for (int j=0; j<count; j++) {
                if (n <= startIndex+j || parent[sequence[startIndex+j]] != now) {
                    System.out.println(0);
                    System.exit(0);
                }
                workQueue.add(sequence[startIndex+j]);
                visited[sequence[startIndex+j]] = true;
            }
            startIndex += count;
        }
        System.out.println(1);
    }

    public void pr16940_2(int n, List<Integer>[] graph, int[] seq) {
        boolean[] visited = new boolean[n];
        Queue<Integer> workQueue = new LinkedList<>();
        int[] parents = new int[n];
        workQueue.add(0);
        visited[0] = true;
        int index = 1;
        for (int i = 0; i < n; i++) {
            if (workQueue.isEmpty()) {
                System.out.println(0);
                System.exit(0);
            }

            int now = workQueue.remove();
            if (now != seq[i]) {
                System.out.println(0);
                System.exit(0);
            }

            int count = 0;
            for (int next: graph[now]) {
                if (visited[next]) continue;
                parents[next] = now;
                count += 1;
            }

            for (int j = 0; j < count; j++) {
                if (n <= j + index || parents[seq[j + index]] != now) {
                    System.out.println(0);
                    System.exit(0);
                }
                workQueue.add(seq[j + index]);
                visited[seq[j + index]] = true;
            }
            index += count;
        }
        System.out.println(1);
    }


    public void pr16964(int n, List<Integer>[] graph, int[] seq, int[] b) {
        boolean[] visited = new boolean[n];
        this.n = n;
        this.visited = visited;
        this.graph = graph;

        for (int i = 0; i < n; i++) {
            Collections.sort(graph[i], (u, v) -> {
                if (seq[u] < seq[v])
                    return -1;
                else if (seq[u] == seq[v])
                    return 0;
                else
                    return 1;
            });
        }

        dfs(0);
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (dfsOrder.get(i) != b[i])
                flag = false;
        }
        System.out.println(flag? 1: 0);
    }

    int n;
    List<Integer>[] graph;
    boolean[] visited;
    List<Integer> dfsOrder = new ArrayList<>();
    public void dfs(int now) {
        if (visited[now]) return;
        dfsOrder.add(now);
        visited[now] = true;
        for (int next: graph[now])
            dfs(next);
    }

    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, -1, 0, 1};
    public void pr2146(int n, int[][] graph) {
        boolean[][] visited = new boolean[n][n];
        int[][] groups = new int[n][n];

        Queue<int[]> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    groups[i][j] = ++count;

                    while (!queue.isEmpty()) {
                        int[] tmp = queue.remove();
                        int y = tmp[0];
                        int x = tmp[1];
                        for (int k = 0; k < 4; k++) {
                            int my = y + dy[k];
                            int mx = x + dx[k];
                            if (mx < 0 || my < 0 || n <= mx || n <= my) continue;
                            if (visited[my][mx]) continue;
                            if (graph[my][mx] == 0) continue;

                            queue.add(new int[]{my, mx});
                            visited[my][mx] = true;
                            groups[my][mx] = count;
                        }
                    }
                }
            }
        }

        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                if (groups[i][j] != 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int[][] counts = new int[n][n];
        while (!queue.isEmpty()) {
            int[] tmp = queue.remove();
            int y = tmp[0];
            int x = tmp[1];

            for (int i = 0; i < 4; i++) {
                int my = y + dy[i];
                int mx = x + dx[i];
                if (mx < 0 || my < 0 || n <= mx || n <= my) continue;
                if (graph[my][mx] != 0) continue;
                if (visited[my][mx]) continue;

                queue.add(new int[] {my, mx});
                visited[my][mx] = true;
                counts[my][mx] = counts[y][x] + 1;
                groups[my][mx] = groups[y][x];
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int my = i + dy[k];
                    int mx = j + dx[k];
                    if (mx < 0 || my < 0 || n <= mx || n <= my) continue;
                    if (groups[i][j] != groups[my][mx]) {
                        ret = Math.min(ret, counts[i][j] + counts[my][mx]);
                    }
                }
            }
        }

        System.out.println(ret);
    }
}
