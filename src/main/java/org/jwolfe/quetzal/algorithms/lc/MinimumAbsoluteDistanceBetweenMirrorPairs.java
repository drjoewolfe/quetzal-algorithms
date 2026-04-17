package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MinimumAbsoluteDistanceBetweenMirrorPairs {
    class Solution {
        public int minMirrorPairDistance(int[] nums) {
            if (nums == null || nums.length < 2) {
                return -1;
            }

            int minDistance = Integer.MAX_VALUE;

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[n - 1], n - 1);

            for (int i = n - 2; i >= 0; i--) {
                int val = nums[i];
                int rval = reverse(val);

                if (map.containsKey(rval)) {
                    int distance = map.get(rval) - i;
                    minDistance = Math.min(minDistance, distance);
                }

                map.put(val, i);
            }

            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
        }

        private int reverse(int val) {
            int rval = 0;
            while (val > 0) {
                rval *= 10;
                rval += val % 10;

                val /= 10;
            }

            return rval;
        }
    }
}

//    3761. Minimum Absolute Distance Between Mirror Pairs
//    Medium
//    You are given an integer array nums.
//
//    A mirror pair is a pair of indices (i, j) such that:
//
//    0 <= i < j < nums.length, and
//    reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading zeros are omitted after reversing, for example reverse(120) = 21.
//    Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices i and j is abs(i - j).
//
//    If no mirror pair exists, return -1.
//
//
//
//    Example 1:
//
//    Input: nums = [12,21,45,33,54]
//
//    Output: 1
//
//    Explanation:
//
//    The mirror pairs are:
//
//    (0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
//    (2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
//    The minimum absolute distance among all pairs is 1.
//
//    Example 2:
//
//    Input: nums = [120,21]
//
//    Output: 1
//
//    Explanation:
//
//    There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].
//
//    The minimum absolute distance is 1.
//
//    Example 3:
//
//    Input: nums = [21,120]
//
//    Output: -1
//
//    Explanation:
//
//    There are no mirror pairs in the array.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109​​​​​​​