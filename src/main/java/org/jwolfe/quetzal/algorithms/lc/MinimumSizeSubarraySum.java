package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumSizeSubarraySum {
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if(nums == null || nums.length == 0 || s < 1) {
                return 0;
            }

            int n = nums.length;
            int minLength = n + 1;

            int sum = 0;
            int left = 0;

            for(int i = 0; i < n; i++) {
                int val = nums[i];
                sum += val;

                while(sum >= s) {
                    int length = i - left + 1;
                    minLength = Math.min(minLength, length);

                    sum -= nums[left];
                    left++;
                }
            }

            return (minLength == n + 1) ? 0 : minLength;
        }
    }

    class Solution_Correct_1 {
        public int minSubArrayLen(int s, int[] nums) {
            if(nums == null || nums.length == 0 || s <= 0) {
                return 0;
            }

            int n = nums.length;
            int left = 0;
            int sum = 0;
            int minLength = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                sum += nums[i];
                while(sum >= s) {
                    minLength = Math.min(minLength, i - left + 1);
                    sum -= nums[left];
                    left++;
                }
            }

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }
    }

    class Solution_BinarySearch {
        public int minSubArrayLen(int s, int[] nums) {
            if(nums == null || nums.length == 0 || s <= 0) {
                return 0;
            }

            int n = nums.length;
            int[] sums = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }

            int minLength = Integer.MAX_VALUE;
            for(int i = 1; i <= n; i++) {
                int a = nums[i - 1];
                int v = sums[i - 1] + s;

                int j = binarySearchForLowerBound(sums, i, v);
                if(j != -1) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

        private int binarySearchForLowerBound(int[] nums, int left, int val) {
            int right = nums.length - 1;

            int lowerBound = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == val) {
                    lowerBound = mid;
                    right = mid - 1;
                } else if(nums[mid] < val) {
                    left = mid + 1;
                } else {
                    lowerBound = mid;
                    right = mid - 1;
                }
            }

            return lowerBound;
        }

        private void print(int[] nums) {
            Arrays.stream(nums).forEach(a -> System.out.print(a + " "));
            System.out.println();
        }
    }

    class Solution_Brute {
        public int minSubArrayLen(int s, int[] nums) {
            if(nums == null || nums.length == 0 || s <= 0) {
                return 0;
            }

            int minLength = Integer.MAX_VALUE;
            for(int i = 0; i < nums.length; i++) {
                int sum = 0;
                for(int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if(sum >= s) {
                        minLength = Math.min(minLength, j - i + 1);
                        break;
                    }
                }
            }

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }
    }
}

//    209. Minimum Size Subarray Sum
//    Medium
//    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
//
//
//
//    Example 1:
//
//    Input: target = 7, nums = [2,3,1,2,4,3]
//    Output: 2
//    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
//    Example 2:
//
//    Input: target = 4, nums = [1,4,4]
//    Output: 1
//    Example 3:
//
//    Input: target = 11, nums = [1,1,1,1,1,1,1,1]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= target <= 109
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 104
//
//
//    Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).