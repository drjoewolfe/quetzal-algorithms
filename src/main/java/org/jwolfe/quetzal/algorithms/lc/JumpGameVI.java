package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class JumpGameVI {
    class Solution {
        public int maxResult(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k == 0) {
                return 0;
            }

            int n = nums.length;
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            maxHeap.offer(new int[] {nums[0], 0});

            int maxScore = nums[0];
            for(int i = 1; i < n; i++) {
                while((i - maxHeap.peek()[1]) > k) {
                    maxHeap.poll();
                }

                int[] preceedingStep = maxHeap.peek();
                maxScore = preceedingStep[0] + nums[i];
                maxHeap.offer(new int[] {maxScore, i});
            }

            return maxScore;
        }
    }

    class Solution_Classic {
        int maxScore;

        public int maxResult(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k == 0) {
                return 0;
            }

            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = nums[0];
            for(int i = 1; i < n; i++) {
                for(int j = Math.max(0, i - k); j < i; j++) {
                    if(dp[j] + nums[i] > dp[i]) {
                        dp[i] = dp[j] + nums[i];
                    }
                }
            }

            return dp[n - 1];
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [1,-1,-2,4,-7,3]
// 2

// [1,-5,-20,4,-1,3,-6,-3]
// 2


// [10,-5,-2,4,0,3]
// 3
}

//    1696. Jump Game VI
//    Medium
//    You are given a 0-indexed integer array nums and an integer k.
//
//    You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
//
//    You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
//
//    Return the maximum score you can get.
//
//
//
//    Example 1:
//
//    Input: nums = [1,-1,-2,4,-7,3], k = 2
//    Output: 7
//    Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
//    Example 2:
//
//    Input: nums = [10,-5,-2,4,0,3], k = 3
//    Output: 17
//    Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
//    Example 3:
//
//    Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length, k <= 105
//    -104 <= nums[i] <= 104
