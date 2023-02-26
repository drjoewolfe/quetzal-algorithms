package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class EditDistance {
    class Solution {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            int m = word1.length();
            int n = word2.length();

            int[][] dp = new int[m + 1][n + 1];

            for(int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }

            for(int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            for(int i = 1; i <= m; i++) {
                char c1 = word1.charAt(i - 1);

                for(int j = 1; j <= n; j++) {
                    char c2 = word2.charAt(j - 1);

                    if(c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                Math.min(dp[i - 1][j],
                                        dp[i][j - 1]));
                    }
                }
            }

            return dp[m][n];
        }
    }

    class Solution_Correct_1 {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            int m = word1.length();
            int n = word2.length();

            int[][] dp = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++) {
                dp[i][0] = i;
            }

            for(int j = 1; j <= n; j++) {
                dp[0][j] = j;
            }

            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                Math.min(
                                        dp[i][j - 1],
                                        dp[i - 1][j]
                                ));
                    }
                }
            }

            return dp[m][n];
        }

        private void print(int[][] matrix) {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memoized {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            int m = word1.length();
            int n = word2.length();

            int[][] memo = new int[m][n];
            for(int i = 0; i < m; i++) {
                Arrays.fill(memo[i], -1);
            }

            return editDistance(word1, word2, word1.length() - 1, word2.length() - 1, memo);
        }

        private int editDistance(String word1, String word2, int index1, int index2, int[][] memo) {
            if(index1 < 0 && index2 < 0) {
                return 0;
            }

            if(index1 < 0) {
                return index2 + 1;
            }

            if(index2 < 0) {
                return index1 + 1;
            }

            if(memo[index1][index2] != -1) {
                return memo[index1][index2];
            }

            if(word1.charAt(index1) == word2.charAt(index2)) {
                memo[index1][index2] = editDistance(word1, word2, index1 - 1, index2 - 1, memo);
            } else {
                memo[index1][index2] = 1 + Math.min(editDistance(word1, word2, index1 - 1, index2 - 1, memo),
                        Math.min(
                                editDistance(word1, word2, index1 - 1, index2, memo),
                                editDistance(word1, word2, index1, index2 - 1, memo)
                        )
                );
            }

            return memo[index1][index2];
        }
    }

    class Solution_Brute {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            return editDistance(word1, word2, word1.length() - 1, word2.length() - 1);
        }

        private int editDistance(String word1, String word2, int index1, int index2) {
            if(index1 < 0 && index2 < 0) {
                return 0;
            }

            if(index1 < 0) {
                return index2 + 1;
            }

            if(index2 < 0) {
                return index1 + 1;
            }

            if(word1.charAt(index1) == word2.charAt(index2)) {
                return editDistance(word1, word2, index1 - 1, index2 - 1);
            } else {
                return 1 + Math.min(editDistance(word1, word2, index1 - 1, index2 - 1),
                        Math.min(
                                editDistance(word1, word2, index1 - 1, index2),
                                editDistance(word1, word2, index1, index2 - 1)
                        )
                );
            }

        }
    }
}

//    72. Edit Distance
//    Hard
//    Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
//
//    You have the following three operations permitted on a word:
//
//    Insert a character
//    Delete a character
//    Replace a character
//
//
//    Example 1:
//
//    Input: word1 = "horse", word2 = "ros"
//    Output: 3
//    Explanation:
//    horse -> rorse (replace 'h' with 'r')
//    rorse -> rose (remove 'r')
//    rose -> ros (remove 'e')
//    Example 2:
//
//    Input: word1 = "intention", word2 = "execution"
//    Output: 5
//    Explanation:
//    intention -> inention (remove 't')
//    inention -> enention (replace 'i' with 'e')
//    enention -> exention (replace 'n' with 'x')
//    exention -> exection (replace 'n' with 'c')
//    exection -> execution (insert 'u')
//
//
//    Constraints:
//
//    0 <= word1.length, word2.length <= 500
//    word1 and word2 consist of lowercase English letters.
