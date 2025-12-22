import sys


intput = sys.stdin.readline
n = int(input().rstrip())
mp, mf, ms, mv = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]

MAX = 7501
min_cost = MAX 
result = {}
for i in range(1 << n):
    tp, tf, ts, tv, tcost = 0, 0, 0, 0, 0
    tmp = []
    for j in range(n):
        if i & (1 << j):
            tmp.append(j + 1)
            p, f, s, v, cost = a[j]
            tp += p
            tf += f
            ts += s
            tv += v
            tcost += cost

    if mp <= tp and mf <= tf and ms <= ts and mv <= tv:
        if tcost <= min_cost:
            min_cost = tcost
            result.setdefault(min_cost, []).append(tmp)

if min_cost == MAX:
    print(-1)
    print("")
else:
    print(min_cost)
    result.get(min_cost).sort()
    print(" ".join(map(str, result[min_cost][0])))