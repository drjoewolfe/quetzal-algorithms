package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumNumberOfRemovalsToMakeMountainArray {
    class Solution {
        public int minimumMountainRemovals(int[] nums) {
            if (nums == null || nums.length < 3) {
                return -1;
            }

            int n = nums.length;
            int[] lis = new int[n];
            int[] lds = new int[n];

            Arrays.fill(lis, 1);
            Arrays.fill(lds, 1);

            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j]) {
                        lis[i] = Math.max(lis[i], lis[j] + 1);
                    }
                }
            }

            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[i] > nums[j]) {
                        lds[i] = Math.max(lds[i], lds[j] + 1);
                    }
                }
            }

            int minRemovals = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (lis[i] > 1 && lds[i] > 1) {
                    minRemovals = Math.min(minRemovals, n - (lis[i] + lds[i] - 1));
                }
            }

            return minRemovals;
        }
    }
}

//    1671. Minimum Number of Removals to Make Mountain Array
//    Hard
//    You may recall that an array arr is a mountain array if and only if:
//
//    arr.length >= 3
//    There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
//    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//    Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,1]
//    Output: 0
//    Explanation: The array itself is a mountain array so we do not need to remove any elements.
//    Example 2:
//
//    Input: nums = [2,1,1,5,6,2,3,1]
//    Output: 3
//    Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
//
//
//    Constraints:
//
//    3 <= nums.length <= 1000
//    1 <= nums[i] <= 109
//    It is guaranteed that you can make a mountain array out of nums.
