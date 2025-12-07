import sys


for line in sys.stdin.readlines():
    num = int(line.strip()) 
    len = 1
    current = 1

    while current % num != 0:
        current = current * 10 + 1
        len += 1
    
    print(len)