package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfEquivalentDominoPairs {
    class Solution {
        public int numEquivDominoPairs(int[][] dominoes) {
            if (dominoes == null || dominoes.length < 2) {
                return 0;
            }

            int[] configurations = new int[100];
            for (int i = 0; i < dominoes.length; i++) {
                int[] domino = dominoes[i];
                int x = domino[0];
                int y = domino[1];

                int index = 0;
                if (x > y) {
                    index = (x * 10) + y;
                } else {
                    index = (y * 10) + x;
                }

                configurations[index]++;
            }

            int pairCount = 0;
            for (int count : configurations) {
                if (count > 1) {
                    pairCount += (count * (count - 1)) / 2;
                }
            }

            return pairCount;
        }
    }

    class Solution_Correct_1 {
        public int numEquivDominoPairs(int[][] dominoes) {
            if (dominoes == null || dominoes.length < 2) {
                return 0;
            }

            // 11 - 99 => 88 configurations possible
            int[] configurations = new int[100];
            for (int i = 0; i < dominoes.length; i++) {
                int[] pair = dominoes[i];

                int x = pair[0];
                int y = pair[1];

                int index = 0;
                if (x > y) {
                    index = x * 10 + y;
                } else {
                    index = y * 10 + x;
                }

                configurations[index]++;
            }

            int pairCount = 0;
            for (int count : configurations) {
                if (count > 1) {
                    pairCount += (count * (count - 1)) / 2;
                }
            }

            return pairCount;
        }
    }

    class Solution_Brute {
        public int numEquivDominoPairs(int[][] dominoes) {
            if (dominoes == null || dominoes.length < 2) {
                return 0;
            }

            int pairCount = 0;
            for (int i = 0; i < dominoes.length - 1; i++) {
                int[] a = dominoes[i];
                for (int j = i + 1; j < dominoes.length; j++) {
                    int[] b = dominoes[j];

                    if ((a[0] == b[0] && a[1] == b[1]) || (a[0] == b[1] && a[1] == b[0])) {
                        pairCount++;
                    }
                }
            }

            return pairCount;
        }
    }
}

//    1128. Number of Equivalent Domino Pairs
//    Easy
//    Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
//
//    Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
//
//
//
//    Example 1:
//
//    Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
//    Output: 1
//    Example 2:
//
//    Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
//    Output: 3
//
//
//    Constraints:
//
//    1 <= dominoes.length <= 4 * 104
//    dominoes[i].length == 2
//    1 <= dominoes[i][j] <= 9