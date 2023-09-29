package org.jwolfe.quetzal.algorithms.lc;

public class MonotonicArray {
    class Solution {
        public boolean isMonotonic(int[] A) {
            if(A == null || A.length < 2) {
                return true;
            }

            boolean increasing = true;
            boolean decreasing = true;

            for(int i = 1; i < A.length; i++) {
                int a = A[i - 1];
                int b = A[i];

                if(a > b) {
                    decreasing = false;
                } else if(a < b) {
                    increasing = false;
                }

                if(!increasing && !decreasing) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean isMonotonic(int[] A) {
            if(A == null) {
                return false;
            }

            if(A.length < 3) {
                return true;
            }

            int i = 1;
            int n = A.length;
            while(i < n && A[i] == A[i - 1]) {
                i++;
            }

            if(i == n) {
                return true;
            }

            boolean increasing = A[i] > A[i - 1];
            for(; i < n; i++) {
                if((increasing && A[i] < A[i - 1])
                        || (!increasing && A[i] > A[i - 1])) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    896. Monotonic Array
//    Easy
//    An array is monotonic if it is either monotone increasing or monotone decreasing.
//
//    An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
//
//    Given an integer array nums, return true if the given array is monotonic, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,2,3]
//    Output: true
//    Example 2:
//
//    Input: nums = [6,5,4,4]
//    Output: true
//    Example 3:
//
//    Input: nums = [1,3,2]
//    Output: false
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -105 <= nums[i] <= 105
