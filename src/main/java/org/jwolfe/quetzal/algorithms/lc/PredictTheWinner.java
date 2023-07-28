package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class PredictTheWinner {
    class Solution {
        public boolean PredictTheWinner(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;
            if(n == 1) {
                return true;
            }

            int[][] memo = new int[n][n];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return maxDiff(nums, 0, n - 1, memo) >= 0;
        }

        private int maxDiff(int[] nums, int left, int right, int[][] memo) {
            if(left == right) {
                return nums[left];
            }

            if(memo[left][right] != -1) {
                return memo[left][right];
            }

            int scoreFromLeft = nums[left] - maxDiff(nums, left + 1, right, memo);
            int scoreFromRight = nums[right] - maxDiff(nums, left, right - 1, memo);

            memo[left][right] = Math.max(scoreFromLeft, scoreFromRight);
            return memo[left][right];
        }
    }

    class Solution_Recursive {
        public boolean PredictTheWinner(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;
            if(n == 1) {
                return true;
            }

            return maxDiff(nums, 0, n - 1) >= 0;
        }

        private int maxDiff(int[] nums, int left, int right) {
            if(left == right) {
                return nums[left];
            }

            int scoreFromLeft = nums[left] - maxDiff(nums, left + 1, right);
            int scoreFromRight = nums[right] - maxDiff(nums, left, right - 1);

            return Math.max(scoreFromLeft, scoreFromRight);
        }
    }

    class Solution_Correct_1 {
        public boolean PredictTheWinner(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;
            if(n == 1){
                return true;
            }

            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++) {
                dp[i][i] = nums[i];
            }

            for(int l = 2; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                            nums[j] - dp[i][j - 1]);
                }
            }

            return dp[0][n - 1] >= 0;
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
        public boolean PredictTheWinner(int[] nums) {
            return canPlayer1Win(nums, 0, nums.length - 1, true, 0, 0);
        }

        private boolean canPlayer1Win(int[] nums, int left, int right, boolean turn1, int score1, int score2) {
            if(left > right) {
                System.out.println(left + ", " + right + ", " + score1 + ", " + score2);
                return score1 >= score2;
            }

            if(turn1) {
                return canPlayer1Win(nums, left + 1, right, false, score1 + nums[left], score2)
                        || canPlayer1Win(nums, left, right - 1, false, score1 + nums[right], score2);
            } else {
                return canPlayer1Win(nums, left + 1, right, true, score1, score2 + nums[left])
                        || canPlayer1Win(nums, left, right - 1, false, score1, score2 + nums[right]);
            }
        }
    }

// [1,5,2]
// [1,1]
}

//    486. Predict the Winner
//    Medium
//    You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
//
//    Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.
//
//    Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.
//
//
//
//    Example 1:
//
//    Input: nums = [1,5,2]
//    Output: false
//    Explanation: Initially, player 1 can choose between 1 and 2.
//    If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
//    So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
//    Hence, player 1 will never be the winner and you need to return false.
//    Example 2:
//
//    Input: nums = [1,5,233,7]
//    Output: true
//    Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
//    Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
//
//
//    Constraints:
//
//    1 <= nums.length <= 20
//    0 <= nums[i] <= 107