package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FindAllGoodIndices {
    class Solution {
        public List<Integer> goodIndices(int[] nums, int k) {
            List<Integer> results = new ArrayList<>();
            if(nums == null || nums.length < 2 * k + 1) {
                return results;
            }

            int n = nums.length;
            int[] nonIncreasingLengthsFromLeft = new int[n];
            int[] nonDecreasingLengthsFromRight = new int[n];

            nonIncreasingLengthsFromLeft[0] = 1;
            for(int i = 1; i < n; i++) {
                if(nums[i] <= nums[i - 1]) {
                    nonIncreasingLengthsFromLeft[i] = nonIncreasingLengthsFromLeft[i - 1] + 1;
                } else {
                    nonIncreasingLengthsFromLeft[i] = 1;
                }
            }

            nonDecreasingLengthsFromRight[n - 1] = 1;
            for(int i = n - 2; i >= 0; i--) {
                if(nums[i] <= nums[i + 1]) {
                    nonDecreasingLengthsFromRight[i] = nonDecreasingLengthsFromRight[i + 1] + 1;
                } else {
                    nonDecreasingLengthsFromRight[i] = 1;
                }
            }

            for(int i = k; i <= n - k; i++) {
                if(i + k >= n) {
                    break;
                }

                if(nonIncreasingLengthsFromLeft[i - 1] >= k
                        && nonDecreasingLengthsFromRight[i + 1] >= k) {
                    results.add(i);
                }
            }

            return results;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public List<Integer> goodIndices(int[] nums, int k) {
            List<Integer> results = new ArrayList<>();
            if(nums == null || nums.length < 2 * k + 1) {
                return results;
            }

            int n = nums.length;
            for(int i = k; i <= n - k; i++) {
                if(i + k >= n) {
                    break;
                }

                if(isNonIncreasing(nums, i - k, i - 1)
                        && isNonDecreasing(nums, i + 1, i + k)) {
                    results.add(i);
                }
            }

            return results;
        }

        private boolean isNonIncreasing(int[] nums, int left, int right) {
            for(int i = left + 1; i <= right; i++) {
                if(nums[i] > nums[i - 1]) {
                    return false;
                }
            }

            return true;
        }

        private boolean isNonDecreasing(int[] nums, int left, int right) {
            for(int i = left + 1; i <= right; i++) {
                if(nums[i] < nums[i - 1]) {
                    return false;
                }
            }

            return true;
        }
    }


// [2,1,1,1,3,4,1]
// 2

// [2,1,1,2]
// 2

// [440043,276285,336957]
// 1
}

//    2420. Find All Good Indices
//    Medium
//    You are given a 0-indexed integer array nums of size n and a positive integer k.
//
//    We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
//
//    The k elements that are just before the index i are in non-increasing order.
//    The k elements that are just after the index i are in non-decreasing order.
//    Return an array of all good indices sorted in increasing order.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,1,1,3,4,1], k = 2
//    Output: [2,3]
//    Explanation: There are two good indices in the array:
//    - Index 2. The subarray [2,1] is in non-increasing order, and the subarray [1,3] is in non-decreasing order.
//    - Index 3. The subarray [1,1] is in non-increasing order, and the subarray [3,4] is in non-decreasing order.
//    Note that the index 4 is not good because [4,1] is not non-decreasing.
//    Example 2:
//
//    Input: nums = [2,1,1,2], k = 2
//    Output: []
//    Explanation: There are no good indices in this array.
//
//
//    Constraints:
//
//    n == nums.length
//    3 <= n <= 105
//    1 <= nums[i] <= 106
//    1 <= k <= n / 2
