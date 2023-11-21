package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountNicePairsInAnArray {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countNicePairs(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int[] revDiffs = new int[n];
            for(int i = 0; i < n; i++) {
                int a = nums[i];
                int b = reverse(a);

                revDiffs[i] = a - b;
            }

            int count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int a = revDiffs[i];
                if(map.containsKey(a)) {
                    count += map.get(a);
                    count %= MOD;
                }

                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            return count;
        }

        private int reverse(int a) {
            int b = 0;
            while(a > 0) {
                b *= 10;
                b += (a % 10);

                a /= 10;
            }

            return b;
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    1814. Count Nice Pairs in an Array
//    Medium
//    You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:
//
//    0 <= i < j < nums.length
//    nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
//    Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: nums = [42,11,1,97]
//    Output: 2
//    Explanation: The two pairs are:
//    - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
//    - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
//    Example 2:
//
//    Input: nums = [13,10,35,24,76]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] <= 109