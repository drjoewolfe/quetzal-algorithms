package org.jwolfe.quetzal.algorithms.lc;

public class MaxDotProductOfTwoSubsequences {
    class Solution {
        public int maxDotProduct(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null) {
                return 0;
            }

            int max1 = Integer.MIN_VALUE;
            int min1 = Integer.MAX_VALUE;

            int max2 = Integer.MIN_VALUE;
            int min2 = Integer.MAX_VALUE;

            for(int a : nums1) {
                max1 = Math.max(max1, a);
                min1 = Math.min(min1, a);
            }

            for(int b : nums2) {
                max2 = Math.max(max2, b);
                min2 = Math.min(min2, b);
            }

            if(max1 < 0 && min2 > 0) {
                return max1 * min2;
            }

            if(max2 < 0 && min1 > 0) {
                return max2 * min1;
            }

            int[][] memo = new int[nums1.length][nums2.length];
            return dp(nums1, nums2, 0, 0, memo);
        }

        private int dp(int[] nums1, int[] nums2, int i, int j, int[][] memo) {
            if(i == nums1.length
                    || j == nums2.length) {
                return 0;
            }

            if(memo[i][j] != 0) {
                return memo[i][j];
            }

            int maxProduct = max(nums1[i] * nums2[j] + dp(nums1, nums2, i + 1, j + 1, memo),
                    dp(nums1, nums2, i + 1, j, memo),
                    dp(nums1, nums2, i, j + 1, memo));

            memo[i][j] = maxProduct;
            return memo[i][j];
        }

        private int max(int a, int b, int c) {
            return Math.max(a,
                    Math.max(b, c));
        }
    }

    class Solution_Recursive_TLE {
        public int maxDotProduct(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null) {
                return 0;
            }

            int max1 = Integer.MIN_VALUE;
            int min1 = Integer.MAX_VALUE;

            int max2 = Integer.MIN_VALUE;
            int min2 = Integer.MAX_VALUE;

            for(int a : nums1) {
                max1 = Math.max(max1, a);
                min1 = Math.min(min1, a);
            }

            for(int b : nums2) {
                max2 = Math.max(max2, b);
                min2 = Math.min(min2, b);
            }

            if(max1 < 0 && min2 > 0) {
                return max1 * min2;
            }

            if(max2 < 0 && min1 > 0) {
                return max2 * min1;
            }

            return dp(nums1, nums2, 0, 0);
        }

        private int dp(int[] nums1, int[] nums2, int i, int j) {
            if(i == nums1.length
                    || j == nums2.length) {
                return 0;
            }

            return max(nums1[i] * nums2[j] + dp(nums1, nums2, i + 1, j + 1),
                    dp(nums1, nums2, i + 1, j),
                    dp(nums1, nums2, i, j + 1));
        }

        private int max(int a, int b, int c) {
            return Math.max(a,
                    Math.max(b, c));
        }
    }

// [2,1,-2,5]
// [3,0,-6]

// [-1,-1]
// [1,1]

// [13,-7,12,-15,-7,8,3,-7,-5,13,-15,-8,5,7,-1,3,-11,-12,2,-12]
// [-1,13,-4,-2,-13,2,-4,6,-9,13,-8,-3,-9]
}

//    1458. Max Dot Product of Two Subsequences
//    Hard
//    Given two arrays nums1 and nums2.
//
//    Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
//
//    A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
//
//
//
//    Example 1:
//
//    Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
//    Output: 18
//    Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
//    Their dot product is (2*3 + (-2)*(-6)) = 18.
//    Example 2:
//
//    Input: nums1 = [3,-2], nums2 = [2,-6,7]
//    Output: 21
//    Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
//    Their dot product is (3*7) = 21.
//    Example 3:
//
//    Input: nums1 = [-1,-1], nums2 = [1,1]
//    Output: -1
//    Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
//    Their dot product is -1.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 500
//    -1000 <= nums1[i], nums2[i] <= 1000