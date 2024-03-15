package org.jwolfe.quetzal.algorithms.lc;

public class ProductOfArrayExceptSelf {
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            if(nums == null || nums.length == 0) {
                return nums;
            }

            int n = nums.length;
            int[] products = new int[n];

            int runningProduct = 1;
            for(int i = 0; i < n; i++) {
                products[i] = runningProduct;
                runningProduct *= nums[i];
            }

            runningProduct = 1;
            for(int i = n - 1; i >= 0; i--) {
                products[i] *= runningProduct;
                runningProduct *= nums[i];
            }

            return products;
        }
    }

    class Solution_Correct_2 {
        public int[] productExceptSelf(int[] nums) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] products = new int[n];

            int runningProduct = 1;
            for(int i = 0; i < n; i++) {
                products[i] = runningProduct;

                int val = nums[i];
                runningProduct *= val;
            }

            runningProduct = 1;
            for(int i = n - 1; i >= 0; i--) {
                products[i] *= runningProduct;

                int val = nums[i];
                runningProduct *= val;
            }

            return products;
        }
    }

    class Solution_Correct_1 {
        public int[] productExceptSelf(int[] nums) {
            if(nums == null || nums.length < 2) {
                return nums;
            }

            int n = nums .length;
            int[] output = new int[n];

            output[0] = 1;
            for(int i = 1; i < n; i++) {
                output[i] = output[i - 1] * nums[i - 1];
            }

            int rightProduct = 1;
            for(int i = n - 1; i >= 0; i--) {
                output[i] = output[i] * rightProduct;
                rightProduct *= nums[i];
            }

            return output;
        }
    }

    class Solution_Classic {
        public int[] productExceptSelf(int[] nums) {
            if(nums == null || nums.length < 2) {
                return nums;
            }

            int n = nums .length;
            int[] leftProducts = new int[n];
            int[] rightProducts = new int[n];

            leftProducts[0] = 1;
            for(int i = 1; i < n; i++) {
                leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
            }

            rightProducts[n - 1] = 1;
            for(int i = n - 2; i >= 0; i--) {
                rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
            }

            int[] output = new int[n];
            for(int i = 0; i < n; i++) {
                output[i] = leftProducts[i] * rightProducts[i];
            }

            return output;
        }
    }
}

//    238. Product of Array Except Self
//    Medium
//    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//    You must write an algorithm that runs in O(n) time and without using the division operation.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4]
//    Output: [24,12,8,6]
//    Example 2:
//
//    Input: nums = [-1,1,0,-3,3]
//    Output: [0,0,9,0,0]
//
//
//    Constraints:
//
//    2 <= nums.length <= 105
//    -30 <= nums[i] <= 30
//    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//
//    Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
