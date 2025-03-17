package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DivideArrayIntoEqualPairs {
    class Solution {
        public boolean divideArray(int[] nums) {
            if (nums == null || nums.length % 2 != 0) {
                return false;
            }

            boolean[] paired = new boolean[501];
            Arrays.fill(paired, true);

            for (int val : nums) {
                paired[val] = !paired[val];
            }

            for (int i = 1; i < 501; i++) {
                if (!paired[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public boolean divideArray(int[] nums) {
            if (nums == null || nums.length % 2 != 0) {
                return false;
            }

            int[] counts = new int[501];
            for (int val : nums) {
                counts[val]++;
            }

            for (int count : counts) {
                if (count % 2 != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean divideArray(int[] nums) {
            if (nums == null || nums.length % 2 != 0) {
                return false;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int val : nums) {
                map.put(val, map.getOrDefault(val, 0) + 1);
            }

            for (Integer count : map.values()) {
                if (count % 2 != 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    2206. Divide Array Into Equal Pairs
//    Easy
//    You are given an integer array nums consisting of 2 * n integers.
//
//    You need to divide nums into n pairs such that:
//
//    Each element belongs to exactly one pair.
//    The elements present in a pair are equal.
//    Return true if nums can be divided into n pairs, otherwise return false.
//
//
//
//    Example 1:
//
//    Input: nums = [3,2,3,2,2,2]
//    Output: true
//    Explanation:
//    There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
//    If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: false
//    Explanation:
//    There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.
//
//
//    Constraints:
//
//    nums.length == 2 * n
//    1 <= n <= 500
//    1 <= nums[i] <= 500