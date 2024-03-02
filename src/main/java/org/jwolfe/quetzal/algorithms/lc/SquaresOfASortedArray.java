package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SquaresOfASortedArray {
    class Solution {
        public int[] sortedSquares(int[] A) {
            if(A == null || A.length == 0) {
                return A;
            }

            int n = A.length;
            int positivePointer = 0;
            while(positivePointer < n && A[positivePointer] < 0) {
                positivePointer++;
            }

            int negativePointer = positivePointer - 1;

            int[] results = new int[n];
            int index = 0;

            while(negativePointer >= 0 && positivePointer < n) {
                int positiveSquare = A[positivePointer] * A[positivePointer];
                int negativeSquare = A[negativePointer] * A[negativePointer];

                if(positiveSquare < negativeSquare) {
                    results[index++] = positiveSquare;
                    positivePointer++;
                } else {
                    results[index++] = negativeSquare;
                    negativePointer--;
                }
            }

            while(negativePointer >= 0) {
                int negativeSquare = A[negativePointer] * A[negativePointer];
                results[index++] = negativeSquare;
                negativePointer--;
            }

            while(positivePointer < n) {
                int positiveSquare = A[positivePointer] * A[positivePointer];
                results[index++] = positiveSquare;
                positivePointer++;
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public int[] sortedSquares(int[] A) {
            if(A == null) {
                return null;
            }

            int positivePointer = 0;
            while(positivePointer < A.length && A[positivePointer] < 0) {
                positivePointer++;
            }

            int negativePointer = positivePointer - 1;
            int[] SA = new int[A.length];
            int index = 0;

            while(negativePointer >= 0 && positivePointer < A.length) {
                int negativeSquare = A[negativePointer] * A[negativePointer];
                int positiveSquare = A[positivePointer] * A[positivePointer];

                if(negativeSquare < positiveSquare) {
                    SA[index++] = negativeSquare;
                    negativePointer--;
                } else {
                    SA[index++] = positiveSquare;
                    positivePointer++;
                }
            }

            while(negativePointer >= 0) {
                int negativeSquare = A[negativePointer] * A[negativePointer];

                SA[index++] = negativeSquare;
                negativePointer--;
            }

            while(positivePointer < A.length) {
                int positiveSquare = A[positivePointer] * A[positivePointer];

                SA[index++] = positiveSquare;
                positivePointer++;
            }

            return SA;
        }

        private int[] sortedSquaresNLogN(int[] A) {
            if(A == null) {
                return null;
            }

            int[] SA = new int[A.length];

            for(int i = 0; i < A.length; i++) {
                SA[i] = A[i] * A[i];
            }

            Arrays.sort(SA);
            return SA;
        }
    }
}

//    977. Squares of a Sorted Array
//    Easy
//
//    8905
//
//    223
//
//    Add to List
//
//    Share
//    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
//
//
//
//    Example 1:
//
//    Input: nums = [-4,-1,0,3,10]
//    Output: [0,1,9,16,100]
//    Explanation: After squaring, the array becomes [16,1,0,9,100].
//    After sorting, it becomes [0,1,9,16,100].
//    Example 2:
//
//    Input: nums = [-7,-3,2,3,11]
//    Output: [4,9,9,49,121]
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -104 <= nums[i] <= 104
//    nums is sorted in non-decreasing order.
//
//
//    Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?