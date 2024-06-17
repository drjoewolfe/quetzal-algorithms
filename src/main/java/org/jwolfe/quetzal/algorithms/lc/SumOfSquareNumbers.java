package org.jwolfe.quetzal.algorithms.lc;

public class SumOfSquareNumbers {
    class Solution {
        public boolean judgeSquareSum(int c) {
            for (long a = 0; a * a <= c; a++) {
                long bs = c - (a * a);
                double b = Math.sqrt(bs);
                if (b == (int) b) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean judgeSquareSum(int c) {
            for (long a = 0; a * a <= c; a++) {
                long bs = c - a * a;
                double b = Math.sqrt(bs);
                if (b == (int) b) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Brute {
        public boolean judgeSquareSum(int c) {
            int sqrt = (int) Math.sqrt(c);
            for (int x = 0; x <= sqrt; x++) {
                int xs = x * x;

                for (int y = sqrt; y >= x; y--) {
                    int ys = y * y;

                    if (xs + ys == c) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}

//    633. Sum of Square Numbers
//    Medium
//    Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
//
//
//
//    Example 1:
//
//    Input: c = 5
//    Output: true
//    Explanation: 1 * 1 + 2 * 2 = 5
//    Example 2:
//
//    Input: c = 3
//    Output: false
//
//
//    Constraints:
//
//    0 <= c <= 231 - 1