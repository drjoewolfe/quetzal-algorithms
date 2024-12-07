package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MinimumLimitOfBallsInABag {
    class Solution {
        public int minimumSize(int[] nums, int maxOperations) {
            if (nums == null || nums.length == 0 || maxOperations < 0) {
                return 0;
            }

            int left = 1;
            int right = nums[0];

            for (int i = 1; i < nums.length; i++) {
                right = Math.max(right, nums[i]);
            }

            while (left < right) {
                int maxBallsInBag = left + (right - left) / 2;

                if (isMaxBallsPossible(nums, maxBallsInBag, maxOperations)) {
                    right = maxBallsInBag;
                } else {
                    left = maxBallsInBag + 1;
                }
            }

            return left;
        }

        private boolean isMaxBallsPossible(int[] nums, int maxBallsInBag, int maxOperations) {
            int totalOperations = 0;
            for (int balls : nums) {
                int operations = (int) Math.ceil(balls / (double) maxBallsInBag) - 1;
                totalOperations += operations;

                if (totalOperations > maxOperations) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Incorrect {
        public int minimumSize(int[] nums, int maxOperations) {
            if (nums == null || nums.length == 0 || maxOperations < 0) {
                return 0;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            for (int val : nums) {
                maxHeap.offer(val);
            }

            for (int i = 1; i <= maxOperations; i++) {
                int balls = maxHeap.poll();
                if (balls == 1) {
                    break;
                }

                int half = balls / 2;
                maxHeap.offer(half);
                if (balls % 2 == 0) {
                    maxHeap.offer(half);
                } else {
                    maxHeap.offer(half + 1);
                }

                System.out.println(maxHeap);
            }

            return maxHeap.poll();
        }
    }
}

//    1760. Minimum Limit of Balls in a Bag
//    Medium
//    You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.
//
//    You can perform the following operation at most maxOperations times:
//
//    Take any bag of balls and divide it into two new bags with a positive number of balls.
//    For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
//    Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
//
//    Return the minimum possible penalty after performing the operations.
//
//
//
//    Example 1:
//
//    Input: nums = [9], maxOperations = 2
//    Output: 3
//    Explanation:
//    - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
//    - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
//    The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
//    Example 2:
//
//    Input: nums = [2,4,8,2], maxOperations = 4
//    Output: 2
//    Explanation:
//    - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
//    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
//    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
//    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
//    The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= maxOperations, nums[i] <= 109