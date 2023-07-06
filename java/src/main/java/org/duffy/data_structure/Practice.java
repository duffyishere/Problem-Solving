package org.duffy.data_structure;

import java.io.*;
import java.util.Arrays;
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

    public void pr10799() throws IOException {
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

    public void pr17298() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        Integer[] result = new Integer[n];
        Integer[] num = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i=1; i<n; i++) {
            while (!stack.isEmpty() && num[i] > num[stack.peek()]) {
                result[stack.pop()] = num[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=0; i<n; i++) {
            bw.write(result[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }

    public void pr17299() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] result = new int[n];
        Integer[] num = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        int[] cnt = new int[1000001];
        for (int i=0; i<n; i++) {
            cnt[num[i]] += 1;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i=1; i<n; i++) {
            while (!stack.isEmpty() && cnt[num[stack.peek()]] < cnt[num[i]]) {
                result[stack.pop()] = num[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        StringBuffer sb = new StringBuffer();
        for (int r: result) {
            sb.append(r + " ");
        }
        System.out.println(sb);
    }
}
