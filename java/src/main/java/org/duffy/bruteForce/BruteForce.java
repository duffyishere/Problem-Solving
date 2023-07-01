package org.duffy.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BruteForce {

    public void pr2309() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[9];
        int sum = 0;
        for (int i=0; i<9; i++) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }

        Arrays.sort(a);
        for (int i=0; i<9; i++) {
            for (int j=i+1; j<9; j++) {
                if (sum - a[i] - a[j] == 100) {
                    for (int k=0; k<9; k++) {
                        if (k == i || k == j) continue;
                        System.out.println(a[k]);
                    }
                    System.exit(0);
                }
            }
        }
    }

    public void pr3085() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] a = new char[n][n];
        for (int i=0; i<n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int ret = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (j+1 < n ) {
                    char tmp = a[i][j];
                    a[i][j] = a[i][j+1];
                    a[i][j+1] = tmp;

                    ret = Math.max(ret, check3085(a));

                    tmp = a[i][j];
                    a[i][j] = a[i][j+1];
                    a[i][j+1] = tmp;
                }
                if (i+1 < n) {
                    char tmp = a[i][j];
                    a[i][j] = a[i+1][j];
                    a[i+1][j] = tmp;

                    ret = Math.max(ret, check3085(a));

                    tmp = a[i][j];
                    a[i][j] = a[i+1][j];
                    a[i+1][j] = tmp;
                }
            }
        }
        System.out.println(ret);
    }

    private int check3085(char[][] a) {
        int ret = 1;
        int l = a.length;
        for (int i=0; i<l; i++) {
            // 가로 비교
            int tmp = 1;
            for (int j=1; j<l; j++) {
                if (a[i][j] == a[i][j-1]) tmp++;
                else tmp = 1;
                ret = Math.max(ret, tmp);
            }
            // 세로 비교
            tmp = 1;
            for (int j=1; j<l; j++) {
                if (a[j][i] == a[j-1][i]) tmp++;
                else tmp = 1;
                ret = Math.max(ret, tmp);
            }
        }

        return ret;
    }

    public void pr1476() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[3];
        for (int i=0; i<3; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[] d = new int[3];
        d[0] = d[1] = d[2] = 1;
        int ret = 1;
        while (true) {
            if (d[0] == a[0] && d[1] == a[1] && d[2] == a[2]) {
                System.out.println(ret);
                break;
            }
            d[0] ++; d[1]++; d[2]++;
            if (d[0]%15 == 1) d[0] %= 15;
            if (d[1]%28 == 1) d[1] %= 28;
            if (d[2]%19 == 1) d[2] %= 19;
            ret++;
        }
    }

    public void pr1107() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] a = new boolean[10];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            a[x] = true;
        }
        int ret = Math.abs(n - 100);

        for (int i=0; i<1000000; i++) {
            int len = check1107(i, a);
            if (len > 0) {
                int press = Math.abs(n - i);
                ret = (press + len < ret)? press + len: ret;
            }
        }
        System.out.println(ret);
    }

    private int check1107(int n, boolean[] a) {
        int len = 0;
        if (n == 0)
            return a[0]? 0: 1;
        while(n > 0) {
            if (a[n%10])
                return 0;
            len++;
            n /= 10;
        }
        return len;
    }

    public void pr6064() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] line = br.readLine().split(" ");
            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);
            int x = Integer.valueOf(line[2])-1;
            int y = Integer.valueOf(line[3])-1;
            boolean flag = false;
            for (int k=x; k<n*m; k+=m) {
                if (k%n==y) {
                    flag = true;
                    System.out.println(k+1);
                    break;
                }
            }
            if (!flag)
                System.out.println(-1);
        }
    }

    public void pr1748() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long ret = 0;
        for (long start=1, len =1; start<=n; start*=10, len++) {
            long end = start*10-1;
            if (end > n)
                end = n;
            ret += (end - start + 1) * len;
        }
        System.out.println(ret);
    }

    private boolean[] c = new boolean[10];
    private int[] a = new int[10];
    private StringBuilder sb = new StringBuilder();

    public void pr15649() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        go15649(0, n, m);
        System.out.println(sb.toString());
    }

    private void go15649(int index, int n, int m) {
        if (m == index) {
            for (int i=0; i<m; i++) {
                sb.append(a[i]);
                if (i != m-1) sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=1; i<=n; i++) {
            if (!c[i]) {
                c[i] = true;
                a[index] = i;
                go15649(index+1, n, m);
                c[i] = false;
            }
        }
    }

    private void go15650(int index, int n, int m) {
        if (index == m) {
            for (int i=0; i<m; i++) {
                sb.append(a[i]);
                if (i != m-1)
                    sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=n; i++) {
            if (c[i] || index != 0 && i < a[index-1]) continue;
            c[i] = true;
            a[index] = i;
            go15650(index+1, n, m);
            c[i] = false;
        }
    }

    private void go15651(int index, int n, int m) {
        if (index == m) {
            for (int i=0; i<m; i++) {
                sb.append(a[i]);
                if (i != m-1)
                    sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=n; i++) {
            a[index] = i;
            go15651(index+1, n, m);
        }
    }

    private static Integer[] num;

    private void go15652(int index, int n, int m) {
        if (index == m) {
            for (int i=0; i<m; i++) {
                sb.append(num[a[i]-1]);
                if (i != m-1)
                    sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=n; i++) {
            if (c[i]) continue;
            c[i] = true;
            a[index] = i;
            go15652(index+1, n, m);
            c[i] = false;
        }
    }

    private void pr15655(int index, int n, int m) {
        if (index == m) {
            for (int i=0; i<m; i++) {
                sb.append(num[a[i]]);
                if (i != m-1)
                    sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=0; i<n; i++) {
            if (c[i] || index!=0 && num[i] < num[a[index-1]]) continue;
            c[i] = true;
            a[index] = i;
            pr15655(index+1, n, m);
            c[i] = false;
        }
    }

    public void pr9375() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = Integer.parseInt(br.readLine());
        while (caseCount-- > 0) {
            Map<String, Integer> clothes = new HashMap<>();
            Set<String> clothTypes = new HashSet<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String[] strings = br.readLine().split(" ");
                clothTypes.add(strings[1]);
                clothes.put(strings[1], clothes.getOrDefault(strings[1], 0) + 1);
            }
            long ret = 1;
            for (String type: clothTypes) {
                ret *= clothes.get(type)+1;
            }
            System.out.println(--ret);
        }
    }


    public void pr1182(int n, int s, int[] nums) {
        this.n = n;
        this.s = s;
        this.nums = nums;
        go1182(0, 0);
        if (s == 0)
            ret = ret - 1;

        System.out.println(ret);
    }

    int n;
    int s;
    int[] nums;
    int ret = 0;
    public void go1182(int num, int index) {
        if (index == n) {
            if (num == s)
                ret++;
            return;
        }

        go1182(num + nums[index], index + 1);
        go1182(num, index + 1);
    }
}
