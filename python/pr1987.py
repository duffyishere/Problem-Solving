from collections import deque
import sys


def main():
    input = sys.stdin.readline
    n, m = map(int, input().split())
    graph = [input().rstrip() for _ in range(n)]

    directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    result = 1

    def dfs(y, x, cnt):
        nonlocal visited, result
        result = max(result, cnt)
        for dy, dx in directions:
            my = y + dy
            mx = x + dx
            if my < 0 or mx < 0 or n <= my or m <= mx:
                continue

            next_word = ord(graph[my][mx]) - 65
            if visited[next_word]:
                continue

            visited[next_word] = True
            dfs(my, mx, cnt + 1)
            visited[next_word] = False

    visited = [False] * 26
    visited[ord(graph[0][0]) - 65] = True
    dfs(0, 0, 1)
    print(result)
if __name__ == "__main__":
    main()