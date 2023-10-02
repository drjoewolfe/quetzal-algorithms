package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {
    class Solution {
        public boolean winnerOfGame(String colors) {
            if(colors == null || colors.length() < 3) {
                return false;
            }

            int n = colors.length();
            int aliceMoves = 0;
            int bobMoves = 0;

            for(int i = 1; i < n - 1; i++) {
                char pc = colors.charAt(i - 1);
                char c = colors.charAt(i);
                char nc = colors.charAt(i + 1);

                if(pc == c && c == nc) {
                    if(c == 'A') {
                        aliceMoves++;
                    } else {
                        bobMoves++;
                    }
                }
            }

            return aliceMoves > bobMoves;
        }
    }

    class Solution_Correct_1 {
        public boolean winnerOfGame(String colors) {
            if(colors == null || colors.length() < 3) {
                return false;
            }

            boolean aStreak = true;
            int streakLength = 0;

            Stack<Integer> aStack = new Stack<>();
            Stack<Integer> bStack = new Stack<>();

            for(int i = 0; i < colors.length(); i++) {
                char c = colors.charAt(i);

                if(c == 'A') {
                    if(aStreak) {
                        streakLength++;
                    } else {
                        if(streakLength > 2) {
                            bStack.push(streakLength);
                        }

                        aStreak = true;
                        streakLength = 1;
                    }
                } else {
                    if(!aStreak) {
                        streakLength++;
                    } else {
                        if(streakLength > 2) {
                            aStack.push(streakLength);
                        }

                        aStreak = false;
                        streakLength = 1;
                    }
                }
            }

            if(streakLength > 2) {
                if(aStreak) {
                    aStack.push(streakLength);
                } else {
                    bStack.push(streakLength);
                }
            }

            return play(aStack, bStack, true);
        }

        private boolean play(Stack<Integer> aStack, Stack<Integer> bStack, boolean aliceTurn) {
            if(aStack.isEmpty() && aliceTurn) {
                // Alice can't win
                return false;
            } else if(bStack.isEmpty() && !aliceTurn) {
                // Bob can't win
                return true;
            }

            if(aliceTurn) {
                var streak = aStack.pop();
                streak--;
                if(streak > 2) {
                    aStack.push(streak);
                }

                return play(aStack,bStack, !aliceTurn);
            } else {
                var streak = bStack.pop();
                streak--;
                if(streak > 2) {
                    bStack.push(streak);
                }

                return play(aStack,bStack, !aliceTurn);
            }
        }
    }
}

//    2038. Remove Colored Pieces if Both Neighbors are the Same Color
//    Medium
//    There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are given a string colors of length n where colors[i] is the color of the ith piece.
//
//    Alice and Bob are playing a game where they take alternating turns removing pieces from the line. In this game, Alice moves first.
//
//    Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'. She is not allowed to remove pieces that are colored 'B'.
//    Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'. He is not allowed to remove pieces that are colored 'A'.
//    Alice and Bob cannot remove pieces from the edge of the line.
//    If a player cannot make a move on their turn, that player loses and the other player wins.
//    Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.
//
//
//
//    Example 1:
//
//    Input: colors = "AAABABB"
//    Output: true
//    Explanation:
//    AAABABB -> AABABB
//    Alice moves first.
//    She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.
//
//    Now it's Bob's turn.
//    Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
//    Thus, Alice wins, so return true.
//    Example 2:
//
//    Input: colors = "AA"
//    Output: false
//    Explanation:
//    Alice has her turn first.
//    There are only two 'A's and both are on the edge of the line, so she cannot move on her turn.
//    Thus, Bob wins, so return false.
//    Example 3:
//
//    Input: colors = "ABBBBBBBAAA"
//    Output: false
//    Explanation:
//    ABBBBBBBAAA -> ABBBBBBBAA
//    Alice moves first.
//    Her only option is to remove the second to last 'A' from the right.
//
//    ABBBBBBBAA -> ABBBBBBAA
//    Next is Bob's turn.
//    He has many options for which 'B' piece to remove. He can pick any.
//
//    On Alice's second turn, she has no more pieces that she can remove.
//    Thus, Bob wins, so return false.
//
//
//    Constraints:
//
//    1 <= colors.length <= 105
//    colors consists of only the letters 'A' and 'B'