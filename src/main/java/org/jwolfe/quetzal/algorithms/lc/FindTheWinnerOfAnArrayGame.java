package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;

public class FindTheWinnerOfAnArrayGame {
    class Solution {
        public int getWinner(int[] arr, int k) {
            if(arr == null || arr.length < 2 || k < 1) {
                return -1;
            }

            int winner = arr[0];
            int streak = 0;

            for(int i = 1; i < arr.length; i++) {
                int challenger = arr[i];

                if(challenger > winner) {
                    winner = challenger;
                    streak = 1;
                } else {
                    streak++;
                }

                if(streak == k) {
                    return winner;
                }
            }

            return winner;
        }
    }

    class Solution_Correct_1 {
        public int getWinner(int[] arr, int k) {
            if(arr == null || arr.length < 2 || k < 1) {
                return -1;
            }

            int winner = arr[0];
            int streak = 0;

            for(int i = 1; i < arr.length; i++) {
                int challenger = arr[i];

                if(challenger > winner) {
                    winner = challenger;
                    streak = 1;
                } else {
                    streak++;
                }

                if(streak == k) {
                    return winner;
                }
            }

            return winner;
        }
    }

    class Solution_Classic {
        public int getWinner(int[] arr, int k) {
            if(arr == null || arr.length < 2 || k < 1) {
                return -1;
            }

            LinkedList<Integer> list = new LinkedList<>();
            for(int a : arr) {
                list.add(a);
            }

            int prevWinner = -1;
            int winner = -1;
            int streak = 0;

            while(streak < k) {
                int a = list.get(0);
                int b = list.get(1);

                if(a > b) {
                    winner = a;
                    list.remove(1);
                    list.add(b);
                } else {
                    winner = b;
                    list.remove(0);
                    list.add(a);
                }

                if(winner == prevWinner) {
                    streak++;
                } else {
                    streak = 1;
                    prevWinner = winner;
                }
            }

            return winner;
        }
    }

// [2,1,3,5,4,6,7]
// 2

}

//    1535. Find the Winner of an Array Game
//    Medium
//    Given an integer array arr of distinct integers and an integer k.
//
//    A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the game, we compare arr[0] with arr[1], the larger integer wins and remains at position 0, and the smaller integer moves to the end of the array. The game ends when an integer wins k consecutive rounds.
//
//    Return the integer which will win the game.
//
//    It is guaranteed that there will be a winner of the game.
//
//
//
//    Example 1:
//
//    Input: arr = [2,1,3,5,4,6,7], k = 2
//    Output: 5
//    Explanation: Let's see the rounds of the game:
//    Round |       arr       | winner | win_count
//    1   | [2,1,3,5,4,6,7] | 2      | 1
//    2   | [2,3,5,4,6,7,1] | 3      | 1
//    3   | [3,5,4,6,7,1,2] | 5      | 1
//    4   | [5,4,6,7,1,2,3] | 5      | 2
//    So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
//    Example 2:
//
//    Input: arr = [3,2,1], k = 10
//    Output: 3
//    Explanation: 3 will win the first 10 rounds consecutively.
//
//
//    Constraints:
//
//    2 <= arr.length <= 105
//    1 <= arr[i] <= 106
//    arr contains distinct integers.
//    1 <= k <= 109