import sys


input = sys.stdin.readline
n, m = map(int, input().split())
cell = [list(map(int, input().split())) for _ in range(n)]
selected_chickens = []
def combinations(startIdx: int, arr: list, selected: list = []):
    if m == len(selected):
        selected_chickens.append(selected.copy())
        return
    
    for i in range(startIdx + 1, len(arr)):
        selected.append(arr[i])
        combinations(i, arr, selected)
        selected.pop()

chicken = []
home = []
for y in range(0, n):
    for x in range(0, n):
        if cell[y][x] == 1:
            home.append([y, x])
        elif cell[y][x] == 2:
            chicken.append([y, x])

result = float('inf')
combinations(-1, chicken, [])
for selected in selected_chickens:
    total_distance = 0
    for home_y, home_x in home:
        min_distance = float('inf')
        for chicken_y, chicken_x in selected:
            distance = abs(home_y - chicken_y) + abs(home_x - chicken_x)
            min_distance = min(min_distance, distance)
        total_distance += min_distance
    result = min(result, total_distance)

print(result)