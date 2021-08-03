package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SubsetsII {
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            HashSet<List<Integer>> powerSet = new HashSet<>();
            LinkedList<Integer> current = new LinkedList<>();

            Arrays.sort(nums);
            generateSets(nums, 0, current, powerSet);
            return new ArrayList<>(powerSet);
        }

        private void generateSets(int[] nums, int index, LinkedList<Integer> current, HashSet<List<Integer>> powerSet) {
            if(index == nums.length) {
                powerSet.add(new ArrayList<>(current));
                return;
            }

            generateSets(nums, index + 1, current, powerSet);

            current.add(nums[index]);
            generateSets(nums, index + 1, current, powerSet);
            current.removeLast();
        }
    }

    class Solution_Incorrect_Again {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> powerSet = new ArrayList<>();
            powerSet.add(new ArrayList<>());
            Set<String> allMementos = new HashSet<>();

            if(nums == null || nums.length == 0) {
                return powerSet;
            }

            int n = nums.length;
            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {
                    List<Integer> set = getSet(nums, i, j);
                    String memento = getMemento(set);
                    if(!allMementos.contains(memento)) {
                        powerSet.add(getSet(nums, i, j));
                        allMementos.add(memento);
                    }
                }
            }

            return powerSet;
        }

        private List<Integer> getSet(int[] nums, int i, int j) {
            List<Integer> set = new ArrayList<>();
            for(int k = i; k <= j; k++) {
                set.add(nums[k]);
            }

            return set;
        }

        private String getMemento(List<Integer> list) {
            StringBuilder memento = new StringBuilder();
            for(int a : list) {
                memento.append(a + "-");
            }

            return memento.toString();
        }
    }

    class Solution_Incorrect {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> powerSet = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return powerSet;
            }

            Set<Integer> set = new HashSet<>();
            for(int a : nums) {
                set.add(a);
            }

            List<Integer> list = new ArrayList<>(set);
            for(int i = 0; i < list.size(); i++) {
                for(int j = i; j < list.size(); j++) {
                    powerSet.add(getSet(list, i, j));
                }
            }

            powerSet.add(new ArrayList<>());

            return powerSet;
        }

        private List<Integer> getSet(List<Integer> list, int i, int j) {
            List<Integer> set = new ArrayList<>();
            for(int k = i; k <= j; k++) {
                set.add(list.get(k));
            }

            return set;
        }
    }

    class Solution_Correct_2 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> powerSets = new ArrayList<>();

            if(nums == null || nums.length == 0) {
                powerSets.add(new ArrayList<>());
                return powerSets;
            }

            Arrays.sort(nums);
            generateSubSetsWithDup(nums, nums.length, 0, new ArrayList<>(), new HashSet<>(), powerSets);
            return powerSets;
        }

        private void generateSubSetsWithDup(int[] nums, int n, int index, List<Integer> currentSet, Set<String> mementos, List<List<Integer>> powerSets) {
            if(index == n) {
                String memento = getMemento(currentSet);
                if(!mementos.contains(memento)) {
                    powerSets.add(new ArrayList<>(currentSet));
                }

                mementos.add(memento);
                return;
            }

            // Without including index
            generateSubSetsWithDup(nums, n, index + 1, currentSet, mementos, powerSets);

            // Including Index
            currentSet.add(nums[index]);
            generateSubSetsWithDup(nums, n, index + 1, currentSet, mementos, powerSets);
            currentSet.remove(currentSet.size() - 1);
        }

        private String getMemento(List<Integer> list) {
            StringBuilder memento = new StringBuilder();
            for(int li : list) {
                memento.append(li + "-");
            }

            return memento.toString();
        }
    }

// [1,2,2]
// [1,2,3]
}

//    90. Subsets II
//    Medium
//    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
//
//    The solution set must not contain duplicate subsets. Return the solution in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,2]
//    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
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
