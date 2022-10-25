package org.jwolfe.quetzal.algorithms.lc;

public class MinimizeMaximumOfArray {
    class Solution {
        public int minimizeArrayValue(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int max = 0;
            for(int a : nums) {
                max = Math.max(max, a);
            }

            int left = 0;
            int right = max;

            int ans = right;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(isMaxElementPossible(nums, mid)) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return ans;
        }

        private boolean isMaxElementPossible(int[] arr, int max) {
            long excess = 0;

            int n = arr.length;
            for(int i = 0; i < n; i++) {
                int val = arr[i];

                if(val > max) {
                    int diff = val - max;
                    if(excess < diff) {
                        return false;
                    }

                    excess -= diff;
                } else {
                    excess += (max - val);
                }
            }

            return true;
        }

//     private boolean isMaxElementPossible(int[] arr, int max) {
//         int[] cnums = arr.clone();
//         int n = cnums.length;
//         for(int i = 0; i < n - 1; i++) {
//             if(cnums[i] > max) {
//                 return false;
//             }

//             cnums[i + 1] -= (max - cnums[i]);
//         }

//         return cnums[n - 1] > max ? false : true;
//     }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    2439. Minimize Maximum of Array
//    Medium
//    You are given a 0-indexed array nums comprising of n non-negative integers.
//
//    In one operation, you must:
//
//    Choose an integer i such that 1 <= i < n and nums[i] > 0.
//    Decrease nums[i] by 1.
//    Increase nums[i - 1] by 1.
//    Return the minimum possible value of the maximum integer of nums after performing any number of operations.
//
//
//
//    Example 1:
//
//    Input: nums = [3,7,1,6]
//    Output: 5
//    Explanation:
//    One set of optimal operations is as follows:
//    1. Choose i = 1, and nums becomes [4,6,1,6].
//    2. Choose i = 3, and nums becomes [4,6,2,5].
//    3. Choose i = 1, and nums becomes [5,5,2,5].
//    The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
//    Therefore, we return 5.
//    Example 2:
//
//    Input: nums = [10,1]
//    Output: 10
//    Explanation:
//    It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
//
//
//    Constraints:
//
//    n == nums.length
//    2 <= n <= 105
//    0 <= nums[i] <= 109