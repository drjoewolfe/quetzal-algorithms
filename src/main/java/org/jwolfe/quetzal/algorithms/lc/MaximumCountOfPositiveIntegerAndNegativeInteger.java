package org.jwolfe.quetzal.algorithms.lc;

public class MaximumCountOfPositiveIntegerAndNegativeInteger {
    class Solution {
        public int maximumCount(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int positiveStart = getPositiveStartIndex(nums);
            int negativeEnd = getNegativeEndIndex(nums);

            int positiveCount = (positiveStart != -1) ? n - positiveStart : 0;
            int negativeCount = (negativeEnd != -1) ? negativeEnd + 1 : 0;

            return Math.max(positiveCount, negativeCount);
        }

        private int getPositiveStartIndex(int[] nums) {
            int start = 0;
            int end = nums.length - 1;

            int index = -1;
            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (nums[mid] > 0) {
                    index = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return index;
        }

        private int getNegativeEndIndex(int[] nums) {
            int start = 0;
            int end = nums.length - 1;

            int index = -1;
            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (nums[mid] < 0) {
                    index = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            return index;
        }
    }

    class Solution_Correct_2 {
        public int maximumCount(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int positiveStart = getFirstPositiveIndex(nums);
            int negativeEnd = getLastNegativeIndex(nums);

            int positiveCount = positiveStart != -1 ? nums.length - positiveStart : 0;
            int negativeCount = negativeEnd != -1 ? negativeEnd + 1 : 0;

            return Math.max(positiveCount, negativeCount);
        }

        private int getFirstPositiveIndex(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            int index = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] > 0) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return index;
        }

        private int getLastNegativeIndex(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            int index = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] < 0) {
                    index = mid;
                    left = left + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }
    }

    class Solution_Correct_1 {
        public int maximumCount(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int positiveCount = 0;
            int negativeCount = 0;
            for (int a : nums) {
                if (a > 0) {
                    positiveCount++;
                } else if (a < 0) {
                    negativeCount++;
                }
            }

            return Math.max(positiveCount, negativeCount);
        }
    }
}

//    2529. Maximum Count of Positive Integer and Negative Integer
//    Easy
//    Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
//
//    In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
//    Note that 0 is neither positive nor negative.
//
//
//
//    Example 1:
//
//    Input: nums = [-2,-1,-1,1,2,3]
//    Output: 3
//    Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
//    Example 2:
//
//    Input: nums = [-3,-2,-1,0,0,1,2]
//    Output: 3
//    Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
//    Example 3:
//
//    Input: nums = [5,20,66,1314]
//    Output: 4
//    Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
//
//
//    Constraints:
//
//    1 <= nums.length <= 2000
//    -2000 <= nums[i] <= 2000
//    nums is sorted in a non-decreasing order.
//
//
//    Follow up: Can you solve the problem in O(log(n)) time complexity?