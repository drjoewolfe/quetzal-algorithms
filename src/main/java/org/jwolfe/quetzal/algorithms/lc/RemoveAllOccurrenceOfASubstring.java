package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemoveAllOccurrenceOfASubstring {
    class Solution {
        public String removeOccurrences(String s, String part) {
            if (s == null || part == null || s.length() < part.length()) {
                return s;
            }

            Stack<Character> stack = new Stack<>();
            int n = s.length();

            int pn = part.length();
            char plc = part.charAt(part.length() - 1);

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                stack.push(c);

                if (stack.size() >= pn && c == plc) {
                    if (checkForPart(stack, part)) {
                        for (int j = 0; j < pn; j++) {
                            stack.pop();
                        }
                    }
                }
            }

            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            builder.reverse();
            return builder.toString();
        }

        private boolean checkForPart(Stack<Character> stack, String part) {
            Stack<Character> temp = new Stack<>();
            temp.addAll(stack);

            int pl = part.length();
            for (int pi = pl - 1; pi >= 0; pi--) {
                char sc = temp.pop();
                int pc = part.charAt(pi);
                if (sc != pc) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    1910. Remove All Occurrences of a Substring
//    Medium
//    Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
//
//    Find the leftmost occurrence of the substring part and remove it from s.
//    Return s after removing all occurrences of part.
//
//    A substring is a contiguous sequence of characters in a string.
//
//
//
//    Example 1:
//
//    Input: s = "daabcbaabcbc", part = "abc"
//    Output: "dab"
//    Explanation: The following operations are done:
//    - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
//    - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
//    - s = "dababc", remove "abc" starting at index 3, so s = "dab".
//    Now s has no occurrences of "abc".
//    Example 2:
//
//    Input: s = "axxxxyyyyb", part = "xy"
//    Output: "ab"
//    Explanation: The following operations are done:
//    - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
//    - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
//    - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
//    - s = "axyb", remove "xy" starting at index 1 so s = "ab".
//    Now s has no occurrences of "xy".
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    1 <= part.length <= 1000
//    s​​​​​​ and part consists of lowercase English letters.