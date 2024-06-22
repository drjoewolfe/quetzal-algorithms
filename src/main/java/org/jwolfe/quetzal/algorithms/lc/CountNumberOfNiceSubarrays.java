package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfNiceSubarrays {
    class Solution {
        public int numberOfSubarrays(int[] nums, int k) {
            if (nums == null || nums.length < k || k < 1) {
                return 0;
            }

            int n = nums.length;
            int niceArrayCount = 0;

            Map<Integer, Integer> prefixSumCounts = new HashMap<>();

            int currentSum = 0;
            prefixSumCounts.put(currentSum, 1);

            for (int i = 0; i < n; i++) {
                currentSum += nums[i] % 2;

                int key = currentSum - k;
                if (prefixSumCounts.containsKey(key)) {
                    niceArrayCount += prefixSumCounts.get(key);
                }

                prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);
            }

            return niceArrayCount;
        }
    }

    class Solution_Incorrect {
        public int numberOfSubarrays(int[] nums, int k) {
            if (nums == null || nums.length < k || k < 1) {
                return 0;
            }

            int n = nums.length;
            int left = 0;
            int right = 0;
            int niceArrayCount = 0;
            int oddCount = 0;

            for (; right < n; right++) {
                int val = nums[right];

                if (val % 2 == 1) {
                    oddCount++;
                }

                if (oddCount == k) {
                    while (left < right) {
                        if (nums[left] % 2 == 1) {
                            oddCount--;
                            break;
                        }

                        niceArrayCount++;
                        left++;
                    }
                }
            }

            return niceArrayCount;
        }
    }

// [1,1,2,1,1]
// 3

// [2,2,2,1,2,2,1,2,2,2]
// 2

// [2,4,6]
// 1
}

//    1248. Count Number of Nice Subarrays
//    Medium
//    Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
//
//    Return the number of nice sub-arrays.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,2,1,1], k = 3
//    Output: 2
//    Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
//    Example 2:
//
//    Input: nums = [2,4,6], k = 1
//    Output: 0
//    Explanation: There are no odd numbers in the array.
//    Example 3:
//
//    Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//    Output: 16
//
//
//    Constraints:
//
//    1 <= nums.length <= 50000
//    1 <= nums[i] <= 10^5
//    1 <= k <= nums.length