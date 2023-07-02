package org.duffy.sort;

import java.util.Queue;

public class Sort {
    public void pr11728(Queue<Integer> q1, Queue<Integer> q2) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (q1.isEmpty()) {
                while (!q2.isEmpty()) {
                    sb.append(q2.remove() + " ");
                }
                break;
            }
            if (q2.isEmpty()) {
                while (!q1.isEmpty()) {
                    sb.append(q1.remove() + " ");
                }
                break;
            }

            int n1 = q1.peek();
            int n2 = q2.peek();
            if (n2 < n1) {
                sb.append(q2.remove() + " ");
            }
            else {
                sb.append(q1.remove() + " ");
            }
        }

        System.out.println(sb);
    }
}
