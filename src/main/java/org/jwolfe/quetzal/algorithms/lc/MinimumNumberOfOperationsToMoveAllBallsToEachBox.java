package org.jwolfe.quetzal.algorithms.lc;

public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    class Solution {
        public int[] minOperations(String boxes) {
            if (boxes == null || boxes.length() == 0) {
                return new int[0];
            }

            int n = boxes.length();
            int[] results = new int[n];

            int ballsToLeft = 0;
            int movesToLeft = 0;

            int ballsToRight = 0;
            int movesToRight = 0;

            for (int i = 0; i < n; i++) {
                results[i] += movesToLeft;
                int leftVal = boxes.charAt(i) - '0';
                ballsToLeft += leftVal;
                movesToLeft += ballsToLeft;

                int j = n - i - 1;
                results[j] += movesToRight;
                int rightVal = boxes.charAt(j) - '0';
                ballsToRight += rightVal;
                movesToRight += ballsToRight;
            }

            return results;
        }
    }
}

//    1769. Minimum Number of Operations to Move All Balls to Each Box
//    Medium
//    You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
//
//    In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
//
//    Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
//
//    Each answer[i] is calculated considering the initial state of the boxes.
//
//
//
//    Example 1:
//
//    Input: boxes = "110"
//    Output: [1,1,3]
//    Explanation: The answer for each box is as follows:
//    1) First box: you will have to move one ball from the second box to the first box in one operation.
//    2) Second box: you will have to move one ball from the first box to the second box in one operation.
//    3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
//    Example 2:
//
//    Input: boxes = "001011"
//    Output: [11,8,5,4,3,4]
//
//
//    Constraints:
//
//    n == boxes.length
//    1 <= n <= 2000
//    boxes[i] is either '0' or '1'.