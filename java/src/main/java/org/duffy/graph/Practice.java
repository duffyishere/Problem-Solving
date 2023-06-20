package org.duffy.graph;

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
}
