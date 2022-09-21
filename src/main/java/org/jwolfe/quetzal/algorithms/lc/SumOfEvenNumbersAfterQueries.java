package org.jwolfe.quetzal.algorithms.lc;

public class SumOfEvenNumbersAfterQueries {
    class Solution {
        public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
            if(A == null || A.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int sum = 0;
            for(int i = 0; i < A.length; i++) {
                if(A[i] % 2 == 0) {
                    sum += A[i];
                }
            }

            int[] results = new int[queries.length];
            for(int i = 0; i < queries.length; i++) {
                int[] query = queries[i];
                int index = query[1];
                int valueToAdd = query[0];

                int prevValue = A[index];
                int newValue = prevValue + valueToAdd;

                if(prevValue % 2 == 0) {
                    // Even - included in sum before
                    if(newValue % 2 == 0) {
                        sum += valueToAdd;
                    } else {
                        sum -= prevValue;
                    }
                } else {
                    if(newValue % 2 == 0) {
                        sum += newValue;
                    }
                }

                A[index] = newValue;
                results[i] = sum;
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
            if(A == null || A.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = queries.length;
            int[] result = new int[n];

            int evenSum = 0;
            for(int a : A) {
                if(a % 2 == 0) {
                    evenSum += a;
                }
            }

            for(int i = 0; i < n; i++) {
                int[] query = queries[i];
                int val = query[0];
                int index = query[1];
                int a = A[index];
                int newVal = a + val;

                if(a % 2 == 0) {
                    if(val % 2 == 0) {
                        evenSum += val;
                    } else {
                        evenSum -= a;
                    }
                } else {
                    if(val % 2 != 0) {
                        evenSum += newVal;
                    }
                }

                A[index] = newVal;

                result[i] = evenSum;
            }

            return result;
        }
    }
}

//    985. Sum of Even Numbers After Queries
//    Medium
//    You are given an integer array nums and an array queries where queries[i] = [vali, indexi].
//
//    For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.
//
//    Return an integer array answer where answer[i] is the answer to the ith query.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
//    Output: [8,6,2,4]
//    Explanation: At the beginning, the array is [1,2,3,4].
//    After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
//    After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
//    After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
//    After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
//    Example 2:
//
//    Input: nums = [1], queries = [[4,0]]
//    Output: [0]
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -104 <= nums[i] <= 104
//    1 <= queries.length <= 104
//    -104 <= vali <= 104
//    0 <= indexi < nums.length