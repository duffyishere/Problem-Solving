package org.duffy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
}
