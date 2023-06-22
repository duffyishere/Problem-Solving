package org.duffy.graph;

import java.util.*;

public class Challenge {
    public void pr16940(int n, List<Integer>[] graph, int[] sequence) {
        boolean[] check = new boolean[n];
        int[] parent = new int[n];
        Queue<Integer> workQueue = new LinkedList<>();
        workQueue.add(0);
        check[0] = true;
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
                if (check[next] == false) {
                    parent[next] = now;
                    count += 1;
                }
            }
            for (int j=0; j<count; j++) {
                if (startIndex+j >= n || parent[sequence[startIndex+j]] != now) {
                    System.out.println(0);
                    System.exit(0);
                }
                workQueue.add(sequence[startIndex+j]);
                check[sequence[startIndex+j]] = true;
            }
            startIndex += count;
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
}
