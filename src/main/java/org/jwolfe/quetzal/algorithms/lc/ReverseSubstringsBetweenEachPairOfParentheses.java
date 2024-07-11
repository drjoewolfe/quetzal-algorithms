package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class ReverseSubstringsBetweenEachPairOfParentheses {
    class Solution {
        public String reverseParentheses(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();

            int[] pairedIndices = new int[n];
            Stack<Integer> openIndices = new Stack<>();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    openIndices.push(i);
                } else if (c == ')') {
                    int openIndex = openIndices.pop();
                    pairedIndices[openIndex] = i;
                    pairedIndices[i] = openIndex;
                }
            }

            int direction = 1;
            int index = 0;
            StringBuilder result = new StringBuilder();
            for (; index < n; index += direction) {
                char c = s.charAt(index);

                if (c == '(' || c == ')') {
                    index = pairedIndices[index];
                    direction = -direction;
                } else {
                    result.append(c);
                }
            }

            return result.toString();
        }
    }

    class Solution_Correct_1 {
        public String reverseParentheses(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ')') {
                    StringBuilder builder = new StringBuilder();
                    while (stack.peek() != '(') {
                        builder.append(stack.pop());
                    }

                    stack.pop();
                    for (int j = 0; j < builder.length(); j++) {
                        stack.push(builder.charAt(j));
                    }
                } else {
                    stack.push(c);
                }
            }

            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.insert(0, stack.pop());
            }

            return builder.toString();
        }
    }
}

//    1190. Reverse Substrings Between Each Pair of Parentheses
//    Medium
//    You are given a string s that consists of lower case English letters and brackets.
//
//    Reverse the strings in each pair of matching parentheses, starting from the innermost one.
//
//    Your result should not contain any brackets.
//
//
//
//    Example 1:
//
//    Input: s = "(abcd)"
//    Output: "dcba"
//    Example 2:
//
//    Input: s = "(u(love)i)"
//    Output: "iloveu"
//    Explanation: The substring "love" is reversed first, then the whole string is reversed.
//    Example 3:
//
//    Input: s = "(ed(et(oc))el)"
//    Output: "leetcode"
//    Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
//
//
//    Constraints:
//
//    1 <= s.length <= 2000
//    s only contains lower case English characters and parentheses.
//    It is guaranteed that all parentheses are balanced.