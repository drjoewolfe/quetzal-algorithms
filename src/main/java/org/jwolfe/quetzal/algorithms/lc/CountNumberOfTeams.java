package org.jwolfe.quetzal.algorithms.lc;

public class CountNumberOfTeams {
    class Solution {
        public int numTeams(int[] rating) {
            if (rating == null || rating.length < 3) {
                return 0;
            }

            int count = 0;
            int n = rating.length;

            Integer[][] increasingMemo = new Integer[n][4];
            Integer[][] decreasingMemo = new Integer[n][4];

            for (int i = 0; i < n - 2; i++) {
                count += countIncreasing(rating, n, i, 1, increasingMemo);
                count += countDecreasing(rating, n, i, 1, decreasingMemo);
            }

            return count;
        }

        private int countIncreasing(int[] rating, int n, int i, int size, Integer[][] memo) {
            if (i == n) {
                return 0;
            }

            if (size == 3) {
                return 1;
            }

            if (memo[i][size] != null) {
                return memo[i][size];
            }

            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (rating[j] > rating[i]) {
                    count += countIncreasing(rating, n, j, size + 1, memo);
                }
            }

            memo[i][size] = count;
            return count;
        }

        private int countDecreasing(int[] rating, int n, int i, int size, Integer[][] memo) {
            if (i == n) {
                return 0;
            }

            if (size == 3) {
                return 1;
            }

            if (memo[i][size] != null) {
                return memo[i][size];
            }

            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (rating[j] < rating[i]) {
                    count += countDecreasing(rating, n, j, size + 1, memo);
                }
            }

            memo[i][size] = count;
            return count;
        }
    }

    class Solution_Correct_2 {
        public int numTeams(int[] rating) {
            if (rating == null || rating.length < 3) {
                return 0;
            }

            int count = 0;
            int n = rating.length;
            for (int i = 0; i < n - 2; i++) {
                count += countIncreasing(rating, n, i, 1);
                count += countDecreasing(rating, n, i, 1);
            }

            return count;
        }

        private int countIncreasing(int[] rating, int n, int i, int size) {
            if (i == n) {
                return 0;
            }

            if (size == 3) {
                return 1;
            }

            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (rating[j] > rating[i]) {
                    count += countIncreasing(rating, n, j, size + 1);
                }
            }

            return count;
        }

        private int countDecreasing(int[] rating, int n, int i, int size) {
            if (i == n) {
                return 0;
            }

            if (size == 3) {
                return 1;
            }

            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (rating[j] < rating[i]) {
                    count += countDecreasing(rating, n, j, size + 1);
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int numTeams(int[] rating) {
            if (rating == null || rating.length < 3) {
                return 0;
            }

            int count = 0;
            int n = rating.length;
            for (int i = 0; i < n - 2; i++) {
                int a = rating[i];

                for (int j = i + 1; j < n - 1; j++) {
                    int b = rating[j];

                    for (int k = j + 1; k < n; k++) {
                        int c = rating[k];

                        if ((a > b && b > c)
                                || (a < b && b < c)) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }
    }
}

//    1395. Count Number of Teams
//    Medium
//    There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
//
//    You have to form a team of 3 soldiers amongst them under the following rules:
//
//    Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
//    A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
//    Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
//
//
//
//    Example 1:
//
//    Input: rating = [2,5,3,4,1]
//    Output: 3
//    Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
//    Example 2:
//
//    Input: rating = [2,1,3]
//    Output: 0
//    Explanation: We can't form any team given the conditions.
//    Example 3:
//
//    Input: rating = [1,2,3,4]
//    Output: 4
//
//
//    Constraints:
//
//    n == rating.length
//    3 <= n <= 1000
//    1 <= rating[i] <= 105
//    All the integers in rating are unique.