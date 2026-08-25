package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingMultipleOfK {
    class Solution {
        public int missingMultiple(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            for (int x : nums) {
                set.add(x);
            }

            int multiple = k;
            while (set.contains(multiple)) {
                multiple += k;
            }

            return multiple;
        }
    }
}

//    3718. Smallest Missing Multiple of K
//    Easy
//    Given an integer array nums and an integer k, return the smallest positive multiple of k that is missing from nums.
//
//    A multiple of k is any positive integer divisible by k.
//
//
//
//    Example 1:
//
//    Input: nums = [8,2,3,4,6], k = 2
//
//    Output: 10
//
//    Explanation:
//
//    The multiples of k = 2 are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from nums is 10.
//
//    Example 2:
//
//    Input: nums = [1,4,7,10,15], k = 5
//
//    Output: 5
//
//    Explanation:
//
//    The multiples of k = 5 are 5, 10, 15, 20... and the smallest multiple missing from nums is 5.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i] <= 100
//    1 <= k <= 100