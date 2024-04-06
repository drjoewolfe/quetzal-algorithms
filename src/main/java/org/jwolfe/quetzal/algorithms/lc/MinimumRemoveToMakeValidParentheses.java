package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses {
    class Solution {
        public String minRemoveToMakeValid(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            Set<Integer> indicesToRemove = new HashSet<>();
            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(c == '(') {
                    stack.push(i);
                } else if(c == ')') {
                    if(stack.isEmpty()) {
                        indicesToRemove.add(i);
                    } else {
                        stack.pop();
                    }
                }
            }

            while(!stack.isEmpty()) {
                indicesToRemove.add(stack.pop());
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < n; i++) {
                if(!indicesToRemove.contains(i)) {
                    builder.append(s.charAt(i));
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_2 {
        public String minRemoveToMakeValid(String s) {
            if(s == null || s.length() == 0) {
                return "";
            }

            Set<Integer> indexes = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '(') {
                    stack.push(i);
                } else if(c == ')') {
                    if(!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        indexes.add(i);
                    }
                }
            }

            while(!stack.isEmpty()) {
                indexes.add(stack.pop());
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < s.length(); i++) {
                if(indexes.contains(i)) {
                    continue;
                }

                builder.append(s.charAt(i));
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String minRemoveToMakeValid(String s) {
            if(s == null || s.length() == 0) {
                return null;
            }

            Set<Integer> indicesToRemove = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '(') {
                    stack.push(i);
                } else if(c == ')') {
                    if(stack.isEmpty()) {
                        indicesToRemove.add(i);
                    } else {
                        stack.pop();
                    }
                }
            }

            while(!stack.isEmpty()) {
                indicesToRemove.add(stack.pop());
            }

            StringBuilder result = new StringBuilder();
            for(int i = 0; i < s.length(); i++) {
                if(indicesToRemove.contains(i)) {
                    continue;
                }

                result.append(s.charAt(i));
            }

            return result.toString();
        }
    }
}

//    1249. Minimum Remove to Make Valid Parentheses
//    Medium
//    Given a string s of '(' , ')' and lowercase English characters.
//
//    Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
//
//    Formally, a parentheses string is valid if and only if:
//
//    It is the empty string, contains only lowercase characters, or
//    It can be written as AB (A concatenated with B), where A and B are valid strings, or
//    It can be written as (A), where A is a valid string.
//
//
//    Example 1:
//
//    Input: s = "lee(t(c)o)de)"
//    Output: "lee(t(c)o)de"
//    Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
//    Example 2:
//
//    Input: s = "a)b(c)d"
//    Output: "ab(c)d"
//    Example 3:
//
//    Input: s = "))(("
//    Output: ""
//    Explanation: An empty string is also valid.
//    Example 4:
//
//    Input: s = "(a(b(c)d)"
//    Output: "a(b(c)d)"
//
//
//    Constraints:
//
//    1 <= s.length <= 10^5
//    s[i] is one of  '(' , ')' and lowercase English letters.