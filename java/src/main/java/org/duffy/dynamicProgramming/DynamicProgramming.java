package org.duffy.dynamicProgramming;

public class DynamicProgramming {

    public long[] temp = new long[101];
    public int[] d = new int[1001];

    public long fibonacci(int a) {
        if (a <= 1)
            return 1;
        else {
            if (temp[a] != 0)
                return temp[a];
            temp[a] = fibonacci(a-1) + fibonacci(a-2);
            return temp[a];
        }
    }

    public int pr1463(int n) {
        if (n == 1)
            return 0;
        if (d[n] != 0)
            return d[n];

        d[n] = pr1463(n-1)+1;

        if (n%2 == 0) {
            int temp = pr1463(n/2) + 1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        if (n%3 == 0) {
            int temp = pr1463(n/3) + 1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }

        return d[n];
    }

    public int pr11726(int n) {
        if (n <= 1)
            return 1;
        if (d[n] != 0)
            return d[n];
        d[n] = pr11726(n-1) + pr11726(n-2);
        d[n] %= 10007;
        return d[n];
    }

    public int pr11727(int n) {
        if (n <= 1)
            return 1;
        if (d[n] != 0)
            return d[n];
        d[n] = pr11727(n-1) + pr11727(n-2) + pr11727(n-2);
        d[n] %= 10007;

        return d[n];
    }
}
