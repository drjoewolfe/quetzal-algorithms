package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class UniqueLength3PalindromicSubsequences {
    class Solution {
        public int countPalindromicSubsequence(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            int n = s.length();
            int[] first = new int[26];
            int[] last = new int[26];

            Arrays.fill(first, -1);
            Arrays.fill(last, -1);

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int index = c - 'a';

                if (first[index] == -1) {
                    first[index] = i;
                } else {
                    last[index] = i;
                }
            }

            int count = 0;
            for (int i = 0; i < 26; i++) {
                if (first[i] == -1 || last[i] == -1 || last[i] - first[i] == 1) {
                    continue;
                }

                Set<Character> set = new HashSet<>();
                for (int j = first[i] + 1; j < last[i]; j++) {
                    set.add(s.charAt(j));
                }

                count += set.size();
            }

            return count;
        }
    }

    class Solution_Correct_2 {
        public int countPalindromicSubsequence(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            int[] firstIndex = new int[26];
            int[] lastIndex = new int[26];

            Arrays.fill(firstIndex, -1);
            Arrays.fill(lastIndex, -1);

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';

                if (firstIndex[index] == -1) {
                    firstIndex[index] = i;
                }

                lastIndex[index] = i;
            }

            int count = 0;
            for (int i = 0; i < 26; i++) {
                int a = firstIndex[i];
                if (a == -1) {
                    continue;
                }

                int b = lastIndex[i];
                Set<Character> seen = new HashSet<>();
                for (int j = a + 1; j < b; j++) {
                    seen.add(s.charAt(j));
                }

                count += seen.size();
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int countPalindromicSubsequence(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }

                map.get(c).add(i);
            }

            int count = 0;
            for (var entry : map.entrySet()) {
                var list = entry.getValue();

                int i = list.get(0);
                int k = list.get(list.size() - 1);
                Set<Character> seen = new HashSet<>();
                for (int j = i + 1; j < k; j++) {
                    seen.add(s.charAt(j));
                }

                count += seen.size();
            }

            return count;
        }
    }

    class Solution_TLE {
        public int countPalindromicSubsequence(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }

                map.get(c).add(i);
            }

            int count = 0;
            for (var entry : map.entrySet()) {
                var list = entry.getValue();

                Set<Character> seen = new HashSet<>();
                for (int i = 0; i < list.size() - 1; i++) {
                    int ii = list.get(i);
                    for (int k = i + 1; k < list.size(); k++) {
                        int kk = list.get(k);
                        for (int jj = ii + 1; jj < kk; jj++) {
                            char c = s.charAt(jj);
                            if (!seen.contains(c)) {
                                count++;
                                seen.add(c);
                            }
                        }
                    }
                }
            }

            return count;
        }
    }
}

//    1930. Unique Length-3 Palindromic Subsequences
//    Medium
//    Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
//
//    Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
//
//    A palindrome is a string that reads the same forwards and backwards.
//
//    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
//
//    For example, "ace" is a subsequence of "abcde".
//
//
//    Example 1:
//
//    Input: s = "aabca"
//    Output: 3
//    Explanation: The 3 palindromic subsequences of length 3 are:
//    - "aba" (subsequence of "aabca")
//    - "aaa" (subsequence of "aabca")
//    - "aca" (subsequence of "aabca")
//    Example 2:
//
//    Input: s = "adc"
//    Output: 0
//    Explanation: There are no palindromic subsequences of length 3 in "adc".
//    Example 3:
//
//    Input: s = "bbcbaba"
//    Output: 4
//    Explanation: The 4 palindromic subsequences of length 3 are:
//    - "bbb" (subsequence of "bbcbaba")
//    - "bcb" (subsequence of "bbcbaba")
//    - "bab" (subsequence of "bbcbaba")
//    - "aba" (subsequence of "bbcbaba")
//
//
//    Constraints:
//
//    3 <= s.length <= 105
//    s consists of only lowercase English letters.