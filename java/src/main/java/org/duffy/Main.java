package org.duffy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int result = 0;
        int cnt = 0;
        Stack<Character> stack = new Stack<>();
        for (char ch: str.toCharArray()) {
            // 레이저
            if (ch == ')' && stack.peek() == '(') {
                if (cnt > 0)
                    cnt--;
                result += cnt;
            }
            // 막대기 시작
            else if (ch == '(') {
                cnt++;
            }
            // 막대기 끝
            else if (ch == ')' && stack.peek() == ')') {
                cnt--;
                result++;
            }

            stack.push(ch);
        }

        System.out.println(result);
    }
}