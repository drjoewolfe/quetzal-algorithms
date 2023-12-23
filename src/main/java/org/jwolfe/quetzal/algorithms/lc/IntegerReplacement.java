package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class IntegerReplacement {
    class Solution {
        public int integerReplacement(int n) {
            if(n < 1) {
                return -1;
            }

            return integerReplacement(n, new HashMap<>());
        }

        private int integerReplacement(int n, Map<Integer, Integer> memo) {
            if(n == 1) {
                return 0;
            }

            if(n == Integer.MAX_VALUE) {
                return 32;
            }

            if(memo.containsKey(n)) {
                return memo.get(n);
            }

            int minOperations = 0;
            if(n % 2 == 0) {
                minOperations = integerReplacement(n / 2, memo) + 1;
            } else {
                minOperations = Math.min(
                        integerReplacement(n - 1, memo),
                        integerReplacement(n + 1, memo)
                ) + 1;
            }

            memo.put(n, minOperations);
            return minOperations;
        }
    }

    class Solution_Correct_1 {
        public int integerReplacement(int n) {
            if(n < 1) {
                return -1;
            }

            return integerReplacement(n, 0);
        }

        private int integerReplacement(long n, int count) {
            if(n == 1) {
                return count;
            }

            if(n % 2 == 0) {
                return integerReplacement(n / 2, count + 1);
            } else {
                return Math.min(
                        integerReplacement(n - 1, count + 1),
                        integerReplacement(n + 1, count + 1)
                );
            }
        }
    }

    class Solution_Incorrect {
        public int integerReplacement(int n) {
            if(n < 1) {
                return -1;
            }

            int[] dp = new int[n + 2];
            dp[2] = 1;
            dp[n + 1] = Integer.MAX_VALUE;

            // Even
            for(int i = 4; i <= n; i += 2) {
                dp[i] = dp[i/2] + 1;
            }

            // Odd
            for(int i = 3; i <= n; i += 2) {
                dp[i] = Math.min(dp[i + 1], dp[i - 1]) + 1;
            }

            print(dp);
            return dp[n];
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

// 8
// 2147483647
}

//    397. Integer Replacement
//    Medium
//    Given a positive integer n, you can apply one of the following operations:
//
//    If n is even, replace n with n / 2.
//    If n is odd, replace n with either n + 1 or n - 1.
//    Return the minimum number of operations needed for n to become 1.
//
//
//
//    Example 1:
//
//    Input: n = 8
//    Output: 3
//    Explanation: 8 -> 4 -> 2 -> 1
//    Example 2:
//
//    Input: n = 7
//    Output: 4
//    Explanation: 7 -> 8 -> 4 -> 2 -> 1
//    or 7 -> 6 -> 3 -> 2 -> 1
//    Example 3:
//
//    Input: n = 4
//    Output: 2
//
//
//    Constraints:
//
//    1 <= n <= 231 - 1