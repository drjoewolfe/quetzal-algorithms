package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class FindThePoweOfKSizeSubarraysI {
    class Solution {
        public int[] resultsArray(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return new int[0];
            }

            if (k == 1) {
                return nums;
            }

            int n = nums.length;
            int[] results = new int[n - k + 1];
            Arrays.fill(results, -1);

            int currentLength = 1;
            for (int i = 0; i < n - 1; i++) {
                int curr = nums[i];
                int next = nums[i + 1];

                if (next == curr + 1) {
                    // Consecutive
                    currentLength++;
                } else {
                    currentLength = 1;
                }

                if (currentLength >= k) {
                    int index = i - k + 2;
                    results[index] = next;
                }
            }

            return results;
        }
    }

// [1,2,3,4,3,2,5]
// 3

// [1]
// 1
}

//    3254. Find the Power of K-Size Subarrays I
//    Medium
//    You are given an array of integers nums of length n and a positive integer k.
//
//    The power of an array is defined as:
//
//    Its maximum element if all of its elements are consecutive and sorted in ascending order.
//    -1 otherwise.
//    You need to find the power of all subarrays of nums of size k.
//
//    Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4,3,2,5], k = 3
//
//    Output: [3,4,-1,-1,-1]
//
//    Explanation:
//
//    There are 5 subarrays of nums of size 3:
//
//    [1, 2, 3] with the maximum element 3.
//    [2, 3, 4] with the maximum element 4.
//    [3, 4, 3] whose elements are not consecutive.
//    [4, 3, 2] whose elements are not sorted.
//    [3, 2, 5] whose elements are not consecutive.
//    Example 2:
//
//    Input: nums = [2,2,2,2,2], k = 4
//
//    Output: [-1,-1]
//
//    Example 3:
//
//    Input: nums = [3,2,3,2,3,2], k = 2
//
//    Output: [-1,3,-1,3,-1]
//
//
//
//    Constraints:
//
//    1 <= n == nums.length <= 500
//    1 <= nums[i] <= 105
//    1 <= k <= n