package org.duffy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Graph {
    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public void pr13023(int n, int m) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean[][] a = new boolean[n][n];
        List<Integer>[] g = new ArrayList[n];
        List<Edge> edges = new ArrayList<>();
        for (int i=0; i<n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to));
            edges.add(new Edge(to, from));
            a[from][to] = a[to][from] = true;
            g[from].add(to);
            g[to].add(from);
        }

        m *= 2;
        for  (int i=0; i<m; i++) {
            for (int j=0; j<m; j++) {
                int A = edges.get(i).from;
                int B = edges.get(i).to;
                int C = edges.get(j).from;
                int D = edges.get(j).to;

                if (A == B || A == C || A == D || B == C || B == D || C == D) continue;
                if (!a[B][C]) continue;
                for (int E : g[D]) {
                    if (A == E || B == E || C == E || D == E) continue;
                    System.out.println(1);
                    System.exit(0);
                }
            }
        }
        System.out.println(0);
    }

    List<Integer>[] graph;
    boolean[] visited;
    int ret = 0;
    public void pr11724(int n) {
        for (int i = 1; i <= n - 1; i++) {
            if (visited[i] == false) {
                dfs11724(i);
                ret++;
            }
        }
        System.out.println(ret);
    }

    public void dfs11724(int start) {
        if (visited[start]) return;
        visited[start] = true;
        for (Integer next: graph[start]) {
            dfs11724(next);
        }
    }

    public void pr2667(List<Integer>[] graph, int n) {
        this.n = n;
        boolean[][] visited = new boolean[n][n];
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && graph[i].get(j) == 1) {
                    int count = dfs(graph, visited, i, j);
                    if (count == 1)
                        ret.add(1);
                    else
                        ret.add(count - 1);
                }
            }
        }

        Collections.sort(ret);
        System.out.println(ret.size());
        for (Integer i: ret) {
            System.out.println(i);
        }
    }
    public int n;
    public int[] dy = {1, 0, -1, 0};
    public int[] dx = {0, -1, 0, 1};
    private Integer dfs(List<Integer>[] graph, boolean[][] visited, int y, int x) {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int my = y + dy[i];
            int mx = x + dx[i];

            if (my < 0 || mx < 0 || n-1 < my || n-1 < mx) continue;
            if (visited[my][mx]) continue;
            if (graph[my].get(mx) == 0) continue;

            visited[my][mx] = true;
            count += dfs(graph, visited, my, mx);
        }

        return count;
    }
}
