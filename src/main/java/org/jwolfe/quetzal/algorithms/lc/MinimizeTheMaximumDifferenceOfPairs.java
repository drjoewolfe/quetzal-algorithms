package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimizeTheMaximumDifferenceOfPairs {
    class Solution {
        public int minimizeMax(int[] nums, int p) {
            if(nums == null || nums.length < 2 || p == 0) {
                return 0;
            }

            int n = nums.length;
            Arrays.sort(nums);

            int left = 0;
            int right = nums[n - 1] - nums[0];

            while(left < right) {
                int mid = left + (right - left) / 2;

                int count = countValidPairs(nums, mid);
                if(count >= p) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private int countValidPairs(int[] nums, int threshold) {
            int count = 0;
            int index = 0;

            while(index < nums.length - 1) {
                int n1 = nums[index];
                int n2 = nums[index + 1];

                if(n2 - n1 <= threshold) {
                    count++;
                    index++;
                }

                index++;
            }

            return count;
        }
    }

    class Solution_Incorrect_1 {
        public int minimizeMax(int[] nums, int p) {
            if(nums == null || nums.length < 2 || p == 0) {
                return 0;
            }

            Arrays.sort(nums);
            print(nums);
            int n = nums.length;

            int[] diff = new int[n - 1];
            for(int i = 1; i < n; i++) {
                diff[i - 1] = nums[i] - nums[i - 1];
            }

            Arrays.sort(diff);
            print(diff);
            return diff[p - 1];
        }

        private void print(int[] arr) {
            for(int i : arr) {
                System.out.print(i + " ");
            }

            System.out.println();
        }
    }
}

//    2616. Minimize the Maximum Difference of Pairs
//    Medium
//    You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.
//
//    Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
//
//    Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.
//
//
//
//    Example 1:
//
//    Input: nums = [10,1,2,7,1,3], p = 2
//    Output: 1
//    Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5.
//    The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.
//    Example 2:
//
//    Input: nums = [4,2,1,2], p = 1
//    Output: 0
//    Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] <= 109
//    0 <= p <= (nums.length)/2