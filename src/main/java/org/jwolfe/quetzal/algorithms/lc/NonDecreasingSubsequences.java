package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonDecreasingSubsequences {
    class Solution {
        public List<List<Integer>> findSubsequences(int[] nums) {
            Set<List<Integer>> results = new HashSet<>();

            generateSubsequences(nums, 0, Integer.MIN_VALUE, new ArrayList<>(), results);
            return new ArrayList<>(results);
        }

        private void generateSubsequences(int[] nums, int index, int previous, List<Integer> currentSubsequence, Set<List<Integer>> results) {
            if(index == nums.length) {
                if(currentSubsequence.size() > 1) {
                    results.add(new ArrayList<>(currentSubsequence));
                }

                return;
            }

            int current = nums[index];
            if(current >= previous) {
                currentSubsequence.add(current);
                generateSubsequences(nums, index + 1, current, currentSubsequence, results);
                currentSubsequence.remove(currentSubsequence.size() - 1);
            }

            generateSubsequences(nums, index + 1, previous, currentSubsequence, results);
        }
    }

    class Solution_Correct_2 {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            generateSubsequences(nums, 0, Integer.MIN_VALUE, new ArrayList<>(), results, new HashSet<>());
            return results;
        }

        private void generateSubsequences(int[] nums, int index, int previous, List<Integer> currentSubsequence, List<List<Integer>> results, Set<String> mementos) {
            if(index == nums.length) {
                if(currentSubsequence.size() > 1) {
                    String memento = getMemento(currentSubsequence);
                    if(!mementos.contains(memento)) {
                        results.add(new ArrayList<>(currentSubsequence));
                        mementos.add(memento);
                    }
                }

                return;
            }

            int current = nums[index];
            if(current >= previous) {
                currentSubsequence.add(current);
                generateSubsequences(nums, index + 1, current, currentSubsequence, results, mementos);
                currentSubsequence.remove(currentSubsequence.size() - 1);
            }

            generateSubsequences(nums, index + 1, previous, currentSubsequence, results, mementos);
        }

        private String getMemento(List<Integer> list) {
            StringBuilder builder = new StringBuilder();
            for(var element : list) {
                builder.append(element + "-");
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            generateIncreasingSubsequences(nums, 0, Integer.MIN_VALUE, new ArrayList<>(), results, new HashSet<String>());
            return results;
        }

        private void generateIncreasingSubsequences(int[] nums, int index, int prev, List<Integer> current, List<List<Integer>> results, Set<String> currentMementos) {
            if(index == nums.length) {
                if(current.size() > 1) {
                    String memento = getMemento(current);
                    if(!currentMementos.contains(memento)) {
                        currentMementos.add(memento);
                        results.add(new ArrayList<>(current));
                    }
                }

                return;
            }

            // Skip current
            generateIncreasingSubsequences(nums, index + 1, prev, current, results, currentMementos);

            // Take (?) current
            if(nums[index] >= prev) {
                current.add(nums[index]);
                generateIncreasingSubsequences(nums, index + 1, nums[index], current, results, currentMementos);
                current.remove(current.size() - 1);
            }
        }

        private String getMemento(List<Integer> list) {
            StringBuilder builder = new StringBuilder();
            for(var a : list) {
                builder.append(a + "|");
            }

            return builder.toString();
        }
    }
}

//    491. Non-decreasing Subsequences
//    Medium
//    Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [4,6,7,7]
//    Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
//    Example 2:
//
//    Input: nums = [4,4,3,2,1]
//    Output: [[4,4]]
//
//
//    Constraints:
//
//    1 <= nums.length <= 15
//    -100 <= nums[i] <= 100