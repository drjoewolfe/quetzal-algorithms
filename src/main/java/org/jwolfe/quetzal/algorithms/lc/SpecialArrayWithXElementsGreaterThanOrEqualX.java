package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    class Solution {
        public int specialArray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            Arrays.sort(nums);
            for(int x = 0; x <= n; x++) {

                int left = 0;
                int right = n - 1;

                int index = -1;
                while(left <= right) {
                    int mid = left + (right - left) / 2;

                    int val = nums[mid];
                    if(val >= x) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                if(index != -1) {
                    int length = n - index;

                    if(length == x) {
                        return x;
                    }
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int specialArray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            Arrays.sort(nums);
            int n = nums.length;

            for(int x = 0; x <= n; x++) {

                int left = 0;
                int right = n - 1;

                int index = -1;
                while(left <= right) {
                    int mid = left + (right - left) / 2;

                    if(nums[mid] >= x) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                if(index != -1) {
                    int length = n - index;

                    if(length == x) {
                        return x;
                    }
                }
            }

            return -1;
        }

        private int search(int[] nums, int x) {
            int left = 0;
            int right = nums.length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(nums[mid] == x) {
                    return mid;
                } else if(nums[mid] > x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_W {
        public int specialArray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            if(nums.length == 1) {
                return 1;
            }

            Arrays.sort(nums);
            int n = nums.length;
            int[] counts = new int[n];

            int a = n;
            int prev = -1;
            for(int i = 0; i < n; i++) {
                int curr = nums[i];
                if(prev == curr) {
                    counts[i] = a;
                } else {
                    counts[i] = a - i;
                    prev = curr;
                }
            }

            print(counts);
            for(int i = 1; i <= n; i++) {
                int index = search(counts, i);
                if(index != -1) {
                    return i;
                }
            }

            return -1;
        }

        private int search(int[] nums, int x) {
            int left = 0;
            int right = nums.length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(nums[mid] == x) {
                    return mid;
                } else if(nums[mid] > x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [3, 5]
}

//    1608. Special Array With X Elements Greater Than or Equal X
//    Easy
//    You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.
//
//    Notice that x does not have to be an element in nums.
//
//    Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.
//
//
//
//    Example 1:
//
//    Input: nums = [3,5]
//    Output: 2
//    Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
//    Example 2:
//
//    Input: nums = [0,0]
//    Output: -1
//    Explanation: No numbers fit the criteria for x.
//    If x = 0, there should be 0 numbers >= x, but there are 2.
//    If x = 1, there should be 1 number >= x, but there are 0.
//    If x = 2, there should be 2 numbers >= x, but there are 0.
//    x cannot be greater since there are only 2 numbers in nums.
//    Example 3:
//
//    Input: nums = [0,4,3,0,4]
//    Output: 3
//    Explanation: There are 3 values that are greater than or equal to 3.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    0 <= nums[i] <= 1000