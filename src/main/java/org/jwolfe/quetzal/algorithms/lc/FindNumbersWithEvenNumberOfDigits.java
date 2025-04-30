package org.jwolfe.quetzal.algorithms.lc;

public class FindNumbersWithEvenNumberOfDigits {
    class Solution {
        public int findNumbers(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int evenCount = 0;
            for (int num : nums) {
                if (areDigitsEven(num)) {
                    evenCount++;
                }
            }

            return evenCount;
        }

        private boolean areDigitsEven(int num) {
            int digits = 0;
            while (num > 0) {
                digits++;
                num /= 10;
            }

            return (digits % 2) == 0;
        }
    }

    class Solution_Correct_1 {
        public int findNumbers(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int count = 0;
            for (int n : nums) {
                if (isDigitsEven(n)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isDigitsEven(int n) {
            int digits = 0;

            while (n != 0) {
                n = n / 10;
                digits++;
            }

            return (digits % 2) == 0;
        }
    }
}

//    1295. Find Numbers with Even Number of Digits
//    Easy
//    Given an array nums of integers, return how many of them contain an even number of digits.
//
//
//
//    Example 1:
//
//    Input: nums = [12,345,2,6,7896]
//    Output: 2
//    Explanation:
//    12 contains 2 digits (even number of digits).
//    345 contains 3 digits (odd number of digits).
//    2 contains 1 digit (odd number of digits).
//    6 contains 1 digit (odd number of digits).
//    7896 contains 4 digits (even number of digits).
//    Therefore only 12 and 7896 contain an even number of digits.
//    Example 2:
//
//    Input: nums = [555,901,482,1771]
//    Output: 1
//    Explanation:
//    Only 1771 contains an even number of digits.
//
//
//    Constraints:
//
//    1 <= nums.length <= 500
//    1 <= nums[i] <= 105