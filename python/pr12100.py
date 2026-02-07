import copy
import sys


def main():
    def merge_left():
        nonlocal graph
        for y in range(n):
            idx = 0
            new_row = [0] * n
            merged = [False] * n
            for x in range(n):
                if graph[y][x] == 0:
                    continue
                if new_row[0] == 0:
                    new_row[0] = graph[y][x]
                    continue

                if new_row[idx] == graph[y][x] and merged[idx] is False:
                    new_row[idx] *= 2
                    merged[idx] = True
                else:
                    idx += 1
                    new_row[idx] = graph[y][x]
            graph[y] = new_row

    def merge_right():
        nonlocal graph
        for y in reversed(range(n)):
            idx = n - 1
            new_row = [0] * n
            merged = [False] * n
            for x in reversed(range(n)):
                if graph[y][x] == 0:
                    continue
                if new_row[n - 1] == 0:
                    new_row[n - 1] = graph[y][x]
                    continue

                if new_row[idx] == graph[y][x] and merged[idx] is False:
                    new_row[idx] *= 2
                    merged[idx] = True
                else:
                    idx -= 1
                    new_row[idx] = graph[y][x]
            graph[y] = new_row
    
    def merge_bottom():
        nonlocal graph
        for x in range(n):
            idx = 0
            new_col = [0] * n
            merged = [False] * n
            for y in range(n):
                if graph[y][x] == 0:
                    continue
                if new_col[0] == 0:
                    new_col[0] = graph[y][x]
                    continue

                if new_col[idx] == graph[y][x] and merged[idx] is False:
                    new_col[idx] *= 2
                    merged[idx] = True
                else:
                    idx += 1
                    new_col[idx] = graph[y][x]

            for y in range(n):
                graph[y][x] = new_col[y]

    def merge_top():
        nonlocal graph
        for x in reversed(range(n)):
            idx = n - 1
            new_col = [0] * n
            merged = [False] * n
            for y in reversed(range(n)):
                if graph[y][x] == 0:
                    continue
                if new_col[n - 1] == 0:
                    new_col[n - 1] = graph[y][x]
                    continue

                if new_col[idx] == graph[y][x] and merged[idx] is False:
                    new_col[idx] *= 2
                    merged[idx] = True
                else:
                    idx -= 1
                    new_col[idx] = graph[y][x]

            for y in reversed(range(n)):
                graph[y][x] = new_col[y]

    def tilt(depth) -> int:
        nonlocal graph
        if depth == 5:
            max_value = 0
            for y in range(n):
                for x in range(n):
                    max_value = max(graph[y][x], max_value)
            return max_value

        result = 0
        original_graph = copy.deepcopy(graph)
        for merge in [merge_top,  merge_right, merge_bottom, merge_left]:
            merge()
            result = max(result, tilt(depth + 1))
            graph = copy.deepcopy(original_graph)

        return result


    input = sys.stdin.readline
    n = int(input().rstrip())
    graph = [list(map(int, input().split())) for _ in range(n)]
    print(tilt(0))

if __name__ == "__main__":
    main()