package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NThTribonacciNumber {
    class Solution {
        public int tribonacci(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n <= 2) {
                return 1;
            }

            int[] dp = new int[n + 1];

            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 1;

            for(int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            return dp[n];
        }
    }

    class Solution_Memoized {
        public int tribonacci(int n) {
            int[] memo = new int[n + 1];
            Arrays.fill(memo, -1);

            return tribonacci(n, memo);
        }

        public int tribonacci(int n, int[] memo) {
            if(memo[n] != -1) {
                return memo[n];
            }

            int value = 0;

            if(n <= 0) {
                value = 0;
            } else if(n == 1) {
                value = 1;
            } else if(n == 2) {
                value = 1;
            } else {
                value = tribonacci(n - 1, memo) + tribonacci(n - 2, memo) + tribonacci(n - 3, memo);
            }

            memo[n] = value;
            return value;
        }

        private void printArray(int[] arr) {
            for(int n : arr) {
                System.out.print(n + " ");
            }

            System.out.println();
        }
    }

    class Solution_Classic {
        public int tribonacci(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n == 1) {
                return 1;
            }

            if(n == 2) {
                return 1;
            }

            return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }
    }

    class Solution_Correct_2 {
        public int tribonacci(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 3) {
                return 1;
            }

            int c = 0;
            int b = 1;
            int a = 1;


            for(int i = 3; i <= n; i++) {
                int x = a + b + c;

                c = b;
                b = a;
                a = x;
            }

            return a;
        }
    }

    class Solution_Classic_1 {
        public int tribonacci(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 3) {
                return 1;
            }

            int[] dp = new int[n + 1];
            dp[1]= 1;
            dp[2] = 1;

            for(int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            return dp[n];
        }
    }

    class Solution_Brute {
        public int tribonacci(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 3) {
                return 1;
            }

            return tribonacci(n - 1)
                    + tribonacci(n - 2)
                    + tribonacci(n - 3);
        }
    }
}

//    1137. N-th Tribonacci Number
//    Easy
//    The Tribonacci sequence Tn is defined as follows:
//
//    T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
//
//    Given n, return the value of Tn.
//
//
//
//    Example 1:
//
//    Input: n = 4
//    Output: 4
//    Explanation:
//    T_3 = 0 + 1 + 1 = 2
//    T_4 = 1 + 1 + 2 = 4
//    Example 2:
//
//    Input: n = 25
//    Output: 1389537
//
//
//    Constraints:
//
//    0 <= n <= 37
//    The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.