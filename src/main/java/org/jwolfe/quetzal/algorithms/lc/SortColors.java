package org.jwolfe.quetzal.algorithms.lc;

public class SortColors {
    class Solution {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length < 2) {
                return;
            }

            int left = 0;
            int right = nums.length - 1;
            int current = 0;

            while (current <= right) {
                int color = nums[current];

                if (color == 0) {
                    swap(nums, current, left);
                    current++;
                    left++;
                } else if (color == 2) {
                    swap(nums, current, right);
                    right--;
                } else {
                    current++;
                }
            }
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }

    class Solution_Correct_1 {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            int l = 0;
            int r = nums.length - 1;
            int i = 0;

            while (i <= r) {
                int v = nums[i];

                if (v == 0) {
                    swap(nums, l++, i++);
                } else if (v == 2) {
                    swap(nums, i, r--);
                } else {
                    i++;
                }
            }
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }

    class Solution_Counting {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            int countZero = 0;
            int countOne = 0;
            int countTwo = 0;

            for (int n : nums) {
                if (n == 0) {
                    countZero++;
                } else if (n == 1) {
                    countOne++;
                } else if (n == 2) {
                    countTwo++;
                } else {
                    return;
                }
            }

            int i = 0;
            while (countZero > 0 && i < nums.length) {
                nums[i++] = 0;
                countZero--;
            }

            while (countOne > 0 && i < nums.length) {
                nums[i++] = 1;
                countOne--;
            }

            while (countTwo > 0 && i < nums.length) {
                nums[i++] = 2;
                countTwo--;
            }
        }
    }
}

//    75. Sort Colors
//    Medium
//    Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
//
//    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
//
//    You must solve this problem without using the library's sort function.
//
//
//
//    Example 1:
//
//    Input: nums = [2,0,2,1,1,0]
//    Output: [0,0,1,1,2,2]
//    Example 2:
//
//    Input: nums = [2,0,1]
//    Output: [0,1,2]
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 300
//    nums[i] is either 0, 1, or 2.
//
//
//    Follow up: Could you come up with a one-pass algorithm using only constant extra space?