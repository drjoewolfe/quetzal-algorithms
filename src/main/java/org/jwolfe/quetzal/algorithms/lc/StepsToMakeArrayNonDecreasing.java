package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class StepsToMakeArrayNonDecreasing {
    class Solution {
        public int totalSteps(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            Stack<int[]> stack = new Stack<>();
            int n = nums.length;

            int max = 0;
            for(int i = n - 1; i >= 0; i--) {
                int val = nums[i];
                int count = 0;
                while(!stack.isEmpty() && stack.peek()[0] < val) {
                    count = Math.max(count + 1, stack.peek()[1]);
                    stack.pop();
                }

                max = Math.max(max, count);
                stack.push(new int[] {val, count});
            }

            return max;
        }
    }

    class Solution_Incorrect {
        public int totalSteps(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            Stack<Integer> stack = new Stack<>();

            int i = 0;
            int n = nums.length;
            int maxStreak = 0;

            while(i < n) {
                stack.push(nums[i++]);
                int streak = 0;
                while(i < n && nums[i] < stack.peek()) {
                    streak++;
                    i++;
                }

                maxStreak = Math.max(maxStreak, streak);
            }

            return maxStreak;
        }
    }


// [5,3,4,4,7,3,6,11,8,5,11]
// [10,1,2,3,4,5,6,1,2,3]
// [7,14,4,14,13,2,6,13]
}

//    2289. Steps to Make Array Non-decreasing
//    Medium
//    You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
//
//    Return the number of steps performed until nums becomes a non-decreasing array.
//
//
//
//    Example 1:
//
//    Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
//    Output: 3
//    Explanation: The following are the steps performed:
//    - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
//    - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
//    - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
//    [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
//    Example 2:
//
//    Input: nums = [4,5,7,7,13]
//    Output: 0
//    Explanation: nums is already a non-decreasing array. Therefore, we return 0.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109