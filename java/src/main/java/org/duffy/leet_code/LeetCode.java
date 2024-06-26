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
            ret = Math.max(ret, h * w);

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
            } else {
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
            } else break;
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
                } else if (sum < target) {
                    left++;
                } else {
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
        for (char ch : word.toCharArray()) {
            for (String str : answer) {
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

        while (list1 != null || list2 != null) {
            if (list1 == null) {
                while (list2 != null) {
                    temp.next = list2;
                    list2 = list2.next;
                    temp = temp.next;
                }
                return answer.next;
            } else if (list2 == null) {
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
            } else {
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
            } else if (node2 == null) {
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
            } else {
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
            from++;
            to--;
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
            } else {
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
            } else {
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
                } else {
                    answer = Math.max(answer, i - stack.peek());
                }
            }
        }

        return answer;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = findStart(nums, target);
        int end = findEnd(nums, target);

        return new int[]{start, end};
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
            } else {
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
        else if (remain == 0) answer.add(new ArrayList<>(tmpList));
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
            } else {
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
            } else {
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
        matrix[from[0]][from[1]] = matrix[to[0]][to[1]];
        matrix[to[0]][to[1]] = tmp;

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
        for (String word : strs) {
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

        for (int[] interval : intervals) {
            if (interval[0] <= newIntervals[1]) {
                newIntervals[1] = Math.max(newIntervals[1], interval[1]);
            } else {
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
            } else if (leftIndex == mid) {
                tmp[i] = nums[rightIndex++];
            } else if (nums[leftIndex] < nums[rightIndex]) {
                tmp[i] = nums[leftIndex++];
            } else {
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
        maxDepth_go(root, 1);
        return ansMaxDepth;
    }

    int ansMaxDepth = 1;

    private void maxDepth_go(TreeNode node, int depth) {
        if (node == null)
            return;

        ansMaxDepth = Math.max(ansMaxDepth, depth);
        maxDepth_go(node.left, depth + 1);
        maxDepth_go(node.right, depth + 1);
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
                TreeNode runner = curr.left;
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
                } else if (j == i) {
                    ans.get(j).add(1);
                } else {
                    ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
        }

        return ans;
    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        int buy = prices[0];

        for (int price : prices) {
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
        for (int now : nums) {
            if (!map.containsKey(now - 1)) {
                int next = now + 1;
                while (map.containsKey(next)) {
                    next++;
                }
                ans = Math.max(ans, next - now);
            }
        }
        return ans;
    }

    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) return new ArrayList<>();

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
        if (n1 == null && n2 != null || n1 != null && n2 == null)
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

        for (String word : wd) {
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
            } else {
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
        int curMax = 1, curMin = 1;

        for (int num : nums) {
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
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
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
            } else if (ans == nums[i]) cnt++;
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
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
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
        return left == null ? right : right == null ? left : root;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int left = 1;
        for (int i = 0; i < len; i++) {
            if (0 < i) {
                left *= nums[i - 1];
                ans[i] = left;
            } else
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
        for (int num : nums) {
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
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            if (res.isEmpty() || res.get(res.size() - 1) < num) res.add(num);
            else {
                int left = 0;
                int right = res.size() - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (res.get(mid) < num)
                        left = mid + 1;
                    else
                        right = mid;
                }
                res.set(right, num);
            }
        }
        return res.size();
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

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] < amount + 1 ? dp[amount] : -1;
    }

    public int[] topKFrequency(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

        int n = nums.length;
        List<Integer>[] bucket = new List[n + 1];

        for (int key : frequencyMap.keySet()) {
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

        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;

        sum = sum / 2;
//        dp = new Boolean[n + 1][sum + 1];
        return subsetSum(nums, 0, sum);
    }

    private boolean subsetSum(int[] nums, int idx, int sum) {
        if (sum == 0) return true;
        else if (idx >= nums.length || sum < 0) return false;

//        if (dp[idx][sum] != null) return dp[idx][sum];

//        return dp[idx][sum] = subsetSum(nums, idx + 1, sum - nums[idx]) || subsetSum(nums, idx + 1, sum);
        return true;
    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']') stack.push(c);
            else {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isLetter(stack.peek()))
                    sb.insert(0, stack.pop());
                String sub = sb.toString();
                stack.pop();

                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek()))
                    sb.insert(0, stack.pop());
                int repeat = Integer.parseInt(sb.toString());

                for (int i = repeat; 0 < i; i--) {
                    for (char ch : sub.toCharArray())
                        stack.push(ch);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        combine(ret, new ArrayList<>(), 1, n, k);
        return ret;
    }

    private void combine(List<List<Integer>> ret, List<Integer> comb, int index, int n, int k) {
        if (k == 0) {
            ret.add(new ArrayList<>(comb));
        } else {
            for (int i = index; i <= n; i++) {
                comb.add(i);
                combine(ret, comb, i + 1, n, k - 1);
                comb.remove(comb.size() - 1);
            }
        }
    }

    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();
        int m = p.length();
        List<Integer> ret = new ArrayList<>();
        if (n < m) return ret;

        int[] sFrequency = new int[26];
        int[] pFrequency = new int[26];

        int left = 0;
        int right = m - 1;

        for (char ch : p.toCharArray()) {
            pFrequency[ch - 'a']++;
        }
        for (int i = left; i <= right; i++) {
            sFrequency[s.charAt(i) - 'a']++;
        }
        if (isAnagram(sFrequency, pFrequency))
            ret.add(left);

        while (right < n - 1) {
            sFrequency[s.charAt(left++) - 'a']--;
            sFrequency[s.charAt(++right) - 'a']++;

            if (isAnagram(sFrequency, pFrequency))
                ret.add(left);
        }

        return ret;
    }

    private boolean isAnagram(int[] s, int[] p) {
        for (int i = 0; i < 26; i++) {
            if (s[i] != p[i])
                return false;
        }
        return true;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth_TreeNode(root);
        return max;
    }

    int max_diameterOfBinaryTree = 0;

    private int maxDepth_TreeNode(TreeNode node) {
        if (node == null) return 0;
        int left = maxDepth_TreeNode(node.left);
        int right = maxDepth_TreeNode(node.right);
        max_diameterOfBinaryTree = Math.max(max_diameterOfBinaryTree, left + right);

        return Math.max(left, right) + 1;
    }

    public boolean confusingNumber(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        Stack<Integer> stack = new Stack<>();
        for (char c : String.valueOf(n).toCharArray()) {
            int num = Character.digit(c, 10);
            if (map.containsKey(num))
                stack.push(map.get(num));
            else
                return false;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());

        return n != Integer.parseInt(sb.toString());
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int ret = 0;
        while (start < end) {
            ret = Math.max(ret, nums[start++] + nums[end--]);
        }
        return ret;
    }


    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long total = 0;
        int res = 0;

        for (int right = 0; right < nums.length; right++) {
            total += nums[right];

            while (total + k < (long) (right - left + 1) * nums[right]) {
                total -= nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1])
                cnt++;
            res += cnt;
        }
        return res;
    }

    public int garbageCollection(String[] garbage, int[] travel) {
        int totalResidentTime = 0;
        String[] truckTypes = new String[]{"G", "P", "M"};

        for (String type : truckTypes) {
            int totalTravelTime = 0;
            int singleMoveTime = 0;
            for (int i = 0; i < garbage.length; i++) {
                int unitCount = 0;
                if (garbage[i].contains(type)) {
                    StringBuilder garbageStringBuilder = new StringBuilder(garbage[i]);
                    while (garbageStringBuilder.indexOf(type) != -1) {
                        unitCount++;
                        garbageStringBuilder.deleteCharAt(garbageStringBuilder.indexOf(type));
                    }

                    totalTravelTime += singleMoveTime + unitCount;
                    singleMoveTime = 0;
                }
                if (i == garbage.length - 1) continue;
                singleMoveTime += travel[i];
            }
            totalResidentTime += totalTravelTime;
        }
        return totalResidentTime;
    }

    public int countNicePairs(int[] nums) {
        int MOD = 1000000000 + 7;
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int div = num - reverse(num);
            res = (res + map.getOrDefault(div, 0)) % MOD;
            map.put(div, map.getOrDefault(div, 0) + 1);
        }

        return res;
    }

    private int reverse(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int res = 1;
        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            int prev = arr[i - 1];
            int curr = arr[i];
            if (prev + 1 <= curr) {
                arr[i] = prev + 1;
                res = prev + 1;
            }
        }

        return res;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int leftIdx = l[i];
            int rightIdx = r[i];
            int[] sub = Arrays.copyOfRange(nums, leftIdx, rightIdx + 1);
            Arrays.sort(sub);

            int val = sub[1] - sub[0];
            boolean flag = true;

            for (int j = 2; j <= r[i] - l[i]; j++) {
                if (val != sub[j] - sub[j - 1]) {
                    flag = false;
                    break;
                }
            }
            res.add(flag);
        }

        return res;
    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int count = piles.length / 3;
        int idx = piles.length - 2;
        int res = 0;

        for (int i = 0; i < count; i++) {
            res += piles[idx];
            idx -= 2;
        }

        return res;
    }

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] + nums[i];

        for (int i = 0; i < n; i++) {
            int leftSum = prefix[i] - nums[i];
            int rightSum = prefix[n - 1] - prefix[i];

            int leftCount = i;
            int rightCount = n - 1 - i;

            int leftTotal = nums[i] * leftCount - leftSum;
            int rightTotal = rightSum - nums[i] * rightCount;
            res[i] = leftTotal + rightTotal;
        }
        return res;
    }

    int[][] knightDialer_dp;
    int knightDialer_MOD = (int) 1e9 + 7;
    int[][] knightDialer_moves = {
            {4, 6},
            {6, 8},
            {7, 9},
            {4, 8},
            {3, 9, 0},
            {},
            {1, 7, 0},
            {2, 6},
            {1, 3},
            {2, 4}
    };

    public int knightDialer(int n) {
        knightDialer_dp = new int[n + 1][10];
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res = (res + knightDialer_dp(n - 1, i)) % knightDialer_MOD;
        }
        return res;
    }

    private int knightDialer_dp(int remain, int now) {
        if (remain == 0) return 1;
        if (knightDialer_dp[remain][now] != 0) return knightDialer_dp[remain][now];
        int res = 0;
        for (int next : knightDialer_moves[now]) {
            res = (knightDialer_dp(remain - 1, next) + res) % knightDialer_MOD;
        }
        knightDialer_dp[remain][now] = res;
        return res;
    }

    public int largestSubmatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int res = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (matrix[row][col] != 0 && row > 0)
                    matrix[row][col] += matrix[row - 1][col];
            }

            int[] currRow = matrix[row].clone();
            Arrays.sort(currRow);
            for (int i = 0; i < m; i++) {
                res = Math.max(res, currRow[i] * (m - i));
            }
        }
        return res;
    }

    public int numberOfWays(String corridor) {
        int mod = (int) 1e9 + 7;
        char[] array = corridor.toCharArray();
        long res = 1;
        int chairs = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'S') {
                chairs++;
                while (++i < array.length && array[i] != 'S') ;
                if (i < array.length) {
                    chairs++;
                }

                int divisions = 1;
                while (++i < array.length && array[i] != 'S') {
                    divisions++;
                }

                if (1 < divisions && i < array.length) {
                    res = (res * divisions) % mod;
                }
                i--;
            }
        }
        return chairs != 0 && chairs % 2 == 0 ? (int) res : 0;
    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n = n >>> 1;
        }
        return res;
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        for (String word : word1) {
            for (char ch : word.toCharArray()) {
                sb1.append(ch);
            }
        }

        StringBuilder sb2 = new StringBuilder();
        for (String word : word2) {
            for (char ch : word.toCharArray()) {
                sb2.append(ch);
            }
        }

        return sb1.compareTo(sb2) == 0;
    }

    public int countCharacters(String[] words, String chars) {
        int[] charFrequency = new int[26];
        for (char ch : chars.toCharArray()) {
            charFrequency[ch - 'a']++;
        }

        int res = 0;
        for (String word : words) {
            int[] wordFrequency = new int[26];
            for (char ch : word.toCharArray()) {
                wordFrequency[ch - 'a']++;
            }

            boolean hasContains = true;
            for (int i = 0; i < 26; i++) {
                if (charFrequency[i] < wordFrequency[i]) {
                    hasContains = false;
                    break;
                }
            }
            if (hasContains) {
                res += word.length();
            }
        }
        return res;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int[] curr = points[i];
            int[] next = points[i + 1];

            int x = Math.abs(curr[0] - next[0]);
            int y = Math.abs(curr[1] - next[1]);
            res += Math.max(x, y);
        }
        return res;
    }

    public String largestGoodInteger(String num) {
        char[] ch = num.toCharArray();
        int n = num.length();
        int max = Integer.MIN_VALUE;

        for (int start = 0; start < n; start++) {
            int repeat = 1;
            while (start + 1 < n && ch[start] == ch[start + 1]) {
                start++;
                repeat++;
            }

            int current = Character.digit(ch[start], 10);
            if (2 < repeat && max < current) {
                max = current;
            }
        }

        return max == Integer.MIN_VALUE ? "" : String.valueOf(max).repeat(3);
    }

    public int numberOfMatches(int n) {
        int res = 0;
        while (0 < n) {
            if (n == 1) {
                break;
            }
            int matchesCount = n / 2;
            n = n - matchesCount;
            res += matchesCount;
        }
        return res;
    }

    public int totalMoney(int n) {
        int total = 0;
        int week;
        for (week = 0; week < n / 7; week++) {
            for (int i = 1; i <= 7; i++) {
                total += i + week;
            }
        }
        week++;
        for (int day = 0; day < n % 7; day++) {
            total += day + week;
        }

        return total;
    }

    public String largestOddNumber(String num) {
        int n = num.length();
        while (0 < n) {
            int sub = Integer.parseInt(num.substring(n - 1, n));
            if (sub % 2 == 1) {
                return num.substring(0, n);
            }
            n--;
        }
        return "";
    }

    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        tree2str_go(root, sb);
        return sb.toString();
    }

    private void tree2str_go(TreeNode node, StringBuilder sb) {
        sb.append(node.val);

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.left != null) {
            sb.append("(");
            tree2str_go(node.left, sb);
            sb.append(")");
        }

        if (node.right != null) {
            if (node.left == null) {
                sb.append("()");
            }

            sb.append("(");
            tree2str_go(node.right, sb);
            sb.append(")");
        }
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal_go(root, res);
        return res;
    }

    private void inorderTraversal_go(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }

        inorderTraversal_go(node.left, res);
        res.add(node.val);
        inorderTraversal_go(node.right, res);
    }

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] transpose = new int[n][m];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                transpose[col][row] = matrix[row][col];
            }
        }

        return transpose;
    }

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int[] candidates = new int[]{arr[n / 4], arr[n / 2], arr[3 * n / 4]};
        int target = n / 4;
        for (int candidate : candidates) {
            int leftIdx = findLeftmostIndex(arr, candidate);
            int rightIdx = findRightmostIndex(arr, candidate) - 1;
            if (target < rightIdx - leftIdx + 1) {
                return candidate;
            }
        }
        return -1;
    }

    private int findRightmostIndex(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int findLeftmostIndex(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target <= arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public int[][] onesMinusZeros(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Map<Integer, Count> rowFreqMap = new HashMap<>();
        Map<Integer, Count> colFreqMap = new HashMap<>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                Count rowFreq = rowFreqMap.getOrDefault(row, new Count());
                Count colFreq = colFreqMap.getOrDefault(col, new Count());

                if (grid[row][col] == 0) {
                    rowFreq.zeroCount++;
                    colFreq.zeroCount++;

                    rowFreqMap.put(row, rowFreq);
                    colFreqMap.put(col, colFreq);
                } else {
                    rowFreq.oneCount++;
                    colFreq.oneCount++;

                    rowFreqMap.put(row, rowFreq);
                    colFreqMap.put(col, colFreq);
                }
            }
        }

        int[][] res = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                res[row][col] = rowFreqMap.get(row).sum(colFreqMap.get(col));
            }
        }
        return res;
    }

    private class Count {
        int zeroCount, oneCount;

        public Count() {
            zeroCount = 0;
            oneCount = 0;
        }

        public int sum(Count c1) {
            return oneCount + c1.oneCount - zeroCount - c1.zeroCount;
        }
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }

    public int numSpecial(int[][] mat) {
        int res = 0;
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == 1 && checkRow(row, col, mat) && checkCol(row, col, mat)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean checkRow(int currRow, int currCol, int[][] mat) {
        boolean isSpecial = true;
        for (int row = 0; row < mat.length; row++) {
            if (row != currRow && mat[row][currCol] == 1) {
                isSpecial = false;
            }
        }
        return isSpecial;
    }

    private boolean checkCol(int currRow, int currCol, int[][] mat) {
        boolean isSpecial = true;
        for (int col = 0; col < mat[0].length; col++) {
            if (col != currCol && mat[currRow][col] == 1) {
                isSpecial = false;
            }
        }
        return isSpecial;
    }

    public String destCity(List<List<String>> paths) {
        Set<String> destination = new HashSet<>();
        for (List<String> path : paths) {
            String start = path.get(0);
            destination.add(start);
        }

        for (List<String> path : paths) {
            String dest = path.get(1);
            if (!destination.contains(dest)) {
                return dest;
            }
        }
        return "";
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];
        for (char ch: s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (char ch: t.toCharArray()) {
            if (freq[ch - 'a']-- == 0) {
                return false;
            }
        }
        return true;
    }


    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0] * nums[1];
        int max = nums[n - 1] * nums[n - 2];
        return max - min;
    }

    public int buyChoco(int[] prices, int money) {
        int cheapest = Integer.MAX_VALUE;
        int secondCheapest = Integer.MAX_VALUE;

        for (int price: prices) {
            if (price < cheapest) {
                secondCheapest = cheapest;
                cheapest = price;
            } else if (price < secondCheapest) {
                secondCheapest = price;
            }
        }

        int sum = cheapest + secondCheapest;
        if (sum <= money) {
            return money - sum;
        }
        return money;
    }


    public List<Integer> inorderTraversal3(TreeNode node) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            res = Math.max(res, points[i][0] - points[i - 1][0]);
        }
        return res;
    }

    public int maxScore(String s) {
        int ones = 0;
        for (char num: s.toCharArray()) {
            if (num == '1') {
                ones++;
            }
        }

        int res = 0;
        int zeroes = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char num = s.charAt(i);
            if (num == '0') {
                zeroes++;
            } else {
                ones--;
            }
            res = Math.max(res, zeroes + ones);
        }
        return res;
    }

    public int findDuplicate2(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public boolean isPathCrossing(String path) {
        Map<Character, int[]> map = new HashMap<>();
        map.put('N', new int[]{1, 0}); // {dy, dx}
        map.put('S', new int[]{-1, 0});
        map.put('E', new int[]{0, 1});
        map.put('W', new int[]{0, -1});

        int y = 0, x = 0;
        Set<String> visited = new HashSet<>();
        visited.add(x + "#" + y);

        for (char c: path.toCharArray()) {
            int[] tmp = map.get(c);
            y += tmp[0];
            x += tmp[1];
            String moved = x + "#" + y;
            if (visited.contains(moved)) {
                return true;
            }
            visited.add(moved);
        }
        return false;
    }

    public int minOperations(String s) {
        int zeroes = 0, ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '0') {
                    zeroes++;
                } else {
                    ones++;
                }
            } else {
                if (s.charAt(i) == '1') {
                    zeroes++;
                } else {
                    ones++;
                }
            }
        }
        return Math.min(zeroes, ones);
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; 0 <= i; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            dp[i] = dp[i + 1];
            if (i < n - 1 && (s.charAt(i) == '1'|| s.charAt(i) == '2'&& s.charAt(i+1) < '7')) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int[] a: dp) {
            Arrays.fill(a, -1);
        }
        return numRollsToTarget_go(n, k, target, dp);
    }
    int MOD = (int) 1e9 + 7;
    private int numRollsToTarget_go(int n, int k, int target, int[][] dp) {
        if (target == 0 && n == 0) {
            return 1;
        }
        if (target < 0 || n == 0) {
            return 0;
        }
        if (dp[n][target] != -1) {
            return dp[n][target];
        }

        int count = 0;
        for (int i = 1; i <= k; i++) {
            count = (count + numRollsToTarget_go(n - 1, k, target - i, dp)) % MOD;
        }
        return dp[n][target] = count;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        longestCommonSubsequence_a = text1.toCharArray();
        longestCommonSubsequence_b = text2.toCharArray();
        int[][] dp = new int[longestCommonSubsequence_a.length + 1][longestCommonSubsequence_b.length + 1];
        for (int[] a: dp) {
            Arrays.fill(a, -1);
        }
        return longestCommonSubsequence_go(0, 0, dp);
    }
    char[] longestCommonSubsequence_a, longestCommonSubsequence_b;
    private int longestCommonSubsequence_go(int i, int j, int[][] dp) {
        if (dp[i][j] < 0) {
            if (longestCommonSubsequence_a.length <= i || longestCommonSubsequence_b.length <= j) {
                dp[i][j] = 0;
            } else if (longestCommonSubsequence_a[i] == longestCommonSubsequence_b[j]) {
                dp[i][j] = longestCommonSubsequence_go(i + 1, j + 1, dp) + 1;
            } else {
                dp[i][j] = Math.max(longestCommonSubsequence_go(i + 1, j, dp), longestCommonSubsequence_go(i, j + 1, dp));
            }
        }
        return dp[i][j];
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return numSquares_go(n, dp) - 1;
    }
    private int numSquares_go(int target, int[] dp) {
        if (dp[target] < 0) {
            if (target == 0) {
                return dp[target] = 1;
            }

            int min = target;
            for (int i = 1; i * i <= target; i++) {
                min = Math.min(min, numSquares_go(target - i * i, dp) + 1);
            }
            dp[target] = min;
        }
        return dp[target];
    }

    public int minCost(String colors, int[] needTime) {
        int n = colors.length();
        int result = 0;
        int prevCost = needTime[0];

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                result += Math.min(needTime[i], prevCost);
                prevCost = Math.max(needTime[i], prevCost);
            } else
                prevCost = needTime[i];
        }

        return result;
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        int[][] dp = new int[101][101];
        for (int[] row: dp) {
            Arrays.fill(row, -1);
        }
        return getLengthOfOptimalCompression_go(s, 0, k, dp);
    }

    private int getLengthOfOptimalCompression_go(String s, int i, int k, int[][] dp) {
        int K = k;
        int n = s.length();
        if (n - i <= k) {
            return 0;
        }
        if (0 < dp[i][k]) {
            return dp[i][k];
        }

        int min = Integer.MAX_VALUE;
        if (0 < k) {
            min = getLengthOfOptimalCompression_go(s, i + 1, k - 1, dp);
        }

        int count = 1;
        for (int j = i + 1; j <= n; j++) {
            min = Math.min(min, getLengthOfOptimalCompression_getLen(count) + getLengthOfOptimalCompression_go(s, j, k, dp));
            if (j < n && s.charAt(i) == s.charAt(j)) {
                count++;
            } else if (--k < 0) {
                break;
            }
        }
        return dp[i][K] = min;
    }

    private int getLengthOfOptimalCompression_getLen(int count) {
        if (count == 1) return 1;
        else if (count < 10) return 2;
        else if (count < 100) return 3;
        else return 4;
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int sum = 0;
        for (int j : jobDifficulty) {
            sum += j;
        }
        if (sum == 0) {
            return 0;
        }

        int[][] dp = new int[d +1 ][n];
        for (int[] a: dp) {
            Arrays.fill(a, -1);
        }
        return minDifficulty_go(jobDifficulty, 0, d, dp);
    }

    private int minDifficulty_go(int[] jobDifficulty, int idx, int d, int[][] dp) {
        int n = jobDifficulty.length;
        if (d == 1) {
            int max = 0;
            for (int i = idx; i < n; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            return max;
        }
        if (0 < dp[d][idx]) {
            return dp[d][idx];
        }

        int max = 0;
        int res = Integer.MAX_VALUE;
        int stop = n - d + 1;
        for (int i = idx; i < stop; i++) {
            max = Math.max(max, jobDifficulty[i]);
            res = Math.min(res, max + minDifficulty_go(jobDifficulty, i + 1, d - 1, dp));
        }
        return dp[d][idx] = res;
    }

    public boolean makeEqual(String[] words) {
        if (words.length == 1) {
            return true;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (String w: words) {
            for (char c: w.toCharArray()) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }

        for (int val: freq.values()) {
            if (val % words.length != 0) {
                return false;
            }
        }
        return true;
    }

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k) + 1;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                res = Math.max(res, i - map.get(c) - 1);
            } else {
                map.put(c, i);
            }
        }
        return res;
    }

    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        int child = 0, cookie = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    public List<List<Integer>> findMatrix(int[] nums) {
        int[] freq = new int[nums.length + 1];
        List<List<Integer>> res = new ArrayList<>();
        for (int n: nums) {
            if (res.size() <= freq[n]) {
                res.add(new ArrayList<>());
            }

            res.get(freq[n]).add(n);
            freq[n]++;
        }
        return res;
    }

    public int numberOfBeams(String[] bank) {
        int res = 0;
        int prev = 0;
        for (String s: bank) {
            int cnt = 0;
            for (char c: s.toCharArray()) {
                if (c == '1') {
                    cnt++;
                }
            }
            if (cnt == 0) {
                continue;
            }

            if (0 < prev) {
                res += prev * cnt;
            }
            prev = cnt;
        }
        return res;
    }

    public int minOperations(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n: nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        int res = 0;
        for (int val: freq.values()) {
            if (val == 1) {
                return -1;
            }
            res += (int) Math.ceil((double) val / 3);
        }
        return res;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        return rangeSumBST_helper(root, low, high);
    }
    private int rangeSumBST_helper(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }

        int res = 0;
        if (low <= node.val && node.val <= high) {
            res += node.val;
        }
        res += rangeSumBST_helper(node.left, low, high);
        res += rangeSumBST_helper(node.right, low, high);
        return res;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] s1Freq = new int[26];
        for (char c: s1.toCharArray()) {
            s1Freq[c - 'a']++;
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2Freq = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                s2Freq[s2.charAt(i + j) - 'a']++;
            }
            if (checkInclusionHelper(s1Freq, s2Freq)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkInclusionHelper(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }

    public int search_2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target? left: -1;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        leafSimilarHelper(root1, l1);
        leafSimilarHelper(root2, l2);

        return l1.equals(l2);
    }
    private void leafSimilarHelper(TreeNode node, List<Integer> leaves) {
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
        }

        if (node.left != null) {
            leafSimilarHelper(node.left, leaves);
        }
        if (node.right != null) {
            leafSimilarHelper(node.right, leaves);
        }
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        generateGraph(root, 0, graph);

        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        queue.add(start);
        visited.put(start, 0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int node: graph.get(curr)) {
                if (visited.containsKey(node)) {
                    continue;
                }
                queue.offer(node);
                visited.put(node, visited.get(curr) + 1);
                res = Math.max(res, visited.get(node));
            }
        }
        return res;
    }
    private void generateGraph(TreeNode node, int parent, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }
        if (!graph.containsKey(node.val)) {
            graph.put(node.val, new ArrayList<>());
        }

        List<Integer> curr = graph.get(node.val);
        if (parent != 0) {
            curr.add(parent);
        }
        if (node.left != null) {
            curr.add(node.left.val);
        }
        if (node.right != null) {
            curr.add(node.right.val);
        }
        generateGraph(node.left, node.val, graph);
        generateGraph(node.right, node.val, graph);
    }

    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiff_go(root, root.val, root.val);
        return maxAncestorDiff_res;
    }
    int maxAncestorDiff_res = 0;
    private void maxAncestorDiff_go(TreeNode node, int max, int min) {
        if (node == null) {
            return;
        }

        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        maxAncestorDiff_res = Math.max(maxAncestorDiff_res, Math.abs(max - min));

        maxAncestorDiff_go(node.left, max, min);
        maxAncestorDiff_go(node.right, max, min);
    }

    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int mid = s.length() / 2;
        int cnt = 0;
        for (int i = 0; i < mid; i++) {
            if (vowels.contains(s.charAt(i))) {
                cnt++;
            }
        }

        for (int i = mid; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                cnt--;
            }
        }

        return cnt == 0;
    }

    public int minSteps(String s, String t) {
        int[] sFreq = new int[26];
        int[] tFreq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sFreq[s.charAt(i) - 'a']++;
            tFreq[t.charAt(i) - 'a']++;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (sFreq[i] < tFreq[i]) {
                res += tFreq[i] - sFreq[i];
            }
        }
        return res;
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] s1Freq = new int[26];
        int[] s2Freq = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            s1Freq[word1.charAt(i) - 'a']++;
            s2Freq[word2.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (s1Freq[i] == 0 && s2Freq[i] != 0 || s1Freq[i] != 0 && s2Freq[i] == 0) {
                return false;
            }
            map.put(s1Freq[i], map.getOrDefault(s1Freq[i], 0) + 1);
            map.put(s2Freq[i], map.getOrDefault(s2Freq[i], 0) - 1);
        }

        for (int val : map.values()) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> losses = new HashMap<>();
        for (int[] m: matches) {
            losses.put(m[0], losses.getOrDefault(m[0], 0));
            losses.put(m[1], losses.getOrDefault(m[1], 0) + 1);
        }

        List<List<Integer>> res = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        for (int player: losses.keySet()) {
            if (losses.get(player) <= 1) {
                res.get(losses.get(player)).add(player);
            }
        }

        Collections.sort(res.get(0));
        Collections.sort(res.get(1));
        return res;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n: arr) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        List<Integer> values = freq.values().stream().sorted().toList();
        for (int i = 1; i < values.size(); i++) {
            if (Objects.equals(values.get(i - 1), values.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, minFallingPathSum_go(0, i, matrix, dp));
        }

        return res;
    }
    public int minFallingPathSum_go(int row, int col, int[][] matrix, int[][] dp) {
        int n = matrix.length;
        if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }
        if (row == n - 1) {
            return dp[row][col] = matrix[row][col];
        }

        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        if (0 < col) {
            left = minFallingPathSum_go(row + 1, col - 1, matrix, dp);
        }
        if (col < n - 1) {
            right = minFallingPathSum_go(row + 1, col + 1, matrix, dp);
        }
        int straight = minFallingPathSum_go(row + 1, col, matrix, dp);

        return dp[row][col] = Math.min(left, Math.min(straight, right)) + matrix[row][col];
    }

    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                res[0] = nums[i];
                res[1] = i + 1;
                return res;
            }
        }
        return new int[] {-1, -1};
    }

    public int maxLength(List<String> arr) {
        int res = 0;
        for (int i = 0; i < arr.size(); i++) {
            Set<Character> chars = new HashSet<>();
            res = Math.max(res, maxLength_go(arr, i, chars));
        }
        return res;
    }

    private int maxLength_go(List<String> arr, int i, Set<Character> chars) {
        if (arr.size() <= i) {
            return 0;
        }
        Set<Character> tmp = new HashSet<>(chars);
        String word = arr.get(i);
        boolean flag = false;
        for (char c : word.toCharArray()) {
            if (tmp.contains(c))
                flag = true;
            tmp.add(c);
        }

        int res = maxLength_go(arr, i + 1, chars);
        if (!flag)
            res = Math.max(res, maxLength_go(arr, i + 1, tmp) + word.length());
        return res;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == destination) return true;
            for (int next: graph[now]) {
                if (!visited[next])
                    queue.offer(next);
                visited[next] = true;
            }
        }

        return false;
    }

    public int openLock(String[] deadends, String target) {
        StringBuilder start = new StringBuilder("0000");
        Queue<StringBuilder> queue = new LinkedList<>();
        queue.offer(start);

        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) {
            return -1;
        }

        int cnt = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                StringBuilder now = queue.poll();
                assert now != null;
                if (now.toString().equals(target)) {
                    return cnt;
                }

                for (int j = 0; j < 4; j++) {
                    for (char nextCh: openLock_getNextNums(now.charAt(j))) {
                        StringBuilder copy = new StringBuilder(now);
                        copy.setCharAt(j, nextCh);

                        if (visited.contains(copy.toString()))
                            continue;

                        queue.offer(copy);
                        visited.add(copy.toString());
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    private char[] openLock_getNextNums(char now) {
        if (now == '0')
            return new char[]{'1', '9'};
        else if (now == '9')
            return new char[] {'0', '8'};
        else {
            int num = Character.digit(now, 10);
            return new char[] {Character.forDigit(num - 1, 10), Character.forDigit(num + 1, 10)};
        }
    }

    public int threeSumClosest(int[] nums, int target) {
        int l = nums.length - 1;
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);

        for (int i = 0; i < l - 1; i++) {
            int j = i + 1;
            int k = l;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (graph[i].size() == 1) {
                leaves.add(i);
                visited.add(i);
            }
        }

        while (2 < n) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int now : leaves) {
                int next = graph[now].iterator().next();
                graph[next].remove(now);
                if (graph[next].size() == 1)
                    newLeaves.add(next);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public int longestIdealString(String s, int k) {
        int[] map = new int[26];
        for (char ch: s.toCharArray()) {
            int t = ch - 'a';
            int max = 0;
            for (int i = 0; i <= k; i++) {
                int adj1 = t + i;
                if (adj1 < 26) {
                    max = Math.max(max, map[adj1] + 1);
                }

                int adj2 = t - i;
                if (0 <= adj2) {
                    max = Math.max(max, map[adj2] + 1);
                }
            }
            map[t] = max;
        }

        int res = 0;
        for (int n: map) {
            res = Math.max(res, n);
        }
        return res;
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long res = 0;
        int turn = 0;
        for (int i = 0, j = happiness.length - 1; i < k; i++, j--) {
            int point = happiness[j] - turn++;
            if (0 < point) {
                res += point;
            }
            System.out.println(res);
        }
        return res;
    }
}