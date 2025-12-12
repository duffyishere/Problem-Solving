from collections import deque
import sys


input = sys.stdin.readline
N, M = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(N)]

MAX = 2147483647
fire_visited = [[MAX] * M for _ in range(N)]
human_visited = [[0] * M for _ in range(N)]

directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
queue = deque()

for y in range(N):
    for x in range(M):
        if graph[y][x] == 'F':
            fire_visited[y][x] = 1
            queue.appendleft((y, x))
        elif graph[y][x] == 'J':
            hy = y
            hx = x

while queue:
    y, x = queue.pop()
    for dy, dx in directions:
        my = y + dy
        mx = x + dx
        if my < 0 or mx < 0 or N <= my or M <= mx:
            continue
        if fire_visited[my][mx] != MAX:
            continue
        if graph[my][mx] == "#":
            continue
        fire_visited[my][mx] = fire_visited[y][x] + 1
        queue.appendleft((my, mx))

human_visited[hy][hx] = 1
queue.append((hy, hx))
result = 0
while queue:
    y, x = queue.pop()
    if y == 0 or x == 0 or y == N - 1 or x == M -1:
        result = human_visited[y][x]
        break
    
    for dy, dx in directions:
        my = y + dy
        mx = x + dx
        if my < 0 or mx < 0 or N <= my or M <= mx:
            continue
        if human_visited[my][mx] != 0:
            continue
        if graph[my][mx] == "#":
            continue
        if fire_visited[my][mx] <= human_visited[y][x]:
            continue
        human_visited[my][mx] = human_visited[y][x] + 1
        queue.appendleft((my, mx))

if result != 0:
    print(result)
else:
    print("IMPOSSIBLE")