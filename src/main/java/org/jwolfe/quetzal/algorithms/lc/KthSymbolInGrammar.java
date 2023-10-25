package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class KthSymbolInGrammar {
    class Solution {
        public int kthGrammar(int N, int K) {
            if(N < 1 || K < 1) {
                return 0;
            }

            return dfs(N, K, 0);
        }

        private int dfs(int n, int k, int rootVal) {
            if(n == 1) {
                return rootVal;
            }

            int leafCount = (int) Math.pow(2, n - 1);
            if(k > leafCount / 2) {
                // right half
                int nextRootVal = rootVal == 0 ? 1: 0;
                return dfs(n - 1, k - leafCount / 2, nextRootVal);
            } else {
                // left half
                int nextRootVal = rootVal == 0 ? 0: 1;
                return dfs(n - 1, k, nextRootVal);
            }
        }
    }

    class Solution_MLE {
        public int kthGrammar(int N, int K) {
            if(N < 1 || K < 1) {
                return 0;
            }

            List<Integer> row = List.of(0);
            List<Integer> prev = null;
            for(int i = 1; i < N; i++) {
                prev = row;
                row = new ArrayList<>();

                for(int j = 0; j < prev.size(); j++) {
                    if(prev.get(j) == 0) {
                        row.add(0);
                        row.add(1);
                    } else {
                        row.add(1);
                        row.add(0);
                    }
                }
            }

            if(K > row.size()) {
                return 0;
            }

            return row.get(K - 1);
        }
    }

    class Solution_Brute {
        public int kthGrammar(int N, int K) {
            if(N < 1 || K < 1) {
                return 0;
            }

            List<List<Integer>> rows = new ArrayList<>();
            rows.add(List.of(0));
            for(int i = 1; i < N; i++) {
                var prevRow = rows.get(i - 1);
                List<Integer> row = new ArrayList<>();
                rows.add(row);

                for(int j = 0; j < prevRow.size(); j++) {
                    if(prevRow.get(j) == 0) {
                        row.add(0);
                        row.add(1);
                    } else {
                        row.add(1);
                        row.add(0);
                    }
                }
            }

            var lastRow = rows.get(N - 1);
            if(K > lastRow.size()) {
                return 0;
            }

            return lastRow.get(K - 1);
        }
    }

// 1
// 1
}

//    779. K-th Symbol in Grammar
//    Medium
//    We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
//
//    For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
//    Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
//
//
//
//    Example 1:
//
//    Input: n = 1, k = 1
//    Output: 0
//    Explanation: row 1: 0
//    Example 2:
//
//    Input: n = 2, k = 1
//    Output: 0
//    Explanation:
//    row 1: 0
//    row 2: 01
//    Example 3:
//
//    Input: n = 2, k = 2
//    Output: 1
//    Explanation:
//    row 1: 0
//    row 2: 01
//
//
//    Constraints:
//
//    1 <= n <= 30
//    1 <= k <= 2n - 1