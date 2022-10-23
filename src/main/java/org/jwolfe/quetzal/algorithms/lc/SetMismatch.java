package org.jwolfe.quetzal.algorithms.lc;

public class SetMismatch {
    class Solution {
        public int[] findErrorNums(int[] nums) {
            if(nums == null || nums.length < 2) {
                return new int[0];
            }

            int n = nums.length;

            int sum = 0;
            for(int a : nums) {
                sum += a;
            }

            int repeatedNumber = -1;
            for(int i = 0; i < n; i++) {
                int v = Math.abs(nums[i]) - 1;

                if(nums[v] < 0) {
                    repeatedNumber = v + 1;
                }

                nums[v] *= -1;
            }

            int missingNumber = (n * (n + 1) / 2) - (sum - repeatedNumber);
            return new int[] {repeatedNumber, missingNumber};
        }
    }

    class Solution_Correct_1 {
        public int[] findErrorNums(int[] nums) {
            if(nums == null || nums.length < 2) {
                return new int[0];
            }

            int n = nums.length;
            int sum = 0;
            for(int v : nums) {
                sum += v;
            }

            int repeatedNumber = 0;
            for(int v : nums) {
                int av = Math.abs(v);
                if(nums[av - 1] < 0) {
                    repeatedNumber = av;
                    break;
                } else {
                    nums[av - 1] *= -1;
                }
            }

            int missingNumber = (n * (n + 1) / 2) - (sum - repeatedNumber);

            return new int[] {repeatedNumber, missingNumber};
        }
    }
}

//    645. Set Mismatch
//    Easy
//    You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
//
//    You are given an integer array nums representing the data status of this set after the error.
//
//    Find the number that occurs twice and the number that is missing and return them in the form of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,2,4]
//    Output: [2,3]
//    Example 2:
//
//    Input: nums = [1,1]
//    Output: [1,2]
//
//
//    Constraints:
//
//    2 <= nums.length <= 104
//    1 <= nums[i] <= 104