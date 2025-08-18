package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame {
    class Solution {
        public boolean judgePoint24(int[] cards) {
            if (cards == null || cards.length != 4) {
                return false;
            }

            List<Double> list = new ArrayList<>();
            for (int card : cards) {
                list.add((double) card);
            }

            return judgePoint24(list);
        }

        private boolean judgePoint24(List<Double> list) {
            if (list.size() == 1) {
                double val = list.get(0);
                return Math.abs(val - 24) < 1e-6;
            }

            int n = list.size();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    List<Double> nextList = new ArrayList<>();
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j) {
                            nextList.add(list.get(k));
                        }
                    }

                    double a = list.get(i);
                    double b = list.get(j);

                    List<Double> results = new ArrayList<>();
                    results.add(a + b);
                    results.add(a - b);
                    results.add(b - a);
                    results.add(a * b);
                    if (Math.abs(b) > 1e-6) {
                        results.add(a / b);
                    }

                    if (Math.abs(a) > 1e-6) {
                        results.add(b / a);
                    }

                    for (double result : results) {
                        nextList.add(result);
                        if (judgePoint24(nextList)) {
                            return true;
                        }

                        nextList.remove(nextList.size() - 1);
                    }
                }
            }

            return false;
        }
    }
}

//    679. 24 Game
//    Hard
//    You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
//
//    You are restricted with the following rules:
//
//    The division operator '/' represents real division, not integer division.
//    For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
//    Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
//    For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
//    You cannot concatenate numbers together
//    For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
//    Return true if you can get such expression that evaluates to 24, and false otherwise.
//
//
//
//    Example 1:
//
//    Input: cards = [4,1,8,7]
//    Output: true
//    Explanation: (8-4) * (7-1) = 24
//    Example 2:
//
//    Input: cards = [1,2,1,2]
//    Output: false
//
//
//    Constraints:
//
//    cards.length == 4
//    1 <= cards[i] <= 9