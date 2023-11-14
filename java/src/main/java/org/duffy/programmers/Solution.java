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

    public int 소수_찾기(String numbers) {
        int n = numbers.length();
        소수_찾기_prime(Math.pow(10, n));
        소수_찾기_go(numbers, new StringBuilder(), n);

        int cnt = 0;
        for (int num: 소수_찾기_set) {
            if (소수_찾기_isPrime[num])
                cnt++;
        }
        return cnt;
    }

    boolean[] 소수_찾기_visited = new boolean[10];
    Set<Integer> 소수_찾기_set = new HashSet<>();
    private void 소수_찾기_go(String str, StringBuilder current, int max) {
        if (max <= current.length())
            return;

        for (int i = 0; i < str.length(); i++) {
            if (소수_찾기_visited[i]) continue;
            소수_찾기_visited[i] = true;
            current.append(str.charAt(i));
            소수_찾기_set.add(Integer.valueOf(current.toString()));
            소수_찾기_go(str, current, max);
            current.deleteCharAt(current.length() - 1);
            소수_찾기_visited[i] = false;
        }
    }

    boolean[] 소수_찾기_isPrime;
    private void 소수_찾기_prime(double range) {
        소수_찾기_isPrime = new boolean[(int) (range + 1)];
        Arrays.fill(소수_찾기_isPrime, true);
        소수_찾기_isPrime[0] = 소수_찾기_isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(range); i++) {
            if (소수_찾기_isPrime[i]) {
                for (int j = i * 2; j <= range; j += i) {
                    소수_찾기_isPrime[j] = false;
                }
            }
        }
    }

    public int[] 카펫(int brown, int yellow) {
        for (int h = 1; h <= yellow; h++) {
            if (yellow % h != 0)  continue;
            else {
                int w = yellow / h;
                int area = (w + 2) * (h + 2);
                if (area - yellow == brown) return new int[] {w + 2, h + 2};
            }
        }
        return new int[]{0};
    }

    public int 피로도(int k, int[][] dungeons) {
        int n = dungeons.length;
        this.피로도_visited = new boolean[n];

        피로도_go(dungeons, k, 0);
        return 피로도_max;
    }

    int 피로도_max = 0;
    boolean[] 피로도_visited;
    public void 피로도_go(int[][] dungeons, int k, int cnt) {
        피로도_max = Math.max(피로도_max, cnt);

        for (int i = 0; i < dungeons.length; i++) {
            if (피로도_visited[i]) continue;
            if (k < dungeons[i][0]) continue;
            피로도_visited[i] = true;
            피로도_go(dungeons, k - dungeons[i][1], cnt + 1);
            피로도_visited[i] = false;
        }
    }

    public int 전력망을_둘로_나누기(int n, int[][] wires) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire: wires) {
            int a = wire[0];
            int b = wire[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int min = Integer.MAX_VALUE;
        for (int[] wire: wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a].remove(b);
            graph[b].remove(a);

            // a에 연결된 갯수 - 나머지
            int cnt1 =  전력망을_둘로_나누기_go(graph, new boolean[n + 1], a);
            int cnt2 = n - cnt1;

            min = Math.min(min, Math.abs(cnt1 - cnt2));

            graph[a].add(b);
            graph[b].add(a);
        }

        return min;
    }

    private int 전력망을_둘로_나누기_go(List<Integer>[] graph, boolean[] visited, int node) {
        visited[node] = true;
        int cnt = 1;

        for (int next: graph[node]) {
            if (visited[next]) continue;
            cnt +=  전력망을_둘로_나누기_go(graph, visited, next);
        }
        return cnt;
    }

    public int 모음사전(String word) {
        모음사전_go(new char[]{'A', 'E', 'I', 'O', 'U'}, new HashSet<>(), new StringBuffer());
        return words.indexOf(word);
    }

    List<String> words = new ArrayList<>();
    private void 모음사전_go(char[] vowels, HashSet<String> visited, StringBuffer word) {
        if (4 < word.length()) return;
        if (visited.contains(word.toString())) return;

        for (char vowel: vowels) {
            visited.add(word.toString());

            word.append(vowel);
            words.add(word.toString());

            모음사전_go(vowels, visited, word);

            word.deleteCharAt(word.length() - 1);
            visited.remove(word.toString());
        }
    }

    public int 타겟_넘버(int[] numbers, int target) {
        return 타겟_넘버_go(numbers, target, 0, 0);
    }

    private int 타겟_넘버_go(int[] numbers, int target, int idx, int num) {
        if (idx == numbers.length)
            return num == target ? 1 : 0;

        int plus = 타겟_넘버_go(numbers, target, idx + 1, num + numbers[idx]);
        int minus = 타겟_넘버_go(numbers, target, idx + 1, num - numbers[idx]);

        return plus + minus;
    }

    public int 네트워크(int n, int[][] computers) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (computers[i][j] == 1) graph[i + 1].add(j + 1);
            }
        }

        boolean[] visited = new boolean[n + 1];

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                cnt++;
                네트워크_dfs(graph, visited, i);
            }
        }

        return cnt;
    }

    private void 네트워크_dfs(ArrayList<Integer>[] graph, boolean[] visited, int node) {
        visited[node] = true;
        for (int next: graph[node]) {
            if (visited[next]) continue;
            네트워크_dfs(graph, visited, next);
        }
    }

    public int 단어_변환(String begin, String target, String[] words) {
        go(new HashSet<>(), words, target, begin, 0);
        return 단어_변환_min == Integer.MAX_VALUE ? 0: 단어_변환_min;
    }

    int 단어_변환_min = Integer.MAX_VALUE;
    private void go(Set<String> visited, String[] words, String target, String current, int count) {
        visited.add(current);

        if (target.equals(current)) {
            단어_변환_min = Math.min(단어_변환_min, count);
            return;
        }

        for (String word: words) {
            if (visited.contains(word)) continue;
            if (단어_변환_canChange(current, word)) {
                go(visited, words, target, word, count + 1);
                visited.remove(word);
            }
        }
    }

    private boolean 단어_변환_canChange(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (1 < ++cnt) return false;
            }
        }
        return true;
    }

    public int 아이템_줍기(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] graph = new int[102][102];
        for (int[] g: graph) Arrays.fill(g, -1);

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        for (int i = rectangle.length - 1; 0 <= i; i--) {
            int[] r = rectangle[i];
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;

            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if (x1 < x && x < x2 && y1 < y && y < y2)
                        graph[y][x] = 0;
                    else if (graph[y][x] != 0)
                        graph[y][x] = 1;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{characterY, characterX});
        int[][] visited = new int[102][102];
        visited[characterY][characterX] = 1;
        int[] dy = new int[] {1, 0, -1, 0};
        int[] dx = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (tmp[0] == itemY && tmp[1] == itemX) break;
            for (int i = 0; i < 4; i++) {
                int my = tmp[0] + dy[i];
                int mx = tmp[1] + dx[i];

                if (my < 2 || mx < 2 || 102 <= my || 102 <= mx) continue;
                if (0 < visited[my][mx]) continue;
                if (graph[my][mx] != 1) continue;

                visited[my][mx] += visited[tmp[0]][tmp[1]] + 1;
                queue.offer(new int[]{my, mx});
            }
        }

        return visited[itemY][itemX] / 2;
    }

    public String[] 여행경로(String[][] tickets) {
        여행경로_ret = new ArrayList<>();
        여행경로_dfs("ICN", new ArrayList<>(), tickets, new boolean[tickets.length + 1]);
        Collections.sort(여행경로_ret);
        return 여행경로_ret.get(0).split(" ");
    }

    List<String> 여행경로_ret;
    private void 여행경로_dfs(String current, List<String> comb, String[][] tickets, boolean[] visited) {
        comb.add(current);
        if (comb.size() == tickets.length + 1) {
            StringBuilder sb = new StringBuilder();
            for (String s: comb) {
                sb.append(s).append(" ");
            }
            여행경로_ret.add(sb.toString().trim());
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                여행경로_dfs(tickets[i][1], comb, tickets, visited);
                visited[i] = false;
                comb.remove(comb.size() - 1);
            }
        }
    }

    public int[] 같은_숫자는_싫어(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num: arr) {
            if (stack.isEmpty()) stack.add(num);
            else {
                if (stack.peek() != num) stack.add(num);
            }
        }

        int n = stack.size();
        int[] ret = new int[n];
        for (int i = n - 1; 0 <= i; i--) {
            ret[i] = stack.pop();
        }
        return ret;
    }
}