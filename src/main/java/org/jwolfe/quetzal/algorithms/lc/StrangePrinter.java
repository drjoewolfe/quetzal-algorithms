package org.jwolfe.quetzal.algorithms.lc;

public class StrangePrinter {
    class Solution {
        public int strangePrinter(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            String us = uniqueString(s);
            int n = us.length();
            Integer[][] memo = new Integer[n][n];

            return strangePrinter(us, 0, n - 1, memo);
        }

        private int strangePrinter(String s, int start, int end, Integer[][] memo) {
            if (start > end) {
                return 0;
            }

            if (memo[start][end] != null) {
                return memo[start][end];
            }

            int minTurns = 1 + strangePrinter(s, start + 1, end, memo);
            for (int k = start + 1; k <= end; k++) {
                if (s.charAt(start) == s.charAt(k)) {
                    int turnsWithMatch = strangePrinter(s, start, k - 1, memo)
                            + strangePrinter(s, k + 1, end, memo);

                    minTurns = Math.min(minTurns, turnsWithMatch);
                }
            }

            return memo[start][end] = minTurns;
        }

        private String uniqueString(String s) {
            StringBuilder sb = new StringBuilder();
            int n = s.length();
            int i = 0;

            while (i < n) {
                char c = s.charAt(i);
                sb.append(c);

                while (i < n
                        && s.charAt(i) == c) {
                    i++;
                }
            }

            return sb.toString();
        }
    }
}

//    664. Strange Printer
//    Hard
//    There is a strange printer with the following two special properties:
//
//    The printer can only print a sequence of the same character each time.
//    At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
//    Given a string s, return the minimum number of turns the printer needed to print it.
//
//
//
//    Example 1:
//
//    Input: s = "aaabbb"
//    Output: 2
//    Explanation: Print "aaa" first and then print "bbb".
//    Example 2:
//
//    Input: s = "aba"
//    Output: 2
//    Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists of lowercase English letters.