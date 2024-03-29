package org.jwolfe.quetzal.algorithms.lc;

public class MinimumWindowSubstring {
    class Solution {
        public String minWindow(String s, String t) {
            if(s == null || t == null || t.length() == 0 || s.length() < t.length()) {
                return "";
            }

            int n = s.length();

            int left = 0;
            int right = 0;

            int[] patternFrequencies = getFrequencies(t);
            int[] stringFrequencies = new int[58];

            int minWindowSize = Integer.MAX_VALUE;
            int minWindowLeft = -1;
            int minWindowRight = -1;

            while(right < n) {
                char c = s.charAt(right);
                int ci = c - 'A';

                stringFrequencies[ci]++;

                while(isSubset(stringFrequencies, patternFrequencies)) {
                    int length = right - left + 1;
                    if(minWindowSize > length) {
                        minWindowSize = length;
                        minWindowLeft = left;
                        minWindowRight = right;
                    }

                    char lc = s.charAt(left);
                    int lci = lc - 'A';

                    stringFrequencies[lci]--;
                    left++;
                }

                right++;
            }

            if(minWindowSize == Integer.MAX_VALUE) {
                return "";
            }

            return s.substring(minWindowLeft, minWindowRight + 1);
        }

        private int[] getFrequencies(String str) {
            int[] frequencies = new int[58];
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                frequencies[c - 'A']++;
            }

            return frequencies;
        }

        private boolean isSubset(int[] superSet, int[] candidateSet) {
            for(int i = 0; i < 58; i++) {
                if(candidateSet[i] > superSet[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public String minWindow(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return "";
            }

            int m = s.length();
            int n = t.length();

            int[] patternFrequencies = getFrequencies(t);
            int[] windowFrequencies = new int[58];

            int left = 0;
            int right = 0;

            int minLength = Integer.MAX_VALUE;
            int leftForMin = -1;
            int rightForMin = -1;

            while(right < m) {
                char rc = s.charAt(right);
                windowFrequencies[rc - 'A']++;

                while(isSubset(patternFrequencies, windowFrequencies)) {
                    int length = right - left + 1;

                    if(minLength > length) {
                        minLength = length;
                        leftForMin = left;
                        rightForMin = right;
                    }

                    char lc = s.charAt(left);
                    windowFrequencies[lc - 'A']--;

                    left++;
                }

                right++;
            }

            if(minLength == Integer.MAX_VALUE) {
                return "";
            }

            return s.substring(leftForMin, rightForMin + 1);
        }

        private int[] getFrequencies(String s) {
            int[] frequencies = new int[58];
            for(int i = 0; i < s.length(); i++) {
                frequencies[s.charAt(i) - 'A']++;
            }

            return frequencies;
        }

        private boolean isSubset(int[] frequencySet1, int[] frequencySet2) {
            for(int i = 0; i < 58; i++) {
                if(frequencySet1[i] > frequencySet2[i]) {
                    return false;
                }
            }

            return true;
        }

        private void print(int[] arr) {
            for(int cell : arr) {
                System.out.print(cell + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
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
