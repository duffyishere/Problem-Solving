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
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                }
                else if (sum < target) {
                    j++;
                }
                else {
                    k--;
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
}
