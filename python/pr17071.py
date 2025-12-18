from collections import deque
import sys


MAX = 500000
n, k = map(int, sys.stdin.readline().split())

if n == k:
    print(0)
    sys.exit()

queue = deque()
visited = [[0 for _ in range(MAX + 2)] for _ in range(2)]
queue.appendleft(n)
visited[0][n] = 1
time = 1
flag = False
while queue:
    k += time
    if MAX < k:
        break
    if visited[time % 2][k]:
        flag = True
        break
    
    queue_size = len(queue)
    for i in range(queue_size):
        now = queue.pop()
        for next in [now * 2, now + 1, now - 1]:
            if next < 0 or MAX < next or visited[time % 2][next] != 0:
                continue

            visited[time % 2][next] = visited[(time + 1) % 2][now] + 1
            if next == k:
                flag = True
                print(time)
                sys.exit()
            queue.appendleft(next)
    time += 1

if flag:
    print(time)
else:
    print(-1)