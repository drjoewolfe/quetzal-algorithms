package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numSubseq(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] powersOfTwo = new int[n];
            powersOfTwo[0] = 1;

            for(int i = 1; i < n; i++) {
                powersOfTwo[i] = powersOfTwo[i - 1] * 2;
                powersOfTwo[i] %= MOD;
            }

            Arrays.sort(nums);

            long count = 0;
            int left = 0;
            int right = n - 1;

            while(left <= right) {
                int a = nums[left];
                int b = nums[right];

                if(a + b <= target) {
                    count += powersOfTwo[right - left];
                    count %= MOD;
                    left++;
                } else {
                    right--;
                }
            }

            return (int) count;
        }
    }

    class Solution_Correct_1 {
        int MOD = 1_000_000_007;

        public int numSubseq(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] powers = new int[n + 1];
            powers[0] = 1;
            for(int i = 1; i <= n; i++) {
                powers[i] = powers[i - 1] * 2;
                powers[i] %= MOD;
            }

            Arrays.sort(nums);

            int left = 0;
            int right = n - 1;

            long count = 0;
            while(left <= right) {
                int min = nums[left];
                int max = nums[right];

                if(min + max <= target) {
                    count += powers[right - left];
                    count %= MOD;

                    left++;
                } else {
                    right--;
                }
            }

            return (int) count;
        }
    }

    class Solution_Better_Brute {
        int MOD = 1_000_000_007;
        int count;

        public int numSubseq(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }


            count = 0;
            computeNumberOfSubsequences(nums, 0, target, Integer.MAX_VALUE, Integer.MIN_VALUE);
            return count;
        }

        private void computeNumberOfSubsequences(int[] nums, int index, int target, int min, int max) {
            if(index == nums.length) {
                if(min != Integer.MAX_VALUE && max != Integer.MIN_VALUE
                        && min + max <= target) {
                    count++;
                    count %= MOD;
                }

                return;
            }

            computeNumberOfSubsequences(nums, index + 1, target, min, max);
            computeNumberOfSubsequences(nums, index + 1, target, Math.min(min, nums[index]), Math.max(max, nums[index]));
        }
    }

    class Solution_Brute {
        int MOD = 1_000_000_007;

        public int numSubseq(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            List<List<Integer>> allSubsequences = new ArrayList<>();
            generateAllSubsequences(nums, 0, new ArrayList<>(), allSubsequences);

            int count = 0;
            for(var subsequence : allSubsequences) {
                if(subMaxMin(subsequence) <= target) {
                    count++;
                    count %= MOD;
                }
            }

            return count;
        }

        private void generateAllSubsequences(int[] nums, int index, List<Integer> current, List<List<Integer>> allSubsequences) {
            if(index == nums.length) {
                if(current.size() > 0) {
                    List<Integer> list = new ArrayList<>(current);
                    allSubsequences.add(list);
                }

                return;
            }

            generateAllSubsequences(nums, index + 1, current, allSubsequences);

            current.add(nums[index]);
            generateAllSubsequences(nums, index + 1, current, allSubsequences);
            current.remove(current.size() - 1);
        }

        private int subMaxMin(List<Integer> subsequence) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(var a : subsequence) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            return min + max;
        }
    }

// [3,5,6,7]
// 9


// [14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14]
// 22

// [9,25,9,28,24,12,17,8,28,7,21,25,10,2,16,19,12,13,15,28,14,12,24,9,6,7,2,15,19,13,30,30,23,19,11,3,17,2,14,20,22,30,12,1,11,2,2,20,20,27,15,9,10,4,12,30,13,5,2,11,29,5,3,13,22,5,16,19,7,19,11,16,11,25,29,21,29,3,2,9,20,15,9]
// 32
}

//    1498. Number of Subsequences That Satisfy the Given Sum Condition
//    Medium
//    You are given an array of integers nums and an integer target.
//
//    Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: nums = [3,5,6,7], target = 9
//    Output: 4
//    Explanation: There are 4 subsequences that satisfy the condition.
//    [3] -> Min value + max value <= target (3 + 3 <= 9)
//    [3,5] -> (3 + 5 <= 9)
//    [3,5,6] -> (3 + 6 <= 9)
//    [3,6] -> (3 + 6 <= 9)
//    Example 2:
//
//    Input: nums = [3,3,6,8], target = 10
//    Output: 6
//    Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
//    [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
//    Example 3:
//
//    Input: nums = [2,3,3,4,6,7], target = 12
//    Output: 61
//    Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
//    Number of valid subsequences (63 - 2 = 61).
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 106
//    1 <= target <= 106