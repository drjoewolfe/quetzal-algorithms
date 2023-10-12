package org.jwolfe.quetzal.algorithms.lc;

public class FindInMountainArray {
    /**
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface MountainArray {
     *     public int get(int index) {}
     *     public int length() {}
     * }
     */

    class Solution {
        public int findInMountainArray(int target, MountainArray mountainArr) {
            if(mountainArr == null) {
                return -1;
            }

            int peak = findPeak(mountainArr);
            if(peak == -1) {
                return -1;
            }

            // Search in left half
            int index = searchLeft(mountainArr, 0, peak, target);
            if(index != -1) {
                return index;
            }

            // Search in right half
            return searchRight(mountainArr, peak, mountainArr.length() - 1, target);
        }

        private int findPeak(MountainArray mountainArr) {
            int n = mountainArr.length();

            int left = 0;
            int right = n - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                int a = (mid > 0) ? mountainArr.get(mid - 1) : -1;
                int b = mountainArr.get(mid);
                int c = (mid < n - 1) ? mountainArr.get(mid + 1) : -1;

                if(a < b && b > c) {
                    // Peak
                    return mid;
                } else if(a > b) {
                    // Downward Slope
                    right = mid - 1;
                } else {
                    // Upward Slope
                    left = mid + 1;
                }
            }

            // Not a mountain array
            return -1;
        }

        private int searchLeft(MountainArray mountainArr, int left, int right, int target) {
            while(left <= right) {
                int mid = left + (right - left) / 2;
                int val = mountainArr.get(mid);

                if(val == target) {
                    return mid;
                } else if(val > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return -1;
        }

        private int searchRight(MountainArray mountainArr, int left, int right, int target) {
            while(left <= right) {
                int mid = left + (right - left) / 2;
                int val = mountainArr.get(mid);

                if(val == target) {
                    return mid;
                } else if(val > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    }

    interface MountainArray {
        public int get(int index);
        public int length();
    }

// [1,2,3,4,5,3,1]
// 3

// [0,1,2,4,2,1]
// 3

// [1,2,3,4,5,3,1]
// 2

// [3,5,3,2,0]
// 0
}

//    1095. Find in Mountain Array
//    Hard
//    (This problem is an interactive problem.)
//
//    You may recall that an array arr is a mountain array if and only if:
//
//    arr.length >= 3
//    There exists some i with 0 < i < arr.length - 1 such that:
//    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//    Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
//
//    You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
//
//    MountainArray.get(k) returns the element of the array at index k (0-indexed).
//    MountainArray.length() returns the length of the array.
//    Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
//
//
//
//    Example 1:
//
//    Input: array = [1,2,3,4,5,3,1], target = 3
//    Output: 2
//    Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
//    Example 2:
//
//    Input: array = [0,1,2,4,2,1], target = 3
//    Output: -1
//    Explanation: 3 does not exist in the array, so we return -1.
//
//
//    Constraints:
//
//    3 <= mountain_arr.length() <= 104
//    0 <= target <= 109
//    0 <= mountain_arr.get(index) <= 109