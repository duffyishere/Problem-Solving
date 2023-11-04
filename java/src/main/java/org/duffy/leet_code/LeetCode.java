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
        return subsets;
    }
    List<List<Integer>> subsets = new ArrayList<>();
    private void subsets(int[] nums, List<Integer> ret, int index) {
        if (index == nums.length) {
            subsets.add(new ArrayList<>(ret));
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

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null && n2 != null || n1 != null && n2 == null) return false;
        if (n1.val != n2.val) return false;

        if (isSymmetric(n1.left, n2.right) && isSymmetric(n1.right, n2.left)) return true;
        else return false;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        levelOrder(ans, root, 0);
        return ans;
    }
    private void levelOrder(List<List<Integer>> ans, TreeNode node, int depth) {
        if (node == null)
            return;

        if (ans.size() - 1 < depth)
            ans.add(new ArrayList<>());

        ans.get(depth).add(node.val);
        levelOrder(ans, node.left, depth + 1);
        levelOrder(ans, node.right, depth + 1);
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        go(root, 1);
        return ansMaxDepth;
    }
    int ansMaxDepth = 1;
    private void go(TreeNode node, int depth) {
        if (node == null)
            return;

        ansMaxDepth = Math.max(ansMaxDepth, depth);
        go(node.left, depth + 1);
        go(node.right, depth + 1);
    }

    int maxIndex = 0;
    int lowIndex = 0;
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        if (chars.length < 2)
            return s;

        for (int i = 0; i < chars.length; i++) {
            longestPalindrome(chars, i, i);
            longestPalindrome(chars, i, i + 1);
        }
        return s.substring(lowIndex, lowIndex + maxIndex - 1);
    }
    private void longestPalindrome(char[] chars, int left, int right) {
        while (0 <= left && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        if (maxIndex < right - left) {
            maxIndex = right - left;
            lowIndex = left + 1;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, Integer.MAX_VALUE);
    }
    int pIndex = 0;
    int iIndex = 0;
    private TreeNode buildTree(int[] preorder, int[] inorder, int stop) {
        if (preorder.length <= pIndex)
            return null;
        if (inorder[iIndex] == stop) {
            iIndex++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[pIndex++]);
        node.left = buildTree(preorder, inorder, node.val);
        node.right = buildTree(preorder, inorder, stop);

        return node;
    }

    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode runner  = curr.left;
                while (runner.right != null) runner = runner.right;
                runner.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        ans.add(tmp);
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    tmp = new ArrayList<>();
                    tmp.add(1);
                    ans.add(tmp);
                }
                else if (j == i) {
                    ans.get(j).add(1);
                }
                else {
                    ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
        }

        return ans;
    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        int buy = prices[0];

        for (int price: prices) {
            ans = Math.max(ans, price - buy);
            buy = Math.min(buy, price);
        }
        return ans;
    }
    int maxPathSumAns = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxPathSumAns;
    }
    private int helper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(helper(node.left), 0);
        int right = Math.max(helper(node.right), 0);

        maxPathSumAns = Math.max(maxPathSumAns, node.val + left + right);

        return node.val + Math.max(left, right);
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        int ans = 0;
        for (int now: nums) {
            if (!map.containsKey(now - 1)) {
                int next = now + 1;
                while (map.containsKey(next)) {next++;}
                ans = Math.max(ans, next - now);
            }
        }
        return ans;
    }

    public List<List<String>> partition(String s) {
        if(s == null || s.isEmpty()) return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        partitionHelper(s, new ArrayList<>(), result);
        return result;
    }
    private void partitionHelper(String str, List<String> step, List<List<String>> result) {
        if (str.isEmpty()) {
            result.add(new ArrayList<>(step));
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String tmp = str.substring(0, i);
            if (!isPalindrome(tmp)) continue;
            step.add(tmp);
            partitionHelper(str.substring(i), step, result);
            step.remove(step.size() - 1);
        }
    }
    private boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (str.charAt(start) == str.charAt(end)) {
            if (end <= start)
                return true;
            start++;
            end--;
        }
        return false;
    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] != nums[i + 1])
                return nums[i - 1];
        }
        return nums[nums.length - 1];
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeHelper(p, q);
    }
    private boolean isSameTreeHelper(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 != null || n1 != null && n2== null)
            return false;
        if (n1 == null && n2 == null)
            return true;
        if (n1.val != n2.val)
            return false;

        return isSameTreeHelper(n1.left, n2.left) && isSameTreeHelper(n1.right, n2.right);
    }

    public void sortColors2(int[] nums) {
        int[] index = new int[3];
        for (int i = 1; i < nums.length; i++) {
            int now = nums[i];
            if (nums[i] < nums[i - 1]) {
                index[now]++;
                nums[i] = nums[i - 1];
                nums[index[now]] = now;
            }
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> oldToNew = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            oldToNew.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            curr = curr.next;
        }
        return oldToNew.get(head);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        this.dp = new int[s.length()];
        this.s = s;
        this.wd = wordDict;
        Arrays.fill(dp, -1);

        return wordBreakHelper(s.length() - 1);
    }
    int[] dp;
    String s;
    List<String> wd;
    private boolean wordBreakHelper(int idx) {
        if (idx < 0) return true;
        if (dp[idx] != -1) return dp[idx] == 1;

        for (String word: wd) {
            if (idx - word.length() + 1 < 0)
                continue;

            if (s.startsWith(word, idx - word.length() + 1) && wordBreakHelper(idx - word.length())) {
                dp[idx] = 1;
                return true;
            }
        }
        dp[idx] = 0;
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode runner = head;
        ListNode walker = head;

        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner)
                return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode runner = head;
        ListNode walker = head;

        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) break;
        }
        if (runner.next == null || runner.next.next == null) return null;

        while (walker != head) {
            walker = walker.next;
            head = head.next;
        }
        return head;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        prev.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return mergeList(l1, l2);
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode merged = new ListNode(), dummy = merged;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            }
            else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        if (l1 != null)
            dummy.next = l1;
        else if (l2 != null)
            dummy.next = l2;

        return merged.next;
    }

    public int maxProduct(int[] nums) {
        int ret = nums[0];
        int curMax = 1 , curMin= 1;

        for (int num: nums) {
            if (num == 0) {
                curMax = 1;
                curMin = 1;
                ret = Math.max(num, ret);
                continue;
            }
            int tmp = curMax * num;
            curMax = Math.max(num, Math.max(num * curMax, num * curMin));
            curMin = Math.min(num, Math.min(tmp, num * curMin));
            ret = Math.max(num, Math.max(ret, curMax));
        }
        return ret;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[right] < nums[mid]) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return nums[left];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null? headB: a.next;
            b = b == null? headA: b.next;
        }
        return a;
    }

    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int cnt = 1;

        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) {
                cnt++;
                ans = nums[i];
            }
            else if (ans == nums[i]) cnt++;
            else cnt--;
        }
        return ans;
    }

    public void rotate(int[] nums, int k) {
        int l = nums.length - 1;
        k %= nums.length;

        reverseRotate(nums, 0, l);
        reverseRotate(nums, 0, k - 1);
        reverseRotate(nums, k, l);
    }
    private void reverseRotate(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            dp[i + 1] = Math.max(dp[i - 1] + num, dp[i]);
        }
        return dp[nums.length];
    }

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 1);
        return ret;
    }
    List<Integer> ret = new ArrayList<>();
    int maxDepth = 0;
    private void dfs(TreeNode node, int depth) {
        if (node == null)
            return;
        if (maxDepth < depth) {
            maxDepth = depth;
            ret.add(node.val);
        }
        dfs(node.right, depth+1);
        dfs(node.left, depth+1);
    }

    public int numIslands(char[][] grid) {
        yLen = grid.length;
        xLen = grid[0].length;
        this.grid = grid;

        int ret = 0;
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    numIslandsDFS(i, j);
                    ret++;
                }
            }
        }

        return ret;
    }

    int yLen, xLen;
    char[][] grid;
    private void numIslandsDFS(int y, int x) {
        if (grid[y][x] == '0' || visited[y][x]) return;
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int my = y + dy[i];
            int mx = x + dx[i];
            if (my < 0 || mx < 0 || yLen <= my || xLen <= mx) continue;
            numIslandsDFS(my, mx);
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTree(left);
        root.right = invertTree(right);
        return root;
    }

    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        addAllTree(queue, root);
        for (int i = 0; i < k - 1; i++)
            queue.poll();
        return queue.peek();
    }
    private void addAllTree(PriorityQueue<Integer> q, TreeNode node) {
        if (node == null)
            return;
        q.offer(node.val);
        addAllTree(q, node.left);
        addAllTree(q, node.right);
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next;
        slow = reverseNode(slow);

        while (slow != null) {
            if (slow.val != head.val) return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    private ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode ret = reverseNode(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null? right: right == null? left: root;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int left = 1;
        for (int i = 0; i < len; i++) {
            if (0 < i) {
                left *= nums[i - 1];
                ans[i] = left;
            }
            else
                ans[i] = left;
        }

        int right = 1;
        for (int i = len - 1; 0 <= i; i--) {
            if (i == len - 1)
                ans[i] *= right;
            else {
                right *= nums[i + 1];
                ans[i] *= right;
            }
        }

        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        this.matrix = matrix;
        this.target = target;
        this.visited = new boolean[matrix.length][matrix[0].length];
        return searchMatrixGo(0, 0);
    }
    int[][] matrix;
    int target;
    private boolean searchMatrixGo(int y, int x) {
        if (matrix.length <= y || matrix[0].length <= x) return false;
        if (visited[y][x]) return false;
        if (matrix[y][x] == target) return true;
        if (target < matrix[y][x]) return false;
        visited[y][x] = true;
        return searchMatrixGo(y + 1, x) || searchMatrixGo(y, x + 1);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (k - 1 <= i) {
                ans[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }

    public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int num: nums) {
            if (num != 0) nums[pos++] = num;
        }
        while (pos < nums.length) {
            nums[pos++] = 0;
        }
    }

    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int low = 1;
        int high = len - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid)
                    cnt++;
            }
            if (cnt <= mid)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num: nums) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < num) lis.add(num);
            else {
                int left = 0;
                int right = lis.size() - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (lis.get(mid) < num)
                        left = mid + 1;
                    else
                        right = mid;
                }
                lis.set(right, num);
            }
        }
        return lis.size();
    }
    public int lengthOfLIS_DP(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] < amount + 1? dp[amount]: -1;
    }

    public int[] topKFrequency(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num: nums) frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

        int n = nums.length;
        List<Integer>[] bucket = new List[n + 1];

        for (int key: frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = n; 0 <= i && ret.size() < k; i--) {
            if (bucket[i] != null)
                ret.addAll(bucket[i]);
        }

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int num: nums) sum += num;
        if (sum % 2 == 1) return false;

        sum = sum / 2;
        dp = new Boolean[n + 1][sum + 1];
        return subsetSum(nums, 0, sum);
    }
    private boolean subsetSum(int[] nums, int idx, int sum) {
        if (sum == 0) return true;
        else if (idx >= nums.length || sum < 0) return false;

        if (dp[idx][sum] != null) return dp[idx][sum];

        return dp[idx][sum] = subsetSum(nums, idx + 1, sum - nums[idx]) || subsetSum(nums, idx + 1, sum);
    }
}
