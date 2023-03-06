package org.jwolfe.quetzal.algorithms.lc;

public class KthMissingPositiveNumber {
    class Solution {
        public int findKthPositive(int[] arr, int k) {
            if(k <= 0) {
                return -1;
            }

            if(arr == null || arr.length == 0) {
                return k;
            }

            int left = 0;
            int right = arr.length;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int val = arr[mid];
                int delta = val - (mid + 1);

                if(delta >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left + k;
        }
    }

    class Solution_Correct_1 {
        public int findKthPositive(int[] arr, int k) {
            if(arr == null || arr.length == 0 || k <= 0) {
                return -1;
            }

            int left = 0;
            int right = arr.length;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int delta = arr[mid] - (mid + 1);

                if(delta >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left + k;
        }
    }

    class Solution_Classic {
        public int findKthPositive(int[] arr, int k) {
            if(arr == null || arr.length == 0 || k <= 0) {
                return -1;
            }

            int prev = 0;
            int last = arr[arr.length - 1];
            int missing = 0;
            for(int i = 0; i < arr.length; i++) {
                int a = arr[i];
                int delta = a - prev - 1;

                for(int j = 1; j <= delta; j++) {
                    missing = prev + j;
                    k--;
                    if(k == 0) {
                        return missing;
                    }
                }

                prev = a;
            }

            return last + k;
        }
    }

// [2,3,4,7,11]
// 5
}

//    1539. Kth Missing Positive Number
//    Easy
//    Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
//
//    Return the kth positive integer that is missing from this array.
//
//
//
//    Example 1:
//
//    Input: arr = [2,3,4,7,11], k = 5
//    Output: 9
//    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
//    Example 2:
//
//    Input: arr = [1,2,3,4], k = 2
//    Output: 6
//    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
//
//
//    Constraints:
//
//    1 <= arr.length <= 1000
//    1 <= arr[i] <= 1000
//    1 <= k <= 1000
//    arr[i] < arr[j] for 1 <= i < j <= arr.length
//
//
//    Follow up:
//
//    Could you solve this problem in less than O(n) complexity?