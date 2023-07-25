package org.jwolfe.quetzal.algorithms.lc;

public class PeakIndexInAMountainArray {
    class Solution {
        public int peakIndexInMountainArray(int[] A) {
            if(A == null || A.length < 3) {
                return -1;
            }

            int left = 0;
            int right = A.length - 1;
            while(left < right) {
                int mid = left + (right - left) / 2;

                int a = A[mid - 1];
                int b = A[mid];
                int c = A[mid + 1];

                if(a < b && b > c) {
                    return mid;
                } else if(a < b) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int peakIndexInMountainArray(int[] A) {
            if(A == null || A.length < 3) {
                return -1;
            }

            int n = A.length;
            int left = 0;
            int right = n - 1;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int a = A[mid - 1];
                int b = A[mid];
                int c = A[mid + 1];

                if(a < b && b > c) {
                    return mid;
                } else if(a < b) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return -1;
        }
    }

    class Solution_Brute {
        public int peakIndexInMountainArray(int[] A) {
            if(A == null || A.length < 3) {
                return -1;
            }

            int n = A.length;
            int i = 0;
            int peakIndex = 0;

            // Climb up
            while(i < n - 1 && A[i] < A[i + 1]) {
                i++;
            }

            peakIndex = i;

            // Climb down
            while(i < n - 1 && A[i] > A[i + 1]) {
                i++;
            }

            if(i != n - 1) {
                return -1;
            }

            return peakIndex;
        }
    }
}

//    852. Peak Index in a Mountain Array
//    Medium
//    An array arr a mountain if the following properties hold:
//
//    arr.length >= 3
//    There exists some i with 0 < i < arr.length - 1 such that:
//    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//    Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
//
//    You must solve it in O(log(arr.length)) time complexity.
//
//
//
//    Example 1:
//
//    Input: arr = [0,1,0]
//    Output: 1
//    Example 2:
//
//    Input: arr = [0,2,1,0]
//    Output: 1
//    Example 3:
//
//    Input: arr = [0,10,5,2]
//    Output: 1
//
//
//    Constraints:
//
//    3 <= arr.length <= 105
//    0 <= arr[i] <= 106
//    arr is guaranteed to be a mountain array.