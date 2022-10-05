package org.jwolfe.quetzal.algorithms.lc;

public class FindGreatestCommonDivisorOfArray {
    class Solution {
        public int findGCD(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int smallest = nums[0];
            int largest = nums[0];
            for(int i = 1; i < nums.length; i++) {
                smallest = Math.min(smallest, nums[i]);
                largest = Math.max(largest, nums[i]);
            }

            return gcd(smallest, largest);
        }

        private int gcd(int x, int y) {
            // Euclid

            int a = (x > y) ? x : y;
            int b = (x > y) ? y : x;
            int r = b;

            while(a % b != 0) {
                r = a % b;
                a = b;
                b = r;
            }

            return r;
        }

        private int gcd_2(int x, int y) {
            while(x != y) {
                if(x > y) {
                    x = x - y;
                } else {
                    y = y - x;
                }
            }

            return x;
        }

        private int gcd_1(int x, int y) {
            int r = 1;
            for(int i = 2; i <= x && i <= y; i++) {
                if(x % i == 0 && y % i == 0) {
                    r = i;
                }
            }

            return r;
        }
    }
}

//    1979. Find Greatest Common Divisor of Array
//    Easy
//    Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
//
//    The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
//
//
//
//    Example 1:
//
//    Input: nums = [2,5,6,9,10]
//    Output: 2
//    Explanation:
//    The smallest number in nums is 2.
//    The largest number in nums is 10.
//    The greatest common divisor of 2 and 10 is 2.
//    Example 2:
//
//    Input: nums = [7,5,6,8,3]
//    Output: 1
//    Explanation:
//    The smallest number in nums is 3.
//    The largest number in nums is 8.
//    The greatest common divisor of 3 and 8 is 1.
//    Example 3:
//
//    Input: nums = [3,3]
//    Output: 3
//    Explanation:
//    The smallest number in nums is 3.
//    The largest number in nums is 3.
//    The greatest common divisor of 3 and 3 is 3.
//
//
//    Constraints:
//
//    2 <= nums.length <= 1000
//    1 <= nums[i] <= 1000