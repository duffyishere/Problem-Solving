from collections import deque
import sys


MAX = 100001
N, K = map(int, sys.stdin.readline().split())

if N == K:
    print(0)
    print(1)
    sys.exit()

queue = deque()
visited = [0 for _ in range(MAX + 3)]
cnt = [0 for _ in range(MAX + 3)]
queue.appendleft(N)
visited[N] = 1
cnt[N] = 1

while queue:
    curr = queue.pop()
    for next in [curr * 2, curr + 1, curr - 1]:
        if next < 0 or MAX < next:
            continue
            
        if visited[next] == 0:
            queue.appendleft(next)
            visited[next] = visited[curr] + 1
            cnt[next] += cnt[curr]
        elif visited[next] == visited[curr] + 1:
            cnt[next] += cnt[curr]

print(visited[K] - 1)
print(cnt[K])