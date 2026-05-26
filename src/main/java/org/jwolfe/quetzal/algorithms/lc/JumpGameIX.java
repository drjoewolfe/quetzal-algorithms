package org.jwolfe.quetzal.algorithms.lc;

public class JumpGameIX {
    class Solution {
        public int[] maxValue(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] maxLeft = new int[n];
            int[] minRight = new int[n];

            maxLeft[0] = nums[0];
            minRight[n - 1] = nums[n - 1];

            for (int i = 1; i < n; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], nums[i]);
            }

            for (int i = n - 2; i >= 0; i--) {
                minRight[i] = Math.min(minRight[i + 1], nums[i]);
            }

            int[] ans = new int[n];
            ans[n - 1] = maxLeft[n - 1];

            for (int i = n - 2; i >= 0; i--) {
                if (maxLeft[i] <= minRight[i + 1]) {
                    ans[i] = maxLeft[i];
                } else {
                    ans[i] = ans[i + 1];
                }
            }

            return ans;
        }
    }
}

//    3660. Jump Game IX
//    Medium
//    You are given an integer array nums.
//
//    From any index i, you can jump to another index j under the following rules:
//
//    Jump to index j where j > i is allowed only if nums[j] < nums[i].
//    Jump to index j where j < i is allowed only if nums[j] > nums[i].
//    For each index i, find the maximum value in nums that can be reached by following any sequence of valid jumps starting at i.
//
//    Return an array ans where ans[i] is the maximum value reachable starting from index i.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,3]
//
//    Output: [2,2,3]
//
//    Explanation:
//
//    For i = 0: No jump increases the value.
//    For i = 1: Jump to j = 0 as nums[j] = 2 is greater than nums[i].
//    For i = 2: Since nums[2] = 3 is the maximum value in nums, no jump increases the value.
//    Thus, ans = [2, 2, 3].
//
//    Example 2:
//
//    Input: nums = [2,3,1]
//
//    Output: [3,3,3]
//
//    Explanation:
//
//    For i = 0: Jump forward to j = 2 as nums[j] = 1 is less than nums[i] = 2, then from i = 2 jump to j = 1 as nums[j] = 3 is greater than nums[2].
//    For i = 1: Since nums[1] = 3 is the maximum value in nums, no jump increases the value.
//    For i = 2: Jump to j = 1 as nums[j] = 3 is greater than nums[2] = 1.
//    Thus, ans = [3, 3, 3].
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109