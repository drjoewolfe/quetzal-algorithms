package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class ValidParenthesisString {
    class Solution {
        public boolean checkValidString(String s) {
            if(s == null || s.length() == 0) {
                return true;
            }

            int openCount = 0;
            int closeCount = 0;

            int n = s.length();

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(c == '(' || c == '*') {
                    openCount++;
                } else {
                    openCount--;
                }

                int li = n - 1 - i;
                int lc = s.charAt(li);

                if(lc == ')' || lc == '*') {
                    closeCount++;
                } else {
                    closeCount--;
                }

                if(openCount < 0 || closeCount < 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean checkValidString(String s) {
            if(s == null || s.length() == 0) {
                return true;
            }

            Stack<Integer> openStack = new Stack<>();
            Stack<Integer> starStack = new Stack<>();

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '(') {
                    openStack.push(i);
                } else if(c == '*') {
                    starStack.push(i);
                } else if(c == ')') {
                    if(openStack.size() > 0) {
                        openStack.pop();
                    } else if(starStack.size() > 0) {
                        starStack.pop();
                    } else {
                        return false;
                    }
                }
            }

            while(openStack.size() > 0) {
                if(starStack.size() == 0) {
                    return false;
                }

                int oi = openStack.pop();
                int si = starStack.pop();
                if(si < oi) {
                    return false;
                }
            }

            return true;
        }
    }

// "()"
// "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"
}

//    678. Valid Parenthesis String
//    Medium
//    Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
//
//    The following rules define a valid string:
//
//    Any left parenthesis '(' must have a corresponding right parenthesis ')'.
//    Any right parenthesis ')' must have a corresponding left parenthesis '('.
//    Left parenthesis '(' must go before the corresponding right parenthesis ')'.
//    '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
//
//
//    Example 1:
//
//    Input: s = "()"
//    Output: true
//    Example 2:
//
//    Input: s = "(*)"
//    Output: true
//    Example 3:
//
//    Input: s = "(*))"
//    Output: true
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s[i] is '(', ')' or '*'.