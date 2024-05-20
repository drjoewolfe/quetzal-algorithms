package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SumOfAllSubsetXORTotals {
    class Solution {
        public int subsetXORSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int result = 0;
            for (int val : nums) {
                result |= val;
            }

            result <<= (n - 1);
            return result;
        }
    }

    class Solution_Correct_2 {
        int xorSum;

        public int subsetXORSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            xorSum = 0;
            subsetXORSum(nums, 0, 0);

            return xorSum;
        }

        private void subsetXORSum(int[] nums, int index, Integer currentXOR) {
            if (index == nums.length) {
                xorSum += currentXOR;
                return;
            }

            // Include current element
            subsetXORSum(nums, index + 1, currentXOR ^ nums[index]);

            // Exclude current element
            subsetXORSum(nums, index + 1, currentXOR);
        }
    }

    class Solution_Approach_1 {
        int xorSum;

        public int subsetXORSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            xorSum = 0;
            subsetXORSum(nums, 0, new ArrayList<>());

            return xorSum;
        }

        private void subsetXORSum(int[] nums, int index, List<Integer> currentSet) {
            if (index == nums.length) {
                int sum = 0;
                for (var element : currentSet) {
                    sum ^= element;
                }

                xorSum += sum;
                return;
            }

            // Include current element
            currentSet.add(nums[index]);
            subsetXORSum(nums, index + 1, currentSet);
            currentSet.remove(currentSet.size() - 1);

            // Exclude current element
            subsetXORSum(nums, index + 1, currentSet);
        }
    }
}

//    1863. Sum of All Subset XOR Totals
//    Easy
//    The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
//
//    For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
//    Given an array nums, return the sum of all XOR totals for every subset of nums.
//
//    Note: Subsets with the same elements should be counted multiple times.
//
//    An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3]
//    Output: 6
//    Explanation: The 4 subsets of [1,3] are:
//    - The empty subset has an XOR total of 0.
//    - [1] has an XOR total of 1.
//    - [3] has an XOR total of 3.
//    - [1,3] has an XOR total of 1 XOR 3 = 2.
//    0 + 1 + 3 + 2 = 6
//    Example 2:
//
//    Input: nums = [5,1,6]
//    Output: 28
//    Explanation: The 8 subsets of [5,1,6] are:
//    - The empty subset has an XOR total of 0.
//    - [5] has an XOR total of 5.
//    - [1] has an XOR total of 1.
//    - [6] has an XOR total of 6.
//    - [5,1] has an XOR total of 5 XOR 1 = 4.
//    - [5,6] has an XOR total of 5 XOR 6 = 3.
//    - [1,6] has an XOR total of 1 XOR 6 = 7.
//    - [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
//    0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
//    Example 3:
//
//    Input: nums = [3,4,5,6,7,8]
//    Output: 480
//    Explanation: The sum of all XOR totals for every subset is 480.
//
//
//    Constraints:
//
//    1 <= nums.length <= 12
//    1 <= nums[i] <= 20
