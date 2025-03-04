package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfNumberIsASumOfPowersOfThree {
    class Solution {
        public boolean checkPowersOfThree(int n) {
            int power = 0;

            while (Math.pow(3, power) <= n) {
                power++;
            }

            while (n > 0) {
                int term = (int) Math.pow(3, power);

                if (n >= term) {
                    n -= term;
                }

                if (n >= term) {
                    return false;
                }

                power--;
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean checkPowersOfThree(int n) {
            return checkPowersOfThree(n, 0);
        }

        private boolean checkPowersOfThree(int n, int power) {
            if (n == 0) {
                return true;
            }

            int term = (int) Math.pow(3, power);
            if (term > n) {
                return false;
            }

            boolean add = checkPowersOfThree(n - term, power + 1);
            boolean skip = checkPowersOfThree(n, power + 1);

            return add || skip;
        }
    }
}

//    1780. Check if Number is a Sum of Powers of Three
//    Medium
//    Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
//
//    An integer y is a power of three if there exists an integer x such that y == 3x.
//
//
//
//    Example 1:
//
//    Input: n = 12
//    Output: true
//    Explanation: 12 = 31 + 32
//    Example 2:
//
//    Input: n = 91
//    Output: true
//    Explanation: 91 = 30 + 32 + 34
//    Example 3:
//
//    Input: n = 21
//    Output: false
//
//
//    Constraints:
//
//    1 <= n <= 107