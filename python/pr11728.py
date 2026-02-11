import sys


input = sys.stdin.readline
n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a_idx, b_idx = 0, 0
result = []

while True:
    if a_idx == n:
        result.extend(b[b_idx:])
        break
    elif b_idx == m:
        result.extend(a[a_idx:])
        break

    if a[a_idx] < b[b_idx]:
        result.append(a[a_idx])
        a_idx += 1
    else:
        result.append(b[b_idx])
        b_idx += 1
print(*result)