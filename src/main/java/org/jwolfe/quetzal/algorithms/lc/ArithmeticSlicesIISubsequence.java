package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlicesIISubsequence {
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            if(nums == null || nums.length < 3) {
                return 0;
            }

            int n = nums.length;
            Map<Integer, Integer>[] dp = new HashMap[n];
            for(int i = 0; i < n; i++) {
                dp[i] = new HashMap<>();
            }

            int count = 0;
            for(int i = 1; i < n; i++) {
                int c = nums[i];
                var iMap = dp[i];

                for(int j = 0; j < i; j++) {
                    int b = nums[j];
                    long dl = (long) c - b;
                    if(dl < Integer.MIN_VALUE || dl > Integer.MAX_VALUE) {
                        continue;
                    }

                    int d = (int) dl;
                    var jMap = dp[j];

                    iMap.put(d, iMap.getOrDefault(d, 0) + 1);
                    if(jMap.containsKey(d)) {
                        iMap.put(d, iMap.get(d) + jMap.get(d));
                        count += jMap.get(d);
                    }
                }
            }

            return count;
        }
    }

    class Solution_TLE {
        public int numberOfArithmeticSlices(int[] nums) {
            if(nums == null || nums.length < 3) {
                return 0;
            }

            return numberOfArithmeticSlices(nums, 0, new ArrayList<>());
        }

        private int numberOfArithmeticSlices(int[] nums, int index, List<Integer> subsequence) {
            if(index == nums.length) {
                if(isArithmeticSlice(subsequence)) {
                    return 1;
                }

                return 0;
            }

            int count = 0;

            // Add current element
            subsequence.add(nums[index]);
            count += numberOfArithmeticSlices(nums, index + 1, subsequence);
            subsequence.remove(subsequence.size() - 1);

            // Dont add current element
            count += numberOfArithmeticSlices(nums, index + 1, subsequence);

            return count;
        }

        private boolean isArithmeticSlice(List<Integer> subsequence) {
            if(subsequence.size() < 3) {
                return false;
            }

            long diff = 1L * subsequence.get(1) - subsequence.get(0);
            for(int i = 2; i < subsequence.size(); i++) {
                if(1L * subsequence.get(i) - subsequence.get(i - 1) != diff) {
                    return false;
                }
            }

            return true;
        }

        private void print(List<Integer> subsequence) {
            for(var element : subsequence) {
                System.out.print(element + " ");
            }

            System.out.println();
        }
    }

// [2,4,6,8,10]
// [0,2000000000,-294967296]
// [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
}

//    446. Arithmetic Slices II - Subsequence
//    Hard
//    Given an integer array nums, return the number of all the arithmetic subsequences of nums.
//
//    A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
//    For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
//    For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
//    A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
//
//    For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
//    The test cases are generated so that the answer fits in 32-bit integer.
//
//
//
//    Example 1:
//
//    Input: nums = [2,4,6,8,10]
//    Output: 7
//    Explanation: All arithmetic subsequence slices are:
//    [2,4,6]
//    [4,6,8]
//    [6,8,10]
//    [2,4,6,8]
//    [4,6,8,10]
//    [2,4,6,8,10]
//    [2,6,10]
//    Example 2:
//
//    Input: nums = [7,7,7,7,7]
//    Output: 16
//    Explanation: Any subsequence of this array is arithmetic.
//
//
//    Constraints:
//
//    1  <= nums.length <= 1000
//    -231 <= nums[i] <= 231 - 1