package org.jwolfe.quetzal.algorithms.lc;

public class PartitionEqualSubsetSum {
    class Solution {
        public boolean canPartition(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;
            int sum = 0;
            for(int val : nums) {
                sum += val;
            }

            if(sum == 0 || sum % 2 != 0) {
                return false;
            }

            int targetSum = sum / 2;
            boolean[][] dp = new boolean[n + 1][targetSum + 1];
            for(int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }

            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= targetSum; j++) {
                    int val = nums[i - 1];

                    if(val > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - val];
                    }
                }
            }

            return dp[n][targetSum];
        }

    }

    class Solution_Correct_DP_1 {
        public boolean canPartition(int[] nums) {
            if(nums == null || nums.length < 2) {
                return false;
            }

            int sum = 0;
            for(int a : nums) {
                sum += a;
            }

            if(sum == 0 || sum % 2 != 0) {
                return false;
            }

            int requiredSum = sum / 2;
            return subsetSum(nums, requiredSum);
        }

        private boolean subsetSum(int[] nums, int sum) {
            int n = nums.length;
            boolean[][] dp = new boolean[n + 1][sum + 1];

            for(int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }

            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= sum; j++) {
                    if(nums[i - 1] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            return dp[n][sum];
        }
    }

    class Solution_Recursion {
        public boolean canPartition(int[] nums) {
            if(nums == null || nums.length < 2) {
                return false;
            }

            int sum = 0;
            for(int a : nums) {
                sum += a;
            }

            if(sum % 2 != 0) {
                return false;
            }

            int requiredSum = sum / 2;
            return subsetSum(nums, requiredSum);
        }

        private boolean subsetSum(int[] nums, int sum) {
            int n = nums.length;

            return subsetSum(nums, n - 1, sum);
        }

        private boolean subsetSum(int[] nums, int index, int sum) {
            if(index < 0) {
                if(sum == 0) {
                    return true;
                } else {
                    return false;
                }
            }

            return subsetSum(nums, index - 1, sum - nums[index])
                    || subsetSum(nums, index - 1, sum);
        }
    }
}

//    416. Partition Equal Subset Sum
//    Medium
//    Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
//
//
//    Example 1:
//
//    Input: nums = [1,5,11,5]
//    Output: true
//    Explanation: The array can be partitioned as [1, 5, 5] and [11].
//    Example 2:
//
//    Input: nums = [1,2,3,5]
//    Output: false
//    Explanation: The array cannot be partitioned into equal sum subsets.
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    1 <= nums[i] <= 100
