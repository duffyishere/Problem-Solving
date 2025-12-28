from collections import deque
import sys


input = sys.stdin.readline
n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * m for _ in range(n)]
directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
cctv = []
res = 0
for i in range(n):
    for j in range(m):
        if 0 < graph[i][j] < 6:
            cctv.append((i, j, graph[i][j]))
        if graph[i][j] == 0:
            res += 1

def update(y, x, dir):
    dir %= 4
    dy, dx = directions[dir]
    while True:
        y = y + dy
        x = x + dx

        if y < 0 or x < 0 or n <= y or m <= x or visited[y][x] == 6:
            return
        if visited[y][x] != 0:
            continue
        visited[y][x] = 7

for tmp in range(1 << (2 * len(cctv))):
    for i in range(n):
        for j in range(m):
            visited[i][j] = graph[i][j]

    brute = tmp
    for i in range(len(cctv)):
        dir = brute % 4
        brute //= 4
        y, x, num = cctv[i]

        if num == 1:
            update(y, x, dir)
        elif num == 2:
            update(y, x, dir)
            update(y, x, dir + 2)
        elif num == 3:
            update(y, x, dir)
            update(y, x, dir + 1)
        elif num == 4:
            update(y, x, dir)
            update(y, x, dir + 1)
            update(y, x, dir + 2)
        else:
            update(y, x, dir)
            update(y, x, dir + 1)
            update(y, x, dir + 2)
            update(y, x, dir + 3)

    val = 0
    for i in range(n):
        for j in range(m):
            if visited[i][j] == 0:
                val += 1

    res = min(res, val)
print(res)