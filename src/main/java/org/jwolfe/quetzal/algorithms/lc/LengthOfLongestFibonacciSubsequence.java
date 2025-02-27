package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class LengthOfLongestFibonacciSubsequence {
    class Solution {
        public int lenLongestFibSubseq(int[] arr) {
            if (arr == null || arr.length < 3) {
                return 0;
            }

            int n = arr.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(arr[i], i);
            }

            int[][] dp = new int[n][n];
            for (int[] row : dp) {
                Arrays.fill(row, 2);
            }

            int maxLength = 0;

            for (int k = 2; k < n; k++) {
                int c = arr[k];

                for (int j = 1; j < k; j++) {
                    int b = arr[j];
                    int a = c - b;

                    if (!map.containsKey(a)) {
                        continue;
                    }

                    int i = map.get(a);
                    if (i >= j) {
                        continue;
                    }

                    dp[j][k] = 1 + dp[i][j];
                    maxLength = Math.max(maxLength, dp[j][k]);
                }
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int lenLongestFibSubseq(int[] arr) {
            if (arr == null || arr.length < 3) {
                return 0;
            }

            int n = arr.length;

            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < n; i++) {
                index.put(arr[i], i);
            }

            int[][] lbs = new int[n][n];
            for (int[] row : lbs) {
                Arrays.fill(row, 2);
            }

            int maxFibLength = 0;
            for (int k = 2; k < n; k++) {
                for (int j = 1; j < k; j++) {
                    int c = arr[k];
                    int b = arr[j];
                    int a = c - b;
                    if (!index.containsKey(a)) {
                        continue;
                    }

                    int i = index.get(a);
                    if (i >= j) {
                        continue;
                    }

                    lbs[j][k] = lbs[i][j] + 1;
                    maxFibLength = Math.max(maxFibLength, lbs[j][k]);
                }
            }

            return maxFibLength;
        }
    }

    class Solution_Classic {
        public int lenLongestFibSubseq(int[] arr) {
            if (arr == null || arr.length < 3) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            for (int val : arr) {
                set.add(val);
            }

            int n = arr.length;
            int maxFibLength = 0;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    int fibLength = getFibLength(set, arr[i], arr[j]);
                    maxFibLength = Math.max(maxFibLength, fibLength);
                }
            }

            return maxFibLength;
        }

        private int getFibLength(Set<Integer> set, int x, int y) {
            int length = 0;
            int z = x + y;
            while (set.contains(z)) {
                int t = z;
                z += y;
                y = t;

                length++;
            }

            return length > 0 ? length + 2 : 0;
        }
    }

// [1,2,3,4,5,6,7,8]
// [1,3,7,11,12,14,18]
// [2,4,7,8,9,10,14,15,18,23,32,50]
// [2392,2545,2666,5043,5090,5869,6978,7293,7795]
}

//    873. Length of Longest Fibonacci Subsequence
//    Medium
//    A sequence x1, x2, ..., xn is Fibonacci-like if:
//
//    n >= 3
//    xi + xi+1 == xi+2 for all i + 2 <= n
//    Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest Fibonacci-like subsequence of arr. If one does not exist, return 0.
//
//    A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr, without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].
//
//
//
//    Example 1:
//
//    Input: arr = [1,2,3,4,5,6,7,8]
//    Output: 5
//    Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].
//    Example 2:
//
//    Input: arr = [1,3,7,11,12,14,18]
//    Output: 3
//    Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].
//
//
//    Constraints:
//
//    3 <= arr.length <= 1000
//    1 <= arr[i] < arr[i + 1] <= 109