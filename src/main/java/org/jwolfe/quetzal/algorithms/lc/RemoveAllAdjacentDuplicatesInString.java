package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    class Solution {
        public String removeDuplicates(String S) {
            if(S == null || S.length() < 2) {
                return S;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);

                if(!stack.isEmpty() && stack.peek() == c) {
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

    class Solution_Correct_1 {
        public String removeDuplicates(String S) {
            if(S == null || S.length() < 2) {
                return S;
            }

            Stack<Character> stack = new Stack<>();
            stack.push(S.charAt(0));
            for(int i = 1; i < S.length(); i++) {
                char c = S.charAt(i);

                if(!stack.isEmpty() && c == stack.peek()) {
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

        public String removeDuplicatesApproach1(String S) {
            if(S == null || S.length() < 2) {
                return S;
            }

            boolean hadDuplicates = true;
            while(hadDuplicates) {
                String T = runRemoveDuplicates(S);
                if(T.length() == S.length()) {
                    hadDuplicates = false;
                }

                S = T;
            }

            return S;
        }


        private String runRemoveDuplicates(String S) {
            int n = S.length();
            StringBuilder builder = new StringBuilder();

            int i = 0;
            while(i < n) {
                char a = S.charAt(i);

                if(i + 1 == n) {
                    // Last character in string
                    builder.append(a);
                    break;
                }

                if(S.charAt(i + 1) == a) {
                    i+=2;
                } else {
                    builder.append(a);
                    i++;
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_2 {
        public String removeDuplicates(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else {
                    stack.pop();
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

//    1047. Remove All Adjacent Duplicates In String
//    Easy
//    You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
//
//    We repeatedly make duplicate removals on s until we no longer can.
//
//    Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
//
//
//
//    Example 1:
//
//    Input: s = "abbaca"
//    Output: "ca"
//    Explanation:
//    For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
//    Example 2:
//
//    Input: s = "azxxzy"
//    Output: "ay"
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.