package org.jwolfe.quetzal.algorithms.lc;

public class SearchInRotatedSortedArrayII {
    class Solution {
        public boolean search(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;

            int left = 0;
            int right = n - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == target) {
                    return true;
                }

                if(!isBinarySearchHelpful(nums, left, nums[mid])) {
                    left++;
                    continue;
                }

                boolean pivotOnLeft = isElementOnLeft(nums, left, nums[mid]);
                boolean targetOnLeft = isElementOnLeft(nums, left, target);

                if(pivotOnLeft != targetOnLeft) {
                    if(pivotOnLeft) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if(nums[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return false;
        }

        private boolean isElementOnLeft(int[] nums, int left, int element) {
            return nums[left] <= element;
        }

        private boolean isBinarySearchHelpful(int[] nums, int left, int element) {
            return nums[left] != element;
        }
    }

    class Solution_Attempt_1 {
        public boolean search(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            if(nums.length < 3) {
                for(int i = 0; i < nums.length; i++) {
                    if(nums[i] == target) {
                        return true;
                    }
                }

                return false;
            }

            int pivot = findPivot(nums);

            if(pivot != 0 && target >= nums[0]) {
                return search(nums, 0, pivot - 1, target);
            } else {
                return search(nums, pivot, nums.length - 1, target);
            }
        }

        private int findPivot(int[] nums) {
            int n = nums.length;
            int lastNumber = nums[n - 1];
            int left = 0;
            int right = nums.length - 1;
            int pivot = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if (!isBinarySearchHelpful(nums, left, nums[mid])) {
                    left++;
                    continue;
                }

                if(nums[mid] <= lastNumber) {
                    pivot = mid;
                    right = mid;

                    if(left == right) {
                        break;
                    }
                } else {
                    left = mid + 1;
                }
            }

            System.out.println("Pivot: " + pivot);
            return pivot;
        }

        private boolean search(int[] nums, int startIndex, int endIndex, int target) {
            int left = startIndex;
            int right = endIndex;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == target) {
                    return true;
                } else if(nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return false;
        }

        private boolean isBinarySearchHelpful(int[] arr, int left, int element) {
            return arr[left] != element;
        }
    }


// [2,5,6,0,0,1,2]
// 0

// [3,5,1]
// 1

// [1,3,1,1,1]
// 3

// [2,2,2,0,2,2]
// 0

// [1,0,1,1,1]
// 0
}

//    81. Search in Rotated Sorted Array II
//    Medium
//    There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
//
//    Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
//
//    Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
//
//    You must decrease the overall operation steps as much as possible.
//
//
//
//    Example 1:
//
//    Input: nums = [2,5,6,0,0,1,2], target = 0
//    Output: true
//    Example 2:
//
//    Input: nums = [2,5,6,0,0,1,2], target = 3
//    Output: false
//
//
//    Constraints:
//
//    1 <= nums.length <= 5000
//    -104 <= nums[i] <= 104
//    nums is guaranteed to be rotated at some pivot.
//    -104 <= target <= 104
//
//
//    Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?