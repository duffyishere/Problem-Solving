import sys


input = sys.stdin.readline
n = int(input().rstrip())
graph = [list(map(str, input().rstrip())) for _ in range(n)]

values = []
for line in graph:
    line_value = 0
    value = 1
    for char  in line:
        if char == "T":
            line_value += value
        value *= 2
    values.append(line_value) 

result = float('inf')
def go(idx):
    global result
    if idx == n:
        total = 0
        i = 1
        while i <= (1 << (n - 1)):
            cnt = 0
            for j in range(0, n):
                if values[j] & i:
                    cnt += 1
            total += min(cnt, n - cnt)
            i *= 2
        result = min(result, total)
        return
            
    go(idx + 1)
    values[idx] = ~values[idx]
    go(idx + 1)

go(0)
print(result)