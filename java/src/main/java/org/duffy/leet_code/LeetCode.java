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
}
