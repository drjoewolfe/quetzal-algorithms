package org.jwolfe.quetzal.algorithms.lc;

public class MinimumMovesToMakeArrayComplementary {
    class Solution {

        // Line
        // 2 ---- a + 1 --- a + b --- b + limit --- 2 * limit

        public int minMoves(int[] nums, int limit) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] cost = new int[2 * limit + 2];

            for (int i = 0; i < n / 2; i++) {
                int x = nums[i];
                int y = nums[n - i - 1];

                int a = Math.min(x, y);
                int b = Math.max(x, y);
                ;

                cost[2] += 2;
                cost[a + 1] -= 2;

                cost[a + 1] += 1;
                cost[a + b] -= 1;

                cost[a + b + 1] += 1;
                cost[b + limit + 1] -= 1;

                cost[b + limit + 1] += 2;
            }

            int minCost = n;
            int currCost = 0;
            for (int i = 2; i <= 2 * limit; i++) {
                currCost += cost[i];
                minCost = Math.min(minCost, currCost);
            }

            return minCost;
        }
    }
}

//    1674. Minimum Moves to Make Array Complementary
//    Medium
//    You are given an integer array nums of even length n and an integer limit. In one move, you can replace any integer from nums with another integer between 1 and limit, inclusive.
//
//    The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number. For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
//
//    Return the minimum number of moves required to make nums complementary.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,4,3], limit = 4
//    Output: 1
//    Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
//    nums[0] + nums[3] = 1 + 3 = 4.
//    nums[1] + nums[2] = 2 + 2 = 4.
//    nums[2] + nums[1] = 2 + 2 = 4.
//    nums[3] + nums[0] = 3 + 1 = 4.
//    Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
//    Example 2:
//
//    Input: nums = [1,2,2,1], limit = 2
//    Output: 2
//    Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
//    Example 3:
//
//    Input: nums = [1,2,1,2], limit = 2
//    Output: 0
//    Explanation: nums is already complementary.
//
//
//    Constraints:
//
//    n == nums.length
//    2 <= n <= 105
//    1 <= nums[i] <= limit <= 105
//    n is even.