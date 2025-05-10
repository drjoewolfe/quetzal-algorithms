package org.jwolfe.quetzal.algorithms.lc;

public class MinimumEqualSumOfTwoArraysAfterReplacingZeros {
    class Solution {
        public long minSum(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return 0;
            }

            long sum1 = 0;
            int zeros1 = 0;
            for (int val : nums1) {
                sum1 += val;

                if (val == 0) {
                    sum1++;
                    zeros1++;
                }
            }

            long sum2 = 0;
            int zeros2 = 0;
            for (int val : nums2) {
                sum2 += val;

                if (val == 0) {
                    sum2++;
                    zeros2++;
                }
            }

            if ((sum1 > sum2 && zeros2 == 0) || (sum1 < sum2 && zeros1 == 0)) {
                return -1;
            }

            return Math.max(sum1, sum2);
        }
    }

    class Solution_Correct_1 {
        public long minSum(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return 0;
            }

            long minSum1 = 0;
            int zeros1 = 0;
            for (int val : nums1) {
                minSum1 += (val == 0) ? 1 : val;
                zeros1 += (val == 0) ? 1 : 0;
            }

            long minSum2 = 0;
            int zeros2 = 0;
            for (int val : nums2) {
                minSum2 += (val == 0) ? 1 : val;
                zeros2 += (val == 0) ? 1 : 0;
            }

            if (minSum1 > minSum2) {
                if (zeros2 == 0) {
                    return -1;
                }

                return minSum1;
            } else if (minSum1 < minSum2) {
                if (zeros1 == 0) {
                    return -1;
                }

                return minSum2;
            } else {
                return minSum1;
            }
        }
    }

    class Solution_Incorrect {
        public long minSum(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return 0;
            }

            long sum1 = 0;
            int zeros1 = 0;
            for (int val : nums1) {
                sum1 += val;
                zeros1 += (val == 0) ? 1 : 0;
            }

            long sum2 = 0;
            int zeros2 = 0;
            for (int val : nums2) {
                sum2 += val;
                zeros2 += (val == 0) ? 1 : 0;
            }

            int maxZeros = Math.max(zeros1, zeros2);
            System.out.println(sum1 + ", " + zeros1 + " ; " + sum2 + ", " + zeros2);

            if (sum1 > sum2) {
                // if(zeros1 == 0 || zeros2 == 0) {
                //     return -1;
                // }

                if (zeros2 == 0) {
                    return -1;
                }

                long diff = sum1 - sum2;
                if (zeros1 == 0 && diff < zeros2) {
                    return -1;
                }

                return sum1 + maxZeros;
            } else if (sum1 < sum2) {
                // if(zeros1 == 0  || zeros2 == 0) {
                //     return -1;
                // }

                if (zeros1 == 0) {
                    return -1;
                }

                long diff = sum2 - sum1;
                if (zeros2 == 0 && diff < zeros1) {
                    return -1;
                }

                return sum2 + maxZeros;
            }

            if ((zeros1 == 0 && zeros2 != 0)
                    || (zeros1 != 0 && zeros2 == 0)) {
                return -1;
            }

            int minZeros = Math.max(zeros1, zeros2);
            return sum1 + minZeros;
        }
    }

// [3,2,0,1,0]
// [6,5,0]

// [0,7,28,17,18]
// [1,2,6,26,1,0,27,3,0,30]

// [8,13,15,18,0,18,0,0,5,20,12,27,3,14,22,0]
// [29,1,6,0,10,24,27,17,14,13,2,19,2,11]

// [0,17,20,17,5,0,14,19,7,8,16,18,6]
// [21,1,27,19,2,2,24,21,16,1,13,27,8,5,3,11,13,7,29,7]

// [9,5]
// [15,12,5,21,4,26,27,9,6,29,0,18,16,0,0,0,20]

// [2,0,2,0]
// [1,4]

// [0,0,10,10,12,0,13,6,0,2,10]
// [24,5,12,22]

// [20,0,18,11,0,0,0,0,0,0,17,28,0,11,10,0,0,15,29]
// [16,9,25,16,1,9,20,28,8,0,1,0,1,27]

// [0,16,28,12,10,15,25,24,6,0,0]
// [20,15,19,5,6,29,25,8,12]

// [25,15,19,15,17,26,0,6,2,0,13,24,12,0,28,3]
// [20,0,0,1,0,25,27,0,29,13,21,11,0,3,0,27,25]
}

//    2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
//    Medium
//    You are given two arrays nums1 and nums2 consisting of positive integers.
//
//    You have to replace all the 0's in both arrays with strictly positive integers such that the sum of elements of both arrays becomes equal.
//
//    Return the minimum equal sum you can obtain, or -1 if it is impossible.
//
//
//
//    Example 1:
//
//    Input: nums1 = [3,2,0,1,0], nums2 = [6,5,0]
//    Output: 12
//    Explanation: We can replace 0's in the following way:
//    - Replace the two 0's in nums1 with the values 2 and 4. The resulting array is nums1 = [3,2,2,1,4].
//    - Replace the 0 in nums2 with the value 1. The resulting array is nums2 = [6,5,1].
//    Both arrays have an equal sum of 12. It can be shown that it is the minimum sum we can obtain.
//    Example 2:
//
//    Input: nums1 = [2,0,2,0], nums2 = [1,4]
//    Output: -1
//    Explanation: It is impossible to make the sum of both arrays equal.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 105
//    0 <= nums1[i], nums2[i] <= 106
