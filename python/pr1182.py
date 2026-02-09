import sys


input = sys.stdin.readline
n, s = map(int, input().split())
nums = list(map(int, input().split()))
visited_total_idx = []

def recursive(total = 0, idx = 0):
    if idx == n:
        if total == s:
            return 1
        return 0
    
    result = 0
    result += recursive(total + nums[idx], idx + 1)
    result += recursive(total, idx + 1)
    return result
    

result = recursive()
if s == 0:
    result -= 1
print(result)