from collections import Counter
import sys


char_cnt = Counter(sys.stdin.readline().rstrip())
center = ""
except_cnt = 0
for k, v in char_cnt.items():
    if v & 1:
        center = str(k)
        except_cnt += 1
        if 1 < except_cnt:
            print("I'm Sorry Hansoo")
            sys.exit()
    
result = ""
for k, v in sorted(char_cnt.items()):
    result += (k * (v // 2))
print(result + center + result[::-1])
