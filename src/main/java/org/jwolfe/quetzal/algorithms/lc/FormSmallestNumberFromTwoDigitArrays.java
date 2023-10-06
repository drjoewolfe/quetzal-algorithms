package org.jwolfe.quetzal.algorithms.lc;

public class FormSmallestNumberFromTwoDigitArrays {
    class Solution {
        public int minNumber(int[] nums1, int[] nums2) {
            if(nums1 == null && nums2 == null) {
                return -1;
            }

            int[] counts1 = new int[10];
            int[] counts2 = new int[10];

            for(int a : nums1) {
                counts1[a]++;
            }

            for(int b : nums2) {
                counts2[b]++;
            }

            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for(int a = 1; a < 10; a++) {
                if(counts1[a] > 0 && counts2[a] > 0) {
                    return a;
                }

                if(counts1[a] > 0) {
                    min1 = Math.min(min1, a);
                }

                if(counts2[a] > 0) {
                    min2 = Math.min(min2, a);
                }
            }

            int minDigit = (min1 >= min2) ? min2 : min1;
            int maxDigit = (min1 >= min2) ? min1 : min2;

            int number = (minDigit * 10) + maxDigit;
            return number;
        }
    }

    class Solution_Incorrect_1 {
        public int minNumber(int[] nums1, int[] nums2) {
            if(nums1 == null && nums2 == null) {
                return -1;
            }

            int digit1 = getSmallestDigit(nums1);
            int digit2 = getSmallestDigit(nums2);

            int minDigit = (digit1 >= digit2) ? digit2 : digit1;
            int maxDigit = (digit1 >= digit2) ? digit1 : digit2;

            int number = (minDigit * 10) + maxDigit;
            return number;
        }

        private int getSmallestDigit(int[] nums) {
            if(nums == null) {
                return 0;
            }

            int minDigit = nums[0];
            for(int i = 1; i < nums.length; i++) {
                minDigit = Math.min(minDigit, nums[i]);
            }

            return minDigit;
        }
    }

// [4,1,3]
// [5,7]

// [3,5,2,6]
// [3,1,7]
}

//    2605. Form Smallest Number From Two Digit Arrays
//    Easy
//    Given two arrays of unique digits nums1 and nums2, return the smallest number that contains at least one digit from each array.
//
//
//    Example 1:
//
//    Input: nums1 = [4,1,3], nums2 = [5,7]
//    Output: 15
//    Explanation: The number 15 contains the digit 1 from nums1 and the digit 5 from nums2. It can be proven that 15 is the smallest number we can have.
//    Example 2:
//
//    Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
//    Output: 3
//    Explanation: The number 3 contains the digit 3 which exists in both arrays.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 9
//    1 <= nums1[i], nums2[i] <= 9
//    All digits in each array are unique.