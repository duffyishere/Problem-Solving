import sys


input = sys.stdin.readline
result = 0
for _ in range(int(input())):
    word = input().rstrip()
    stack = []
    for i in range(0, len(word)):
        if not stack:
            stack.append(word[i])
        elif stack[-1] == word[i]:
            stack.pop()
        else:
            stack.append(word[i])

    if not stack:
        result += 1
print(result)