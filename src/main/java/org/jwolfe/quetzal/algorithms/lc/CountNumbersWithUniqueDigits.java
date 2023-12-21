package org.jwolfe.quetzal.algorithms.lc;

public class CountNumbersWithUniqueDigits {
    class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            if(n < 0 || n > 8) {
                return 0;
            }

            int[] dp = new int[9];
            dp[0] = 1;
            dp[1] = 10;
            dp[2] = 91;
            for(int i = 3; i < 9; i++) {
                int ways = 9;
                int count = 9;
                for(int j = 1; j < i; j++) {
                    ways = ways * count;
                    count--;
                }

                dp[i] = dp[i - 1] + ways;
            }

            return dp[n];
        }
    }
}

//    357. Count Numbers with Unique Digits
//    Medium
//    Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: 91
//    Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
//    Example 2:
//
//    Input: n = 0
//    Output: 1
//
//
//    Constraints:
//
//    0 <= n <= 8