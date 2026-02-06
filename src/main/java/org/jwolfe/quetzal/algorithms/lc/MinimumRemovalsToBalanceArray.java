package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumRemovalsToBalanceArray {
    class Solution {
        public int minRemoval(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            Arrays.sort(nums);

            int ans = n;
            int right = 0;
            for (int left = 0; left < n; left++) {
                while (right < n && nums[right] <= 1L * nums[left] * k) {
                    right++;
                }

                int elementsToRemove = n - (right - left);
                ans = Math.min(ans, elementsToRemove);
            }

            return ans;
        }
    }

    class Solution_TLE_2 {
        public int minRemoval(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            Integer[][] memo = new Integer[n][n];

            Arrays.sort(nums);
            return minRemoval(nums, k, 0, n - 1, memo);
        }

        private int minRemoval(int[] nums, int k, int left, int right, Integer[][] memo) {
            if (left == right) {
                return 0;
            }

            if (memo[left][right] != null) {
                return memo[left][right];
            }

            int x = nums[left];
            int y = nums[right];

            if (y <= 1L * k * x) {
                return 0;
            }

            int leftRemoved = minRemoval(nums, k, left + 1, right, memo) + 1;
            int rightRemoved = minRemoval(nums, k, left, right - 1, memo) + 1;

            return memo[left][right] = Math.min(leftRemoved, rightRemoved);
        }
    }

    class Solutio_TLE {
        public int minRemoval(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);
            return minRemoval(nums, k, 0, nums.length - 1);
        }

        private int minRemoval(int[] nums, int k, int left, int right) {
            if (left == right) {
                return 0;
            }

            int x = nums[left];
            int y = nums[right];
            if (y <= k * x) {
                return 0;
            }

            int leftRemoved = minRemoval(nums, k, left + 1, right) + 1;
            int rightRemoved = minRemoval(nums, k, left, right - 1) + 1;

            return Math.min(leftRemoved, rightRemoved);
        }
    }
}

//    3634. Minimum Removals to Balance Array
//    Medium
//    You are given an integer array nums and an integer k.
//
//    An array is considered balanced if the value of its maximum element is at most k times the minimum element.
//
//    You may remove any number of elements from nums​​​​​​​ without making it empty.
//
//    Return the minimum number of elements to remove so that the remaining array is balanced.
//
//    Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,5], k = 2
//
//    Output: 1
//
//    Explanation:
//
//    Remove nums[2] = 5 to get nums = [2, 1].
//    Now max = 2, min = 1 and max <= min * k as 2 <= 1 * 2. Thus, the answer is 1.
//    Example 2:
//
//    Input: nums = [1,6,2,9], k = 3
//
//    Output: 2
//
//    Explanation:
//
//    Remove nums[0] = 1 and nums[3] = 9 to get nums = [6, 2].
//    Now max = 6, min = 2 and max <= min * k as 6 <= 2 * 3. Thus, the answer is 2.
//    Example 3:
//
//    Input: nums = [4,6], k = 2
//
//    Output: 0
//
//    Explanation:
//
//    Since nums is already balanced as 6 <= 4 * 2, no elements need to be removed.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    1 <= k <= 105