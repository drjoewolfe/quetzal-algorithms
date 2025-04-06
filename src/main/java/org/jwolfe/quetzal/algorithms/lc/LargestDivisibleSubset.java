package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> subset = new ArrayList<>();

            int n = nums.length;
            int[] largestDivisibleCounts = new int[n];
            int[] largestDivisibleIndexes = new int[n];
            int largestDivisibleCountIndex = 0;

            Arrays.fill(largestDivisibleIndexes, -1);

            Arrays.sort(nums);

            for (int i = 1; i < n; i++) {
                int a = nums[i];
                for (int j = 0; j < i; j++) {
                    int b = nums[j];

                    if (a % b == 0) {
                        if (largestDivisibleCounts[j] + 1 > largestDivisibleCounts[i]) {
                            largestDivisibleCounts[i] = largestDivisibleCounts[j] + 1;
                            largestDivisibleIndexes[i] = j;

                            if (largestDivisibleCounts[i] > largestDivisibleCounts[largestDivisibleCountIndex]) {
                                largestDivisibleCountIndex = i;
                            }
                        }
                    }
                }
            }

            while (largestDivisibleCountIndex >= 0) {
                subset.add(nums[largestDivisibleCountIndex]);
                largestDivisibleCountIndex = largestDivisibleIndexes[largestDivisibleCountIndex];
            }

            return subset;
        }
    }

    class Solution_Correct_2 {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> subset = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return subset;
            }

            int n = nums.length;
            int[] largestDivisibleCounts = new int[n];
            int[] largestDivisibleIndexes = new int[n];
            int largestDivisibleCountIndex = 0;

            Arrays.fill(largestDivisibleIndexes, -1);

            Arrays.sort(nums);

            for (int i = 1; i < n; i++) {
                int a = nums[i];

                for (int j = 0; j < i; j++) {
                    int b = nums[j];

                    if (a % b == 0) {
                        if (largestDivisibleCounts[i] < largestDivisibleCounts[j] + 1) {
                            largestDivisibleCounts[i] = largestDivisibleCounts[j] + 1;
                            largestDivisibleIndexes[i] = j;

                            if (largestDivisibleCounts[largestDivisibleCountIndex] < largestDivisibleCounts[i]) {
                                largestDivisibleCountIndex = i;
                            }
                        }
                    }
                }
            }

            while (largestDivisibleCountIndex >= 0) {
                subset.add(nums[largestDivisibleCountIndex]);
                largestDivisibleCountIndex = largestDivisibleIndexes[largestDivisibleCountIndex];
            }

            return subset;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> largestDivisibleSubset = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return largestDivisibleSubset;
            }

            int n = nums.length;
            int largestDivisibleCountIndex = 0;
            int[] largestDivisibleCounts = new int[n];
            int[] largestFactorIndexes = new int[n];
            Arrays.fill(largestDivisibleCounts, 1);
            Arrays.fill(largestFactorIndexes, -1);

            Arrays.sort(nums);

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    int a = nums[i];
                    int b = nums[j];

                    if (a % b != 0) {
                        continue;
                    }

                    if (largestDivisibleCounts[i] < 1 + largestDivisibleCounts[j]) {
                        largestDivisibleCounts[i] = 1 + largestDivisibleCounts[j];
                        largestFactorIndexes[i] = j;
                        largestDivisibleCountIndex = largestDivisibleCounts[i] > largestDivisibleCounts[largestDivisibleCountIndex] ? i : largestDivisibleCountIndex;
                    }
                }
            }

            while (largestDivisibleCountIndex >= 0) {
                largestDivisibleSubset.add(nums[largestDivisibleCountIndex]);
                largestDivisibleCountIndex = largestFactorIndexes[largestDivisibleCountIndex];
            }

            return largestDivisibleSubset;
        }
    }
}

//    368. Largest Divisible Subset
//    Medium
//    Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
//
//    answer[i] % answer[j] == 0, or
//    answer[j] % answer[i] == 0
//    If there are multiple solutions, return any of them.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: [1,2]
//    Explanation: [1,3] is also accepted.
//    Example 2:
//
//    Input: nums = [1,2,4,8]
//    Output: [1,2,4,8]
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 2 * 109
//    All the integers in nums are unique.