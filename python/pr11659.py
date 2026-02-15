import sys


input = sys.stdin.readline
n, m = map(int, input().split())
nums = list(map(int, input().split()))
d = []
d.append(0)
d.append(nums[0])
for i in range(2, n + 1):
    d.append(d[i - 1] + nums[i - 1])
for _ in range(m):
    start, end = map(int, input().split())
    print(d[end] - d[start - 1])