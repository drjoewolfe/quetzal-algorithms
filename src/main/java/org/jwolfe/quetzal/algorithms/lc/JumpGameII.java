package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class JumpGameII {
    class Solution {
        public int jump(int[] nums) {
            if(nums == null || nums.length < 2 || nums[0] == 0) {
                return 0;
            }

            int n = nums.length;
            int[] minJumps = new int[n];
            Arrays.fill(minJumps, -1);
            minJumps[n - 1] = 0;

            computeMinJumps(nums, 0, minJumps);
            return minJumps[0];
        }

        private int computeMinJumps(int[] nums, int index, int[] minJumps) {
            if(minJumps[index] != -1) {
                return minJumps[index];
            }

            int jumps = Integer.MAX_VALUE;
            for(int j = 1; j <= nums[index]; j++) {
                int pos = index + j;
                if(pos >= nums.length) {
                    break;
                }

                int minJumpsFromPost = computeMinJumps(nums, index + j, minJumps);

                if(minJumpsFromPost != Integer.MAX_VALUE) {
                    jumps = Math.min(jumps, 1 + minJumpsFromPost);
                }
            }

            minJumps[index] = jumps;
            return minJumps[index];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [2,3,1,1,4]
}

//    45. Jump Game II
//    Medium
//    Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
//    Each element in the array represents your maximum jump length at that position.
//
//    Your goal is to reach the last index in the minimum number of jumps.
//
//    You can assume that you can always reach the last index.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,1,1,4]
//    Output: 2
//    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//    Example 2:
//
//    Input: nums = [2,3,0,1,4]
//    Output: 2
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    0 <= nums[i] <= 105
