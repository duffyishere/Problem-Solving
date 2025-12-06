import sys


word = sys.stdin.readline().strip()
left, right = 0, len(word) - 1

while left <= right:
    if word[left] == word[right]:
        left += 1
        right -= 1
    else:
        print(0)
        sys.exit(0)
print(1)