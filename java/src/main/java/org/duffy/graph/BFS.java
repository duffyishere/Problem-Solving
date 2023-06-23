package org.duffy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    public void pr7576(int h, int w, int[][]graph, List<Integer[]> tomatoes) {
        Queue<Integer[]> workQueue = new LinkedList<>();
        int[][] visited = new int[h][w];
        for (Integer[] tomato: tomatoes) {
            workQueue.add(tomato);
            visited[tomato[0]][tomato[1]] = 1;
        }

        while (workQueue.size() > 0) {
            Integer[] work = workQueue.poll();
            int y = work[0];
            int x = work[1];
            for (int i = 0; i < 4; i++) {
                int my = y + dy[i];
                int mx = x + dx[i];
                if (0 <= my && my < h && 0 <= mx && mx < w) {
                    if (visited[my][mx] == 0 && graph[my][mx] == 0) {
                        visited[my][mx] = visited[y][x] + 1;
                        graph[my][mx] = 1;
                        workQueue.add(new Integer[]{my, mx});
                    }
                }
            }
        }

        boolean flag = false;
        int ret = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                ret = Math.max(visited[i][j], ret);
                if (visited[i][j] == 0 && graph[i][j] != -1) flag = true;
            }
        }
        System.out.println(flag? -1: ret - 1);
    }

    public void pr1679(int n, int k) {
        final int MAX = 100001;
        int[] visited = new int[MAX];
        Queue<Integer> workQueue = new LinkedList<>();

        visited[n] = 1;
        workQueue.add(n);
        while (!workQueue.isEmpty()) {
            int now = workQueue.remove();

            if (0 <= now - 1) {
                if (visited[now - 1] < 1) {
                    visited[now - 1] = visited[now] + 1;
                    workQueue.add(now - 1);
                }
            }
            if (now + 1 < MAX) {
                if (visited[now + 1] < 1) {
                    visited[now + 1] = visited[now] + 1;
                    workQueue.add(now + 1);
                }
            }
            if (now * 2 < MAX) {
                if (visited[now * 2] < 1) {
                    visited[now * 2] = visited[now] + 1;
                    workQueue.add(now * 2);
                }
            }
        }
        System.out.println(visited[k] - 1);
    }

    public void pr13913(int n, int k) {
        final int MAX = 1000001;
        int[] visited = new int[MAX];
        int[] from = new int[MAX];
        Queue<Integer> visitQueue = new LinkedList<>();

        visitQueue.add(n);
        visited[n] = 1;
        while (!visitQueue.isEmpty()) {
            if (visited[k] != 0)
                break;

            int now = visitQueue.remove();
            if (0 <= now - 1) {
                if (visited[now - 1] < 1) {
                    visited[now - 1] = visited[now] + 1;
                    visitQueue.add(now - 1);
                    from[now - 1] = now;
                }
            }
            if (now + 1 < MAX) {
                if (visited[now + 1] < 1) {
                    visited[now + 1] =  visited[now] + 1;
                    visitQueue.add(now + 1);
                    from[now + 1] = now;
                }
            }
            if (now * 2 < MAX) {
                if (visited[now * 2] < 1) {
                    visited[now * 2] = visited[now] + 1;
                    visitQueue.add(now * 2);
                    from[now * 2] = now;
                }
            }
        }


        Stack<Integer> ret = new Stack<>();
        for (int i = k; i != n; i = from[i]) {
            ret.push(i);
        }
        ret.push(n);

        StringBuilder sb = new StringBuilder();
        while (!ret.isEmpty()) {
            sb.append(ret.pop() + " ");
        }

        System.out.println(visited[k] - 1);
        System.out.println(sb);
    }
}
