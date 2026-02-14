import sys


input = sys.stdin.readline
n = int(input())
prices = [list(map(int, input().split())) for _ in range(n)]
d = [list([1000010] * 3) for _ in range(n)]
d[0] = prices[0]

# d[idx][0] = min(d[idx - 1][1], d[idx - 1][2]) + price[idx][0]
for i in range(1, n):
    d[i][0] = min(d[i - 1][1], d[i - 1][2]) + prices[i][0]
    d[i][1] = min(d[i - 1][0], d[i - 1][2]) + prices[i][1]
    d[i][2] = min(d[i - 1][1], d[i - 1][0]) + prices[i][2]

print(min(d[n - 1]))