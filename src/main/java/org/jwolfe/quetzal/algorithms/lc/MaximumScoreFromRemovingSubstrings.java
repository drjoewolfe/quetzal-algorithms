package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class MaximumScoreFromRemovingSubstrings {
    class Solution {
        public int maximumGain(String s, int x, int y) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int score = 0;

            char c1 = x > y ? 'a' : 'b';
            char c2 = x > y ? 'b' : 'a';
            int z = x > y ? x : y;

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c1 && c == c2) {
                    stack.pop();
                    score += z;
                } else {
                    stack.push(c);
                }
            }

            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            s = builder.reverse().toString();

            c1 = x > y ? 'b' : 'a';
            c2 = x > y ? 'a' : 'b';
            z = x > y ? y : x;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c1 && c == c2) {
                    stack.pop();
                    score += z;
                } else {
                    stack.push(c);
                }
            }

            return score;
        }
    }
}

//    1717. Maximum Score From Removing Substrings
//    Medium
//    You are given a string s and two integers x and y. You can perform two types of operations any number of times.
//
//    Remove substring "ab" and gain x points.
//    For example, when removing "ab" from "cabxbae" it becomes "cxbae".
//    Remove substring "ba" and gain y points.
//    For example, when removing "ba" from "cabxbae" it becomes "cabxe".
//    Return the maximum points you can gain after applying the above operations on s.
//
//
//
//    Example 1:
//
//    Input: s = "cdbcbbaaabab", x = 4, y = 5
//    Output: 19
//    Explanation:
//    - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
//    - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
//    - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
//    - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
//    Total score = 5 + 4 + 5 + 5 = 19.
//    Example 2:
//
//    Input: s = "aabbaaxybbaabb", x = 5, y = 4
//    Output: 20
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    1 <= x, y <= 104
//    s consists of lowercase English letters.