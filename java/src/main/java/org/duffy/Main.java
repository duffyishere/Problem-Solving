package org.duffy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

    }

    private static int gcd(int a, int b) {
        return b==0? a: gcd(b, a%b);
    }
}