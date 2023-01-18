package org.jwolfe.quetzal.algorithms.lc;

public class MaximumSumCircularSubarray {
    class Solution {
        public int maxSubarraySumCircular(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int maxSoFar = Integer.MIN_VALUE;
            int maxEndingHere = 0;

            int minSoFar = Integer.MAX_VALUE;
            int minEndingHere = 0;

            int sum = 0;

            boolean allNegative = true;
            int maxNegative = Integer.MIN_VALUE;

            for(int a : A) {
                sum += a;

                maxEndingHere += a;
                if(maxEndingHere < 0) {
                    maxEndingHere = 0;
                }

                minEndingHere += a;
                if(minEndingHere > 0) {
                    minEndingHere = 0;
                }

                maxSoFar = Math.max(maxSoFar, maxEndingHere);
                minSoFar = Math.min(minSoFar, minEndingHere);

                if(a < 0) {
                    maxNegative = Math.max(maxNegative, a);
                } else {
                    allNegative = false;
                }
            }

            if(allNegative) {
                return maxNegative;
            }

            return Math.max(maxSoFar, sum - minSoFar);
        }
    }

    class Solution_Correct_1 {
        public int maxSubarraySumCircular(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int arraySum = 0;

            int maxSubarraySum = Integer.MIN_VALUE;
            int runningMaxSubarraySum = 0;

            int minSubarraySum = Integer.MAX_VALUE;
            int runningMinSubarraySum = 0;

            int maxNegative = Integer.MIN_VALUE;
            boolean allNegative = true;

            for(int i = 0; i < A.length; i++) {
                int v = A[i];

                arraySum += v;
                runningMaxSubarraySum += v;
                runningMinSubarraySum += v;

                if(runningMaxSubarraySum < 0) {
                    runningMaxSubarraySum = 0;
                }

                if(runningMinSubarraySum > 0) {
                    runningMinSubarraySum = 0;
                }

                if(v < 0) {
                    maxNegative = Math.max(maxNegative, v);
                } else {
                    allNegative = false;
                }

                maxSubarraySum = Math.max(maxSubarraySum, runningMaxSubarraySum);
                minSubarraySum = Math.min(minSubarraySum, runningMinSubarraySum);
            }

            if(allNegative) {
                return maxNegative;
            }

            return Math.max(maxSubarraySum, arraySum - minSubarraySum);
        }
    }

// Testcases
//  [1,-2,3,-2]
//  [-2,-3,-1]
//  [5,-3,5]

    class Solution_Classic {
        public int maxSubarraySumCircular(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int maxSubArraySumOfA = maxSubArraySum(A);

            int arraySum = arraySum(A);
            invertSign(A);
            int minSubArraySumOfA = maxSubArraySum(A) * (-1);

            if(arraySum == minSubArraySumOfA) {
                // All negatives in the array
                return maxSubArraySumOfA;
            }

            int maxCircularSumOfA = arraySum - minSubArraySumOfA;

            return Math.max(maxSubArraySumOfA, maxCircularSumOfA);
        }

        private int maxSubArraySum(int[] A) {
            int maxSoFar = 0;
            int maxNegative = Integer.MIN_VALUE;
            boolean hasZero = false;

            int maxEndingHere = 0;

            for(int i = 0; i < A.length; i++) {
                maxEndingHere += A[i];

                if(maxEndingHere < 0) {
                    maxEndingHere = 0;
                }

                if(A[i] == 0) {
                    hasZero = true;
                } else if(A[i] < 0) {
                    maxNegative = Math.max(maxNegative, A[i]);
                }

                maxSoFar = Math.max(maxSoFar, maxEndingHere);
            }

            if(maxSoFar == 0 && !hasZero) {
                return maxNegative;
            }

            return maxSoFar;
        }

        private int arraySum(int[] A) {
            int sum = 0;
            for(int n : A) {
                sum += n;
            }

            return sum;
        }

        private void invertSign(int[] A) {
            for(int i = 0; i < A.length; i++) {
                A[i] = -A[i];
            }
        }
    }

    class Solution_Brute {
        public int maxSubarraySumCircular(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int length = A.length;

            int maxSoFar = 0;
            int maxNegative = Integer.MIN_VALUE;
            boolean hasZero = false;

            int currentMax =   0;

            for(int i = 0; i < length; i++) {
                currentMax = 0;

                for(int j = 0; j < length; j++) {
                    int k = (i + j) % length;
                    currentMax += A[k];
                    if(currentMax < 0) {
                        currentMax = 0;
                    }

                    if(A[k] == 0 && !hasZero) {
                        hasZero = true;
                    }

                    if(A[k] < 0) {
                        maxNegative = Math.max(maxNegative, A[k]);
                    }

                    maxSoFar = Math.max(maxSoFar, currentMax);
                }
            }

            if(!hasZero & maxSoFar == 0) {
                return maxNegative;
            }

            return maxSoFar;
        }
    }

// [1,-2,3,-2]
// [-3,-2,-3]
}

//    918. Maximum Sum Circular Subarray
//    Medium
//    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
//
//    A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
//
//    A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
//
//
//
//    Example 1:
//
//    Input: nums = [1,-2,3,-2]
//    Output: 3
//    Explanation: Subarray [3] has maximum sum 3.
//    Example 2:
//
//    Input: nums = [5,-3,5]
//    Output: 10
//    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
//    Example 3:
//
//    Input: nums = [-3,-2,-3]
//    Output: -2
//    Explanation: Subarray [-2] has maximum sum -2.
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 3 * 104
//    -3 * 104 <= nums[i] <= 3 * 104