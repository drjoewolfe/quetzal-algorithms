package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindSubsequenceOfLengthKWithTheLargestSum {
    class Solution {
        public int[] maxSubsequence(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i, j) -> nums[j] - nums[i]);
            for(int i = 0; i < nums.length; i++) {
                maxHeap.offer(i);
            }

            int[] indexes = new int[k];
            for(int i = 0; i < k; i++) {
                indexes[i] = maxHeap.poll();
            }

            Arrays.sort(indexes);
            int[] results = new int[k];
            for(int i = 0; i < k; i++) {
                results[i] = nums[indexes[i]];
            }

            return results;
        }
    }

    class Solution_TLE {
        int[] maximumSubsequence;
        int maxSum;

        public int[] maxSubsequence(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            maximumSubsequence = new int[k];
            maxSum = Integer.MIN_VALUE;

            maxSubsequence(nums, 0, k, new ArrayList<>());
            return maximumSubsequence;
        }

        private void maxSubsequence(int[] nums, int index, int k, List<Integer> subsequence) {
            if(subsequence.size() == k) {
                int sum = getSum(subsequence);
                if(sum > maxSum) {
                    maxSum = sum;

                    for(int i = 0; i < k; i++) {
                        maximumSubsequence[i] = subsequence.get(i);
                    }
                }

                return;
            }

            if(index == nums.length) {
                return;
            }

            // Include this element
            subsequence.add(nums[index]);
            maxSubsequence(nums, index + 1, k, subsequence);
            subsequence.remove(subsequence.size() - 1);

            // Do not include this element
            maxSubsequence(nums, index + 1, k, subsequence);
        }

        private int getSum(List<Integer> list) {
            return list.stream().mapToInt(Integer::intValue).sum();
        }
    }


    class Solution_Incorrect {
        public int[] maxSubsequence(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            Arrays.sort(nums);
            int[] result = new int[k];
            int index = 0;
            for(int i = nums.length - k; i < nums.length; i++) {
                result[index++] = nums[i];
            }

            return result;
        }
    }

// [2,1,3,3]
// 2

// [50,-75]
// 2

// [-76,-694,44,197,357,-833,-277,358,724,-585,-960,214,465,-593,-431,625,-83,58,-889,31,765,8,-17,476,992,803,863,242,379,561,673,526,-447]
// 14
}

//    2099. Find Subsequence of Length K With the Largest Sum
//    Easy
//    You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
//
//    Return any such subsequence as an integer array of length k.
//
//    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,3,3], k = 2
//    Output: [3,3]
//    Explanation:
//    The subsequence has the largest sum of 3 + 3 = 6.
//    Example 2:
//
//    Input: nums = [-1,-2,3,4], k = 3
//    Output: [-1,3,4]
//    Explanation:
//    The subsequence has the largest sum of -1 + 3 + 4 = 6.
//    Example 3:
//
//    Input: nums = [3,4,3,3], k = 2
//    Output: [3,4]
//    Explanation:
//    The subsequence has the largest sum of 3 + 4 = 7.
//    Another possible subsequence is [4, 3].
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    -105 <= nums[i] <= 105
//    1 <= k <= nums.length