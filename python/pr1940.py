import sys


input = sys.stdin.readline
n = int(input().rstrip())
m = int(input().rstrip())
numbers = map(int, input().rstrip().split())
numbers = sorted(numbers)

result = 0
left, right = 0, n - 1
while left < right:
    if numbers[left] + numbers[right] < m:
        left += 1
    elif numbers[left] + numbers[right] > m:
        right -= 1
    else:
        result += 1
        left += 1
        right -= 1
print(result)