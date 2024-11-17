package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.PriorityQueue;

public class ShortestSubarrayWithSumAtLeastK {
    class Solution {
        public int shortestSubarray(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            PriorityQueue<Pair<Long, Integer>> prefixSumHeap = new PriorityQueue<>((a, b) -> Long.compare(a.getKey(), b.getKey()));

            int index = 0;
            long cumulativeSum = 0;
            int minLength = Integer.MAX_VALUE;

            while (index < n) {
                cumulativeSum += nums[index];

                if (cumulativeSum >= k) {
                    int length = index + 1;
                    minLength = Math.min(minLength, length);
                }

                while (!prefixSumHeap.isEmpty()
                        && cumulativeSum - prefixSumHeap.peek().getKey() >= k) {
                    var pair = prefixSumHeap.poll();
                    int length = index - pair.getValue();
                    minLength = Math.min(minLength, length);
                }

                prefixSumHeap.offer(new Pair<>(cumulativeSum, index));
                index++;
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }
    }


    class Solution_Incorrect {
        public int shortestSubarray(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            int sum = 0;

            int left = 0;
            int right = 0;
            int minLength = Integer.MAX_VALUE;

            while (right < n) {
                sum += nums[right];

                while (left <= right
                        && sum >= k) {
                    int length = right - left + 1;
                    minLength = Math.min(minLength, length);

                    sum -= nums[left];
                    left++;
                }

                right++;
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }
    }

    class Solution_TLE2 {
        public int shortestSubarray(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            int[] prefixSums = new int[n];
            prefixSums[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i];
            }

            for (int l = 1; l <= n; l++) {
                for (int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    int sum = prefixSums[j] - prefixSums[i] + nums[i];
                    if (sum >= k) {
                        return l;
                    }
                }
            }

            return -1;
        }
    }

    class Solution_TLE {
        public int shortestSubarray(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    int sum = arraySum(nums, i, j);
                    if (sum >= k) {
                        return l;
                    }
                }
            }

            return -1;
        }

        private int arraySum(int[] nums, int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }

            return sum;
        }
    }

// [1]
// 1

// [84,-37,32,40,95]
// 167
}

//    862. Shortest Subarray with Sum at Least K
//    Hard
//    Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
//
//    A subarray is a contiguous part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1], k = 1
//    Output: 1
//    Example 2:
//
//    Input: nums = [1,2], k = 4
//    Output: -1
//    Example 3:
//
//    Input: nums = [2,-1,2], k = 3
//    Output: 3
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -105 <= nums[i] <= 105
//    1 <= k <= 109