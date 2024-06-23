package org.jwolfe.quetzal.algorithms.lc;

import java.util.Deque;
import java.util.LinkedList;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    class Solution {
        public int longestSubarray(int[] nums, int limit) {
            if(nums == null || nums.length == 0 || limit < 0) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 0;

            Deque<Integer> maxQueue = new LinkedList<>();
            Deque<Integer> minQueue = new LinkedList<>();

            int left = 0;
            for(int right = 0; right < n; right++) {
                int val = nums[right];

                // maintain maxQueue in decreasing order
                while(!maxQueue.isEmpty()
                        && maxQueue.peekLast() < val) {
                    maxQueue.pollLast();
                }

                maxQueue.offerLast(val);

                // maintain minQueue in increasing order
                while(!minQueue.isEmpty()
                        && minQueue.peekLast() > val) {
                    minQueue.pollLast();
                }

                minQueue.offerLast(val);

                while(maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                    int leftVal = nums[left];

                    if(maxQueue.peekFirst() == leftVal) {
                        maxQueue.pollFirst();
                    }

                    if(minQueue.peekFirst() == leftVal) {
                        minQueue.pollFirst();
                    }

                    left++;
                }

                int length = right - left + 1;
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int longestSubarray(int[] nums, int limit) {
            if(nums == null || nums.length == 0 || limit < 0) {
                return 0;
            }

            int maxLength = 1;
            int n = nums.length;

            Deque<Integer> maxQueue = new LinkedList<>();
            Deque<Integer> minQueue = new LinkedList<>();

            int left = 0;
            for(int right = 0; right < n; right++) {
                while(!maxQueue.isEmpty() && maxQueue.peekLast() < nums[right]) {
                    maxQueue.pollLast();
                }

                while(!minQueue.isEmpty() && minQueue.peekLast() > nums[right]) {
                    minQueue.pollLast();
                }

                maxQueue.offerLast(nums[right]);
                minQueue.offerLast(nums[right]);

                if(maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                    if(nums[left] == maxQueue.peekFirst()) {
                        maxQueue.pollFirst();
                    } else if(nums[left] == minQueue.peekFirst()) {
                        minQueue.pollFirst();
                    }

                    left++;
                }

                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
        }
    }

    class Solution_N2 {
        public int longestSubarray(int[] nums, int limit) {
            if(nums == null || nums.length == 0 || limit < 0) {
                return 0;
            }

            int longestLength = 1;
            int n = nums.length;

            int left = 0;
            int right = 0;
            int min = nums[0];
            int max = nums[0];

            while(right < n - 1) {
                right++;
                min = Math.min(min, nums[right]);
                max = Math.max(max, nums[right]);

                if(max - min <= limit) {
                    longestLength = Math.max(longestLength, right - left + 1);
                } else {
                    while(max - min > limit) {
                        left++;

                        max = nums[left];
                        min = nums[left];

                        for(int i = left + 1; i <= right; i++) {
                            max = Math.max(max, nums[i]);
                            min = Math.min(min, nums[i]);
                        }
                    }
                }
            }

            return longestLength;
        }
    }

    class Solution_Brute {
        public int longestSubarray(int[] nums, int limit) {
            if(nums == null || nums.length == 0 || limit < 0) {
                return 0;
            }

            int longestLength = 1;

            int n = nums.length;
            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {

                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;

                    for(int k = i; k <= j; k++) {
                        min = Math.min(min, nums[k]);
                        max = Math.max(max, nums[k]);
                    }

                    if((max - min) <= limit) {
                        longestLength = Math.max(longestLength, j - i + 1);
                    }
                }
            }

            return longestLength;
        }
    }

// [8,2,4,7]
// 4

// [1,5,6,7,8,10,6,5,6]
// 4
}

//    1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
//    Medium
//    Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
//
//
//
//    Example 1:
//
//    Input: nums = [8,2,4,7], limit = 4
//    Output: 2
//    Explanation: All subarrays are:
//    [8] with maximum absolute diff |8-8| = 0 <= 4.
//    [8,2] with maximum absolute diff |8-2| = 6 > 4.
//    [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//    [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//    [2] with maximum absolute diff |2-2| = 0 <= 4.
//    [2,4] with maximum absolute diff |2-4| = 2 <= 4.
//    [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//    [4] with maximum absolute diff |4-4| = 0 <= 4.
//    [4,7] with maximum absolute diff |4-7| = 3 <= 4.
//    [7] with maximum absolute diff |7-7| = 0 <= 4.
//    Therefore, the size of the longest subarray is 2.
//    Example 2:
//
//    Input: nums = [10,1,2,4,7,2], limit = 5
//    Output: 4
//    Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
//    Example 3:
//
//    Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//    Output: 3
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    0 <= limit <= 109