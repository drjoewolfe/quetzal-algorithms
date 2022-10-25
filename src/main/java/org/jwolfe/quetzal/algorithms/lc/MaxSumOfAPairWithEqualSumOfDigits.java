package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MaxSumOfAPairWithEqualSumOfDigits {
    class Solution {
        public int maximumSum(int[] nums) {
            Arrays.sort(nums);

            int n = nums.length;
            int max = -1;
            int[] sums = new int[82]; // 9 * 9, max number is 999...99 -> 9, 9 times
            for(int val : nums) {
                int digitSum = sumOfDigits(val);
                if(sums[digitSum] > 0) {
                    max = Math.max(max, sums[digitSum] + val);
                }

                sums[digitSum] = Math.max(sums[digitSum], val);
            }

            return max;
        }

        private int sumOfDigits(int val) {
            int sum = 0;
            while(val > 0) {
                sum += val % 10;
                val /= 10;
            }

            return sum;
        }
    }

    class Solution_Correct_1 {
        public int maximumSum(int[] nums) {
            Arrays.sort(nums);

            int n = nums.length;

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int digitSum = sumOfDigits(nums[i]);
                if(!map.containsKey(digitSum)) {
                    map.put(digitSum, new ArrayList<>());
                }

                map.get(digitSum).add(i);
            }

            int max = -1;
            for(var entry : map.entrySet()) {
                var list = entry.getValue();
                if(list.size() > 1) {
                    int size = list.size();
                    max = Math.max(max,
                            nums[list.get(size - 1)] + nums[list.get(size - 2)]);
                }
            }

            return max;
        }

        private int sumOfDigits(int val) {
            int sum = 0;
            while(val > 0) {
                sum += val % 10;
                val /= 10;
            }

            return sum;
        }
    }

// [18,43,36,13,7]
// [10,12,19,14]
}

//    2342. Max Sum of a Pair With Equal Sum of Digits
//    Medium
//    You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
//
//    Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.
//
//
//
//    Example 1:
//
//    Input: nums = [18,43,36,13,7]
//    Output: 54
//    Explanation: The pairs (i, j) that satisfy the conditions are:
//    - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
//    - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
//    So the maximum sum that we can obtain is 54.
//    Example 2:
//
//    Input: nums = [10,12,19,14]
//    Output: -1
//    Explanation: There are no two numbers that satisfy the conditions, so we return -1.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109