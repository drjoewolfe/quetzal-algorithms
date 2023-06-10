package org.jwolfe.quetzal.algorithms.lc;

public class MaximumValueAtAGivenIndexInABoundedArray {
    class Solution {
        public int maxValue(int n, int index, int maxSum) {
            if(n < 1 || index < 0 || index >= n || maxSum < 0) {
                return -1;
            }

            long min = 1;
            long max = maxSum;

            long  val = 0;

            while(min <= max) {
                long target = 1L * (min + (max - min) / 2);

                if(feasible(n, index, maxSum, target)) {
                    val = target;
                    min = target + 1;
                } else {
                    max = target - 1;
                }
            }

            return (int) val;
        }

        private boolean feasible(int n, int index, int maxSum, long target) {
            long sum = 0;

            // left
            if(index < target) {
                sum += 1L * (((target - index) + target) * (index + 1) / 2);
            } else {
                sum += 1L * ((target * (target + 1) / 2) + (index - target + 1));
            }

            // right
            if(n - index <= target) {
                sum += 1L * ((target + (target - n + 1 + index)) * (n - index) / 2);
            } else {
                sum += 1L * ((target * (target + 1) / 2) + (n - index - target));
            }

            sum -= target;
            return sum <= maxSum;
        }

        private int naturalSum(int n) {
            return n * (n + 1) / 2;
        }
    }

// 4
// 2
// 6

// 6
// 1
// 10

// 3
// 0
// 815094800

// 8257285
// 4828516
// 850015631
}

//    1802. Maximum Value at a Given Index in a Bounded Array
//    Medium
//    You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
//
//    nums.length == n
//    nums[i] is a positive integer where 0 <= i < n.
//    abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
//    The sum of all the elements of nums does not exceed maxSum.
//    nums[index] is maximized.
//    Return nums[index] of the constructed array.
//
//    Note that abs(x) equals x if x >= 0, and -x otherwise.
//
//
//
//    Example 1:
//
//    Input: n = 4, index = 2,  maxSum = 6
//    Output: 2
//    Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
//    There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
//    Example 2:
//
//    Input: n = 6, index = 1,  maxSum = 10
//    Output: 3
//
//
//    Constraints:
//
//    1 <= n <= maxSum <= 109
//    0 <= index < n
