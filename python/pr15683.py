import copy
import sys


input = sys.stdin.readline
n, m = map(int, input().split())
graph = []
cctv_positions = []
for i in range(n):
    row = list(map(int, input().split()))
    graph.append(row)
    for j in range(m):
        if 0 < row[j] < 6: cctv_positions.append((i, j))

directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
def watch(y, x, dir, graph):
    dy, dx = directions[dir]
    my, mx = y + dy, x + dx

    while True:
        if my < 0 or mx < 0 or n <= my or m <= mx:
            break
        if graph[my][mx] == 6:
            break
        if graph[my][mx] == 0:
            graph[my][mx] = 7
        my += dy
        mx += dx

def recursive(idx, graph):
    if idx == len(cctv_positions):
        cnt = 0
        for i in range(n):
            for j in range(m):
                if graph[i][j] == 0: cnt += 1
        return cnt
    
    result = 100
    cy, cx = cctv_positions[idx]
    cctv_type = graph[cy][cx]
    if cctv_type == 1:
        for dir in range(4):
            copy_graph = copy.deepcopy(graph)
            watch(cy, cx, dir, copy_graph)
            result = min(result, recursive(idx + 1, copy_graph))
    elif cctv_type == 2:
        for dir in range(2):
            copy_graph = copy.deepcopy(graph)
            watch(cy, cx, dir, copy_graph)
            watch(cy, cx, dir + 2, copy_graph)
            result = min(result, recursive(idx + 1, copy_graph))
    elif cctv_type == 3:
        for dir in range(4):
            copy_graph = copy.deepcopy(graph)
            watch(cy, cx, dir, copy_graph)
            watch(cy, cx, (dir + 1) % 4, copy_graph)
            result = min(result, recursive(idx + 1, copy_graph))
    elif cctv_type == 4:
        for dir in range(4):
            copy_graph = copy.deepcopy(graph)
            watch(cy, cx, dir, copy_graph)
            watch(cy, cx, (dir + 1) % 4, copy_graph)
            watch(cy, cx, (dir + 3) % 4, copy_graph)
            result = min(result, recursive(idx + 1, copy_graph))
    elif cctv_type == 5:
        copy_graph = copy.deepcopy(graph)
        watch(cy, cx, 0, copy_graph)
        watch(cy, cx, 1, copy_graph)
        watch(cy, cx, 2, copy_graph)
        watch(cy, cx, 3, copy_graph)
        result = min(result, recursive(idx + 1, copy_graph))

    return result

print(recursive(0, graph))