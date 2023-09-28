package org.jwolfe.quetzal.algorithms.lc;

public class SortArrayByParity {
    class Solution {
        public int[] sortArrayByParity(int[] A) {
            if(A == null || A.length < 2) {
                return A;
            }

            int n = A.length;
            int left = 0;
            for(int right = 0; right < n; right++) {
                if(A[right] % 2 == 0) {
                    swap(A, left, right);
                    left++;
                }
            }

            return A;
        }

        private void swap(int[] A, int left, int right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
        }
    }

    class Solution_Correct_1 {
        public int[] sortArrayByParity(int[] A) {
            if(A == null || A.length == 0) {
                return A;
            }

            int index = 0;
            for(int i = 0 ; i < A.length; i++) {
                if(A[i] % 2 == 0) {
                    swap(A, i, index);
                    index++;
                }
            }

            return A;
        }

        private void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }
}

//    905. Sort Array By Parity
//    Easy
//    Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
//
//    Return any array that satisfies this condition.
//
//
//
//    Example 1:
//
//    Input: nums = [3,1,2,4]
//    Output: [2,4,3,1]
//    Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
//    Example 2:
//
//    Input: nums = [0]
//    Output: [0]
//
//
//    Constraints:
//
//    1 <= nums.length <= 5000
//    0 <= nums[i] <= 5000