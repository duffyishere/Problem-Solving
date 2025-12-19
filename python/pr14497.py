from collections import deque
import sys


def dec(x): 
    return x - 1

input = sys.stdin.readline
n, m = map(int, input().split())
start_y, start_x, target_y, tartget_x = map(dec, map(int, input().split()))
graph = [list(map(str, input().rstrip())) for _ in range(n)]

directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
queue = deque()
visited = [[0] * m for _ in range(n)]
queue.appendleft((start_y, start_x))
visited[start_y][start_x] = 1

time = 1
while queue:
    queue_size = len(queue)
    i = 0
    while i < queue_size:
        y, x = queue.pop()
        for dy, dx in directions:
            my = y + dy
            mx = x + dx

            if my < 0 or mx < 0 or n <= my or m <= mx:
                continue
            if visited[my][mx] != 0:
                continue

            visited[my][mx] = time
            if graph[my][mx] == '0':
                queue.append((my, mx))
                queue_size += 1
            else:
                queue.appendleft((my, mx))
        i += 1
    time += 1

print(visited[target_y][tartget_x])