def pr1972A():
    for _ in range(int(input())):
        n = int(input())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))

        res, a_idx, b_idx = 0, 0, 0
        while a_idx < n and b_idx < n:
            if a[a_idx] <= b[b_idx]:
                a_idx += 1
                b_idx += 1
            else:
                res += 1
                b_idx += 1
        print(res)

def pr1972B():
    for _ in range(int(input())):
        _ = input()
        coins = input()
        cnt = 0
        for c in coins:
            if c == "U":
                cnt += 1
        
        if cnt % 2 == 0:
            print("NO")
        else:
            print("YES")