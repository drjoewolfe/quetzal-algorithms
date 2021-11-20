package org.jwolfe.quetzal.algorithms.lc;

public class SingleElementInASortedArray {
    class Solution {
        public int singleNonDuplicate(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 == 0) {
                return -1;
            }

            if(nums.length == 1) {
                return nums[0];
            }

            int n = nums.length;
            int left = 0;
            int right = n - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(mid < n - 1 && nums[mid] == nums[mid + 1]) {
                    if((n - mid) % 2 == 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else if(mid > 0 && nums[mid] == nums[mid - 1]) {
                    if((n - mid + 1) % 2 == 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    return nums[mid];
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int singleNonDuplicate(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int length = nums.length;
            int left = 0;
            int right = length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(mid < length - 1 && nums[mid] == nums[mid + 1]) {
                    if((length - mid) % 2 == 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else if(mid > 0 && nums[mid] == nums[mid - 1]) {
                    if((length - mid + 1) % 2 == 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    return nums[mid];
                }
            }

            return -1;
        }
    }

    class Solution_Classic {
        public int singleNonDuplicate(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int number = nums[0];
            for(int i = 1; i < nums.length; i++) {
                number ^= nums[i];
            }

            return number;
        }
    }

// [1,1,2,3,3,4,4,8,8]
// [3,3,7,7,10,11,11]
}

//    540. Single Element in a Sorted Array
//    Medium
//    You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
//
//    Return the single element that appears only once.
//
//    Your solution must run in O(log n) time and O(1) space.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,2,3,3,4,4,8,8]
//    Output: 2
//    Example 2:
//
//    Input: nums = [3,3,7,7,10,11,11]
//    Output: 10
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] <= 105
