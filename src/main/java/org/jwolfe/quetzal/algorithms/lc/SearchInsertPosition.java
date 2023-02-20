package org.jwolfe.quetzal.algorithms.lc;

public class SearchInsertPosition {
    class Solution {
        public int searchInsert(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int left = 0;
            int right = nums.length - 1;

            if(nums[right] < target) {
                return right + 1;
            }

            while(left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if(val == target) {
                    return mid;
                } if(val < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

    class Solution_Correct_1 {
        public int searchInsert(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            if(target < nums[0]) {
                return 0;
            }

            if(target > nums[nums.length - 1]) {
                return nums.length;
            }

            int left = 0;
            int right = nums.length;

            while(left < right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == target) {
                    return mid;
                }

                if(nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

// [1,3,5,6]
// 5

// [1,3,5,6]
// 7
}

//    35. Search Insert Position
//    Easy
//    Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//    You must write an algorithm with O(log n) runtime complexity.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,5,6], target = 5
//    Output: 2
//    Example 2:
//
//    Input: nums = [1,3,5,6], target = 2
//    Output: 1
//    Example 3:
//
//    Input: nums = [1,3,5,6], target = 7
//    Output: 4
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -104 <= nums[i] <= 104
//    nums contains distinct values sorted in ascending order.
//    -104 <= target <= 104