package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SingleNumberIII {
    class Solution {
        public int[] singleNumber(int[] nums) {
            if(nums == null || nums.length < 2) {
                return new int[0];
            }

            int xor = 0;
            for(int a : nums) {
                xor ^= a;
            }

            int firstSetBit = 0;
            for(int i = 31; i >= 0; i--) {
                if((xor & (1 << i)) > 1) {
                    firstSetBit = i;
                    break;
                }
            }

            int first = 0;
            for(int a : nums) {
                if((a & (1 << firstSetBit)) > 1) {
                    first ^= a;
                }
            }

            int second = first ^ xor;
            return new int[] {first, second};
        }
    }

    class Solution_Correct_1 {
        public int[] singleNumber(int[] nums) {
            if(nums == null || nums.length < 2) {
                return new int[0];
            }

            int xor = 0;
            for(int a : nums) {
                xor ^= a;
            }

            // Duplicates eliminated. xor is first ^ second
            // Get first set bit
            int firstSetBit = 0;
            for(int i = 0; i < 32; i++) {
                if(((xor >> i) & 1) == 1) {
                    firstSetBit = i;
                }
            }

            int first = 0;
            int second = 0;
            for(int a : nums) {
                if(((a >> firstSetBit) & 1) == 1) {
                    first ^= a;
                }
            }

            second = first ^ xor;
            return new int[] {first, second};
        }
    }

    class Solution_Map {
        public int[] singleNumber(int[] nums) {
            if(nums == null || nums.length < 2) {
                return new int[0];
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : nums) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            int[] result = new int[2];
            int index = 0;
            for(var entry : frequencies.entrySet()) {
                if(entry.getValue() == 1) {
                    result[index++] = entry.getKey();

                    if(index > 1) {
                        break;
                    }
                }
            }

            return result;
        }
    }
}

//    260. Single Number III
//    Medium
//    Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
//
//    You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,1,3,2,5]
//    Output: [3,5]
//    Explanation:  [5, 3] is also a valid answer.
//    Example 2:
//
//    Input: nums = [-1,0]
//    Output: [-1,0]
//    Example 3:
//
//    Input: nums = [0,1]
//    Output: [1,0]
//
//
//    Constraints:
//
//    2 <= nums.length <= 3 * 104
//    -231 <= nums[i] <= 231 - 1
//    Each integer in nums will appear twice, only two integers will appear once.