package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubstringII {
    class Solution {
        public int longestBalanced(String s) {
            char[] c = s.toCharArray();
            int n = c.length;

            int cur_a = 0, cur_b = 0, cur_c = 0;
            int max_a = 0, max_b = 0, max_c = 0;

            for (int i = 0; i < n; i++) {
                if (c[i] == 'a') {
                    cur_a = (i > 0 && c[i - 1] == 'a') ? cur_a + 1 : 1;
                    max_a = Math.max(max_a, cur_a);
                } else if (c[i] == 'b') {
                    cur_b = (i > 0 && c[i - 1] == 'b') ? cur_b + 1 : 1;
                    max_b = Math.max(max_b, cur_b);
                } else {
                    cur_c = (i > 0 && c[i - 1] == 'c') ? cur_c + 1 : 1;
                    max_c = Math.max(max_c, cur_c);
                }
            }

            int res = Math.max(Math.max(max_a, max_b), max_c);

            res = Math.max(res, find2(c, 'a', 'b'));
            res = Math.max(res, find2(c, 'a', 'c'));
            res = Math.max(res, find2(c, 'b', 'c'));

            res = Math.max(res, find3(c));

            return res;
        }

        private int find2(char[] c, char x, char y) {
            int n = c.length, max_len = 0;
            int[] first = new int[2 * n + 1];
            Arrays.fill(first, -2);

            int clear_idx = -1, diff = n;
            first[diff] = -1;

            for (int i = 0; i < n; i++) {
                if (c[i] != x && c[i] != y) {
                    clear_idx = i;
                    diff = n;
                    first[diff] = clear_idx;
                } else {
                    if (c[i] == x) diff++;
                    else diff--;

                    if (first[diff] < clear_idx) {
                        first[diff] = i;
                    } else {
                        max_len = Math.max(max_len, i - first[diff]);
                    }
                }
            }

            return max_len;
        }

        private int find3(char[] c) {
            long state = Long.MAX_VALUE / 2;
            Map<Long, Integer> first = new HashMap<>();
            first.put(state, -1);

            int max_len = 0;

            for (int i = 0; i < c.length; i++) {
                if (c[i] == 'a') state += 1_000_001;
                else if (c[i] == 'b') state -= 1_000_000;
                else state--;

                if (first.containsKey(state)) {
                    max_len = Math.max(max_len, i - first.get(state));
                } else {
                    first.put(state, i);
                }
            }

            return max_len;
        }
    }

    class Solution_TLE {
        public int longestBalanced(String s) {
            if (s == null) {
                return 0;
            }

            int n = s.length();
            int maxLength = 0;

            // Case 1: Longest streak of one character;
            int oneCharLength = 1;
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                char pc = s.charAt(i - 1);

                if (c != pc) {
                    maxLength = Math.max(maxLength, oneCharLength);
                    oneCharLength = 1;
                } else {
                    oneCharLength++;
                }
            }

            maxLength = Math.max(maxLength, oneCharLength);

            // Case 2: Longest streak with two characters;
            maxLength = Math.max(maxLength, longestStreakOf2(s, 'a', 'b'));
            maxLength = Math.max(maxLength, longestStreakOf2(s, 'a', 'c'));
            maxLength = Math.max(maxLength, longestStreakOf2(s, 'b', 'c'));

            // Case 3: Longest streak with three characters;
            int countA = 0;
            int countB = 0;
            int countC = 0;

            Map<Long, Integer> map = new HashMap<>();
            map.put(0L, -1);
            // Map<String, Integer> map = new HashMap<>();
            // map.put("0-0", -1);
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == 'a') {
                    countA++;
                } else if (c == 'b') {
                    countB++;
                } else {
                    countC++;
                }

                if (countA == countB && countB == countC) {
                    // Balanced
                    int length = countA + countB + countC;
                    maxLength = Math.max(maxLength, length);
                }

                int diffAB = countA - countB;
                int diffAC = countA - countC;

                // String key = Integer.toString(diffAB) + "-" + Integer.toString(diffAC);
                long key = (((long) diffAB) << 32) | (diffAC & 0xffffffffL);
                if (map.containsKey(key)) {
                    int length = i - map.get(key);
                    maxLength = Math.max(maxLength, length);
                } else {
                    map.put(key, i);
                }
            }

            return maxLength;
        }

        private int longestStreakOf2(String s, char c1, char c2) {
            int maxLength = 0;

            int count1 = 0;
            int count2 = 0;

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c != c1 && c != c2) {
                    count1 = 0;
                    count2 = 0;

                    map.clear();
                    map.put(0, i);
                    continue;
                }

                if (c == c1) {
                    count1++;
                } else if (c == c2) {
                    count2++;
                }

                if (count1 == count2) {
                    // Balanced
                    int length = count1 + count2;
                    maxLength = Math.max(maxLength, length);
                }

                int diff = count1 - count2;
                int key = diff;
                if (map.containsKey(key)) {
                    int length = i - map.get(key);
                    maxLength = Math.max(maxLength, length);
                } else {
                    map.put(key, i);
                }
            }

            return maxLength;
        }
    }
}

//    3714. Longest Balanced Substring II
//    Medium
//    You are given a string s consisting only of the characters 'a', 'b', and 'c'.
//
//    A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
//
//    Return the length of the longest balanced substring of s.
//
//
//
//    Example 1:
//
//    Input: s = "abbac"
//
//    Output: 4
//
//    Explanation:
//
//    The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.
//
//    Example 2:
//
//    Input: s = "aabcc"
//
//    Output: 3
//
//    Explanation:
//
//    The longest balanced substring is "abc" because all distinct characters 'a', 'b' and 'c' each appear exactly 1 time.
//
//    Example 3:
//
//    Input: s = "aba"
//
//    Output: 2
//
//    Explanation:
//
//    One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".
//
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s contains only the characters 'a', 'b', and 'c'.