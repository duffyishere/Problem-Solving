import sys


input = sys.stdin.readline
n = int(input().rstrip())
mod = 10007
d = [0] * 10005
d[1] = 1
d[2] = 2

for i in range(3, n + 1):
    d[i] = d[i - 1] + d[i - 2]
    d[i] %= mod
print(d[n])