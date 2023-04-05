package org.duffy.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Practice {

    public void pr17413() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine()+'\n';
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean flag = false;
        for(char ch: str.toCharArray()) {
            if (ch == '<' || flag) {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                flag = true;
                sb.append(ch);
                if (ch == '>') {
                    flag = false;
                    sb.append(ch);
                }
            }
            else {
                if (ch == ' ' || ch == '\n') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                }
                else {
                    stack.push(ch);
                }
            }
        }

        System.out.println(sb);
    }
}
