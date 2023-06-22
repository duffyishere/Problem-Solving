package org.duffy.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
