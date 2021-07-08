package org.jwolfe.quetzal.algorithms.lc;

public class MaximumLengthOfRepeatedSubarray {
    class Solution {
        public int findLength(int[] A, int[] B) {
            if(A == null || A.length == 0 || B == null || B.length == 0) {
                return 0;
            }

            int m = A.length;
            int n = B.length;

            int[][] dp = new int[m + 1][n + 1];
            int maxLength = 0;
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    int a = A[i - 1];
                    int b = B[j - 1];

                    if(a == b) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                        maxLength = Math.max(maxLength, dp[i][j]);
                    }
                }
            }

            return maxLength;
        }
    }

    class Solution_Correct_2 {
        public int findLength(int[] nums1, int[] nums2) {
            if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return 0;
            }

            int m = nums1.length;
            int n = nums2.length;
            int[][] dp = new int[m][n];

            for(int j = 0; j < n; j++) {
                dp[0][j] = (nums1[0] == nums2[j]) ? 1 : 0;
            }

            for(int i = 0; i < m; i++) {
                dp[i][0] = (nums1[i] == nums2[0]) ? 1 : 0;
            }

            int max= 0;
            for(int i = 1; i < m; i++) {
                for(int j = 1; j < n; j++) {
                    if(nums1[i] == nums2[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }

            return max;
        }
    }

// [1,2,3,2,1]
// [3,2,1,4,7]
}

//    718. Maximum Length of Repeated Subarray
//    Medium
//    Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
//    Output: 3
//    Explanation: The repeated subarray with maximum length is [3,2,1].
//    Example 2:
//
//    Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
//    Output: 5
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 1000
//    0 <= nums1[i], nums2[i] <= 100
