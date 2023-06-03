package org.duffy;

import java.io.*;
import java.util.Arrays;

public class Main {
    /*
    a = [2, 3(i-1),| 5(i), 4, 4]
    Next -> [2, 4, 3, 4, 5]
     */
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer[] nums = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}