package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountTheNumberOfGoodSubarrays {
    class Solution {
        public long countGood(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return 0;
            }

            long goodPairSetCount = 0;

            int n = nums.length;
            Map<Integer, Integer> countMap = new HashMap<>();

            int left = 0;
            int right = 0;

            long currentPairCount = 0;

            while(right < n) {
                int val = nums[right];

                currentPairCount += countMap.getOrDefault(val, 0);
                countMap.put(val, countMap.getOrDefault(val, 0) + 1);

                while(left <= right
                        && currentPairCount >= k) {
                    goodPairSetCount += (n - right);

                    int lval = nums[left];

                    countMap.put(lval, countMap.get(lval) - 1);
                    currentPairCount -= countMap.get(lval);

                    left++;
                }

                right++;
            }

            return goodPairSetCount;
        }
    }

    class Solution_Correct {
        public long countGood(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return 0;
            }

            long goodPairSetCount = 0;

            int n = nums.length;
            Map<Integer, Integer> countMap = new HashMap<>();
            countMap.put(nums[0], 1);

            int left = 0;
            int right = 1;

            while(right < n) {
                int val = nums[right];
                countMap.put(val, countMap.getOrDefault(val, 0) + 1);

                int pairCount = getGoodPairCount(countMap);
                if(pairCount >= k) {
                    goodPairSetCount += (n - right);
                    countMap.put(nums[left], countMap.get(nums[left++]) - 1);
                    while(getGoodPairCount(countMap) >= k) {
                        goodPairSetCount += (n - right);
                        countMap.put(nums[left], countMap.get(nums[left++]) - 1);
                    }
                }

                right++;
            }

            return goodPairSetCount;
        }

        private int getGoodPairCount(Map<Integer, Integer> countMap) {
            int pairCount = 0;
            for(var entry : countMap.entrySet()) {
                int count = entry.getValue();
                if(count < 2) {
                    continue;
                }

                pairCount += (factorial(count) / (factorial(count - 2) * 2));
            }

            return pairCount;
        }

        private long factorial(int n) {
            int result = 1;
            while(n > 0) {
                result *= n;
                n--;
            }

            return result;
        }
    }

// [1,1,1,1,1]
// 10

// [3,1,4,3,2,2,4]
// 2
}

//    2537. Count the Number of Good Subarrays
//    Medium
//    Given an integer array nums and an integer k, return the number of good subarrays of nums.
//
//    A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,1,1,1], k = 10
//    Output: 1
//    Explanation: The only good subarray is the array nums itself.
//    Example 2:
//
//    Input: nums = [3,1,4,3,2,2,4], k = 2
//    Output: 4
//    Explanation: There are 4 different good subarrays:
//    - [3,1,4,3,2,2] that has 2 pairs.
//    - [3,1,4,3,2,2,4] that has 3 pairs.
//    - [1,4,3,2,2,4] that has 2 pairs.
//    - [4,3,2,2,4] that has 2 pairs.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i], k <= 109