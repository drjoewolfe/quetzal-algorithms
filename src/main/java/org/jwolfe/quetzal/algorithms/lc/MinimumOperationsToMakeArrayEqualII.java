package org.jwolfe.quetzal.algorithms.lc;

public class MinimumOperationsToMakeArrayEqualII {
    class Solution {
        public long minOperations(int[] nums1, int[] nums2, int k) {
            if(nums1 == null || nums1.length == 0 || nums2 == null || nums1.length != nums2.length || k < 0) {
                return -1;
            }

            int n = nums1.length;

            long sum = 0;
            long positiveSum = 0;

            for(int i = 0; i < n; i++) {
                long diff = nums1[i] - nums2[i];
                if(diff != 0) {
                    if(k != 0 && diff % k != 0) {
                        return -1;
                    }

                    sum += diff;

                    if(diff > 0) {
                        positiveSum += diff;
                    }
                }
            }

            if(sum != 0) {
                return -1;
            }

            if(k == 0) {
                return (positiveSum == 0) ? 0 : -1;
            }

            if(positiveSum % k != 0) {
                return -1;
            }

            return positiveSum / k;
        }
    }

// [10,5,15,20]
// [20,10,15,5]
// 0

// [1,1,1]
// [0,0,3]
// 2
}

//    2541. Minimum Operations to Make Array Equal II
//    Medium
//    You are given two integer arrays nums1 and nums2 of equal length n and an integer k. You can perform the following operation on nums1:
//
//    Choose two indexes i and j and increment nums1[i] by k and decrement nums1[j] by k. In other words, nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
//    nums1 is said to be equal to nums2 if for all indices i such that 0 <= i < n, nums1[i] == nums2[i].
//
//    Return the minimum number of operations required to make nums1 equal to nums2. If it is impossible to make them equal, return -1.
//
//
//
//    Example 1:
//
//    Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
//    Output: 2
//    Explanation: In 2 operations, we can transform nums1 to nums2.
//    1st operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
//    2nd operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
//    One can prove that it is impossible to make arrays equal in fewer operations.
//    Example 2:
//
//    Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
//    Output: -1
//    Explanation: It can be proved that it is impossible to make the two arrays equal.
//
//
//    Constraints:
//
//    n == nums1.length == nums2.length
//    2 <= n <= 105
//    0 <= nums1[i], nums2[j] <= 109
//    0 <= k <= 105