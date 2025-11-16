from collections import deque
import sys

def pr18258():
    input = sys.stdin.readline
    queue = deque()
    line = int(input())
    out = []

    for __ in range(line):
        cmd = input().split()
        if cmd[0] == "push":
            queue.appendleft(cmd[1])
        elif cmd[0] == "pop":
            if queue:
                out.append(queue.pop())
            else:
                out.append("-1")
        elif cmd[0] == "size":
            out.append(str(len(queue)))
        elif cmd[0] == "empty":
            out.append("1" if not queue else "0")
        elif cmd[0] == "front":
            out.append(queue[-1] if queue else "-1")
        elif cmd[0] == "back":
            out.append(queue[0] if queue else "-1")
    sys.stdout.write("\n".join(out))

pr18258()