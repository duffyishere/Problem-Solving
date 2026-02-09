import sys


input = sys.stdin.readline
n = int(input().rstrip())

def recursive(curr, check1: list, check2: list, check3: list):
    if curr == n:
        return 1
    
    total = 0
    for i in range(n):
        if check1[i]:
            continue
        if check2[i + curr]:
            continue
        if check3[i - curr + n -1]:
            continue

        check1[i] = True
        check2[i + curr] = True
        check3[i - curr + n -1] = True
        total += recursive(curr + 1, check1, check2, check3)
        check1[i] = False
        check2[i + curr] = False
        check3[i - curr + n -1] = False
    return total

print(recursive(0, [False] * n, [False] * 40, [False] * 40))