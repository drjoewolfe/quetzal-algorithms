package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ValidTriangleNumber {
    class Solution {
        public int triangleNumber(int[] nums) {
            if (nums == null || nums.length < 0) {
                return 0;
            }

            Arrays.sort(nums);

            int count = 0;
            int n = nums.length;

            for (int i = 0; i < n - 2; i++) {
                int a = nums[i];

                for (int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];

                    int k = binarySearchForGreaterOrEqual(nums, j + 1, n - 1, a + b);
                    if (k > j) {
                        count += (k - j - 1);
                    }
                }
            }

            return count;
        }

        private int binarySearchForGreaterOrEqual(int[] nums, int left, int right, int target) {
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Correct_1 {
        public int triangleNumber(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            Arrays.sort(nums);
            int count = 0;
            int n = nums.length;
            for (int i = 0; i < n - 2; i++) {
                int a = nums[i];
                for (int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];
                    int k = binarySearchForGreaterOrEqual(nums, j + 1, n - 1, a + b);

                    if (k > 0) {
                        count += (k - j - 1);
                    }
                }
            }

            return count;
        }

        private int binarySearchForGreaterOrEqual(int[] nums, int left, int right, int x) {
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] >= x) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Brute {
        public int triangleNumber(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            int count = 0;
            int n = nums.length;
            for (int i = 0; i < n - 2; i++) {
                int a = nums[i];
                for (int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];
                    for (int k = j + 1; k < n; k++) {
                        int c = nums[k];

                        if (isValidTriangle(a, b, c)) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }

        private boolean isValidTriangle(int a, int b, int c) {
            return ((a + b) > c)
                    && ((b + c) > a)
                    && ((c + a) > b);
        }
    }

// [2,2,3,4]
}

//    611. Valid Triangle Number
//    Medium
//    Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
//
//
//
//    Example 1:
//
//    Input: nums = [2,2,3,4]
//    Output: 3
//    Explanation: Valid combinations are:
//    2,3,4 (using the first 2)
//    2,3,4 (using the second 2)
//    2,2,3
//    Example 2:
//
//    Input: nums = [4,2,3,4]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    0 <= nums[i] <= 1000
