package org.jwolfe.quetzal.algorithms.lc;

public class CountWaysToBuildGoodStrings {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countGoodStrings(int low, int high, int zero, int one) {
            if (low < 0 || high < 0) {
                return 0;
            }

            int[] dp = new int[high + 1];
            dp[0] = 1;

            for (int i = 1; i <= high; i++) {
                if (i >= zero) {
                    dp[i] += dp[i - zero];
                }

                if (i >= one) {
                    dp[i] += dp[i - one];
                }

                dp[i] %= MOD;
            }

            int count = 0;
            for (int i = low; i <= high; i++) {
                count += dp[i];
                count %= MOD;
            }

            return count;
        }
    }
}

//    2466. Count Ways To Build Good Strings
//    Medium
//    Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
//
//    Append the character '0' zero times.
//    Append the character '1' one times.
//    This can be performed any number of times.
//
//    A good string is a string constructed by the above process having a length between low and high (inclusive).
//
//    Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: low = 3, high = 3, zero = 1, one = 1
//    Output: 8
//    Explanation:
//    One possible valid good string is "011".
//    It can be constructed as follows: "" -> "0" -> "01" -> "011".
//    All binary strings from "000" to "111" are good strings in this example.
//    Example 2:
//
//    Input: low = 2, high = 3, zero = 1, one = 2
//    Output: 5
//    Explanation: The good strings are "00", "11", "000", "110", and "011".
//
//
//    Constraints:
//
//    1 <= low <= high <= 105
//    1 <= zero, one <= low