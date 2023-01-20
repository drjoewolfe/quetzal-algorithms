package org.jwolfe.quetzal.algorithms.lc;

public class LongestTurbulentSubarray {
    class Solution {
        public int maxTurbulenceSize(int[] arr) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int maxLength = 1;

            int start = 0;
            for(int i = 1; i < n; i++) {
                int previous = arr[i - 1];
                int current = arr[i];

                int leftCompare = Integer.compare(previous, current);

                if(leftCompare == 0) {
                    start = i;
                } else if(i == n - 1
                        || leftCompare * Integer.compare(current, arr[i + 1]) != -1) {
                    int length = i - start + 1;
                    maxLength = Math.max(maxLength, length);

                    start = i;
                }
            }

            return maxLength;
        }
    }

    class Solution_TLE {
        public int maxTurbulenceSize(int[] arr) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            if(n == 1) {
                return 1;
            }

            int maxLength = 1;
            for(int i  = 0; i < n - 1; i++) {
                for(int j = i + 1; j < n; j++) {
                    if(isTurbulentArray(arr, i, j)) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }

            return maxLength;
        }

        private boolean isTurbulentArray(int[] arr, int i, int j) {
            boolean flag  = arr[i] > arr[i + 1] ? true : false;
            for(int k = i + 1; k <= j; k++) {
                if((flag && arr[k - 1] <= arr[k])
                        || (!flag && arr[k - 1] >= arr[k])) {
                    return false;
                }

                flag = !flag;
            }

            return true;
        }
    }

// [9,4,2,10,7,8,8,1,9]
}

//    978. Longest Turbulent Subarray
//    Medium
//    Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
//
//    A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
//
//    More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
//
//    For i <= k < j:
//    arr[k] > arr[k + 1] when k is odd, and
//    arr[k] < arr[k + 1] when k is even.
//    Or, for i <= k < j:
//    arr[k] > arr[k + 1] when k is even, and
//    arr[k] < arr[k + 1] when k is odd.
//
//
//    Example 1:
//
//    Input: arr = [9,4,2,10,7,8,8,1,9]
//    Output: 5
//    Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
//    Example 2:
//
//    Input: arr = [4,8,12,16]
//    Output: 2
//    Example 3:
//
//    Input: arr = [100]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= arr.length <= 4 * 104
//    0 <= arr[i] <= 109