import sys


def rotate(sticker: list):
    n, m = len(sticker), len(sticker[0])
    res = [[0] * n for _ in range(m)]

    for i in range(n):
        for j in range(m):
            res[j][n - 1 - i] = sticker[i][j]
    return res

def can_paste(y, x, graph, sticker):
    row, col = len(sticker), len(sticker[0])
    for i in range(row):
        for j in range(col):
            if graph[y + i][x + j] == 1 and sticker[i][j] == 1:
                return False
    return True

def paste(y, x, graph, sticker):
    row, col = len(sticker), len(sticker[0])
    for i in range(row):
        for j in range(col):
            if sticker[i][j] == 1:
                graph[y + i][x + j] = 1

input = sys.stdin.readline
n, m, k = map(int, input().split())
graph = [[0] * m for _ in range(n)]
for _ in range(k):
    row, col = map(int, input().split())
    sticker = [list(map(int, input().split())) for _ in range(row)]

    for _ in range(4):
        already_paste = False
        for y in range(n - row + 1):
            if already_paste: break
            for x in range(m - col + 1):
                if can_paste(y, x, graph, sticker):
                    paste(y, x, graph, sticker)                    
                    already_paste = True
                    break

        if already_paste: break
        sticker = rotate(sticker)
        row, col = len(sticker), len(sticker[0])
cnt = 0
for i in range(n):
    for j in range(m):
        cnt += graph[i][j]
print(cnt)