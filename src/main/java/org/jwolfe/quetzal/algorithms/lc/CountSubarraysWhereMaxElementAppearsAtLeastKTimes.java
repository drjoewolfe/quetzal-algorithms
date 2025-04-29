package org.jwolfe.quetzal.algorithms.lc;

public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {
    class Solution {
        public long countSubarrays(int[] nums, int k) {
            if (nums == null || nums.length < k || k < 1) {
                return 0;
            }

            long count = 0;
            int n = nums.length;

            int maxElement = Integer.MIN_VALUE;
            for (int val : nums) {
                maxElement = Math.max(maxElement, val);
            }

            int maxElementsInWindow = 0;
            for (int left = 0, right = 0; right < n; right++) {
                int element = nums[right];
                if (element == maxElement) {
                    maxElementsInWindow++;
                }

                while (maxElementsInWindow == k) {
                    int leftElement = nums[left];
                    if (leftElement == maxElement) {
                        maxElementsInWindow--;
                    }

                    left++;
                }

                count += left;
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public long countSubarrays(int[] nums, int k) {
            if (nums == null || nums.length < k || k < 1) {
                return 0;
            }

            int n = nums.length;
            long count = 0;

            int maxElement = nums[0];
            for (int i = 1; i < n; i++) {
                maxElement = Math.max(maxElement, nums[i]);
            }

            int left = 0;
            int right = 0;
            int maxElementsInWindow = 0;

            for (; right < n; right++) {
                int element = nums[right];

                if (element == maxElement) {
                    maxElementsInWindow++;
                }

                while (maxElementsInWindow == k) {
                    int leftElement = nums[left];
                    if (leftElement == maxElement) {
                        maxElementsInWindow--;
                    }

                    left++;
                }

                count += left;
            }

            return count;
        }
    }
}

//    2962. Count Subarrays Where Max Element Appears at Least K Times
//    Medium
//    You are given an integer array nums and a positive integer k.
//
//    Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
//
//    A subarray is a contiguous sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,2,3,3], k = 2
//    Output: 6
//    Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
//    Example 2:
//
//    Input: nums = [1,4,2,1], k = 3
//    Output: 0
//    Explanation: No subarray contains the element 4 at least 3 times.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 106
//    1 <= k <= 105