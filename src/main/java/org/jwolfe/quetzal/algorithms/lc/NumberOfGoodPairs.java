package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodPairs {
    class Solution {
        public int numIdenticalPairs(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int[] counts = new int[101];
            for(int a : nums) {
                counts[a]++;
            }

            int goodPairs = 0;
            for(int count : counts) {
                if(count > 1) {
                    goodPairs += (count) * (count - 1) / 2;
                }
            }

            return goodPairs;
        }
    }

    class Solution_Correct_1 {
        public int numIdenticalPairs(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : nums) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            int goodPairs = 0;
            for(int count : frequencies.values()) {
                if(count > 1) {
                    goodPairs += (count * (count - 1) / 2);
                }
            }

            return goodPairs;
        }
    }

    class Solution_Brute {
        public int numIdenticalPairs(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int goodPairs = 0;
            for(int i = 0; i < nums.length - 1; i++) {
                for(int j = i + 1; j < nums.length; j++) {
                    if(nums[i] == nums[j]) {
                        goodPairs++;
                    }
                }
            }

            return goodPairs;
        }
    }
}

//    1512. Number of Good Pairs
//    Easy
//    Given an array of integers nums, return the number of good pairs.
//
//    A pair (i, j) is called good if nums[i] == nums[j] and i < j.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,1,1,3]
//    Output: 4
//    Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
//    Example 2:
//
//    Input: nums = [1,1,1,1]
//    Output: 6
//    Explanation: Each pair in the array are good.
//    Example 3:
//
//    Input: nums = [1,2,3]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i] <= 100