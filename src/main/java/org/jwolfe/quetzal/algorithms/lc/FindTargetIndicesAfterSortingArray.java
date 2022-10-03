package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndicesAfterSortingArray {
    class Solution {
        public List<Integer> targetIndices(int[] nums, int target) {
            List<Integer> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            Arrays.sort(nums);
            for(int i = 0; i < nums.length; i++) {
                int val = nums[i];
                if(val == target) {
                    results.add(i);
                } else if(val > target) {
                    break;
                }
            }

            return results;
        }
    }

    class Solution_BinarySearch {
        public List<Integer> targetIndices(int[] nums, int target) {
            List<Integer> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            Arrays.sort(nums);
            int start = binarySearchForStart(nums, target);
            int end = binarySearchForEnd(nums, target);

            if(start != -1) {
                for(int i = start; i <= end; i++) {
                    results.add(i);
                }
            }

            return results;
        }

        private int binarySearchForStart(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            int index = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == target) {
                    index = mid;
                    right = mid - 1;
                } else if(nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }

        private int binarySearchForEnd(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            int index = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == target) {
                    index = mid;
                    left = mid + 1;
                } else if(nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }
    }

// [1,2,5,2,3]
// 2

// [1,2,5,2,3]
// 4
}

//    2089. Find Target Indices After Sorting Array
//    Easy
//    You are given a 0-indexed integer array nums and a target element target.
//
//    A target index is an index i such that nums[i] == target.
//
//    Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,5,2,3], target = 2
//    Output: [1,2]
//    Explanation: After sorting, nums is [1,2,2,3,5].
//    The indices where nums[i] == 2 are 1 and 2.
//    Example 2:
//
//    Input: nums = [1,2,5,2,3], target = 3
//    Output: [3]
//    Explanation: After sorting, nums is [1,2,2,3,5].
//    The index where nums[i] == 3 is 3.
//    Example 3:
//
//    Input: nums = [1,2,5,2,3], target = 5
//    Output: [4]
//    Explanation: After sorting, nums is [1,2,2,3,5].
//    The index where nums[i] == 5 is 4.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i], target <= 100