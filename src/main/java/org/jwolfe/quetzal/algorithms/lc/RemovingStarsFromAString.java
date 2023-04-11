package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemovingStarsFromAString {
    class Solution {
        public String removeStars(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            char[] sb = s.toCharArray();

            int j = 0;
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(c == '*') {
                    j--;
                } else {
                    sb[j++] = c;
                }
            }

            return String.valueOf(sb).substring(0, j);
        }
    }

    class Solution_Correct_1 {
        public String removeStars(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            char[] sb = s.toCharArray();

            int j = 0;
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(c == '*') {
                    j--;
                } else {
                    sb[j] = c;
                    j++;
                }
            }

            return String.valueOf(sb).substring(0, j);
        }
    }

    class Solution_Stack {
        public String removeStars(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '*') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            return builder.reverse().toString();
        }
    }
}

//    2390. Removing Stars From a String
//    Medium
//    You are given a string s, which contains stars *.
//
//    In one operation, you can:
//
//    Choose a star in s.
//    Remove the closest non-star character to its left, as well as remove the star itself.
//    Return the string after all stars have been removed.
//
//    Note:
//
//    The input will be generated such that the operation is always possible.
//    It can be shown that the resulting string will always be unique.
//
//
//    Example 1:
//
//    Input: s = "leet**cod*e"
//    Output: "lecoe"
//    Explanation: Performing the removals from left to right:
//    - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
//    - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
//    - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
//    There are no more stars, so we return "lecoe".
//    Example 2:
//
//    Input: s = "erase*****"
//    Output: ""
//    Explanation: The entire string is removed, so we return an empty string.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters and stars *.
//    The operation above can be performed on s.