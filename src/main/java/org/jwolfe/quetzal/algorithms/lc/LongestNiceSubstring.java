package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class LongestNiceSubstring {
    class Solution {
        public String longestNiceSubstring(String s) {
            if(s == null || s.length() < 1) {
                return s;
            }

            int n = s.length();
            for(int l = n; l >= 2; l--) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    if(isNice(s, i, j)) {
                        return s.substring(i, j + 1);
                    }
                }
            }

            return "";
        }

        private boolean isNice(String s, int i, int j) {
            Set<Character> set = new HashSet<>();
            for(int k = i; k <= j; k++) {
                set.add(s.charAt(k));
            }

            for(int k = i; k <= j; k++) {
                char c = s.charAt(k);
                if(c >= 'a' && c <= 'z') {
                    char uc = (char) ('A' + (c - 'a'));
                    if(!set.contains(uc)) {
                        return false;
                    }
                } else {
                    char lc = (char) ('a' + (c - 'A'));
                    if(!set.contains(lc)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}

//    1763. Longest Nice Substring
//    Easy
//    A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
//
//    Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.
//
//
//
//    Example 1:
//
//    Input: s = "YazaAay"
//    Output: "aAa"
//    Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
//    "aAa" is the longest nice substring.
//    Example 2:
//
//    Input: s = "Bb"
//    Output: "Bb"
//    Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
//    Example 3:
//
//    Input: s = "c"
//    Output: ""
//    Explanation: There are no nice substrings.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists of uppercase and lowercase English letters.