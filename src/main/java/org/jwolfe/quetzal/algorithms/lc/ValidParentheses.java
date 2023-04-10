package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class ValidParentheses {
    class Solution {
        public boolean isValid(String s) {
            if(s == null || s.length() == 0) {
                return true;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if(stack.size() == 0) {
                        return false;
                    }

                    char oc = stack.pop();
                    if(!((oc == '(' && c == ')')
                            || (oc == '[' && c == ']')
                            || (oc == '{' && c == '}'))) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }
    }

    class Solution_Correct_1 {
        public boolean isValid(String s) {
            if(s == null || s.length() == 0) {
                return true;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == ')' || c == '}' || c == ']') {
                    if(stack.size() == 0) {
                        return false;
                    }

                    char pc = stack.pop();
                    if(!((pc == '(' && c == ')') || (pc == '{' && c == '}') || (pc == '[' && c == ']'))) {
                        return false;
                    }

                } else {
                    stack.push(c);
                }
            }

            if(stack.size() != 0) {
                return false;
            }

            return true;
        }
    }
}

//    20. Valid Parentheses
//    Easy
//    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//    An input string is valid if:
//
//    Open brackets must be closed by the same type of brackets.
//    Open brackets must be closed in the correct order.
//    Every close bracket has a corresponding open bracket of the same type.
//
//
//    Example 1:
//
//    Input: s = "()"
//    Output: true
//    Example 2:
//
//    Input: s = "()[]{}"
//    Output: true
//    Example 3:
//
//    Input: s = "(]"
//    Output: false
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s consists of parentheses only '()[]{}'.