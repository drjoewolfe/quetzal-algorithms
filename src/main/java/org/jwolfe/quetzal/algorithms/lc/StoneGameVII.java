package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class StoneGameVII {
    class Solution {
        public int stoneGameVII(int[] stones) {
            if(stones == null || stones.length == 0) {
                return 0;
            }

            int n = stones.length;
            int[][] memo = new int[n][n];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            int sum = 0;
            for(int s : stones) {
                sum += s;
            }

            return stoneGameVII(stones, 0, n - 1, memo, sum);
        }

        private int stoneGameVII(int[] stones, int left, int right, int[][] memo, int sum) {
            if(left == right) {
                // Only one stone. Take that & nothing left. Score = 0;
                return 0;
            }

            if(right - left == 1) {
                // 2 items. Optimal play implies take the lower stone & get max score from rest
                return Math.max(stones[left], stones[right]);
            }

            if(memo[left][right] != -1) {
                return memo[left][right];
            }

            // Take Left
            int diffLeft = sum - stones[left] - stoneGameVII(stones, left + 1, right, memo, sum - stones[left]);

            // Take Right
            int diffRight = sum - stones[right] - stoneGameVII(stones, left, right - 1, memo, sum - stones[right]);

            int maxDiff = Math.max(diffLeft, diffRight);
            memo[left][right] = maxDiff;
            return maxDiff;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        int minDifference;

        public int stoneGameVII(int[] stones) {
            if(stones == null || stones.length == 0) {
                return 0;
            }

            int n = stones.length;
            int sum = 0;
            for(int a : stones) {
                sum += a;
            }

            minDifference = Integer.MAX_VALUE;
            stoneGameVII(stones, n, sum, 0, n - 1, true, 0, 0);

            return minDifference;
        }

        private void stoneGameVII(int[] stones, int n, int sum, int left, int right, boolean player1, int player1Score, int player2Score) {
            if(right < left) {
                // Game over
                int difference = player1Score - player2Score;
                minDifference = Math.min(minDifference, difference);

                return;
            }

            // Try Left
            int score = sum - stones[left];
            stoneGameVII(stones, n, score, left + 1, right, !player1, player1Score + (player1 ? score : 0), player2Score + (!player1 ? score : 0));


            // Try Right
            score = sum - stones[right];
            stoneGameVII(stones, n, score, left, right - 1, !player1, player1Score + (player1 ? score : 0), player2Score + (!player1 ? score : 0));
        }
    }
}

//    1690. Stone Game VII
//    Medium
//    Alice and Bob take turns playing a game, with Alice starting first.
//
//    There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row. The winner is the one with the higher score when there are no stones left to remove.
//
//    Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. Alice's goal is to maximize the difference in the score.
//
//    Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and Bob's score if they both play optimally.
//
//
//
//    Example 1:
//
//    Input: stones = [5,3,1,4,2]
//    Output: 6
//    Explanation:
//    - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
//    - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
//    - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
//    - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
//    - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
//    The score difference is 18 - 12 = 6.
//    Example 2:
//
//    Input: stones = [7,90,5,1,100,10,10,2]
//    Output: 122
//
//
//    Constraints:
//
//    n == stones.length
//    2 <= n <= 1000
//    1 <= stones[i] <= 1000