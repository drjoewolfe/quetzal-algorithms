package org.jwolfe.quetzal.algorithms.lc;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class RemoveDuplicateLetters {
    class Solution {
        public String removeDuplicateLetters(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int[] lastIndex = new int[26];
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                lastIndex[index] = i;
            }

            boolean[] visited = new boolean[26];
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(visited[c - 'a']) {
                    continue;
                }

                while(!stack.isEmpty()
                        && stack.peek() > c) {
                    char pc = stack.peek();

                    if(lastIndex[pc - 'a'] > i) {
                        stack.pop();
                        visited[pc - 'a'] = false;
                    } else {
                        break;
                    }
                }

                stack.push(c);
                visited[c - 'a'] = true;
            }


            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            return builder.reverse().toString();
        }
    }

    class Solution_Correct_1 {
        public String removeDuplicateLetters(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int n = s.length();

            int[] lastIndex = new int[26];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                lastIndex[c - 'a'] = i;
            }

            boolean[] visited = new boolean[26];
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(visited[c - 'a']) {
                    continue;
                }

                while(!stack.isEmpty() && stack.peek() > c) {
                    char pc = stack.peek();
                    if(lastIndex[pc - 'a'] > i) {
                        stack.pop();
                        visited[pc - 'a'] = false;
                    } else {
                        break;
                    }
                }

                stack.push(c);
                visited[c - 'a'] = true;
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            return builder.reverse().toString();
        }
    }

    class Solution_IncorrectAgain {
        public String removeDuplicateLetters(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            Set<Character> set = new TreeSet<>();
            for(int i = 0; i < s.length(); i++) {
                set.add(s.charAt(i));
            }

            StringBuilder builder = new StringBuilder();
            for(char c : set) {
                builder.append(c);
            }

            return builder.toString();
        }
    }

    class Solution_Incorrect {
        public String removeDuplicateLetters(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int[] counters = new int[26];
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                counters[c - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                if(counters[i] > 0) {
                    builder.append((char) ('a' + i));
                }
            }

            return builder.toString();
        }
    }


// "bcabc"
// "cbacdcbc"
}

//    316. Remove Duplicate Letters
//    Medium
//    Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
//
//
//    Example 1:
//
//    Input: s = "bcabc"
//    Output: "abc"
//    Example 2:
//
//    Input: s = "cbacdcbc"
//    Output: "acdb"
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s consists of lowercase English letters.
//
//
//    Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/