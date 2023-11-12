package org.duffy.programmers;

import java.util.*;

public class Solution {

    public int 포켄몬(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) set.add(num);
        int k = nums.length / 2;

        return Math.min(set.size(), k);
    }

    public String 완주하지_못한_선수(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        int n = participant.length;
        int m = completion.length;
        String answer = "";

        for (int i = 0; i < m; i++) {
            if (!participant[i].equals(completion[i])) {
                answer = participant[i];
                break;
            }
        }

        return answer.isEmpty() ? participant[n - 1] : answer;
    }

    public boolean 전화번호_목록(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            String now = phone_book[i];
            String next = phone_book[i + 1];

            if (now.length() <= next.length() && next.startsWith(now))
                return false;
        }
        return true;
    }

    public String 가장_큰_수(int[] numbers) {
        List<String> str = new ArrayList<>();
        for (int num : numbers) str.add(String.valueOf(num));

        str.sort(((o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1);
        }));

        StringBuilder sb = new StringBuilder();
        for (String s : str)
            sb.append(s);

        if (str.get(0).charAt(0) == '0') return "0";

        return sb.toString();
    }

    public int 의상(String[][] clothes) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String[] cloth: clothes) {
            String category = cloth[1];
            frequencyMap.put(category, frequencyMap.getOrDefault(category, 0) + 1);
        }

        int result = 0;
        for (String key: frequencyMap.keySet()) {
            Integer frequency = frequencyMap.get(key);
            if (result == 0) result += frequency + 1;
            else result *= frequency + 1;
        }
        return result - 1;
    }

    public int[] 베스트앨범(String[] genres, int[] plays) {
        int n = genres.length;
        Map<String, Integer> totalPlay = new HashMap<>();
        Map<String, Map<Integer, Integer>> musics = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String genre = genres[i];
            int play = plays[i];

            totalPlay.put(genre, totalPlay.getOrDefault(genre, 0) + play);

            if (musics.containsKey(genre)) {
                musics.get(genre).put(i, play);
            } else {
                Map<Integer, Integer> music = new HashMap<>();
                music.put(i, play);
                musics.put(genre, music);
            }
        }

        List<String> sortedKey = new ArrayList<>(totalPlay.keySet());
        sortedKey.sort(((o1, o2) -> totalPlay.get(o2).compareTo(totalPlay.get(o1))));
        List<Integer> ret = new ArrayList<>();

        for (String genre: sortedKey) {
            Map<Integer, Integer> music = musics.get(genre);
            List<Integer> genreKey = new ArrayList<>(music.keySet());

            genreKey.sort(((o1, o2) -> music.get(o2).compareTo(music.get(o1))));

            ret.add(genreKey.get(0));
            if (1 < genreKey.size()) ret.add(genreKey.get(1));
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] K번째수(int[] array, int[][] commands) {
        int n = commands.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++)
            ret[i] = K번째수_helper(array, commands[i]);

        return ret;
    }

    private int K번째수_helper(int[] array, int[] command) {
        int n = command[0];
        int m = command[1];
        int k = command[2];

        int[] tmp = new int[m - n + 1];
        int i = 0;
        n = n - 1;
        m = m - 1;
        while (n <= m) {
            tmp[i++] = array[n++];
        }

        Arrays.sort(tmp);
        return tmp[k - 1];
    }

    public int H_Index(int[] citations) {
        int n = citations.length;
        int ret = 0;
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            int h = n - i;
            if (h <= citations[i]) {
                ret = h;
                break;
            }
        }
        return ret;
    }

    public int 더_맵게(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num: scoville) pq.offer(num);
        int ret = 0;

        while (pq.peek() < K) {
            if (pq.size() < 2) {
                return -1;
            }
            int n1 = pq.poll();
            int n2 = pq.poll();
            pq.offer(n1 + (n2 * 2));
            ret++;
        }
        return ret;
    }

    public int 디스크_컨트롤러(int[][] jobs) {
        List<int[]> work = new ArrayList<>(Arrays.asList(jobs));
        work.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int ret = 0;
        int now = 0;
        while (!work.isEmpty() || !pq.isEmpty()) {
            while (!work.isEmpty() && work.get(0)[0] <= now) {
                pq.add(work.remove(0));
            }

            if (!pq.isEmpty()) {
                int[] job = pq.poll();
                now += job[1];
                ret += now - job[0];
            } else {
                now = work.get(0)[0];
            }
        }
        return ret / jobs.length;
    }

    public int[] 이중우선순위큐(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> reverse = new ArrayList<>();

        for (String str: operations) {
            StringTokenizer st = new StringTokenizer(str);
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            switch (command) {
                case "I":
                    pq.add(value);
                    reverse.add(value);
                    reverse.sort((i1, i2) -> i2 - i1);
                    break;
                case "D":
                    if (pq.isEmpty()) break;
                    if (value == 1) {
                        pq.remove(reverse.get(0));
                        reverse.remove(0);
                        reverse.sort((i1, i2) -> i2 - i1);
                    }
                    else
                        pq.poll();
                    break;
            }
        }

        if (pq.isEmpty()) return new int[] {0, 0};
        else return new int[] {reverse.get(0), pq.poll()};
    }

    public int 최소직사각형(int[][] sizes) {
        int wMax = Integer.MIN_VALUE;
        int hMax = Integer.MIN_VALUE;
        for (int[] size: sizes) {
            wMax = Math.max(wMax, size[0]);
            hMax = Math.max(hMax, size[1]);
        }


        if (hMax < wMax) { // 가로로 뒤집고, 최대 세로 길이 다시 구하기
            hMax = Integer.MIN_VALUE;
            for (int[] size: sizes) {
                if (size[0] < size[1]) hMax = Math.max(hMax, size[0]);
                else hMax = Math.max(hMax, size[1]);
            }
        } else {
            wMax = Integer.MIN_VALUE;
            for (int[] size: sizes) {
                if (size[1] < size[0]) wMax = Math.max(wMax, size[1]);
                else wMax = Math.max(wMax, size[0]);
            }
        }

        return wMax * hMax;
    }

    public int[] 모의고사(int[] answers) {
        int[] s1 = new int[] {1, 2, 3, 4, 5};
        int[] s2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] s3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        int max = 0;

        for (int i = 0; i < answers.length; i++) {
            int ans = answers[i];
            if (ans == s1[i % s1.length]) cnt1++;
            if (ans == s2[i % s2.length]) cnt2++;
            if (ans == s3[i % s3.length]) cnt3++;
        }
        max = Math.max(cnt1, Math.max(cnt2, cnt3));
        List<Integer> ret = new ArrayList<>();
        if (cnt1 == max) ret.add(1);
        if (cnt2 == max) ret.add(2);
        if (cnt3 == max) ret.add(3);

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }
}