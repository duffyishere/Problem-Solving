package org.duffy.dataStructure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/{p_id}
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

    public void pr1874() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        List<Integer> arr = new ArrayList<>();
        while (t-- > 0) {
            arr.add(Integer.valueOf(br.readLine()));
        }

        this.pr1874Method(arr);
    }

    private void pr1874Method(List<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int input : arr) {
            if (max < input) {
                while (max < input) {
                    stack.push(++max);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else {
                if (stack.peek() == input) {
                    stack.pop();
                    sb.append("-\n");
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println(sb);
    }

    public void pr1406() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 커서 기준 왼쪽
        Stack<Character> l = new Stack<>();
        // 커서 기준 오른쪽
        Stack<Character> r = new Stack<>();
        for (char ch: br.readLine().toCharArray()) {
            l.push(ch);
        }
        int m  = Integer.valueOf(br.readLine());
        while (m-- > 0) {
            String cmd = br.readLine();
            StringTokenizer stk = new StringTokenizer(cmd);
            switch (stk.nextToken()) {
                case "L":
                    if (!l.isEmpty())
                        r.push(l.pop());
                    break;
                case "D":
                    if (!r.isEmpty())
                        l.push(r.pop());
                    break;
                case "B":
                    if (!l.isEmpty())
                        l.pop();
                    break;
                case "P":
                    l.push(stk.nextToken().charAt(0));
                    break;
            }
        }

        while (!l.isEmpty()) {
            r.push(l.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!r.isEmpty()) {
            sb.append(r.pop());
        }

        System.out.println(sb);
    }
}
