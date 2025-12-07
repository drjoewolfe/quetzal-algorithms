package org.jwolfe.quetzal.algorithms.lc;

public class CountOddNumbersInAnIntervalRange {
    class Solution {
        public int countOdds(int low, int high) {
            if (low % 2 == 0) {
                low++;
            }

            if (high % 2 == 0) {
                high--;
            }

            return (high - low) / 2 + 1;
        }
    }

    class Solution_Correct_2 {
        public int countOdds(int low, int high) {
            if (low % 2 == 0) {
                low++;
            }

            return low > high ? 0 : (high - low) / 2 + 1;
        }
    }

    class Solution_Correct_1 {
        public int countOdds(int low, int high) {
            int oddsTillHigh = (high / 2) + ((high % 2) != 0 ? 1 : 0);
            int oddsTillLow = (low / 2) + ((low % 2) != 0 ? 1 : 0);

            return oddsTillHigh - oddsTillLow + ((low % 2 != 0) ? 1 : 0);
        }
    }
}

//    1523. Count Odd Numbers in an Interval Range
//    Easy
//    Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
//
//
//
//    Example 1:
//
//    Input: low = 3, high = 7
//    Output: 3
//    Explanation: The odd numbers between 3 and 7 are [3,5,7].
//    Example 2:
//
//    Input: low = 8, high = 10
//    Output: 1
//    Explanation: The odd numbers between 8 and 10 are [9].
//
//
//    Constraints:
//
//    0 <= low <= high <= 10^9