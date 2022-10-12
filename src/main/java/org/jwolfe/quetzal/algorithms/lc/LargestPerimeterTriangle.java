package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class LargestPerimeterTriangle {
    class Solution {
        public int largestPerimeter(int[] A) {
            if(A == null || A.length < 3) {
                return 0;
            }

            Arrays.sort(A);
            int n = A.length;
            for(int i = n - 3; i >= 0; i--) {
                int a = A[i];
                int b = A[i + 1];
                int c = A[i + 2];

                if(a + b > c) {
                    return a + b + c;
                }
            }

            return 0;
        }
    }

    class Solution_Correct_1 {
        public int largestPerimeter(int[] A) {
            if(A == null || A.length < 3) {
                return 0;
            }

            Arrays.sort(A);
            int n = A.length;
            for(int i = n - 3; i >= 0; i--) {
                int a = A[i];
                int b = A[i + 1];
                int c = A[i + 2];

                if(isValidTriangle(a, b, c)) {
                    return a + b + c;
                }
            }

            return 0;
        }

        private boolean isValidTriangle(int a, int b, int c) {
            if(a + b > c
                    && b + c > a
                    && a + c > b) {
                return true;
            }

            return false;
        }
    }

    class Solution_TimeExceeded {
        public int largestPerimeter(int[] A) {
            if(A == null || A.length < 3) {
                return 0;
            }

            Arrays.sort(A);
            int n = A.length;
            for(int i = n - 1; i >= 2; i--) {
                for(int j = i - 1; j >= 1; j--) {
                    for(int k = j - 1; k >= 0; k--) {
                        int a = A[i];
                        int b = A[j];
                        int c = A[k];

                        if(isValidTriangle(a, b, c)) {
                            return a + b + c;
                        }
                    }
                }
            }

            return 0;
        }

        private boolean isValidTriangle(int a, int b, int c) {
            if(a + b > c
                    && b + c > a
                    && a + c > b) {
                return true;
            }

            return false;
        }
    }
}

//    976. Largest Perimeter Triangle
//    Easy
//    Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,2]
//    Output: 5
//    Example 2:
//
//    Input: nums = [1,2,1]
//    Output: 0
//
//
//    Constraints:
//
//    3 <= nums.length <= 104
//    1 <= nums[i] <= 106
