package org.jwolfe.quetzal.algorithms.lc;

public class MaximumScoreOfAGoodSubarray {
    class Solution {
        public int maximumScore(int[] nums, int k) {
            if(nums == null || k < 0 || nums.length < k) {
                return 0;
            }

            int max1 = solve(nums, k);
            reverse(nums);
            int max2 = solve(nums, nums.length - k - 1);

            return Math.max(max1, max2);
        }

        private int solve(int[] nums, int k) {
            int n = nums.length;
            int[] centrifugalMins = new int[n];
            centrifugalMins[k] = nums[k];
            for(int i = k - 1; i >= 0; i--) {
                centrifugalMins[i] = Math.min(nums[i], centrifugalMins[i + 1]);
            }

            for(int i = k + 1; i < n; i++) {
                centrifugalMins[i] = Math.min(nums[i], centrifugalMins[i - 1]);
            }

            int maxScore = 0;
            for(int j = k; j < n; j++) {
                int min = centrifugalMins[j];
                int i = findLeft(centrifugalMins, 0, k, min);
                int score = min * (j - i + 1);
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        }

        private void reverse(int[] nums) {
            int n = nums.length;
            for(int i = 0; i < n / 2; i++) {
                int temp = nums[i];
                nums[i] = nums[n - i - 1];
                nums[n - i - 1] = temp;
            }
        }

        private int findLeft(int[] centrifugalMins, int left, int right, int val) {
            while(left < right) {
                int mid = left + (right - left) / 2;

                if(centrifugalMins[mid] >= val) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return right;
        }

        private void print(int[] nums) {
            for(int val : nums) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

    class Solution_Better_TLE {
        public int maximumScore(int[] nums, int k) {
            if(nums == null || k < 0 || nums.length < k) {
                return 0;
            }

            int n = nums.length;
            int[] centrifugalMins = new int[n];
            centrifugalMins[k] = nums[k];
            for(int i = k - 1; i >= 0; i--) {
                centrifugalMins[i] = Math.min(nums[i], centrifugalMins[i + 1]);
            }

            for(int i = k + 1; i < n; i++) {
                centrifugalMins[i] = Math.min(nums[i], centrifugalMins[i - 1]);
            }


            int maxScore = 0;
            for(int i = 0; i <= k; i++) {
                for(int j = k; j < n; j++) {
                    int score = getScore(nums, i, j, centrifugalMins);
                    maxScore = Math.max(maxScore, score);
                }
            }

            return maxScore;
        }

        private int getScore(int[] nums, int left, int right, int[] centrifugalMins) {
            int min = Math.min(centrifugalMins[left], centrifugalMins[right]);
            return min * (right - left + 1);
        }
    }

    class Solution_Brute_TLE {
        public int maximumScore(int[] nums, int k) {
            if(nums == null || k < 0 || nums.length < k) {
                return 0;
            }

            int n = nums.length;
            int maxScore = 0;
            for(int i = 0; i <= k; i++) {
                for(int j = k; j < n; j++) {
                    int score = getScore(nums, i, j);
                    maxScore = Math.max(maxScore, score);
                }
            }

            return maxScore;
        }

        private int getScore(int[] nums, int left, int right) {
            int min = nums[left];
            for(int i = left + 1; i <= right; i++) {
                min = Math.min(min, nums[i]);
            }

            return min * (right - left + 1);
        }
    }

// [1,4,3,7,4,5]
// 3
}

//    1793. Maximum Score of a Good Subarray
//    Hard
//    You are given an array of integers nums (0-indexed) and an integer k.
//
//    The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
//
//    Return the maximum possible score of a good subarray.
//
//
//
//    Example 1:
//
//    Input: nums = [1,4,3,7,4,5], k = 3
//    Output: 15
//    Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
//    Example 2:
//
//    Input: nums = [5,5,4,5,4,1,1,1], k = 0
//    Output: 20
//    Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 2 * 104
//    0 <= k < nums.length