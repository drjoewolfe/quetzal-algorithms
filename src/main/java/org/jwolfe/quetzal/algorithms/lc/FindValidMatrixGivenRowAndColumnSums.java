package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class FindValidMatrixGivenRowAndColumnSums {
    class Solution {
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            if (rowSum == null || rowSum.length == 0 || colSum == null || colSum.length == 0) {
                return new int[0][0];
            }

            int m = rowSum.length;
            int n = colSum.length;

            int[][] matrix = new int[m][n];

            int[] currentRowSum = new int[m];
            int[] currentColSum = new int[n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int rowVal = rowSum[r] - currentRowSum[r];
                    int colVal = colSum[c] - currentColSum[c];

                    int val = Math.min(rowVal, colVal);
                    matrix[r][c] = val;

                    currentRowSum[r] += val;
                    currentColSum[c] += val;
                }
            }

            return matrix;
        }
    }

    class Solution_Correct_1 {
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            if (rowSum == null || rowSum.length == 0 || colSum == null || colSum.length == 0) {
                return new int[0][0];
            }

            int m = rowSum.length;
            int n = colSum.length;

            int[][] matrix = new int[m][n];
            PriorityQueue<Ordinate> rowQueue = new PriorityQueue<>((a, b) -> a.value - b.value);
            PriorityQueue<Ordinate> colQueue = new PriorityQueue<>((a, b) -> a.value - b.value);
            for (int i = 0; i < m; i++) {
                rowQueue.offer(new Ordinate(i, rowSum[i]));
            }

            for (int j = 0; j < n; j++) {
                colQueue.offer(new Ordinate(j, colSum[j]));
            }

            while (!rowQueue.isEmpty() && !colQueue.isEmpty()) {
                Ordinate rowOrdinate = rowQueue.poll();
                Ordinate colOrdinate = colQueue.poll();

                int v = Math.min(rowOrdinate.value, colOrdinate.value);
                matrix[rowOrdinate.index][colOrdinate.index] = v;

                rowOrdinate.value -= v;
                colOrdinate.value -= v;

                if (rowOrdinate.value > 0) {
                    rowQueue.offer(rowOrdinate);
                }

                if (colOrdinate.value > 0) {
                    colQueue.offer(colOrdinate);
                }
            }

            return matrix;
        }

        private class Ordinate {
            int index;
            int value;

            public Ordinate() {

            }

            public Ordinate(int index, int value) {
                this();

                this.index = index;
                this.value = value;
            }
        }
    }
}

//    1605. Find Valid Matrix Given Row and Column Sums
//    Medium
//    You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you do not know the elements of the matrix, but you do know the sums of each row and column.
//
//    Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and colSum requirements.
//
//    Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one matrix that fulfills the requirements exists.
//
//
//
//    Example 1:
//
//    Input: rowSum = [3,8], colSum = [4,7]
//    Output: [[3,0],
//    [1,7]]
//    Explanation:
//    0th row: 3 + 0 = 3 == rowSum[0]
//    1st row: 1 + 7 = 8 == rowSum[1]
//    0th column: 3 + 1 = 4 == colSum[0]
//    1st column: 0 + 7 = 7 == colSum[1]
//    The row and column sums match, and all matrix elements are non-negative.
//    Another possible matrix is: [[1,2],
//    [3,5]]
//    Example 2:
//
//    Input: rowSum = [5,7,10], colSum = [8,6,8]
//    Output: [[0,5,0],
//    [6,1,0],
//    [2,0,8]]
//
//
//    Constraints:
//
//    1 <= rowSum.length, colSum.length <= 500
//    0 <= rowSum[i], colSum[i] <= 108
//    sum(rowSum) == sum(colSum)