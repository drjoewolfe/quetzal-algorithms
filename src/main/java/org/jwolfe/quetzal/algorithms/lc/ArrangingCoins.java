package org.jwolfe.quetzal.algorithms.lc;

public class ArrangingCoins {
    class Solution {
        public int arrangeCoins(int n) {
            if(n <= 0) {
                return 0;
            }

            long left = 0;
            long right = n;

            while(left <= right) {
                long k = left + (right - left) / 2;
                long sum = k * (k + 1) / 2;
                if(sum == n) {
                    return (int) k;
                } else if(sum < n) {
                    left = k + 1;
                } else {
                    right = k - 1;
                }
            }

            return (int) right;
        }
    }

    class Solution_Standard {
        public int arrangeCoins(int n) {
            if(n <= 0) {
                return 0;
            }

            int k = 0;
            while(n >= k) {
                n -= k;
                k++;
            }

            return k - 1;
        }
    }

    class Solution_Correct_1 {
        public int arrangeCoins(int n) {
            if(n <= 0) {
                return 0;
            }

            int nextRowCapacity = 1;
            int rowCount = 0;

            while(n >= nextRowCapacity) {
                n -= nextRowCapacity;
                rowCount++;

                nextRowCapacity++;
            }

            return rowCount;
        }
    }

// 5
// 1804289383
}

//    441. Arranging Coins
//    Easy
//    You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
//
//    Given the integer n, return the number of complete rows of the staircase you will build.
//
//
//
//    Example 1:
//
//
//    Input: n = 5
//    Output: 2
//    Explanation: Because the 3rd row is incomplete, we return 2.
//    Example 2:
//
//
//    Input: n = 8
//    Output: 3
//    Explanation: Because the 4th row is incomplete, we return 3.
//
//
//    Constraints:
//
//    1 <= n <= 231 - 1
