package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ElementAppearingMoreThan25PercentInSortedArray {
    class Solution {
        public int findSpecialInteger(int[] arr) {
            if(arr == null || arr.length == 0) {
                return -1;
            }

            int n = arr.length;
            if(n < 3) {
                return arr[0];
            }

            int targetCount = n / 4;

            for(int i = 0; i < n; i += targetCount) {
                int candidate = arr[i];

                int leftBound = getLeft(arr, candidate);
                int rightBound = getRight(arr, candidate) - 1;

                if(rightBound - leftBound + 1 > targetCount) {
                    return candidate;
                }
            }

            return -1;
        }

        private int getLeft(int[] arr, int target) {
            int left = 0;
            int right = arr.length - 1;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int val = arr[mid];
                if(val >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private int getRight(int[] arr, int target) {
            int left = 0;
            int right = arr.length;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int val = arr[mid];
                if(val > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Correct_1 {
        public int findSpecialInteger(int[] arr) {
            if(arr == null || arr.length == 0) {
                return Integer.MIN_VALUE;
            }

            if(arr.length < 3) {
                return arr[0];
            }

            int n = arr.length;
            int target = n / 4;

            for(int i = 0; i < n; i += target) {
                int candidate = arr[i];

                int left = getLeft(arr, candidate);
                int right = getRight(arr, candidate);

                if((right - left + 1) > target) {
                    return candidate;
                }
            }

            return Integer.MIN_VALUE;
        }

        private int getLeft(int[] arr, int x) {
            int left = 0;
            int right = arr.length - 1;
            int res = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] > x) {
                    right = mid - 1;
                } else if(arr[mid] < x) {
                    left = mid + 1;
                } else {
                    res = mid;
                    right = mid - 1;
                }
            }

            return res;
        }

        private int getRight(int[] arr, int x) {
            int left = 0;
            int right = arr.length - 1;
            int res = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] > x) {
                    right = mid - 1;
                } else if(arr[mid] < x) {
                    left = mid + 1;
                } else {
                    res = mid;
                    left = mid + 1;
                }
            }

            return res;
        }
    }

    class Solution_Map {
        public int findSpecialInteger(int[] arr) {
            if(arr == null || arr.length == 0) {
                return Integer.MIN_VALUE;
            }

            int n = arr.length;
            // int target = (n % 2 == 0) ? n / 4 : n / 4 + 1;
            int target = n / 4;

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : arr) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
                if(frequencies.get(a) > target) {
                    return a;
                }
            }

            return Integer.MIN_VALUE;
        }
    }

// [1,2,2,6,6,6,6,7,10]
// [1,2,3,3]
}

//    1287. Element Appearing More Than 25% In Sorted Array
//    Easy
//    Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.
//
//
//
//    Example 1:
//
//    Input: arr = [1,2,2,6,6,6,6,7,10]
//    Output: 6
//    Example 2:
//
//    Input: arr = [1,1]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= arr.length <= 104
//    0 <= arr[i] <= 105