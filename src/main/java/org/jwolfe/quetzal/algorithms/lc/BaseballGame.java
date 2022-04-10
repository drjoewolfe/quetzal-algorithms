package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BaseballGame {
    class Solution {
        public int calPoints(String[] ops) {
            if (ops == null || ops.length == 0) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            for (String operation : ops) {
                int n = list.size();
                switch (operation) {
                    case "+":
                        list.add(list.get(n - 1) + list.get(n - 2));
                        break;
                    case "D":
                        list.add(2 * list.get(n - 1));
                        break;
                    case "C":
                        list.remove(n - 1);
                        break;
                    default:
                        list.add(Integer.parseInt(operation));
                }
            }

            int totalScore = 0;
            for (int score : list) {
                totalScore += score;
            }

            return totalScore;
        }
    }

    class Solution_Correct_1 {
        public int calPoints(String[] ops) {
            if (ops == null || ops.length == 0) {
                return 0;
            }

            int score = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < ops.length; i++) {
                String s = ops[i];
                int points = 0;

                if (s.equals("C")) {
                    if (stack.isEmpty()) {
                        return 0;
                    }

                    points = (-1) * stack.pop();
                } else if (s.equals("D")) {
                    if (stack.isEmpty()) {
                        return 0;
                    }

                    int a = stack.peek();
                    points = a * 2;
                    stack.push(points);

                } else if (s.equals("+")) {
                    if (stack.size() < 2) {
                        return 0;
                    }

                    int a = stack.pop();
                    int b = stack.peek();
                    points = a + b;

                    stack.push(a);
                    stack.push(points);

                } else {
                    points = Integer.valueOf(s);
                    stack.push(points);
                }

                score += points;
            }

            return score;
        }
    }

// ["5","2","C","D","+"]
}

//    682. Baseball Game
//    Easy
//    You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.
//
//    At the beginning of the game, you start with an empty record. You are given a list of strings ops, where ops[i] is the ith operation you must apply to the record and is one of the following:
//
//    An integer x - Record a new score of x.
//    "+" - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
//    "D" - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
//    "C" - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
//    Return the sum of all the scores on the record.
//
//
//
//    Example 1:
//
//    Input: ops = ["5","2","C","D","+"]
//    Output: 30
//    Explanation:
//    "5" - Add 5 to the record, record is now [5].
//    "2" - Add 2 to the record, record is now [5, 2].
//    "C" - Invalidate and remove the previous score, record is now [5].
//    "D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
//    "+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
//    The total sum is 5 + 10 + 15 = 30.
//    Example 2:
//
//    Input: ops = ["5","-2","4","C","D","9","+","+"]
//    Output: 27
//    Explanation:
//    "5" - Add 5 to the record, record is now [5].
//    "-2" - Add -2 to the record, record is now [5, -2].
//    "4" - Add 4 to the record, record is now [5, -2, 4].
//    "C" - Invalidate and remove the previous score, record is now [5, -2].
//    "D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
//    "9" - Add 9 to the record, record is now [5, -2, -4, 9].
//    "+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
//    "+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
//    The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
//    Example 3:
//
//    Input: ops = ["1"]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= ops.length <= 1000
//    ops[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 104, 3 * 104].
//    For operation "+", there will always be at least two previous scores on the record.
//    For operations "C" and "D", there will always be at least one previous score on the record.