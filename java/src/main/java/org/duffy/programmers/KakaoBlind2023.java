package org.duffy.programmers;

import java.util.*;

public class KakaoBlind2023 {
    public int[] solution1(String today, String[] terms, String[] privacies) {
        StringTokenizer st;
        Map<String, Integer> termMap = new HashMap<>();
        for (String term: terms) {
            st = new StringTokenizer(term);
            termMap.put(st.nextToken(), Integer.valueOf(st.nextToken()));
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String privacyStr = privacies[i];
            st = new StringTokenizer(privacyStr);

            Date privacyDate = Date.valueOf(st.nextToken());
            String type = st.nextToken();

            Date addedDate = privacyDate.plusMonth(termMap.get(type));
            System.out.println(addedDate.compareTo(Date.valueOf(today)));
            if (0 < addedDate.compareTo(Date.valueOf(today))) {
                res.add(i + 1);
            }
        }

        return res.stream().mapToInt(Integer::intValue)
                .toArray();
    }
}

class Date implements Comparable<Date> {
    int year, month, day;

    public Date(String year, String month, String day) {
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(day);
    }

    public Date(Date date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    public static Date valueOf(String s) {
        StringTokenizer st = new StringTokenizer(s, ".");
        return new Date(st.nextToken(), st.nextToken(), st.nextToken());
    }

    public Date plusMonth(int month) {
        Date date = new Date(this);
        date.month += month;

        if (12 < date.month) {
            date.year += date.month / 12;
            date.month = date.month % 12;
            if (date.month == 0) {
                date.month = 12;
                date.year--;
            }
        }

        date.day--;
        if (date.day == 0) {
            date.day = 28;
            date.month--;
            if (date.month == 0) {
                date.month = 12;
                date.year--;
            }
        }

        return date;
    }

    @Override
    public int compareTo(Date d) {
        if (this.year == d.year) {
            if (this.month == d.month) {
                return Integer.compare(this.day, d.day);
            } else {
                return Integer.compare(this.month, d.month);
            }
        } else {
            return Integer.compare(this.year, d.year);
        }
    }
}
