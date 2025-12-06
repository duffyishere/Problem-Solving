import sys


input = sys.stdin.readline
a, b, c = map(int, input().split())
b = b * 2
c = c * 3
array: int = [0 for _ in range(101)]

for _ in range(3):
    start, end = map(int, input().split())
    for i in range(start, end):
        if array[i] == 0:
            array[i] = a
        elif array[i] == a:
            array[i] = b
        elif array[i] == b:
            array[i] = c

result: int = 0
for price in array:
    if price != 0:
        result += price
print(result)
