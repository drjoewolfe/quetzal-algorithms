package org.jwolfe.quetzal.algorithms.lc;

public class PerfectSquares {
    class Solution {
        public int numSquares(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n <= 3) {
                return n;
            }

            int[] dp = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                dp[i] = i;
            }

            for(int val = 4; val <= n; val++) {
                double sqrt = Math.sqrt(val);

                for(int f = 1; f <= sqrt; f++) {
                    int square = f * f;

                    dp[val] = Math.min(dp[val], 1 + dp[val - square]);
                }
            }

            return dp[n];
        }
    }

    class Solution_Correct_2 {
        public int numSquares(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 4) {
                return n;
            }

            int[] dp = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                dp[i] = i;
            }

            for(int i = 4; i <= n; i++) {
                double sqrt = Math.sqrt(i);

                for(int f = 1; f <= sqrt; f++) {
                    int s = f * f;

                    dp[i] = Math.min(dp[i],
                            1 + dp[i - s]);
                }
            }

            return dp[n];
        }
    }

    class Solution_Correct_1 {
        public int numSquares(int n) {
            if(n < 0) {
                return 0;
            }

            if(n <= 3) {
                return n;
            }

            int[] dp = new int[n + 1];
            for(int i = 0; i < n + 1; i++) {
                dp[i] = i;
            }

            for(int v = 4; v <= n; v++) {
                double sqrt = Math.sqrt(v);
                for(int f = 1; f <= sqrt; f++) {
                    int s = f * f;

                    dp[v] = Math.min(dp[v],
                            1 + dp[v - s]);
                }
            }

            return dp[n];
        }
    }

    class Solution_Brute {
        public int numSquares(int n) {
            if(n < 0) {
                return 0;
            }

            if(n <= 3) {
                return n;
            }

            double sqrt = Math.sqrt(n);
            int ans = n;
            for(int f = 1; f <= sqrt; f++) {
                int s = f * f;

                ans = Math.min(ans,
                        1 + numSquares(n - s));
            }

            return ans;
        }
    }
}

//    279. Perfect Squares
//    Medium
//    Given an integer n, return the least number of perfect square numbers that sum to n.
//
//    A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
//
//
//
//    Example 1:
//
//    Input: n = 12
//    Output: 3
//    Explanation: 12 = 4 + 4 + 4.
//    Example 2:
//
//    Input: n = 13
//    Output: 2
//    Explanation: 13 = 4 + 9.
//
//
//    Constraints:
//
//    1 <= n <= 104