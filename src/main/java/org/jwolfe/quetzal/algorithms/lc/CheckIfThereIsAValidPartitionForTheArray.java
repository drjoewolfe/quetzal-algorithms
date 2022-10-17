package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfThereIsAValidPartitionForTheArray {
    class Solution {
        public boolean validPartition(int[] nums) {
            if(nums == null || nums.length < 2) {
                return false;
            }

            int n = nums.length;
            boolean[] dp = new boolean[n];
            dp[0] = false;
            dp[1] = (nums[0] == nums[1]) ? true : false;
            if(n == 2) {
                return dp[1];
            }

            dp[2] = ((nums[2] == nums[1] && nums[2] == nums[0]) || (nums[2] == nums[1] + 1 && nums[2] == nums[0] + 2));

            for(int i = 3; i < n; i++) {
                // 2 equal elements ending at this index
                if(nums[i] == nums[i - 1] && dp[i - 2]) {
                    dp[i] = true;
                }

                // 3 equal elements ending at this index
                if(nums[i] == nums[i - 1] && nums[i] == nums[i - 2] && dp[i - 3]) {
                    dp[i] = true;
                }

                // 3 consecutive increasing elements ending at this index
                if(nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2 && dp[i - 3]) {
                    dp[i] = true;
                }
            }

            return dp[n - 1];
        }
    }

    class Solution_Memoized {
        public boolean validPartition(int[] nums) {
            if(nums == null || nums.length < 2) {
                return false;
            }

            Boolean[] memo = new Boolean[nums.length];
            return validPartition(nums, 0, memo);
        }

        private boolean validPartition(int[] nums, int index, Boolean[] memo) {
            if(index == nums.length) {
                return true;
            }

            if(memo[index] != null) {
                return memo[index];
            }

            // 2 equal elements from this index
            if(index + 1 < nums.length) {
                if(nums[index] == nums[index + 1]
                        && validPartition(nums, index + 2, memo)) {
                    memo[index] = true;
                    return true;
                }
            }

            // 3 equal elements from this index
            if(index + 2 < nums.length) {
                if(nums[index] == nums[index + 1]
                        && nums[index] == nums[index + 2]
                        && validPartition(nums, index + 3, memo)) {
                    memo[index] = true;
                    return true;
                }
            }

            // 3 consecutive increasing elements from this index
            if(index + 2 < nums.length) {
                if(nums[index] + 1 == nums[index + 1]
                        && nums[index] + 2 == nums[index + 2]
                        && validPartition(nums, index + 3, memo)) {
                    memo[index] = true;
                    return true;
                }
            }

            memo[index] = false;
            return false;
        }
    }

    class Solution_Recursive_TLE {
        public boolean validPartition(int[] nums) {
            if(nums == null || nums.length < 2) {
                return false;
            }

            return validPartition(nums, 0);
        }

        private boolean validPartition(int[] nums, int index) {
            if(index == nums.length) {
                return true;
            }

            // 2 equal elements from this index
            if(index + 1 < nums.length) {
                if(nums[index] == nums[index + 1]
                        && validPartition(nums, index + 2)) {
                    return true;
                }
            }

            // 3 equal elements from this index
            if(index + 2 < nums.length) {
                if(nums[index] == nums[index + 1]
                        && nums[index] == nums[index + 2]
                        && validPartition(nums, index + 3)) {
                    return true;
                }
            }

            // 3 consecutive increasing elements from this index
            if(index + 2 < nums.length) {
                if(nums[index] + 1 == nums[index + 1]
                        && nums[index] + 2 == nums[index + 2]
                        && validPartition(nums, index + 3)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    2369. Check if There is a Valid Partition For The Array
//    Medium
//    You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
//
//    We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
//
//    The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
//    The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
//    The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
//    Return true if the array has at least one valid partition. Otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: nums = [4,4,4,5,6]
//    Output: true
//    Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
//    This partition is valid, so we return true.
//    Example 2:
//
//    Input: nums = [1,1,1,2]
//    Output: false
//    Explanation: There is no valid partition for this array.
//
//
//    Constraints:
//
//    2 <= nums.length <= 105
//    1 <= nums[i] <= 106