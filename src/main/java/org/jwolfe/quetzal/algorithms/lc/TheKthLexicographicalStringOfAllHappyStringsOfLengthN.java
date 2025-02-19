package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    class Solution {
        public String getHappyString(int n, int k) {
            if (n < 1 || k < 1) {
                return "";
            }

            List<String> happyStrings = new ArrayList<>();
            generateHappyStrings(n, "", happyStrings);

            if (happyStrings.size() < k) {
                return "";
            }

            Collections.sort(happyStrings);
            return happyStrings.get(k - 1);
        }

        private void generateHappyStrings(int n, String current, List<String> happyStrings) {
            if (current.length() == n) {
                happyStrings.add(current);
                return;
            }

            for (char c = 'a'; c <= 'c'; c++) {
                if (current.length() > 0
                        && current.charAt(current.length() - 1) == c) {
                    continue;
                }

                generateHappyStrings(n, current + c, happyStrings);
            }
        }
    }
}

//    1415. The k-th Lexicographical String of All Happy Strings of Length n
//    Medium
//    A happy string is a string that:
//
//    consists only of letters of the set ['a', 'b', 'c'].
//    s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
//    For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
//
//    Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
//
//    Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
//
//
//
//    Example 1:
//
//    Input: n = 1, k = 3
//    Output: "c"
//    Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
//    Example 2:
//
//    Input: n = 1, k = 4
//    Output: ""
//    Explanation: There are only 3 happy strings of length 1.
//    Example 3:
//
//    Input: n = 3, k = 9
//    Output: "cab"
//    Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
//
//
//    Constraints:
//
//    1 <= n <= 10
//    1 <= k <= 100
