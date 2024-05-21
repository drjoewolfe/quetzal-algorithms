package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> powerSet = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return powerSet;
            }

            generatePowerSet(nums, 0, new ArrayList<>(), powerSet);
            return powerSet;
        }

        private void generatePowerSet(int[] nums, int index, List<Integer> currentSet, List<List<Integer>> powerSet) {
            if (index == nums.length) {
                powerSet.add(new ArrayList(currentSet));
                return;
            }

            // Include
            currentSet.add(nums[index]);
            generatePowerSet(nums, index + 1, currentSet, powerSet);
            currentSet.remove(currentSet.size() - 1);

            // Exclude
            generatePowerSet(nums, index + 1, currentSet, powerSet);
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> powerSet = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return powerSet;
            }

            List<Integer> currentSet = new ArrayList<>();

            // Emtpy Set
            powerSet.add(new ArrayList<>());

            for (int l = 1; l <= nums.length; l++) {
                generateSubsets(nums, 0, l, currentSet, powerSet);
            }

            return powerSet;
        }

        private void generateSubsets(int[] nums, int index, int length, List<Integer> currentSet, List<List<Integer>> powerSet) {
            if (length == 0) {
                powerSet.add(new ArrayList(currentSet));
                return;
            }

            for (int i = index; i + length <= nums.length; i++) {
                currentSet.add(nums[i]);
                generateSubsets(nums, i + 1, length - 1, currentSet, powerSet);

                currentSet.remove(currentSet.size() - 1);
            }
        }

        private void print(List<List<Integer>> list) {
            for (List<Integer> subList : list) {
                System.out.print(" { ");
                for (int n : subList) {
                    System.out.print(n + " ");
                }

                System.out.print(" } ");
            }

            System.out.println();
        }
    }
}

//    78. Subsets
//    Medium
//    Given an integer array nums of unique elements, return all possible subsets (the power set).
//
//    The solution set must not contain duplicate subsets. Return the solution in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//    Example 2:
//
//    Input: nums = [0]
//    Output: [[],[0]]
//
//
//    Constraints:
//
//    1 <= nums.length <= 10
//    -10 <= nums[i] <= 10
//    All the numbers of nums are unique.