package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        if(mat == null || mat.length == 0 || k < 1) {
            return new int[0];
        }

        int m = mat.length;
        int n = mat[0].length;

        List<int[]> rowMetrics = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            int[] row = mat[i];
            int soldierCount = getSoldierCount(row);
            rowMetrics.add(new int[] {i, soldierCount});
        }

        rowMetrics.sort((a, b) -> {
            if(a[1] == b[1]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        int[] rv = new int[k];
        for(int i = 0; i < k; i++) {
            rv[i] = rowMetrics.get(i)[0];
        }

        return rv;
    }

    private int getSoldierCount(int[] row) {
//         for(int i = 0; i < row.length; i++) {
//             if(row[i] == 0) {
//                 return i;
//             }
//         }

//         return row.length;
        int left = 0;
        int right = row.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private void print(List<int[]> metrics) {
        for(int[] val : metrics) {
            System.out.print("(" + val[0] + ", " + val[1] + ") ");
        }

        System.out.println();
    }
}

class Solution_Correct_1 {
    public int[] kWeakestRows(int[][] mat, int k) {
        if(mat == null || mat.length == 0 || k < 1) {
            return new int[0];
        }

        int rowCount = mat.length;
        int colCount = mat[0].length;

        PriorityQueue<RowWithStrength> maxHeap = new PriorityQueue<>((rs1, rs2) -> {
            if(rs2.strength != rs1.strength) {
                return rs2.strength - rs1.strength;
            }

            return rs2.row - rs1.row;
        });

        for(int r = 0; r < rowCount; r++) {
            int rowStrength = 0;
            for(int c = 0; c < colCount; c++) {
                rowStrength += mat[r][c];
            }

            maxHeap.offer(new RowWithStrength(r, rowStrength));

            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int n = maxHeap.size();
        int i = n - 1;
        int[] results = new int[n];
        while(!maxHeap.isEmpty()) {
            results[i--] = maxHeap.poll().row;
        }

        return results;
    }

    class RowWithStrength {
        int row;
        int strength;

        public RowWithStrength(int row, int strength) {
            this.row = row;
            this.strength = strength;
        }
    }
}

// [[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]
// 3

//    1337. The K Weakest Rows in a Matrix
//    Easy
//    Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
//
//    A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
//
//
//
//    Example 1:
//
//    Input: mat =
//    [[1,1,0,0,0],
//    [1,1,1,1,0],
//    [1,0,0,0,0],
//    [1,1,0,0,0],
//    [1,1,1,1,1]],
//    k = 3
//    Output: [2,0,3]
//    Explanation:
//    The number of soldiers for each row is:
//    row 0 -> 2
//    row 1 -> 4
//    row 2 -> 1
//    row 3 -> 2
//    row 4 -> 5
//    Rows ordered from the weakest to the strongest are [2,0,3,1,4]
//    Example 2:
//
//    Input: mat =
//    [[1,0,0,0],
//    [1,1,1,1],
//    [1,0,0,0],
//    [1,0,0,0]],
//    k = 2
//    Output: [0,2]
//    Explanation:
//    The number of soldiers for each row is:
//    row 0 -> 1
//    row 1 -> 4
//    row 2 -> 1
//    row 3 -> 1
//    Rows ordered from the weakest to the strongest are [0,2,3,1]
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    2 <= n, m <= 100
//    1 <= k <= m
//    matrix[i][j] is either 0 or 1.
