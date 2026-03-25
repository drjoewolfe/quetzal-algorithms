package org.jwolfe.quetzal.algorithms.lc;

public class FindAllPossibleStableBinaryArraysII {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][][] dp = new int[zero + 1][one + 1][2];

            for (int i = 0; i <= Math.min(zero, limit); i++) {
                dp[i][0][0] = 1;
            }

            for (int j = 0; j <= Math.min(one, limit); j++) {
                dp[0][j][1] = 1;
            }

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    if (i == 0 || j == 0) {
                        continue;
                    }

                    dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    if (j - 1 >= limit) {
                        dp[i][j][1] = (dp[i][j][1] - dp[i][j - 1 - limit][0] + MOD) % MOD;
                    }

                    dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    if (i - 1 >= limit) {
                        dp[i][j][0] = (dp[i][j][0] - dp[i - 1 - limit][j][1] + MOD) % MOD;
                    }
                }
            }

            return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
        }
    }

    class Solution_Memoized_TLE {
        private int MOD = 1_000_000_007;

        public int numberOfStableArrays(int zero, int one, int limit) {
            Integer[][][] memo = new Integer[1001][1001][2];
            long oneStart = numberOfStableArrays(zero, one, limit, true, memo);
            long zeroStart = numberOfStableArrays(zero, one, limit, false, memo);

            return (int) (oneStart + zeroStart) % MOD;
        }

        private int numberOfStableArrays(int zerosLeft, int onesLeft, int limit, boolean lastWasOne, Integer[][][] memo) {
            if (zerosLeft == 0 && onesLeft == 0) {
                // Stable
                return 1;
            }

            int lastMemoIndex = lastWasOne ? 0 : 1;
            if (memo[zerosLeft][onesLeft][lastMemoIndex] != null) {
                return memo[zerosLeft][onesLeft][lastMemoIndex];
            }

            long result = 0;

            if (lastWasOne) {
                for (int length = 1; length <= Math.min(zerosLeft, limit); length++) {
                    long count = numberOfStableArrays(zerosLeft - length, onesLeft, limit, false, memo) % MOD;
                    result = (result + count) % MOD;
                }
            } else {
                for (int length = 1; length <= Math.min(onesLeft, limit); length++) {
                    long count = numberOfStableArrays(zerosLeft, onesLeft - length, limit, true, memo) % MOD;
                    result = (result + count) % MOD;
                }
            }

            return memo[zerosLeft][onesLeft][lastMemoIndex] = (int) result;
        }
    }

    class Solution_Recursive_TLE {
        private int MOD = 1_000_000_007;

        public int numberOfStableArrays(int zero, int one, int limit) {
            int oneStart = numberOfStableArrays(zero, one, limit, true);
            int zeroStart = numberOfStableArrays(zero, one, limit, false);

            return (oneStart + zeroStart) % MOD;
        }

        private int numberOfStableArrays(int zerosLeft, int onesLeft, int limit, boolean lastWasOne) {
            if (zerosLeft == 0 && onesLeft == 0) {
                // Stable
                return 1;
            }

            int result = 0;

            if (lastWasOne) {
                for (int length = 1; length <= Math.min(zerosLeft, limit); length++) {
                    int count = numberOfStableArrays(zerosLeft - length, onesLeft, limit, false) % MOD;
                    result += count;
                }
            } else {
                for (int length = 1; length <= Math.min(onesLeft, limit); length++) {
                    int count = numberOfStableArrays(zerosLeft, onesLeft - length, limit, true) % MOD;
                    result += count;
                }
            }

            return result;
        }
    }
}

//    3130. Find All Possible Stable Binary Arrays II
//    Hard
//    You are given 3 positive integers zero, one, and limit.
//
//    A binary array arr is called stable if:
//
//    The number of occurrences of 0 in arr is exactly zero.
//    The number of occurrences of 1 in arr is exactly one.
//    Each subarray of arr with a size greater than limit must contain both 0 and 1.
//    Return the total number of stable binary arrays.
//
//    Since the answer may be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: zero = 1, one = 1, limit = 2
//
//    Output: 2
//
//    Explanation:
//
//    The two possible stable binary arrays are [1,0] and [0,1].
//
//    Example 2:
//
//    Input: zero = 1, one = 2, limit = 1
//
//    Output: 1
//
//    Explanation:
//
//    The only possible stable binary array is [1,0,1].
//
//    Example 3:
//
//    Input: zero = 3, one = 3, limit = 2
//
//    Output: 14
//
//    Explanation:
//
//    All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0], [1,1,0,0,1,0], and [1,1,0,1,0,0].
//
//
//
//    Constraints:
//
//    1 <= zero, one, limit <= 1000