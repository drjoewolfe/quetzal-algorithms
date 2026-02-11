package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubarrayII {
    class Solution {
        int[] segmentTreeMin;
        int[] segmentTreeMax;
        int[] segmentTreeLazy;

        public int longestBalanced(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            segmentTreeMin = new int[4 * n];
            segmentTreeMax = new int[4 * n];
            segmentTreeLazy = new int[4 * n];

            Map<Integer, Integer> map = new HashMap<>();
            int maxLength = 0;

            for (int r = 0; r < n; r++) {
                int x = nums[r];
                int val = (x % 2 == 0) ? 1 : -1;

                int prev = -1;
                if (map.containsKey(x)) {
                    prev = map.get(x);
                }

                if (prev != -1) {
                    updateRange(0, prev, 0, 0, n - 1, -val);
                }

                updateRange(0, r, 0, 0, n - 1, val);

                int l = findLeftMostZero(0, 0, n - 1);
                if (l != -1) {
                    maxLength = Math.max(maxLength, r - l + 1);
                }

                map.put(x, r);
            }

            return maxLength;
        }

        private void updateRange(int start, int end, int index, int left, int right, int val) {
            propogate(index, left, right);

            if (left > end || right < start) {
                return;
            }

            if (left >= start && right <= end) {
                segmentTreeLazy[index] += val;
                propogate(index, left, right);

                return;
            }

            int mid = left + (right - left) / 2;
            updateRange(start, end, 2 * index + 1, left, mid, val);
            updateRange(start, end, 2 * index + 2, mid + 1, right, val);

            segmentTreeMin[index] = Math.min(segmentTreeMin[2 * index + 1], segmentTreeMin[2 * index + 2]);
            segmentTreeMax[index] = Math.max(segmentTreeMax[2 * index + 1], segmentTreeMax[2 * index + 2]);
        }

        private int findLeftMostZero(int index, int left, int right) {
            propogate(index, left, right);

            if (segmentTreeMin[index] > 0 || segmentTreeMax[index] < 0) {
                return -1;
            }

            if (left == right) {
                return left;
            }

            int mid = left + (right - left) / 2;
            int leftResult = findLeftMostZero(2 * index + 1, left, mid);
            if (leftResult != -1) {
                return leftResult;
            }

            return findLeftMostZero(2 * index + 2, mid + 1, right);
        }

        private void propogate(int index, int left, int right) {
            if (segmentTreeLazy[index] != 0) {
                segmentTreeMin[index] += segmentTreeLazy[index];
                segmentTreeMax[index] += segmentTreeLazy[index];
            }

            if (left != right) {
                segmentTreeLazy[2 * index + 1] += segmentTreeLazy[index];
                segmentTreeLazy[2 * index + 2] += segmentTreeLazy[index];
            }

            segmentTreeLazy[index] = 0;
        }
    }

    class Solution_TLE {
        public int longestBalanced(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int[] prefixSums = new int[n];
            Map<Integer, Integer> map = new HashMap<>();
            int maxLength = 0;

            for (int r = 0; r < n; r++) {
                int x = nums[r];
                int val = (x % 2 == 0) ? 1 : -1;

                int prev = -1;
                if (map.containsKey(x)) {
                    prev = map.get(x);
                }

                if (prev != -1) {
                    for (int l = 0; l <= prev; l++) {
                        prefixSums[l] -= val;
                    }
                }

                for (int l = 0; l <= r; l++) {
                    prefixSums[l] += val;
                }

                for (int l = 0; l <= r; l++) {
                    if (prefixSums[l] == 0) {
                        maxLength = Math.max(maxLength, r - l + 1);
                        break;
                    }
                }

                map.put(x, r);
            }

            return maxLength;
        }
    }
}

//    3721. Longest Balanced Subarray II
//    Hard
//    You are given an integer array nums.
//
//    A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.
//
//    Return the length of the longest balanced subarray.
//
//
//
//    Example 1:
//
//    Input: nums = [2,5,4,3]
//
//    Output: 4
//
//    Explanation:
//
//    The longest balanced subarray is [2, 5, 4, 3].
//    It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.
//    Example 2:
//
//    Input: nums = [3,2,2,5,4]
//
//    Output: 5
//
//    Explanation:
//
//    The longest balanced subarray is [3, 2, 2, 5, 4].
//    It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5]. Thus, the answer is 5.
//    Example 3:
//
//    Input: nums = [1,2,3,2]
//
//    Output: 3
//
//    Explanation:
//
//    The longest balanced subarray is [2, 3, 2].
//    It has 1 distinct even number [2] and 1 distinct odd number [3]. Thus, the answer is 3.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 105