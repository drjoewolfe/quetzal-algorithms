package org.jwolfe.quetzal.algorithms.lc;

public class OnesAndZeroes {
    class Solution {
        public int findMaxForm(String[] strs, int m, int n) {
            if(strs == null || strs.length == 0 || m < 0 || n < 0) {
                return 0;
            }

            int[][] dp = new int[m + 1][n + 1];

            for(String s : strs) {
                int zeros = 0;
                int ones = 0;
                for(int j = 0; j < s.length(); j++) {
                    if(s.charAt(j) == '0') {
                        zeros++;
                    } else {
                        ones++;
                    }
                }

                for(int i = m; i >= zeros; i--) {
                    for(int j = n; j >= ones; j--) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    }
                }
            }

            return dp[m][n];
        }

        private void print(int[][] dp) {
            for(int[] row : dp) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }
            }

            System.out.println();
        }
    }

    class Solution_Recursive {
        int maxSubsetSize;

        public int findMaxForm(String[] strs, int m, int n) {
            if(strs == null || strs.length == 0 || m <= 0 || n <= 0) {
                return 0;
            }

            int l = strs.length;
            int[][] frequencies = new int[l][2];
            for(int i = 0; i < l; i++) {
                String s = strs[i];
                for(int j = 0; j < s.length(); j++) {
                    if(s.charAt(j) == '0') {
                        frequencies[i][0]++;
                    } else {
                        frequencies[i][1]++;
                    }
                }
            }

            maxSubsetSize = 0;
            validateSubsets(frequencies, 0, 0, 0, m, n, 0);

            return maxSubsetSize;
        }

        private void validateSubsets(int[][] frequencies, int index, int zeroCount, int oneCount, int m, int n, int size) {
            if(zeroCount > m || oneCount > n) {
                return;
            }

            if(index == frequencies.length) {
                maxSubsetSize = Math.max(maxSubsetSize, size);
                return;
            }

            // exclude current index
            validateSubsets(frequencies, index + 1, zeroCount, oneCount, m, n, size);

            // include current index
            validateSubsets(frequencies, index + 1, zeroCount + frequencies[index][0], oneCount + frequencies[index][1], m, n, size + 1);
        }
    }

// ["10","0001","111001","1","0"]
// 5
// 3
}

//    474. Ones and Zeroes
//    Medium
//    You are given an array of binary strings strs and two integers m and n.
//
//    Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
//
//    A set x is a subset of a set y if all elements of x are also elements of y.
//
//
//
//    Example 1:
//
//    Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
//    Output: 4
//    Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
//    Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
//    {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
//    Example 2:
//
//    Input: strs = ["10","0","1"], m = 1, n = 1
//    Output: 2
//    Explanation: The largest subset is {"0", "1"}, so the answer is 2.
//
//
//    Constraints:
//
//    1 <= strs.length <= 600
//    1 <= strs[i].length <= 100
//    strs[i] consists only of digits '0' and '1'.
//    1 <= m, n <= 100
