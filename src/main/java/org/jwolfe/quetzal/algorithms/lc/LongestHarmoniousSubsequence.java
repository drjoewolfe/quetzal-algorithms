package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int a : nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        int lhs = 0;
        for(int a : nums) {
            int b = a + 1;
            if(map.containsKey(b)) {
                lhs = Math.max(lhs, map.get(a) + map.get(b));
            }
        }

        return lhs;
    }
}

//    594. Longest Harmonious Subsequence
//    Easy
//    We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
//
//    Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
//
//    A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,2,2,5,2,3,7]
//    Output: 5
//    Explanation: The longest harmonious subsequence is [3,2,2,2,3].
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: 2
//    Example 3:
//
//    Input: nums = [1,1,1,1]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 104
//    -109 <= nums[i] <= 109