import sys


input = sys.stdin.readline
N = int(input())
EXPRESSION = input().rstrip()

num, oper = [], []
for i in range(0, len(EXPRESSION)):
    if i % 2 == 0:
        num.append(int(EXPRESSION[i]))
    else:
        oper.append(str(EXPRESSION[i]))

def calc(operation: str, num1: int, num2: int):
    if operation == "*":
        return num1 * num2
    elif operation == "+":
        return num1 + num2
    else:
        return num1 - num2

result = float('-inf')
def go(idx, total):
    if len(num) - 1 == idx:
        global result
        result = max(result, total)
        return
    
    go(idx + 1, calc(oper[idx], total, num[idx + 1]))

    if idx + 2 <= len(num) - 1:
        tmp = calc(oper[idx + 1], num[idx + 1], num[idx + 2])
        go(idx + 2, calc(oper[idx], total, tmp))
    
    return

go(0, num[0])
print(result)