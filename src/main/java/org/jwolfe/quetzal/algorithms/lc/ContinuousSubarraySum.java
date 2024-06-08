package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            Map<Integer, Integer> modMap = new HashMap<>();
            modMap.put(0, -1);
            int prefixMod = 0;
            for(int i = 0; i < nums.length; i++) {
                prefixMod = (prefixMod + nums[i]) % k;

                if(modMap.containsKey(prefixMod)) {
                    int j = modMap.get(prefixMod);
                    if(i - j > 1) {
                        return true;
                    }
                } else {
                    modMap.put(prefixMod, i);
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean checkSubarraySum(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 0);
            int sum = 0;
            for(int i = 0; i < n; i++) {
                int val = nums[i];
                sum += val;
                int m = sum % k;

                if(!map.containsKey(m)) {
                    map.put(m, i + 1);
                }
                else if(map.get(m) < i) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Brute_TLE {
        public boolean checkSubarraySum(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            int n = nums.length;
            for(int i = 0; i < n - 1; i++) {
                int sum = nums[i];

                for(int j = i + 1; j < n; j++) {
                    sum += nums[j];

                    if(sum % k == 0) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// [23,2,4,6,7]
// 6

// [5,0,0,0]
// 3
}

//    523. Continuous Subarray Sum
//    Medium
//    Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
//
//    An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
//
//
//
//    Example 1:
//
//    Input: nums = [23,2,4,6,7], k = 6
//    Output: true
//    Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
//    Example 2:
//
//    Input: nums = [23,2,6,4,7], k = 6
//    Output: true
//    Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
//    42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
//    Example 3:
//
//    Input: nums = [23,2,6,4,7], k = 13
//    Output: false
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] <= 109
//    0 <= sum(nums[i]) <= 231 - 1
//    1 <= k <= 231 - 1