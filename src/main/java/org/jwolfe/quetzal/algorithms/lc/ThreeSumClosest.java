package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ThreeSumClosest {
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int minAbsDiff = Integer.MAX_VALUE;
            int minDiff = Integer.MAX_VALUE;

            Arrays.sort(nums);
            for(int i = 0; i < n - 2; i++) {
                int a = nums[i];

                int j = i + 1;
                int k = n - 1;
                while(j < k) {
                    int b = nums[j];
                    int c = nums[k];

                    int sum = a + b + c;
                    int absDiff = Math.abs(target - sum);
                    if(absDiff < minAbsDiff) {
                        minAbsDiff = absDiff;
                        minDiff = target - sum;
                    }

                    if(sum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }

            return target - minDiff;
        }
    }

    class Solution_Brute {
        public int threeSumClosest(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int closestSum = Integer.MAX_VALUE;
            int closestDifference = Integer.MAX_VALUE;

            for(int i = 0; i < n - 2; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];
                    for(int k = j + 1; k < n; k++) {
                        int c = nums[k];

                        int sum = a + b + c;
                        int diff = Math.abs(target - sum);
                        if(diff < closestDifference) {
                            closestDifference = diff;
                            closestSum = sum;
                        }
                    }
                }
            }

            return closestSum;
        }
    }

    class Solution_Correct_2 {
        public int threeSumClosest(int[] nums, int target) {
            if(nums == null || nums.length < 3) {
                throw new RuntimeException("Invalid input");
            }

            Arrays.sort(nums);
            int n = nums.length;
            int closestSum = Integer.MAX_VALUE;
            int closestAbsDiff = Integer.MAX_VALUE;
            for(int i = 0; i < n - 2; i++) {
                int a = nums[i];

                int j = i + 1;
                int k = n - 1;
                while(j < k) {
                    int b = nums[j];
                    int c = nums[k];

                    int sum = a + b + c;
                    int diff = target - sum;
                    if(diff == 0) {
                        return sum;
                    } else {
                        int absDiff = Math.abs(diff);
                        if(closestAbsDiff > absDiff) {
                            closestAbsDiff = absDiff;
                            closestSum = sum;
                        }

                        if(diff > 0) {
                            j++;
                        } else {
                            k--;
                        }
                    }
                }
            }

            return closestSum;
        }
    }

    class Solution_Brute_2 {
        public int threeSumClosest(int[] nums, int target) {
            if(nums == null || nums.length < 3) {
                throw new RuntimeException("Invalid input");
            }

            int n = nums.length;
            int closestSum = Integer.MAX_VALUE;
            int closestDiff = Integer.MAX_VALUE;
            for(int i = 0; i < n - 2; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];
                    for(int k = j + 1; k < n; k++) {
                        int c = nums[k];
                        int sum = a + b + c;

                        int diff = Math.abs(target - sum);
                        if(closestDiff > diff) {
                            closestDiff = diff;
                            closestSum = sum;
                        }
                    }
                }
            }

            return closestSum;
        }
    }
}

//    16. 3Sum Closest
//    Medium
//    Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//
//
//    Example 1:
//
//    Input: nums = [-1,2,1,-4], target = 1
//    Output: 2
//    Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
//
//
//    Constraints:
//
//    3 <= nums.length <= 10^3
//    -10^3 <= nums[i] <= 10^3
//    -10^4 <= target <= 10^4