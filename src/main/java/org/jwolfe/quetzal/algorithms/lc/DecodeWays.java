package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class DecodeWays {
    class Solution {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;

            for(int i = n - 2; i >= 0; i--) {
                int d1 = s.charAt(i) - '0';
                int d2 = s.charAt(i + 1) - '0';

                int oneDigit = d1;
                int twoDigits = d1 * 10 + d2;

                if(oneDigit > 0) {
                    dp[i] += dp[i + 1];
                }

                if(twoDigits >= 10 && twoDigits <= 26) {
                    dp[i] += dp[i + 2];
                }
            }

            return dp[0];
        }
    }

    class Solution_Correct_3 {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;

            for(int i = n - 2; i >= 0; i--) {
                int oneDigit = s.charAt(i) - '0';
                int twoDigits = Integer.valueOf(s.substring(i, i + 2));

                // one digit
                if(oneDigit > 0) {
                    dp[i] += dp[i + 1];
                }

                // two digits
                if(twoDigits >= 10 && twoDigits <= 26) {
                    dp[i] += dp[i + 2];
                }
            }

            return dp[0];
        }
    }

    class Solution_Memoized {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int[] cache = new int[s.length()];
            Arrays.fill(cache, -1);

            return numDecodings(s, 0, cache);
        }

        private int numDecodings(String s, int index, int[] cache) {
            if(index == s.length()) {
                return 1;
            }

            if(index == s.length() - 1) {
                char c = s.charAt(s.length() - 1);
                return (c == '0') ? 0 : 1;
            }

            if(cache[index] != -1) {
                return cache[index];
            }

            int ways = 0;

            int oneDigit = s.charAt(index) - '0';
            int twoDigits = Integer.valueOf(s.substring(index, index + 2));

            // one digit
            if(oneDigit > 0) {
                ways += numDecodings(s, index + 1, cache);
            }

            // two digits
            if(twoDigits >= 10 && twoDigits <= 26) {
                ways += numDecodings(s, index + 2, cache);
            }

            cache[index] = ways;
            return ways;
        }
    }

    class Solution_Recursive {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            return numDecodings(s, 0);
        }

        private int numDecodings(String s, int index) {
            if(index == s.length()) {
                return 1;
            }

            if(index == s.length() - 1) {
                char c = s.charAt(s.length() - 1);
                return (c == '0') ? 0 : 1;
            }

            int ways = 0;

            int oneDigit = s.charAt(index) - '0';
            int twoDigits = Integer.valueOf(s.substring(index, index + 2));

            // one digit
            if(oneDigit > 0) {
                ways += numDecodings(s, index + 1);
            }

            // two digits
            if(twoDigits >= 10 && twoDigits <= 26) {
                ways += numDecodings(s, index + 2);
            }

            return ways;
        }
    }

    class Solution_Correct_1 {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int length = s.length();
            int[] dp = new int[length + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            for(int i = 2; i <= length; i++) {
                int oneDigit = Integer.valueOf(s.substring(i - 1, i));
                int twoDigits = Integer.valueOf(s.substring(i - 2, i));

                if(oneDigit >= 1) {
                    dp[i] += dp[i - 1];
                }

                if(twoDigits >= 10 && twoDigits <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[length];
        }
    }

    class Solution_Correct_2 {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for(int i = 1; i <= n; i++) {
                char c = s.charAt(i - 1);

                // Single char
                if(c != '0') {
                    dp[i] += dp[i - 1];
                }

                // Two char
                if(i > 1) {
                    char pc = s.charAt(i - 2);
                    if(pc == '1' || (pc == '2' && c < '7')) {
                        dp[i] += dp[i - 2];
                    }
                }
            }

            return dp[n];
        }
    }

// "12"
}

//    91. Decode Ways
//    Medium
//    A message containing letters from A-Z can be encoded into numbers using the following mapping:
//
//    'A' -> "1"
//    'B' -> "2"
//    ...
//    'Z' -> "26"
//    To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
//
//    "AAJF" with the grouping (1 1 10 6)
//    "KJF" with the grouping (11 10 6)
//    Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
//
//    Given a string s containing only digits, return the number of ways to decode it.
//
//    The answer is guaranteed to fit in a 32-bit integer.
//
//
//
//    Example 1:
//
//    Input: s = "12"
//    Output: 2
//    Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
//    Example 2:
//
//    Input: s = "226"
//    Output: 3
//    Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
//    Example 3:
//
//    Input: s = "0"
//    Output: 0
//    Explanation: There is no character that is mapped to a number starting with 0.
//    The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
//    Hence, there are no valid ways to decode this since all digits need to be mapped.
//    Example 4:
//
//    Input: s = "06"
//    Output: 0
//    Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s contains only digits and may contain leading zero(s).