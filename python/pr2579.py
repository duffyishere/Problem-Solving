import sys


input = sys.stdin.readline
n = int(input())
stairs = [int(input()) for _ in range(n)]
d = [list([0] * 2) for _ in range(n + 1)]

def recursive(idx):
    if n < idx:
        return

    d[idx][0] = max(d[idx - 2][0], d[idx - 2][1]) + stairs[idx - 1]
    d[idx][1] = d[idx - 1][0] + stairs[idx - 1]
    recursive(idx + 1)

d[0][0], d[0][1] = 0, 0
d[1][0], d[1][1] = stairs[0], stairs[0]
recursive(2)
print(max(d[n][0], d[n][1]))