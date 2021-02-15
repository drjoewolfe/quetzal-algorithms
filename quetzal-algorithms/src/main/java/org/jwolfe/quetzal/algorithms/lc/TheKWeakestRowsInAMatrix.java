package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class TheKWeakestRowsInAMatrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        if(mat == null || mat.length == 0) {
            return new int[0];
        }

        int m = mat.length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return b[0] - a[0];
            }

            return b[1] - a[1];
        });

        for(int r = 0; r < m; r++) {
            int soldiers = getSoldierCount(mat[r]);
            minHeap.offer(new int[] {r, soldiers});

            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] results = new int[k];
        int i = k - 1;
        while(!minHeap.isEmpty()) {
            results[i--] = minHeap.poll()[0];
        }

        return results;
    }

    private int getSoldierCount(int[] row) {
        int left = 0;
        int right = row.length - 1;

        int soldierEnd = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(row[mid] == 1) {
                soldierEnd = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if(soldierEnd == -1) {
            return 0;
        }

        return soldierEnd + 1;
    }
}

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
