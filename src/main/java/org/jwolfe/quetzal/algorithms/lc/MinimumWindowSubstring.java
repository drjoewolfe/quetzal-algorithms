package org.jwolfe.quetzal.algorithms.lc;

public class MinimumWindowSubstring {
    class Solution {
        public String minWindow(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return "";
            }

            int m = s.length();
            int n = t.length();

            int[] patternFrequencies = getFrequencies(t);
            int[] subFrequencies = new int[58];

            int i = 0;
            int minLength = Integer.MAX_VALUE;
            String minSubstring = "";
            for(int j = 0; j < m; j++) {
                subFrequencies[s.charAt(j) - 'A']++;

                while(isSubset(patternFrequencies, subFrequencies)) {
                    int l = j - i + 1;
                    if(minLength > l) {
                        minLength = l;
                        minSubstring = s.substring(i, j + 1);
                    }

                    subFrequencies[s.charAt(i) - 'A']--;
                    i++;
                }
            }

            return minSubstring;
        }

        private int[] getFrequencies(String s) {
            int[] frequencies = new int[58];
            for(int i = 0; i < s.length(); i++) {
                frequencies[s.charAt(i) - 'A']++;
            }

            return frequencies;
        }

        private boolean isSubset(int[] patternFrequencies, int[] subFrequencies) {
            for(int i = 0; i < 58; i++) {
                if(patternFrequencies[i] > subFrequencies[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_N2 {
        public String minWindow(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return "";
            }

            int m = s.length();
            int n = t.length();

            int[] patternFrequencies = getFrequencies(t);
            for(int l = n; l <= m; l++) {
                for(int i = 0; i + l <= m; i++) {
                    String sub = s.substring(i, i + l);
                    int[] subFrequencies = getFrequencies(sub);
                    if(isSubset(patternFrequencies, subFrequencies)) {
                        return sub;
                    }
                }
            }

            return "";
        }

        private int[] getFrequencies(String s) {
            int[] frequencies = new int[58];
            for(int i = 0; i < s.length(); i++) {
                frequencies[s.charAt(i) - 'A']++;
            }

            return frequencies;
        }

        private boolean isSubset(int[] patternFrequencies, int[] subFrequencies) {
            for(int i = 0; i < 58; i++) {
                if(patternFrequencies[i] > subFrequencies[i]) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    76. Minimum Window Substring
//    Hard
//    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//    The testcases will be generated such that the answer is unique.
//
//    A substring is a contiguous sequence of characters within the string.
//
//
//
//    Example 1:
//
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"
//    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//    Example 2:
//
//    Input: s = "a", t = "a"
//    Output: "a"
//    Explanation: The entire string s is the minimum window.
//    Example 3:
//
//    Input: s = "a", t = "aa"
//    Output: ""
//    Explanation: Both 'a's from t must be included in the window.
//    Since the largest window of s only has one 'a', return empty string.
//
//
//    Constraints:
//
//    m == s.length
//    n == t.length
//    1 <= m, n <= 105
//    s and t consist of uppercase and lowercase English letters.
//
//
//    Follow up: Could you find an algorithm that runs in O(m + n) time?
