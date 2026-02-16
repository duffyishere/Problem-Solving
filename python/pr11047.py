import sys


input = sys.stdin.readline
n, k = map(int, input().split())
coins = list(int(input().rstrip()) for _ in range(n))
idx = n - 1
cnt = 0

while k != 0:
    while coins[idx] <= k:
        k -= coins[idx]
        cnt += 1
    idx -= 1
print(cnt)