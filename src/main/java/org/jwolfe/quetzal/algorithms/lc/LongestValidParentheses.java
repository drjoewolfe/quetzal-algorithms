package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class LongestValidParentheses {
    class Solution {
        public int longestValidParentheses(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int maxLength = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if(!stack.isEmpty()) {
                        int length = i - stack.peek();
                        maxLength = Math.max(maxLength, length);
                    } else {
                        stack.push(i);
                    }
                }
            }

            return maxLength;
        }
    }

    class Solution_N2 {
        public int longestValidParentheses(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int maxLength = 0;
            for(int i = 0; i < s.length() - 1; i++) {
                int length = longestBalancedLength(s, i);
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }

        private int longestBalancedLength(String s, int i) {
            int length = 0;

            Stack<Character> stack = new Stack<>();
            for(int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if(c == '(') {
                    stack.push(c);
                } else {
                    if(!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return length;
                    }
                }

                if(stack.size() == 0) {
                    length = j - i + 1;
                }
            }

            return length;
        }
    }

// "(()"
// ")()())"
}

//    32. Longest Valid Parentheses
//    Hard
//    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//
//
//
//    Example 1:
//
//    Input: s = "(()"
//    Output: 2
//    Explanation: The longest valid parentheses substring is "()".
//    Example 2:
//
//    Input: s = ")()())"
//    Output: 4
//    Explanation: The longest valid parentheses substring is "()()".
//    Example 3:
//
//    Input: s = ""
//    Output: 0
//
//
//    Constraints:
//
//    0 <= s.length <= 3 * 104
//    s[i] is '(', or ')'.
