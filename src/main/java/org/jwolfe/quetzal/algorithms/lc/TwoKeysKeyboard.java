package org.jwolfe.quetzal.algorithms.lc;

public class TwoKeysKeyboard {
    class Solution {
        public int minSteps(int n) {
            if (n <= 1) {
                return 0;
            }

            int[][] memo = new int[n + 1][n / 2 + 1];

            return 1 + minSteps(n, 1, 1, memo);
        }

        private int minSteps(int n, int current, int clip, int[][] memo) {
            if (current == n) {
                return 0;
            }

            if (current > n) {
                return 2000;
            }

            if (memo[current][clip] != 0) {
                return memo[current][clip];
            }

            // Option 1: Copy + Paste & Recurse
            int option1 = 2 + minSteps(n, current * 2, current, memo);

            // Option 2: Paste & Recursive
            int option2 = 1 + minSteps(n, current + clip, clip, memo);

            return memo[current][clip] = Math.min(option1, option2);
        }
    }

    class Solution_Correct_2 {
        public int minSteps(int n) {
            if (n <= 1) {
                return 0;
            }

            return 1 + minSteps(n, 1, 1);
        }

        private int minSteps(int n, int current, int clip) {
            if (current == n) {
                return 0;
            }

            if (current > n) {
                return 2000;
            }

            // Option 1: Copy + Paste & Recurse
            int option1 = 2 + minSteps(n, current * 2, current);

            // Option 2: Paste & Recursive
            int option2 = 1 + minSteps(n, current + clip, clip);

            return Math.min(option1, option2);
        }
    }

    class Solution_Correct_1 {
        public int minSteps(int n) {
            int steps = 0;
            int d = 2;

            while (n > 1) {
                while (n % d == 0) {
                    n /= d;
                    steps += d;
                }

                d++;
            }

            return steps;
        }
    }
}

//    650. 2 Keys Keyboard
//    Medium
//    There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:
//
//    Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
//    Paste: You can paste the characters which are copied last time.
//    Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
//
//
//
//    Example 1:
//
//    Input: n = 3
//    Output: 3
//    Explanation: Initially, we have one character 'A'.
//    In step 1, we use Copy All operation.
//    In step 2, we use Paste operation to get 'AA'.
//    In step 3, we use Paste operation to get 'AAA'.
//    Example 2:
//
//    Input: n = 1
//    Output: 0
//
//
//    Constraints:
//
//    1 <= n <= 1000