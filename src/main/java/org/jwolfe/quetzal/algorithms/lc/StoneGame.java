package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class StoneGame {
    class Solution {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0) {
                return false;
            }

            int n = piles.length;
            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++) {
                dp[i][i] = piles[i];
            }

            for(int l = 2; l <= n; l++) {
                for(int left = 0; left <= n - l; left++) {
                    int right = left + l - 1;
                    dp[left][right] = Math.max(
                            piles[left] - dp[left + 1][right],
                            piles[right] - dp[left][right - 1]
                    );
                }
            }

            return dp[0][n - 1] > 0;
        }
    }

    class Solution_Memoization {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0 || piles.length % 2 != 0) {
                return false;
            }

            int n = piles.length;
            Integer[][] memo = new Integer[n][n];
            for(Integer[] row : memo) {
                Arrays.fill(row, null);
            }

            int stones = stoneGame(piles, 0, n - 1, memo);
            return stones > 0;
        }

        private int stoneGame(int[] piles, int left, int right, Integer[][] memo) {
            if(left > right) {
                return 0;
            }

            if(memo[left][right] != null) {
                return memo[left][right];
            }

            memo[left][right] = Math.max(
                    piles[left] - stoneGame(piles, left + 1, right, memo),
                    piles[right] - stoneGame(piles, left, right - 1, memo));

            return memo[left][right];
        }
    }

    class Solution_Recursive {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0 || piles.length % 2 != 0) {
                return false;
            }

            int stones = stoneGame(piles, 0, piles.length - 1);
            return stones > 0;
        }

        private int stoneGame(int[] piles, int left, int right) {
            if(left > right) {
                return 0;
            }

            return Math.max(
                    piles[left] - stoneGame(piles, left + 1, right),
                    piles[right] - stoneGame(piles, left, right - 1));
        }
    }

    class Solution_Recursive_Classic {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0 || piles.length % 2 != 0) {
                return false;
            }

            return canPlayer1WinStoneGame(piles, 0, piles.length - 1, 0, 0, true);
        }

        private boolean canPlayer1WinStoneGame(int[] piles, int left, int right, int stones1, int stones2, boolean player1Turn) {
            if(left >= right) {
                return stones1 > stones2;
            }

            if(player1Turn) {
                return
                        canPlayer1WinStoneGame(piles, left + 1, right, stones1 + piles[left], stones2, false)
                                || canPlayer1WinStoneGame(piles, left, right - 1, stones1 + piles[right], stones2, false);

            } else {
                return
                        canPlayer1WinStoneGame(piles, left + 1, right, stones1, stones2 + piles[left], true)
                                || canPlayer1WinStoneGame(piles, left, right - 1, stones1, stones2 + piles[right], true);
            }
        }
    }

    class Solution_Correct_2 {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0) {
                return false;
            }

            int n = piles.length;
            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++) {
                dp[i][i] = piles[i];
            }

            for(int l = 2; l <= n; l++) {
                for(int left = 0; left <= n - l; left++) {
                    int right = left + l - 1;
                    dp[left][right] = Math.max(
                            piles[left] - dp[left + 1][right],
                            piles[right] - dp[left][right - 1]
                    );
                }
            }

            return dp[0][n - 1] > 0;
        }

        private void print(int[][] dp) {
            for(int[] row : dp) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memoized_2 {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0) {
                return false;
            }

            int n = piles.length;
            return stoneGame(piles, 0, n - 1, new Integer[n][n]) > 0;
        }

        private int stoneGame(int[] piles, int left, int right, Integer[][] memo) {
            if(left > right) {
                return 0;
            }

            if(memo[left][right] != null) {
                return memo[left][right];
            }

            memo[left][right] = Math.max(
                    piles[left] - stoneGame(piles, left + 1, right, memo),
                    piles[right] - stoneGame(piles, left, right - 1, memo)
            );

            return memo[left][right];
        }
    }

    class Solution_Recursive_2 {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0) {
                return false;
            }

            int n = piles.length;
            return stoneGame(piles, 0, n - 1) > 0;
        }

        private int stoneGame(int[] piles, int left, int right) {
            if(left > right) {
                return 0;
            }

            return Math.max(
                    piles[left] - stoneGame(piles, left + 1, right),
                    piles[right] - stoneGame(piles, left, right - 1)
            );
        }
    }


    class Solution_Recursive_3 {
        public boolean stoneGame(int[] piles) {
            if(piles == null || piles.length == 0) {
                return false;
            }

            int n = piles.length;
            if(n == 1) {
                return true;
            }

            return stoneGameP1Win(piles, 0, n - 1, 0, 0, true);
        }

        private boolean stoneGameP1Win(int[] piles, int left, int right, int c1, int c2, boolean p1) {
            if(left > right) {
                return (c1 - c2) > 0;
            }

            // Try left
            if(stoneGameP1Win(piles, left + 1, right, p1 ? c1 + piles[left] : c1, !p1 ? c2 + piles[left] : c2, !p1)) {
                return true;
            }

            // Try right
            return stoneGameP1Win(piles, left, right - 1, p1 ? c1 + piles[right] : c1, !p1 ? c2 + piles[right] : c2, !p1);
        }
    }

// [5,3,4,5]
}

//    877. Stone Game
//    Medium
//    Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
//
//    The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
//
//    Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
//
//    Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
//
//
//
//    Example 1:
//
//    Input: piles = [5,3,4,5]
//    Output: true
//    Explanation:
//    Alex starts first, and can only take the first 5 or the last 5.
//    Say he takes the first 5, so that the row becomes [3, 4, 5].
//    If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
//    If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
//    This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
//
//
//    Constraints:
//
//    2 <= piles.length <= 500
//    piles.length is even.
//    1 <= piles[i] <= 500
//    sum(piles) is odd.