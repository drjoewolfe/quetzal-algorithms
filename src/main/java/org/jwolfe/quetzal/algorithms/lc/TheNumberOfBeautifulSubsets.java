package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TheNumberOfBeautifulSubsets {
    class Solution {
        public int beautifulSubsets(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            Arrays.sort(nums);
            Map<Integer, Integer> frequencies = new HashMap<>();
            return beautifulSubsets(nums, k, 0, frequencies) - 1;
        }

        private int beautifulSubsets(int[] nums, int k, int index, Map<Integer, Integer> frequencies) {
            if(index == nums.length) {
                // subset of lenggth 1
                return 1;
            }

            int val = nums[index];
            int count = 0;

            // Exclude
            count = beautifulSubsets(nums, k, index + 1, frequencies);

            // Include
            int prev = val - k;
            if(!frequencies.containsKey(prev)) {
                // Mark val as taken
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
                count += beautifulSubsets(nums, k, index + 1, frequencies);

                if(frequencies.get(val) == 1) {
                    frequencies.remove(val);
                } else {
                    frequencies.put(val, frequencies.get(val) - 1);
                }
            }

            return count;
        }
    }
}

//    2597. The Number of Beautiful Subsets
//    Medium
//    You are given an array nums of positive integers and a positive integer k.
//
//    A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
//
//    Return the number of non-empty beautiful subsets of the array nums.
//
//    A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
//
//
//
//    Example 1:
//
//    Input: nums = [2,4,6], k = 2
//    Output: 4
//    Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
//    It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
//    Example 2:
//
//    Input: nums = [1], k = 1
//    Output: 1
//    Explanation: The beautiful subset of the array nums is [1].
//    It can be proved that there is only 1 beautiful subset in the array [1].
//
//
//    Constraints:
//
//    1 <= nums.length <= 20
//    1 <= nums[i], k <= 1000