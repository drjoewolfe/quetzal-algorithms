package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> combinations = new ArrayList<>();
            if(candidates == null || candidates.length == 0 || target < 0) {
                return combinations;
            }

            generateCombinations(candidates, target, 0, 0, new ArrayList<>(), combinations);
            return combinations;
        }

        private void generateCombinations(int[] candidates, int target, int currentSum, int index, List<Integer> currentCombination, List<List<Integer>> combinations) {
            if(currentSum == target) {
                List<Integer> result = new ArrayList<>(currentCombination);
                combinations.add(result);

                return;
            }

            if(index == candidates.length || currentSum > target) {
                return;
            }

            // Take current
            int val = candidates[index];
            currentCombination.add(val);
            generateCombinations(candidates, target, currentSum + val, index, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);

            // Skip current
            generateCombinations(candidates, target, currentSum, index + 1, currentCombination, combinations);
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if(candidates == null || candidates.length == 0 || target < 1 || target > 500) {
                return null;
            }

            List<List<Integer>> combinations = new ArrayList<>();
            combinationSum(candidates, target, 0, new ArrayList<>(), combinations);

            return combinations;
        }

        private void combinationSum(int[] candidates, int target, int index, List<Integer> currentCombination, List<List<Integer>> combinations) {
            if(target == 0) {
                combinations.add(new ArrayList<>(currentCombination));
                return;
            }

            if(index >= candidates.length || target < 0) {
                return;
            }

            int c = candidates[index];
            currentCombination.add(c);
            combinationSum(candidates, target - c, index, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);

            combinationSum(candidates, target, index + 1, currentCombination, combinations);
        }
    }
}

//    39. Combination Sum
//    Medium
//    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
//
//    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
//
//    It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
//
//
//
//    Example 1:
//
//    Input: candidates = [2,3,6,7], target = 7
//    Output: [[2,2,3],[7]]
//    Explanation:
//    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//    7 is a candidate, and 7 = 7.
//    These are the only two combinations.
//    Example 2:
//
//    Input: candidates = [2,3,5], target = 8
//    Output: [[2,2,2,2],[2,3,3],[3,5]]
//    Example 3:
//
//    Input: candidates = [2], target = 1
//    Output: []
//
//
//    Constraints:
//
//    1 <= candidates.length <= 30
//    1 <= candidates[i] <= 200
//    All elements of candidates are distinct.
//    1 <= target <= 500