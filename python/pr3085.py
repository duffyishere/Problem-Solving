import sys


input = sys.stdin.readline
n = int(input())
board = [list(input().rstrip()) for _ in range(n)]

def count_max_candy():
    max_cnt = 1
    for y in range(n):
        cnt = 1
        for moved_x in range(1, n):
            if board[y][moved_x] == board[y][moved_x - 1]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)
            
        cnt = 1
        for moved_y in range(1, n):
            if board[moved_y][y] == board[moved_y - 1][y]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)
    return max_cnt

result = 0
for y in range(n):
    for x in range(n):
        if x + 1 < n and board[y][x] != board[y][x + 1]:
            board[y][x], board[y][x + 1] = board[y][x + 1], board[y][x]
            result = max(result, count_max_candy())
            board[y][x], board[y][x + 1] = board[y][x + 1], board[y][x]
        if y + 1 < n and board[y][x] != board[y + 1][x]:
            board[y][x], board[y + 1][x] = board[y + 1][x], board[y][x]
            result = max(result, count_max_candy())
            board[y][x], board[y + 1][x] = board[y + 1][x], board[y][x]
print(result)
