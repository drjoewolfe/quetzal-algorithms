package org.jwolfe.quetzal.algorithms.lc;

public class MinimumDistanceToTheTargetElement {
    class Solution {
        public int getMinDistance(int[] nums, int target, int start) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;

            int left = start;
            int right = start;
            int minAbsDistance = Integer.MAX_VALUE;

            while(left >= 0 || right < n) {
                if(left >= 0) {
                    if(nums[left] == target) {
                        minAbsDistance = start - left;
                        break;
                    }

                    left--;
                }

                if(right < n) {
                    if(nums[right] == target) {
                        minAbsDistance = right - start;
                        break;
                    }

                    right++;
                }
            }

            return minAbsDistance;
        }
    }

    class Solution_Approach_1 {
        public int getMinDistance(int[] nums, int target, int start) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int minAbsDistance = Integer.MAX_VALUE;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == target) {
                    minAbsDistance = Math.min(minAbsDistance, Math.abs(i - start));
                }
            }

            return minAbsDistance;
        }
    }
}

//    1848. Minimum Distance to the Target Element
//    Easy
//    Given an integer array nums (0-indexed) and two integers target and start, find an index i such that nums[i] == target and abs(i - start) is minimized. Note that abs(x) is the absolute value of x.
//
//    Return abs(i - start).
//
//    It is guaranteed that target exists in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4,5], target = 5, start = 3
//    Output: 1
//    Explanation: nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
//    Example 2:
//
//    Input: nums = [1], target = 1, start = 0
//    Output: 0
//    Explanation: nums[0] = 1 is the only value equal to target, so the answer is abs(0 - 0) = 0.
//    Example 3:
//
//    Input: nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
//    Output: 0
//    Explanation: Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 104
//    0 <= start < nums.length
//    target is in nums.
