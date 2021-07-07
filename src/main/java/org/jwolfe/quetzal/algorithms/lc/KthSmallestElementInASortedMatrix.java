package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0) {
                return Integer.MAX_VALUE;
            }

            int n = matrix.length;
            for(int[] row : matrix) {
                if(row.length != n) {
                    return Integer.MAX_VALUE;
                }
            }

            if(k < 1 || k > n * n) {
                return Integer.MAX_VALUE;
            }

            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];
            int ans = Integer.MAX_VALUE;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int count = getCountOfNumbersLessThanOrEqualToCeil(matrix, n, mid);

                if(count >= k) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return ans;
        }

        private int getCountOfNumbersLessThanOrEqualToCeil(int[][] matrix, int n, int ceil) {
            int col = n - 1;

            int count = 0;
            for(int row = 0; row < n; row++) {
                while(col >= 0 && matrix[row][col] > ceil) {
                    col--;
                }

                count += (col + 1);
            }

            return count;
        }
    }

    class Solution_Heap {
        public int kthSmallest(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0) {
                return Integer.MAX_VALUE;
            }

            int n = matrix.length;
            for(int[] row : matrix) {
                if(row.length != n) {
                    return Integer.MAX_VALUE;
                }
            }

            if(k < 1 || k > n * n) {
                return Integer.MAX_VALUE;
            }

            PriorityQueue<HeapNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
            for(int i = 0; i < n; i++) {
                HeapNode node = new HeapNode(matrix[0][i], 0, i);
                heap.offer(node);
            }

            for(int i = 0; i < k - 1; i++) {
                HeapNode node = heap.poll();

                if(node.row < n - 1) {
                    HeapNode nextNode = new HeapNode(matrix[node.row + 1][node.column], node.row + 1, node.column);
                    heap.offer(nextNode);
                }
            }

            return heap.poll().val;
        }

        class HeapNode {
            int val;
            int row;
            int column;

            public HeapNode(int val, int row, int column) {
                this.val = val;
                this.row = row;
                this.column = column;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof HeapNode) {
                    return this.val == ((HeapNode) o).val;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash *= 31;
                hash += val;
                return hash;
            }
        }
    }


    class Solution_Correct_2 {
        public int kthSmallest(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return Integer.MAX_VALUE;
            }

            int n = matrix.length;
            if(k < 0 || k > n * n) {
                return Integer.MAX_VALUE;
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            for(int j = 0; j < n; j++) {
                heap.offer(new int[] {matrix[0][j], 0, j});
            }

            for(int round = 1; round < k; round++) {
                int[] element = heap.poll();
                int row = element[1];
                int col = element[2];

                if(row < n - 1) {
                    heap.offer(new int[]{matrix[row + 1][col], row + 1, col});
                }
            }

            return heap.poll()[0];
        }
    }
}

//    378. Kth Smallest Element in a Sorted Matrix
//    Medium
//    Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
//
//    Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//
//
//    Example 1:
//
//    Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//    Output: 13
//    Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
//    Example 2:
//
//    Input: matrix = [[-5]], k = 1
//    Output: -5
//
//
//    Constraints:
//
//    n == matrix.length
//    n == matrix[i].length
//    1 <= n <= 300
//    -109 <= matrix[i][j] <= 109
//    All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
//    1 <= k <= n2