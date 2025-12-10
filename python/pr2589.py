from collections import deque
import sys


input = sys.stdin.readline
n, m = map(int, input().split())
cell = [list(input().rstrip()) for _ in range(n)]

def bfs(start_y, start_x):
    directions = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    visited = [[False] * m for _ in range(n)]
    queue = deque()
    queue.append([start_y, start_x])
    visited[start_y][start_x] = 0

    max_distance = 0

    while queue:
        y, x = queue.popleft()
        for d_y, d_x in directions:
            moved_y = y + d_y
            moved_x = x + d_x

            if moved_x < 0 or moved_y < 0 or m <= moved_x or n <= moved_y:
                continue
            if visited[moved_y][moved_x]:
                continue
            if cell[moved_y][moved_x] == 'W':
                continue
            
            visited[moved_y][moved_x] = visited[y][x] + 1
            max_distance = visited[moved_y][moved_x]
            queue.append([moved_y, moved_x])
    return max_distance


result = 0
for y in range(n):
    for x in range(m):
        if cell[y][x] == 'L':
            result = max(result, bfs(y, x))
            
print(result)