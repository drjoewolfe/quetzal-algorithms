package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximizeSubarraysAfterRemovingOneConflictingPair {
    class Solution {
        public long maxSubarrays(int n, int[][] conflictingPairs) {
            if (n < 1) {
                return 0;
            }

            long totalSubArrays = 1L * n * (n + 1) / 2;
            int m = conflictingPairs.length;
            for (int i = 0; i < m; i++) {
                int[] pair = conflictingPairs[i];
                int start = pair[0];
                int end = pair[1];

                if (start > end) {
                    swap(pair);
                }
            }

            Arrays.sort(conflictingPairs, (a, b) -> a[1] - b[1]);

            int maxStart1 = 0;
            int maxStart2 = 0;
            long profit = 0;
            long maxProfit = 0;
            long blocked = 0;

            for (int i = 0; i < m; i++) {
                int[] pair = conflictingPairs[i];
                int start = pair[0];
                int end = pair[1];

                int bottom = ((i < m - 1) ? conflictingPairs[i + 1][1] : n + 1);
                int gap = bottom - end;

                if (start > maxStart1) {
                    maxStart2 = maxStart1;
                    maxStart1 = start;
                    profit = 0;
                } else if (start > maxStart2) {
                    maxStart2 = start;
                }

                profit += 1L * (maxStart1 - maxStart2) * gap;
                maxProfit = Math.max(maxProfit, profit);

                blocked += 1L * maxStart1 * gap;
            }

            return totalSubArrays - blocked + maxProfit;
        }

        private void swap(int[] pair) {
            int temp = pair[0];
            pair[0] = pair[1];
            pair[1] = temp;
        }
    }
}

//    3480. Maximize Subarrays After Removing One Conflicting Pair
//    Hard
//    You are given an integer n which represents an array nums containing the numbers from 1 to n in order. Additionally, you are given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates that a and b form a conflicting pair.
//
//    Remove exactly one element from conflictingPairs. Afterward, count the number of non-empty subarrays of nums which do not contain both a and b for any remaining conflicting pair [a, b].
//
//    Return the maximum number of subarrays possible after removing exactly one conflicting pair.
//
//
//
//    Example 1:
//
//    Input: n = 4, conflictingPairs = [[2,3],[1,4]]
//
//    Output: 9
//
//    Explanation:
//
//    Remove [2, 3] from conflictingPairs. Now, conflictingPairs = [[1, 4]].
//    There are 9 subarrays in nums where [1, 4] do not appear together. They are [1], [2], [3], [4], [1, 2], [2, 3], [3, 4], [1, 2, 3] and [2, 3, 4].
//    The maximum number of subarrays we can achieve after removing one element from conflictingPairs is 9.
//    Example 2:
//
//    Input: n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]
//
//    Output: 12
//
//    Explanation:
//
//    Remove [1, 2] from conflictingPairs. Now, conflictingPairs = [[2, 5], [3, 5]].
//    There are 12 subarrays in nums where [2, 5] and [3, 5] do not appear together.
//    The maximum number of subarrays we can achieve after removing one element from conflictingPairs is 12.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= conflictingPairs.length <= 2 * n
//    conflictingPairs[i].length == 2
//    1 <= conflictingPairs[i][j] <= n
//    conflictingPairs[i][0] != conflictingPairs[i][1]