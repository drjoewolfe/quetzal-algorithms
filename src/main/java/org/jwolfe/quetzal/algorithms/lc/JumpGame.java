package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class JumpGame {
    class Solution {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length < 2) {
                return true;
            }

            int n = nums.length;
            int lastPosition = n - 1;
            for(int i = n - 2; i >= 0; i--) {
                int maxJumps = i + nums[i];
                if(maxJumps >= lastPosition) {
                    lastPosition = i;
                }
            }

            return lastPosition == 0;
        }
    }

    class Solution_Correct_1 {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int length = nums.length;

            int lastPosition = length - 1;
            for(int i = length - 1; i >= 0; i--) {
                if(i + nums[i] >= lastPosition) {
                    lastPosition = i;
                }
            }

            return lastPosition == 0;
        }
    }

    class Solution_DP {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int length = nums.length;

            boolean[] dp = new boolean[length];
            dp[length - 1] = true;

            for(int i = length - 2; i >= 0; i--) {
                int maxJump = Math.min(i + nums[i], length - 1);
                for(int index = i; index <= maxJump; index++) {
                    if(dp[index]) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[0];
        }
    }

    class Solution_Memoized {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            Boolean[] memo = new Boolean[nums.length];
            Arrays.fill(memo, null);

            return canJump(nums, 0, memo);
        }

        private boolean canJump(int[] nums, int index, Boolean[] memo) {
            if(index >= nums.length) {
                return false;
            }

            if(memo[index] != null) {
                return memo[index];
            }

            if(index == nums.length - 1) {
                memo[index] = true;
            } else {
                memo[index] = false;
                for(int n = 1; n <= nums[index]; n++) {
                    if(canJump(nums, index + n, memo)) {
                        memo[index] = true;
                        break;
                    }
                }
            }

            return memo[index];
        }
    }

    class Solution_Brute {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            return canJump(nums, 0);
        }

        private boolean canJump(int[] nums, int index) {
            if(index >= nums.length) {
                return false;
            }

            if(index == nums.length - 1) {
                return true;
            }

            for(int n = 1; n <= nums[index]; n++) {
                if(canJump(nums, index + n)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    55. Jump Game
//    Medium
//    You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
//
//    Return true if you can reach the last index, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,1,1,4]
//    Output: true
//    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//    Example 2:
//
//    Input: nums = [3,2,1,0,4]
//    Output: false
//    Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    0 <= nums[i] <= 105