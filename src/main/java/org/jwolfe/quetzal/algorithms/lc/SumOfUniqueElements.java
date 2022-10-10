package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SumOfUniqueElements {
    class Solution {
        public int sumOfUnique(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int val : nums) {
                map.put(val, map.getOrDefault(val, 0) + 1);
            }

            int sum = 0;
            for(var entry : map.entrySet()) {
                if(entry.getValue() == 1) {
                    sum += entry.getKey();
                }
            }

            return sum;
        }
    }
}

//    1748. Sum of Unique Elements
//    Easy
//    You are given an integer array nums. The unique elements of an array are the elements that appear exactly once in the array.
//
//    Return the sum of all the unique elements of nums.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,2]
//    Output: 4
//    Explanation: The unique elements are [1,3], and the sum is 4.
//    Example 2:
//
//    Input: nums = [1,1,1,1,1]
//    Output: 0
//    Explanation: There are no unique elements, and the sum is 0.
//    Example 3:
//
//    Input: nums = [1,2,3,4,5]
//    Output: 15
//    Explanation: The unique elements are [1,2,3,4,5], and the sum is 15.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i] <= 100
