package org.duffy.leet_code;

import java.util.*;

public class LeetCode {
    public int maxArea(int[] height) {
        int ret = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            ret  = Math.max(ret, h * w);

            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return ret;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                ret -= map.get(s.charAt(i));
            }
            else {
                ret += map.get(s.charAt(i));
            }
        }

        return ret;
    }

    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;

        String prefix = "";
        while (i < first.length() && i < last.length()) {
            if (first.charAt(i) == last.charAt(i)) {
                prefix += first.charAt(i);
                i++;
            }
            else break;
        }

        return prefix;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> ret = new ArrayList<>();

        Arrays.sort(nums);
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        ret.addAll(set);
        return ret;
    }

    private List<String> letterCombinations(String digits) {
        String[] strings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> answer = new ArrayList<>();
        if (digits.length() == 0) return answer;

        answer.add("");
        for (int i = 0; i < digits.length(); i++) {
            String word = strings[Character.getNumericValue(digits.charAt(i))];
            answer = combinations(word, answer);
        }

        return answer;
    }

    private List<String> combinations(String word, List<String> answer) {
        List<String> ret = new ArrayList<>();
        for (char ch: word.toCharArray()) {
            for (String str: answer) {
                ret.add(str + ch);
            }
        }

        return ret;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        for (int i = 0; i < n + 1; i++)
            fast = fast.next;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    public boolean isValid(String s) {
        if (s.length() < 2)
            return false;
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            } else
                stack.push(c);
        }

        return stack.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode answer = new ListNode(0);
        ListNode temp = answer;

        while(list1 != null || list2 != null) {
            if (list1 == null) {
                while (list2 != null) {
                    temp.next = list2;
                    list2 = list2.next;
                    temp = temp.next;
                }
                return answer.next;
            }
            else if (list2 == null) {
                while (list1 != null) {
                    temp.next = list1;
                    list1 = list1.next;
                    temp = temp.next;
                }
                return answer.next;
            }

            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
                temp = temp.next;
            }
            else {
                temp.next = list2;
                list2 = list2.next;
                temp = temp.next;
            }
        }

        return answer.next;
    }

    public List<String> generateParenthesis(int n) {
        this.max = n;
        List<String> answer = new ArrayList<>();
        go_generateParenthesis(answer, "", 0, 0);

        return answer;
    }

    int max;
    private void go_generateParenthesis(List<String> list, String str, int open, int close) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max)
            go_generateParenthesis(list, str + "(", open + 1, close);
        if (close < open)
            go_generateParenthesis(list, str + ")", open, close + 1);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        if (len == 1) return lists[0];

        ListNode answer = mergeKLists_merge(lists[0], lists[1]);
        for (int i = 2; i < len; i++) {
            answer = mergeKLists_merge(answer, lists[i]);
        }

        return answer;
    }

    private ListNode mergeKLists_merge(ListNode node1, ListNode node2) {
        ListNode merged = new ListNode(0);
        ListNode tmp = merged;

        while (node1 != null || node2 != null) {
            if (node1 == null) {
                while (node2 != null) {
                    tmp.next = node2;
                    tmp = tmp.next;
                    node2 = node2.next;
                }
                break;
            }
            else if (node2 == null) {
                while (node1 != null) {
                    tmp.next = node1;
                    tmp = tmp.next;
                    node1 = node1.next;
                }
                break;
            }

            if (node1.val < node2.val) {
                tmp.next = node1;
                tmp = tmp.next;
                node1 = node1.next;
            }
            else {
                tmp.next = node2;
                tmp = tmp.next;
                node2 = node2.next;
            }
        }

        return merged.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = head;
        int tmp;
        while (dummy != null && dummy.next != null) {
            tmp = dummy.val;
            dummy.val = dummy.next.val;
            dummy.next.val = tmp;

            dummy = dummy.next.next;
        }

        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode lastNode = head;
        int count = 0;
        while (lastNode != null && count != k) {
            lastNode = lastNode.next;
            count++;
        }
        if (count == k) {
            lastNode = reverseKGroup(lastNode, k);
            while (count-- > 0) {
                ListNode tmp = head.next;
                head.next = lastNode;
                lastNode = head;
                head = tmp;
            }
            head = lastNode;
        }
        return head;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (0 < i && nums[i] <= nums[i - 1])
            i--;
        if (i <= 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int j = nums.length - 1;
        while (nums[j] <= nums[i - 1])
            j--;

        swap(nums, i - 1, j);

        reverse(nums, i, nums.length - 1);
    }

    private int[] swap(int[] nums, int from, int to) {
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;

        return nums;
    }
    private int[] reverse(int[] nums, int from, int to) {
        while (from < to) {
            swap(nums, from, to);
            from++; to--;
        }

        return nums;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[nums.length - 1] < nums[mid]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        // left is pivot index
        int answer = binarySearch(nums, left, nums.length - 1, target);
        if (answer != -1)
            return answer;

        answer = binarySearch(nums, 0, left - 1, target);
        return answer;
    }

    private int binarySearch(int[] nums, int leftBoundary, int rightBoundary, int target) {
        int left = leftBoundary;
        int right = rightBoundary;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    answer = Math.max(answer, i - stack.peek());
                }
            }
        }

        return answer;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = findStart(nums, target);
        int end = findEnd(nums, target);

        return new int[] {start, end};
    }

    private int findStart(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        // 5,7,7,8,8,8,10
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }

            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    private int findEnd(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        // 5,7,7,8,8,8,10
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(answer, new ArrayList<>(), candidates, target, 0);
        return answer;
    }

    private void backtrack(List<List<Integer>> answer, List<Integer> tmpList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if(remain == 0) answer.add(new ArrayList<>(tmpList));
        else {
            for (int i = start; i < nums.length; i++) {
                tmpList.add(nums[i]);
                backtrack(answer, tmpList, nums, remain - nums[i], i);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        int len = nums.length;
        while (i < len) {
            int correct = nums[i] - 1;
            if (nums[i] < len && 0 < nums[i] && nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            }
            else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }

        return nums.length + 1;
    }

    public int trap(int[] height) {
        int leftIndex = 0, rightIndex = height.length - 1;
        int leftMaxHeight = height[0], rightMaxHeight = height[height.length - 1];
        int answer = 0;

        while (leftIndex < rightIndex) {
            if (leftMaxHeight < rightMaxHeight) {
                leftIndex++;
                if (height[leftIndex] < leftMaxHeight) answer += leftMaxHeight - height[leftIndex];
                else leftMaxHeight = height[leftIndex];
            }
            else {
                rightIndex--;
                if (height[rightIndex] < rightMaxHeight) answer += rightMaxHeight - height[rightIndex];
                else rightMaxHeight = height[rightIndex];
            }
        }

        return answer;
    }

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;

        int farthest = 0;
        int end = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) {
                ans++;
                break;
            }
            if (i == end) {
                ans++;
                end = farthest;
            }
        }

        return ans;
    }

    public void rotate(int[][] matrix) {
        int len = matrix.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                swapMatrix(matrix, new int[]{i, j}, new int[]{j, i});
            }
        }

        for (int i = 0; i < len; i++) {
            matrix[i] = reverse(matrix[i]);
        }
    }

    private int[][] swapMatrix(int[][] matrix, int[] from, int[] to) {
        int tmp = matrix[from[0]][from[1]];
        matrix[from[0]][from[1]] = matrix[to[0]] [to[1]];
        matrix[to[0]] [to[1]] = tmp;

        return matrix;
    }

    private int[] reverse(int[] arrays) {
        int left = 0;
        int right = arrays.length - 1;
        while (left <= right) {
            int tmp = arrays[left];
            arrays[left] = arrays[right];
            arrays[right] = tmp;
            left++;
            right--;
        }

        return arrays;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word: strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            if (!map.containsKey(sortedWord))
                map.put(sortedWord, new ArrayList<>());

            map.get(sortedWord).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;
        List<Integer> ans = new ArrayList<>();

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++)
                ans.add(matrix[top][i]);
            top++;

            for (int i = top; i <= bottom; i++)
                ans.add(matrix[i][right]);
            right--;

            if (top <= bottom) {
                for (int i = right; left <= i; i--)
                    ans.add(matrix[bottom][i]);
            }
            bottom--;

            if (left <= right) {
                for (int i = bottom; top <= i; i--)
                    ans.add(matrix[i][left]);
            }
            left++;
        }
        return ans;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> ans = new ArrayList<>();
        int[] newIntervals = intervals[0];
        ans.add(newIntervals);

        for(int[] interval: intervals) {
            if (interval[0] <= newIntervals[1]) {
                newIntervals[1] = Math.max(newIntervals[1], interval[1]);
            }
            else {
                newIntervals = interval;
                ans.add(newIntervals);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                else if (i == 0 && j != 0)
                    grid[i][j] += grid[i][j - 1];
                else if (i != 0 && j == 0)
                    grid[i][j] += grid[i - 1][j];
                else
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public int climbStairs(int n) {
        if (n == 1)
            return 1;

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n - 1];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 0; i <= n; i++)
            dp[0][i] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = (Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]))) + 1;
            }
        }
        return dp[m][n];
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rowFlag = false, colFlag = false;

        for (int i = 0; i < m; i++)
            if (matrix[i][0] == 0)
                rowFlag = true;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0)
                colFlag = true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (rowFlag) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
        if (colFlag) {
            for (int i = 0; i < n; i++)
                matrix[0][i] = 0;
        }
    }

    public void sortColors(int[] nums) {
        mergeSort(nums, 0, nums.length);
    }
    private void mergeSort(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if (left + 1 == right)
            return;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid, right);
        merge(nums, left, mid, right);
    }
    private void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = mid;

        for (int i = left; i < right; i++) {
            if (rightIndex == right) {
                tmp[i] = nums[leftIndex++];
            }
            else if (leftIndex == mid) {
                tmp[i] = nums[rightIndex++];
            }
            else if (nums[leftIndex] < nums[rightIndex]) {
                tmp[i] = nums[leftIndex++];
            }
            else {
                tmp[i] = nums[rightIndex++];
            }
        }

        for (int i = left; i < right; i++)
            nums[i] = tmp[i];
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length())
            return new String();

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int[] map = new int[128];
        for (char ch : tArray)
            map[ch]++;

        int count = tArray.length;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE, minStart = 0;

        while (right < s.length()) {
            if (0 < map[sArray[right]])
                count--;
            map[sArray[right]]--;

            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                map[sArray[left]]++;
                if (0 < map[sArray[left]]) {
                    count++;
                }
                left++;
            }
            right++;
        }
        if (minLen == Integer.MAX_VALUE) return new String();
        else return s.substring(minStart, minStart + minLen);
    }

    public List<List<Integer>> subsets(int[] nums) {
        subsets(nums, new ArrayList<>(), 0);
        return ans;
    }
    List<List<Integer>> ans = new ArrayList<>();
    private void subsets(int[] nums, List<Integer> ret, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(ret));
            return;
        }
        ret.add(nums[index]);
        subsets(nums, ret, index + 1);
        ret.remove(ret.size() - 1);
        subsets(nums, ret, index + 1);
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        int m = board.length;
        int n = board[0].length;
        this.visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j] && search(i, j, 0))
                    return true;
            }
        }
        return false;
    }
    char[][] board;
    String word;
    boolean[][] visited;
    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};
    private boolean search(int y, int x, int count) {
        if (count == word.length())
            return true;

        if (y < 0 || x < 0 || board.length <= y || board[0].length <= x) return false;
        if (visited[y][x]) return false;
        if (word.charAt(count) != board[y][x]) return false;

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int my = y + dy[i];
            int mx = x + dx[i];
            if (search(my, mx, count + 1))
                return true;
        }
        visited[y][x] = false;

        return false;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer[]> stack = new Stack<>();
        int len = heights.length;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int idx = i;
            while (0 < stack.size() && heights[i] <= stack.peek()[0]) {
                Integer[] pair = stack.pop();
                idx = pair[1];
                int area = pair[0] * (i - idx);
                ans = Math.max(ans, area);
            }
            stack.push(new Integer[]{heights[i], idx});
        }
        while (0 < stack.size()) {
            Integer[] pair = stack.pop();
            ans = Math.max(ans, pair[0] * (len - pair[1]));
        }
        return ans;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> ret = new ArrayList<>();
        if (root.left != null)
            ret.addAll(inorderTraversal(root.left));
        ret.add(root.val);
        if (root.right != null)
            ret.addAll(inorderTraversal(root.right));

        return ret;
    }
}