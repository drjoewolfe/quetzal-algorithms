package org.jwolfe.quetzal.algorithms.lc;

public class FindFirstAndLastPositionOfElementInSortedArray {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[] {-1, -1};
            if(nums == null || nums.length == 0) {
                return result;
            }

            result[0] = binarySearchLeft(nums, target);
            if(result[0] != -1) {
                result[1] = binarySearchRight(nums, target);
            }

            return result;
        }

        private int binarySearchLeft(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            int index = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = nums[mid];

                if(midValue == target) {
                    index = mid;
                    right = mid - 1;
                } else if(midValue < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }

        private int binarySearchRight(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            int index = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = nums[mid];

                if(midValue == target) {
                    index = mid;
                    left = mid + 1;
                } else if(midValue < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }
    }

    class Solution_Good1 {
        public int[] searchRange(int[] nums, int target) {
            int[] positions = new int[] {-1, -1};

            if(nums == null || nums.length == 0) {
                return positions;
            }


            positions[0] = getStartIndex(nums, target);
            positions[1] = getEndIndex(nums, target);

            return positions;
        }

        private int getStartIndex(int[] nums, int target) {
            int index = -1;

            int left = 0;
            int right = nums.length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = nums[mid];

                if(midValue >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

                if(midValue == target) {
                    index = mid;
                }
            }

            return index;
        }

        private int getEndIndex(int[] nums, int target) {
            int index = -1;

            int left = 0;
            int right = nums.length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = nums[mid];

                if(midValue <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

                if(midValue == target) {
                    index = mid;
                }
            }

            return index;
        }
    }
}

//    34. Find First and Last Position of Element in Sorted Array
//    Medium
//    Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
//    If target is not found in the array, return [-1, -1].
//
//    Follow up: Could you write an algorithm with O(log n) runtime complexity?
//
//
//
//    Example 1:
//
//    Input: nums = [5,7,7,8,8,10], target = 8
//    Output: [3,4]
//    Example 2:
//
//    Input: nums = [5,7,7,8,8,10], target = 6
//    Output: [-1,-1]
//    Example 3:
//
//    Input: nums = [], target = 0
//    Output: [-1,-1]
//
//
//    Constraints:
//
//    0 <= nums.length <= 105
//    -109 <= nums[i] <= 109
//    nums is a non-decreasing array.
//    -109 <= target <= 109