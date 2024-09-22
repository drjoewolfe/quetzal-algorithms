package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInLexicographicalOrder {
    class Solution {
        public int findKthNumber(int n, int k) {
            if (n < 1 || k < 1 || k > n) {
                return -1;
            }

            int curr = 1;
            k--;

            while (k > 0) {
                int next = curr + 1;
                int count = getElementCount(curr, next, n);

                if (count <= k) {
                    curr = next;
                    k -= count;
                } else {
                    k--;
                    curr *= 10;
                }
            }

            return curr;
        }

        private int getElementCount(long from, long to, int max) {
            int count = 0;
            while (from <= max) {
                count += Math.min(to, max + 1) - from;

                from *= 10;
                to *= 10;
            }

            return count;
        }
    }

    class Solution_MLE {
        public int findKthNumber(int n, int k) {
            if (n < 1 || k < 1 || k > n) {
                return -1;
            }

            List<Integer> orderedNumbers = new ArrayList<>();
            computeLexicographicalOrdering(n, orderedNumbers);

            int kthNumber = orderedNumbers.get(k - 1);
            return kthNumber;
        }

        private void computeLexicographicalOrdering(int n, List<Integer> orderedNumbers) {
            for (int a = 1; a <= 9; a++) {
                if (a > n) {
                    return;
                }

                orderedNumbers.add(a);
                computeLexicographicalOrderingHelper(a, n, orderedNumbers);
            }
        }

        private void computeLexicographicalOrderingHelper(int a, int n, List<Integer> orderedNumbers) {
            for (int b = 0; b <= 9; b++) {
                int val = a * 10 + b;
                if (val > n) {
                    return;
                }

                orderedNumbers.add(val);
                computeLexicographicalOrderingHelper(val, n, orderedNumbers);
            }
        }
    }

// 13
// 2

// 9885387
// 8786251

// 681692778
// 351251360
}

//    440. K-th Smallest in Lexicographical Order
//    Hard
//    Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
//
//
//
//    Example 1:
//
//    Input: n = 13, k = 2
//    Output: 10
//    Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
//    Example 2:
//
//    Input: n = 1, k = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= k <= n <= 109