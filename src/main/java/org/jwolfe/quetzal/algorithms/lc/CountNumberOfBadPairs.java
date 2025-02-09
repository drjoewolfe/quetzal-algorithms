package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBadPairs {
    class Solution {
        public long countBadPairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            long totalPairs = 1L * n * (n - 1) / 2;
            long goodPairs = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int val = nums[i];
                int diff = i - val;

                if (map.containsKey(diff)) {
                    goodPairs += map.get(diff);
                }

                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }

            long badPairs = totalPairs - goodPairs;
            return badPairs;
        }
    }

    class Solution_Correct_1 {
        public long countBadPairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            long goodPairs = 0;
            Map<Integer, Integer> map = new HashMap<>();


            // j - i = nums[j] - nums[i] => j - nums[j] = i - nums[i]
            for (int i = 0; i < n; i++) {
                int val = nums[i];

                int diff = i - val;
                if (map.containsKey(diff)) {
                    goodPairs += map.get(diff);
                }

                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }

            long totalPairs = 1L * (n - 1) * n / 2;
            long badPairs = totalPairs - goodPairs;

            return badPairs;
        }
    }

    class Solution_Brute {
        public long countBadPairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int badPairs = 0;
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                int a = nums[i];

                for (int j = i + 1; j < n; j++) {
                    int b = nums[j];
                    if ((j - i) != (b - a)) {
                        badPairs++;
                    }
                }
            }

            return badPairs;
        }
    }

// [4,1,3,3]
}

//    2364. Count Number of Bad Pairs
//    Medium
//    You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].
//
//    Return the total number of bad pairs in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [4,1,3,3]
//    Output: 5
//    Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
//    The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
//    The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
//    The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
//    The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
//    There are a total of 5 bad pairs, so we return 5.
//    Example 2:
//
//    Input: nums = [1,2,3,4,5]
//    Output: 0
//    Explanation: There are no bad pairs.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
