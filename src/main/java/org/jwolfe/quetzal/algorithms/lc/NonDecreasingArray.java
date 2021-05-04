package org.jwolfe.quetzal.algorithms.lc;

public class NonDecreasingArray {
    class Solution {
        public boolean checkPossibility(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int index = -1;
            for(int i = 0; i < nums.length - 1; i++) {
                if(nums[i] > nums[i + 1]) {
                    if(index != -1) {
                        // Second occurance
                        return false;
                    }

                    index = i;
                }
            }


            if(index < 1 || index == nums.length - 2) {
                return true;
            }

            if(nums[index - 1] <= nums[index + 1]) {
                return true;
            }

            if(nums[index] <= nums[index + 2]) {
                return true;
            }

            return false;
        }
    }

    class Solution_Incorrect {
        public boolean checkPossibility(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int count = 0;
            for(int i = 0; i < nums.length - 1; i++) {
                int a = nums[i];
                int b = nums[i + 1];

                if(a > b) {
                    if(i > 0) {
                        if(nums[i - 1] > b) {
                            return false;
                        }
                    }

                    count++;
                }

            }

            return count <= 1;
        }
    }

// [4,2,3]
// [2,3,3,2,4]
}

//    665. Non-decreasing Array
//    Medium
//    Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
//
//    We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
//
//
//
//    Example 1:
//
//    Input: nums = [4,2,3]
//    Output: true
//    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
//    Example 2:
//
//    Input: nums = [4,2,1]
//    Output: false
//    Explanation: You can't get a non-decreasing array by modify at most one element.
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 104
//    -105 <= nums[i] <= 105