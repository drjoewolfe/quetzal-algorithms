package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SingleNumberII {
    class Solution {
        public int singleNumber(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int ones = 0;
            int twos = 0;
            for(int val : nums) {
                ones = (ones ^ val) & (~twos);
                twos = (twos ^ val) & (~ones);
            }

            return ones;
        }
    }

    class Solution_Correct_1 {
        public int singleNumber(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int ones = 0;
            int twos = 0;
            int threes = 0;

            for(int v : nums) {
                twos = twos | (ones & v);
                ones = ones ^ v;
                threes = twos & ones;
                twos = twos & (~threes);
                ones = ones & (~threes);
            }

            return ones;
        }
    }

    class Solution_BitCounting {
        public int singleNumber(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int[] bits = new int[32];
            for(int i = 0; i < 32; i++) {
                for(int v : nums) {
                    bits[i] += v >> i & 1;
                    bits[i] %= 3;
                }
            }

            int result = 0;
            for(int i = 0; i < 32; i++) {
                result |= (bits[i] << i);
            }

            return result;
        }

        private void print(int[] arr) {
            for(int v : arr) {
                System.out.print(v + " ");
            }

            System.out.println();
        }
    }

    class Solution_WithSpace {
        public int singleNumber(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int v : nums) {
                frequencies.put(v, frequencies.getOrDefault(v, 0) + 1);
            }

            for(var entry : frequencies.entrySet()) {
                if(entry.getValue() != 3) {
                    return entry.getKey();
                }
            }

            return -1;
        }
    }
}

//    137. Single Number II
//    Medium
//    Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
//
//    You must implement a solution with a linear runtime complexity and use only constant extra space.
//
//
//
//    Example 1:
//
//    Input: nums = [2,2,3,2]
//    Output: 3
//    Example 2:
//
//    Input: nums = [0,1,0,1,0,1,99]
//    Output: 99
//
//
//    Constraints:
//
//    1 <= nums.length <= 3 * 104
//    -231 <= nums[i] <= 231 - 1
//    Each element in nums appears exactly three times except for one element which appears once.