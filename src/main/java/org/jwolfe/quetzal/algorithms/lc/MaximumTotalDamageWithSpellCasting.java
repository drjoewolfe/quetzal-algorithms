package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumTotalDamageWithSpellCasting {
    class Solution {
        public long maximumTotalDamage(int[] power) {
            if (power == null || power.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int p : power) {
                map.put(p, map.getOrDefault(p, 0) + 1);
            }

            int n = map.size();
            int[] uniquePowers = new int[n];
            int i = 0;
            for (var key : map.keySet()) {
                uniquePowers[i++] = key;
            }

            Arrays.sort(uniquePowers);

            long[] dp = new long[n];
            long maxDamage = 0;
            for (i = n - 1; i >= 0; i--) {
                int p = uniquePowers[i];
                int j = lowerBound(uniquePowers, i, n, uniquePowers[i] + 3);

                long skip = (i < n - 1) ? dp[i + 1] : 0;
                long take = 1L * p * map.get(p) + (j < n ? dp[j] : 0);
                dp[i] = Math.max(skip, take);
                maxDamage = Math.max(maxDamage, dp[i]);
            }

            return maxDamage;
        }

        private int lowerBound(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }
}

//    3186. Maximum Total Damage With Spell Casting
//    Medium
//    A magician has various spells.
//
//    You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.
//
//    It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
//
//    Each spell can be cast only once.
//
//    Return the maximum possible total damage that a magician can cast.
//
//
//
//    Example 1:
//
//    Input: power = [1,1,3,4]
//
//    Output: 6
//
//    Explanation:
//
//    The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.
//
//    Example 2:
//
//    Input: power = [7,1,6,6]
//
//    Output: 13
//
//    Explanation:
//
//    The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.
//
//
//
//    Constraints:
//
//    1 <= power.length <= 105
//    1 <= power[i] <= 109