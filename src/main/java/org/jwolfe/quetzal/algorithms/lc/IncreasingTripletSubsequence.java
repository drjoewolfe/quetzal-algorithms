package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class IncreasingTripletSubsequence {
    class Solution {
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            int first = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;

            for(int val : nums) {
                if(val <= first) {
                    first = val;
                } else if(val <= second) {
                    second = val;
                } else {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            int first = nums[0];
            int second = Integer.MAX_VALUE;

            for(int i = 1; i < nums.length; i++) {
                int v = nums[i];

                if(v <= first) {
                    first = v;
                } else if(v <= second) {
                    second = v;
                } else {
                    return true;
                }
            }

            return false;
        }
    }

// TC
//  [5,4,3,2,1]
//  [1,2,3,4,5]


    class Solution_LinearTimeAndSpace {
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            boolean[] smallerOnLeftExists = new boolean[nums.length];
            boolean[] largerOnRightExists = new boolean[nums.length];

            int smallestOnLeft = nums[0];
            for(int i = 1; i < nums.length; i++) {
                if(nums[i] > smallestOnLeft) {
                    smallerOnLeftExists[i] = true;
                } else {
                    smallestOnLeft = nums[i];
                }
            }

            int largestOnRight = nums[nums.length - 1];
            for(int i = nums.length - 1; i >= 0; i--) {
                if(nums[i] < largestOnRight) {
                    largerOnRightExists[i] = true;
                } else {
                    largestOnRight = nums[i];
                }
            }

            for(int i = 1; i < nums.length - 1; i++) {
                if(smallerOnLeftExists[i] && largerOnRightExists[i]) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_LIS {
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            int[] lis = new int[nums.length];
            Arrays.fill(lis, 1);
            for(int i = 1; i < nums.length; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[j] < nums[i]
                            && lis[i] < lis[j] + 1) {
                        lis[i] = lis[j] + 1;

                        if(lis[i] == 3) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    class Solution_Brute {
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            for(int i = 0; i < nums.length - 2; i++) {
                for(int j = i + 1; j < nums.length - 1; j++) {
                    if(nums[j] <= nums[i]) {
                        continue;
                    }

                    for(int k = j + 1; k < nums.length; k++) {
                        if(nums[k] <= nums[j]) {
                            continue;
                        }

                        return true;
                    }
                }
            }

            return false;
        }
    }
}

//    334. Increasing Triplet Subsequence
//    Medium
//    Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4,5]
//    Output: true
//    Explanation: Any triplet where i < j < k is valid.
//    Example 2:
//
//    Input: nums = [5,4,3,2,1]
//    Output: false
//    Explanation: No triplet exists.
//    Example 3:
//
//    Input: nums = [2,1,5,0,4,6]
//    Output: true
//    Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
//
//
//    Constraints:
//
//    1 <= nums.length <= 5 * 105
//    -231 <= nums[i] <= 231 - 1
//
//
//    Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?