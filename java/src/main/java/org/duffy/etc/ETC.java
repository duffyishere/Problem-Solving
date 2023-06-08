package org.duffy.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class ETC {

    public void pr2910() throws IOException {
        class ZippedNumber implements Comparable<ZippedNumber> {

            private int num;
            private int count;
            private int firstIndex;

            public ZippedNumber(int num, int count, int firstIndex) {
                this.num = num;
                this.count = count;
                this.firstIndex = firstIndex;
            }

            public int getNum() { return num; }
            public int getCount() { return count; }

            public void addCount() { count++; }

            @Override
            public int compareTo(ZippedNumber o) {
                if (this.count == o.count)
                    return Integer.compare(this.firstIndex, o.firstIndex);
                else
                    return -Integer.compare(this.count, o.count);
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        int n = Integer.parseInt(buffer[0]);
        int c = Integer.parseInt(buffer[1]);

        Map<Integer, ZippedNumber> zippedNums = new HashMap<>();
        buffer = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(buffer[i]);
            if (zippedNums.get(num) == null)
                zippedNums.put(num, new ZippedNumber(num, 1, i));
            else
                zippedNums.get(num).addCount();
        }

        List<ZippedNumber> zippedNumberList = new ArrayList<>(zippedNums.values());
        Collections.sort(zippedNumberList);

        StringBuilder sb = new StringBuilder();
        for (ZippedNumber zippedNum : zippedNumberList) {
            for(int i = 0 ; i < zippedNum.getCount(); i++){
                sb.append(zippedNum.getNum() + " ");
            }
        }

        System.out.println(sb);
    }

    public List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");

    public List<String> numerics = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

    public void pr4659() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = br.readLine()).equals("end")) {
            String[] strings = input.split("");
            boolean flag = true;
            int count = 0;
            int vowelCount = 0;
            int consonantCount = 0;
            for (int i=0; i<strings.length; i++) {
                String str = strings[i];
                if (isVowel(str)) {
                    vowelCount++;
                    count++;
                    consonantCount = 0;
                }
                else {
                    consonantCount++;
                    vowelCount = 0;
                }

                if (3 <= vowelCount || 3 <= consonantCount) {
                    flag = false;
                    break;
                }
                if (i != strings.length-1 && strings[i].equals(strings[i+1])) {
                    if (strings[i].equals("e") && strings[i+1].equals("e") || strings[i].equals("o") && strings[i+1].equals("o"))
                        break;
                    flag = false;
                    break;
                }
            }
            if (!flag || count < 1)
                System.out.println("<"+input+"> is not acceptable.");
            else
                System.out.println("<"+input+"> is acceptable.");
        }
    }

    private boolean isVowel(String str) {
        return vowels.contains(str);
    }

    public List<String> numerics = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

    public void pr2870() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<BigDecimal> nums = new ArrayList<>();
        while (n-- > 0) {
            String[] strings = br.readLine().split("");
            Stack<BigDecimal> tmp = new Stack<>();
            boolean flag = false;
            for (int i = 0; i <strings.length; i++) {
                String str = strings[i];
                if (isNumeric(str)) {
                    if (flag) // 이전 값이 숫자인 경우
                        tmp.push(tmp.pop().multiply(BigDecimal.valueOf(10)).add(BigDecimal.valueOf(Integer.valueOf(str))));
                    else
                        tmp.push(BigDecimal.valueOf(Integer.valueOf(str)));
                    flag = true;
                }
                else {
                    flag = false;
                }
            }
            nums.addAll(tmp);
        }

        Collections.sort(nums);
        for (BigDecimal num: nums) {
            System.out.println(num);
        }
    }

    private boolean isNumeric(String str) {
        return numerics.contains(str);
    }
}
