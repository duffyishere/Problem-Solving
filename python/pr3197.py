from collections import deque
import sys


def main():
    input = sys.stdin.readline
    n, m = map(int, input().split())
    graph = []

    swan_y, swan_x = 0, 0
    swan_queue = deque()
    swan_visited = [[0] * m for _ in range(n)] 
    swan_tmp = deque()
    water_queue = deque()
    water_tmp = deque()
    water_visited = [[0] * m for _ in range(n)]
    for i in range(n):
        row = list(input().rstrip())
        graph.append(row)
        for j in range(m):
            if graph[i][j] == "." or graph[i][j] == "L":
                water_queue.appendleft((i, j))
                water_visited[i][j] = 1 
            if graph[i][j] == "L":
                swan_y = i
                swan_x = j

    swan_queue.append((swan_y, swan_x))
    swan_visited[swan_y][swan_x] = 1

    time = 0
    directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    while True:
        # move swan
        while swan_queue:
            y, x = swan_queue.pop()
            for dy, dx in directions:
                my = y + dy
                mx = x + dx
                if my < 0 or mx < 0 or n <= my or m <= mx:
                    continue
                if swan_visited[my][mx] != 0:
                    continue

                swan_visited[my][mx] = 1
                if graph[my][mx] == ".":
                    swan_queue.append((my, mx))
                elif graph[my][mx] == "X":
                    swan_tmp.append((my, mx))
                elif graph[my][mx] == "L":
                    print(time)
                    sys.exit()

        # melt water
        while water_queue:
            y, x = water_queue.pop()
            for dy, dx in directions:
                my = y + dy
                mx = x + dx
                if my < 0 or mx < 0 or n <= my or m <= mx:
                    continue
                if water_visited[my][mx] != 0:
                    continue 
                if graph[my][mx] == 'X':
                    water_tmp.append((my, mx))
                    water_visited[my][mx] = time
                    graph[my][mx] = "."

        swan_queue = swan_tmp
        water_queue =  water_tmp
        swan_tmp = deque()
        water_tmp = deque()
        time += 1

if __name__ == "__main__":
    main()