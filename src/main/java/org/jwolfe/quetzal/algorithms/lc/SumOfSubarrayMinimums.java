package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubarrayMinimums {
    class Solution {
        private int MOD = 1_000_000_007;
        public int sumSubarrayMins(int[] arr) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int[] leftMin = new int[n];
            int[] rightMin = new int[n];

            Arrays.fill(leftMin, -1);
            Arrays.fill(rightMin, n);

            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < n; i++) {
                int val = arr[i];

                while(!stack.isEmpty()
                        && val < arr[stack.peek()]) {
                    int index = stack.pop();
                    rightMin[index] = i;
                }

                if(!stack.isEmpty()) {
                    leftMin[i] = stack.peek();
                }

                stack.push(i);
            }

            long minSum = 0;
            for(int i = 0; i < n; i++) {
                minSum += 1L* (i - leftMin[i]) * (rightMin[i] - i) * arr[i];
                minSum %= MOD;
            }

            return (int) minSum;
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        private int MOD = 1_000_000_007;
        public int sumSubarrayMins(int[] arr) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int minSum = 0;
            for(int l = 1; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    int min = arr[i];

                    for(int k = i + 1; k <= j; k++) {
                        min = Math.min(min, arr[k]);
                    }

                    minSum += min;
                    minSum %= MOD;
                }
            }

            return minSum;
        }
    }

// [3,1,2,4]
}

//    907. Sum of Subarray Minimums
//    Medium
//    Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: arr = [3,1,2,4]
//    Output: 17
//    Explanation:
//    Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
//    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//    Sum is 17.
//    Example 2:
//
//    Input: arr = [11,81,94,43,3]
//    Output: 444
//
//
//    Constraints:
//
//    1 <= arr.length <= 3 * 104
//    1 <= arr[i] <= 3 * 104