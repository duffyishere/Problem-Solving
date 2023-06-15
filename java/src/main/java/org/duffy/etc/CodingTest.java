package org.duffy.etc;

import java.util.HashMap;
import java.util.Map;

public class CodingTest {
    public long[] solution(int n, int m, int k, int[][] acts) {
        long[] ret = new long[k];
        int[][] grid = new int[n][m];

        for (int[] act: acts) {
            int index = act[1];
            int color = act[2];
            if (act[0] == 1) {
                for (int i = 0; i < m; i++) {
                    grid[index][i] = color;
                }
            }
            else {
                for (int i = 0; i < n; i++) {
                    grid[i][index] = color;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[grid[i][j]-1]++;
            }
        }

        long[] newRet = new long[ret.length - 1];
        System.arraycopy(ret, 1, newRet, 0, newRet.length);
        return newRet;
    }

    public long[] solution2(int n, int m, int k, int[][] acts) {
        long[] answer = new long[k];
        Map<Integer, Color> rowCount = new HashMap<>(); // 색과 나온 횟수, 문제는 중복된 색깔을 다시 칠 할 수 있음.
        Map<Integer, Color> columnCount = new HashMap<>();
        for (int[] act: acts) {
            int type = act[0];
            int index = act[1]-1;
            int color = act[2];

            if (type == 1) {
                if (rowCount.get(color) == null) {
                    rowCount.put(color, new Color(1, color, index));
                }
                else {
                    if (rowCount.get(color).row == index) {
                        answer[rowCount.get(color).k] -= m;
//                        rowCount
                        rowCount.get(color).increaseCount(index);
                    }
                }
                for (Color c: columnCount.values()) {
                    if (answer[c.k-1] > 0)
                        answer[c.k-1] -= c.count;
                }
                answer[color-1] += m;
            }
            else {
                if (columnCount.get(color) == null) {
                    columnCount.put(color, new Color(1, color, index));
                }
                else {
                    if (columnCount.get(color).row == index)
                        answer[columnCount.get(color).k] -= n;
                    columnCount.get(color).increaseCount(index);
                }
                for (Color c: rowCount.values()) {
                    if (answer[c.k-1] > 0)
                        answer[c.k-1] -= c.count;
                }
                answer[color-1] += n;
            }
        }

        return answer;
    }

    public class Color {
        int count;
        int k;
        int row;

        public Color(int count, int k, int row) {
            this.count = count;
            this.k = k;
            this.row = row;
        }
        public void increaseCount(int row) {
            count++;
        }
    }
    public int solution1(int mod1, int mod2, int max_range) {
        int answer = 0;
        for (int i = 1; i <= max_range; i++) {
            if (i%mod1==0 && i%mod2!=0)
                answer++;
        }

        return answer;
    }
}
