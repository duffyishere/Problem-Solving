package org.duffy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS {
    private int length, width;
    private int[][] visited;
    private int[][] grid;
    public int[] dx = new int[] {0, -1, 0, 1};
    public int[] dy = new int[] {1, 0, -1, 0};
    public Queue<Integer[]> queue = new LinkedList<>();

    public int height;
    public List<Integer[]> viruses = new ArrayList<>();

    public void pr14502() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        height = Integer.parseInt(buffer[0]);
        width = Integer.parseInt(buffer[1]);
        grid = new int[height][width];

        List<Integer[]> walls = new ArrayList<>();
        for (int i = 0; i <height; i++) {
            buffer = br.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                int num = Integer.parseInt(buffer[j]);
                grid[i][j] = num;
                if (num == 0) walls.add(new Integer[]{i, j});
                else if (num == 2) viruses.add(new Integer[]{i, j});
            }
        }

        // todo: 임의의 벽 3개를 세우는 코드
        int ret = 0;
        for (int i = 0; i < walls.size(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    grid[walls.get(i)[0]][walls.get(i)[1]] = 1;
                    grid[walls.get(j)[0]][walls.get(j)[1]] = 1;
                    grid[walls.get(k)[0]][walls.get(k)[1]] = 1;
                    ret = Math.max(ret, solve());
                    grid[walls.get(i)[0]][walls.get(i)[1]] = 0;
                    grid[walls.get(j)[0]][walls.get(j)[1]] = 0;
                    grid[walls.get(k)[0]][walls.get(k)[1]] = 0;
                }
            }
        }
        System.out.println(ret);
    }

    public int solve() {
        visited = new int[height][width];
        for (Integer[] virus: viruses) {
            visited[virus[0]][virus[1]] = 1;
            dfs(virus[0], virus[1]);
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0 && visited[i][j] == 0)
                    count++;
            }
        }
        return count;
    }

    public void pr2636() throws IOException {
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

        int timeCount = 0;
        int tmp = 0;
        while (true) {
            visited = new int[height][width];
            int edgeCount = dfs2636(0, 0);
            if (edgeCount == 0)
                break;
            visited[0][0] = 1;

            timeCount++;
            tmp = edgeCount;
        }
        System.out.println(timeCount);
        System.out.println(tmp);
    }

    public int dfs2636(int y, int x) {
        int edgeCount = 0;
        for (int i = 0; i < 4; i++) {
            int movedY = y + dy[i];
            int movedX = x + dx[i];

            if (movedY < 0 || movedX < 0 || height <= movedY || width <= movedX) continue;
            if (visited[movedY][movedX] == 1) continue;
            if (grid[movedY][movedX] == 1) {
                edgeCount++;
                grid[movedY][movedX] = 0;
                visited[movedY][movedX] = 1;
                continue;
            }

            visited[movedY][movedX] = 1;
            edgeCount+=dfs2636(movedY, movedX);
        }
        return edgeCount;
    }

    public List<Integer>[] tree;
    public int removedNode;
    public void pr1068() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        String[] buffer =  br.readLine().split(" ");
        int rootIndex = 0;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(buffer[i]);
            if (parent == -1) rootIndex = i;
            else tree[parent].add(i);
        }
        removedNode = Integer.parseInt(br.readLine());

        if (rootIndex == removedNode) {
            System.out.println(0);
            return;
        }
        System.out.println(dfs1068(rootIndex));
    }

    private int dfs1068(int there) {
        int ret = 0;
        int childrenCount = 0;
        for (int here: tree[there]) {
            if (here == removedNode) continue;
            ret += dfs1068(here);
            childrenCount++;
        }
        return childrenCount == 0? 1: ret;
    }

    public void dfs(int x, int y) {
        for (int i=0; i<4; i++) {
            int mx = dx[i] + x;
            int my = dy[i] + y;

            if (mx < 0 || my < 0 || length <= mx || width <= my) continue;
            if (visited[mx][my] > 0) continue;
            if (grid[mx][my] == 0) continue;

            visited[mx][my] = visited[x][y] + 1;
            dfs(mx, my);
        }
    }
}
