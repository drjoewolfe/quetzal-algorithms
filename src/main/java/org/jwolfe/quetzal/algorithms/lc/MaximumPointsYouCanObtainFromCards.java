package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumPointsYouCanObtainFromCards {
    class Solution {
        public int maxScore(int[] cardPoints, int k) {
            if(cardPoints == null || cardPoints.length < k) {
                return 0;
            }

            int n = cardPoints.length;

            int totalScore = 0;
            for(int p : cardPoints) {
                totalScore += p;
            }

            if(n == k) {
                int score = 0;
                for(int p : cardPoints) {
                    score += p;
                }

                return score;
            }


            // Find min array sum within card points for length ignoreLength;
            int ignoreLength = n - k;

            int sum = cardPoints[0];
            int left = 0;
            int right = 0;

            while(right < n && right - left + 1 < ignoreLength) {
                right++;
                sum += cardPoints[right];
            }

            int minSumForIgnoredLength = sum;
            while(right < n - 1) {
                right++;

                sum += cardPoints[right];
                if(right - left + 1 > ignoreLength) {
                    sum -= cardPoints[left];
                    left++;
                }

                minSumForIgnoredLength = Math.min(minSumForIgnoredLength, sum);
            }

            return totalScore - minSumForIgnoredLength;
        }
    }

    class Solution_Memoized {
        public int maxScore(int[] cardPoints, int k) {
            if(cardPoints == null || cardPoints.length == 0 || k < 1) {
                return 0;
            }

            int n = cardPoints.length;
            if(k >= n) {
                int score = 0;
                for(int p : cardPoints) {
                    score += p;
                }

                return score;
            }

            int[][] memo = new int[n][n];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return maxScore(cardPoints, k, 0, n - 1, memo);
        }

        private int maxScore(int[] cardPoints, int k, int left, int right, int[][] memo) {
            if(k == 0 || left > right) {
                return 0;
            }

            if(memo[left][right] != -1) {
                return memo[left][right];
            }

            // Take left
            int score = cardPoints[left] + maxScore(cardPoints, k - 1, left + 1, right, memo);

            // Take right
            score = Math.max(score,
                    cardPoints[right] + maxScore(cardPoints, k - 1, left, right - 1, memo));

            memo[left][right] = score;
            return score;
        }
    }

// [1,2,3,4,5,6,1]
// 3

// [61,5,22,64,14,16,93,28,7,99,8,17,2,83,9,88,16,97,33,50,78,5,78,100,100,44,43,73,14,31,1,72,93,42,48,49,3,26,59,8,20,39,40,67,27]
// 34
}

//    1423. Maximum Points You Can Obtain from Cards
//    Medium
//    There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
//
//    In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
//
//    Your score is the sum of the points of the cards you have taken.
//
//    Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
//
//
//
//    Example 1:
//
//    Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//    Output: 12
//    Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
//    Example 2:
//
//    Input: cardPoints = [2,2,2], k = 2
//    Output: 4
//    Explanation: Regardless of which two cards you take, your score will always be 4.
//    Example 3:
//
//    Input: cardPoints = [9,7,7,9,7,7,9], k = 7
//    Output: 55
//    Explanation: You have to take all the cards. Your score is the sum of points of all cards.
//    Example 4:
//
//    Input: cardPoints = [1,1000,1], k = 1
//    Output: 1
//    Explanation: You cannot take the card in the middle. Your best score is 1.
//    Example 5:
//
//    Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
//    Output: 202
//
//
//    Constraints:
//
//    1 <= cardPoints.length <= 10^5
//    1 <= cardPoints[i] <= 10^4
//    1 <= k <= cardPoints.length