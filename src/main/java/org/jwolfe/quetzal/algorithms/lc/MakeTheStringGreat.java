package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class MakeTheStringGreat {
    class Solution {
        public String makeGood(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(!stack.isEmpty()
                        && Math.abs(stack.peek() - c) == 32) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.insert(0, stack.pop());
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String makeGood(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            StringBuilder sb = new StringBuilder();
            while(!isGood(s)) {
                sb.setLength(0);
                int n = s.length();
                for(int i = 0; i < n; i++) {
                    if(i == n - 1) {
                        sb.append(s.charAt(i));
                        break;
                    }

                    char c = s.charAt(i);
                    char nc = s.charAt(i + 1);

                    if(isBadPair(c, nc)) {
                        i++;
                    } else {
                        sb.append(c);
                    }
                }

                s = sb.toString();
            }

            return s;
        }

        private boolean isGood(String s) {
            for(int i = 0; i < s.length() - 1; i++) {
                char c = s.charAt(i);
                char nc = s.charAt(i + 1);

                if(isBadPair(c, nc)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isBadPair(char c1, char c2) {
            // System.out.println(c1 + " - " + c2 + " = " + Math.abs(c1 - c2));
            return Math.abs(c1 - c2) == 32;
        }
    }

// "leEeetcode"
// "abBAcC"
}

//    1544. Make The String Great
//    Easy
//    Given a string s of lower and upper case English letters.
//
//    A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
//
//    0 <= i <= s.length - 2
//    s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
//    To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
//
//    Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
//
//    Notice that an empty string is also good.
//
//
//
//    Example 1:
//
//    Input: s = "leEeetcode"
//    Output: "leetcode"
//    Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
//    Example 2:
//
//    Input: s = "abBAcC"
//    Output: ""
//    Explanation: We have many possible scenarios, and all lead to the same answer. For example:
//    "abBAcC" --> "aAcC" --> "cC" --> ""
//    "abBAcC" --> "abBA" --> "aA" --> ""
//    Example 3:
//
//    Input: s = "s"
//    Output: "s"
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s contains only lower and upper case English letters.