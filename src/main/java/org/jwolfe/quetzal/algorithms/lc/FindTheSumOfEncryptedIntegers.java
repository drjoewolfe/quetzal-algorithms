package org.jwolfe.quetzal.algorithms.lc;

public class FindTheSumOfEncryptedIntegers {
    class Solution {
        public int sumOfEncryptedInt(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int sum = 0;
            for(int val : nums) {
                int length = 0;
                int maxDigit = 0;

                while(val > 0) {
                    length++;
                    int digit = val % 10;
                    maxDigit = Math.max(maxDigit, digit);

                    val /= 10;
                }

                int encryptedNumber = 0;
                while(length > 0) {
                    length--;
                    encryptedNumber *= 10;
                    encryptedNumber += maxDigit;
                }

                sum += encryptedNumber;
            }

            return sum;
        }
    }

// [1,2,3]
// [10,21,31]
}

//    3079. Find the Sum of Encrypted Integers
//    Easy
//    You are given an integer array nums containing positive integers. We define a function encrypt such that encrypt(x) replaces every digit in x with the largest digit in x. For example, encrypt(523) = 555 and encrypt(213) = 333.
//
//    Return the sum of encrypted elements.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//
//    Output: 6
//
//    Explanation: The encrypted elements are [1,2,3]. The sum of encrypted elements is 1 + 2 + 3 == 6.
//
//    Example 2:
//
//    Input: nums = [10,21,31]
//
//    Output: 66
//
//    Explanation: The encrypted elements are [11,22,33]. The sum of encrypted elements is 11 + 22 + 33 == 66.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 50
//    1 <= nums[i] <= 1000