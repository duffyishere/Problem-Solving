import sys


input = sys.stdin.readline
n, m = map(int, input().split())

def combi(arr: list):
    if len(arr) == m:
        print(*arr)
        return
    
    for i in range(1, n + 1):
        if i in arr: continue

        combi(arr + [i])

combi([])