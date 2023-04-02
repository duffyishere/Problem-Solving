package org.duffy.dataStructure;

import java.io.*;
import java.util.Stack;

public class StackProblem {
    public void pr9093() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        while (t-- > 0) {
            String line = reader.readLine() + "\n";
            Stack<Character> stack = new Stack<>();
            for (char chr: line.toCharArray()) {
                if (chr == ' ' || chr == '\n') {
                    while (!stack.isEmpty()) {
                        writer.write(stack.pop());
                    }
                    writer.write(chr);
                }
                else {
                    stack.push(chr);
                }
            }
        }
        writer.flush();
    }

    public void pr2012() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());

        loop:
        while (t-- > 0) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            // NO가 나오는 경우: 1.스택에서 pop 할 게 없음 2.혹은 모든 문자를 돌리고 남은 스택이 있을 경우
            for (char ch: str.toCharArray()) {
                if (ch == '(') {
                    stack.push('1');
                }
                else {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    else {
                        System.out.println("NO");
                        continue loop;
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
