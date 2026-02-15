import sys


input = sys.stdin.readline
n = int(input().rstrip())
d = list([1000009] * (n + 1))
d[0], d[1] = 0, 0
prev = list([1000009] * (n + 1))
prev[0], prev[1] = 0, 0

for i in range(2, n + 1):
    flag = True
    if i % 3 == 0:
        if d[int(i / 3)] + 1 < d[i]:
            d[i] = d[int(i / 3)] + 1
            prev[i] = int(i / 3)
    if i % 2 == 0:
        if d[int(i / 2)] + 1 < d[i]:
            d[i] = d[int(i / 2)] + 1
            prev[i] = int(i / 2)
    if d[i - 1] + 1 < d[i]:
        d[i] = d[i - 1] + 1
        prev[i] = i - 1

print(d[n])
result = [n]
while 1 < n:
    result.append(prev[n])
    n = prev[n]
print(*result)