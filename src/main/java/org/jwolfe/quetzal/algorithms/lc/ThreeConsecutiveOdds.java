package org.jwolfe.quetzal.algorithms.lc;

public class ThreeConsecutiveOdds {
    class Solution {
        public boolean threeConsecutiveOdds(int[] arr) {
            if (arr == null || arr.length < 3) {
                return false;
            }

            int n = arr.length;
            for (int i = 2; i < n; i++) {
                int a = arr[i - 2];
                int b = arr[i - 1];
                int c = arr[i];

                if (a % 2 != 0 && b % 2 != 0 && c % 2 != 0) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean threeConsecutiveOdds(int[] arr) {
            if (arr == null || arr.length < 3) {
                return false;
            }

            int streak = 0;
            for (int a : arr) {
                if (a % 2 == 0) {
                    streak = 0;
                } else {
                    streak++;

                    if (streak == 3) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}

//    1550. Three Consecutive Odds
//    Easy
//    Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.
//
//
//    Example 1:
//
//    Input: arr = [2,6,4,1]
//    Output: false
//    Explanation: There are no three consecutive odds.
//    Example 2:
//
//    Input: arr = [1,2,34,3,4,5,7,23,12]
//    Output: true
//    Explanation: [5,7,23] are three consecutive odds.
//
//
//    Constraints:
//
//    1 <= arr.length <= 1000
//    1 <= arr[i] <= 1000