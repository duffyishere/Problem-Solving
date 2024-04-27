package org.duffy.etc;

import java.util.*;

public class Toss2023 {
    public int pr1(String s, int n) {
        int l = s.length();
        int res = -1;
        for (int i = 0; i < l - n; i++) {
            String sub = s.substring(i, i + n);
            if (isSequence(sub.toCharArray(), n)) {
                res = Math.max(res, Integer.parseInt(sub));
            }
        }
        return res;
    }

    private boolean isSequence(char[] chars, int n) {
        int[] cnt = new int[10];
        for (char ch: chars) {
            int num = Character.digit(ch, 10);
            if (n < num || 0 < cnt[num]) {
                return false;
            }
            cnt[num]++;
        }
        return true;
    }

    public int pr2(int[][] relationships, int target, int limit) {
        int n = 102;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] nums: relationships) {
            graph[nums[0]].add(nums[1]);
            graph[nums[1]].add(nums[0]);
        }

        int res = 0;
        int newFriendCount = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int depth = 1;
        visited.add(target);
        queue.offer(target);
        while (!queue.isEmpty()) {
            if (limit < depth) break;
            int l = queue.size();
            for (int i = 0; i < l; i++) {
                int now = queue.poll();
                for (int next: graph[now]) {
                    if (visited.contains(next)) continue;

                    if (depth <= 1) {
                        res += 5;
                    } else {
                        res += 10;
                        newFriendCount++;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
            depth++;
        }
        return res + newFriendCount;
    }
}
