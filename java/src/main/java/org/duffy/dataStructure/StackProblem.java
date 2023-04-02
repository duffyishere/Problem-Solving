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
}
