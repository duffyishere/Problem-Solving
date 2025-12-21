import sys


def main():
    input = sys.stdin.readline
    n = int(input().rstrip())
    a = list(map(str, input().split()))
    max_result = "0"
    min_result = "9999999999"

    def comp(oper: str, num1: int, num2: int):
        if oper == "<":
            return num1 < num2
        else:
            return num1 > num2

    def go(idx: int, num: list):
        nonlocal max_result, min_result
        if n == idx:
            res = "".join(map(str, num))
            max_result = max(max_result, res)
            min_result = min(min_result, res)
            return
        
        for next in range(10):
            if next in num:
                continue
            if not comp(a[idx], num[idx], next):
                continue
            num.append(next)
            go(idx + 1, num)
            num.pop()

    for i in range(10):
        go(0, [i])

    print(max_result)
    print(min_result)

if __name__ == "__main__":
    main()