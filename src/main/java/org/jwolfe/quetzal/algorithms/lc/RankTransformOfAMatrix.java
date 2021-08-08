package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RankTransformOfAMatrix {
    class Solution {
        public int[][] matrixRankTransform(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return null;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            TreeMap<Integer, List<Position>> elementPositions = new TreeMap<>();
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int v = matrix[r][c];

                    if(!elementPositions.containsKey(v)) {
                        elementPositions.put(v, new ArrayList<>());
                    }

                    elementPositions.get(v).add(new Position(r, c));
                }
            }

            int[][] results = new int[m][n];
            int[] rowRanks = new int[m];
            int[] colRanks = new int[n];

            for(var entry : elementPositions.entrySet()) {
                Integer val = entry.getKey();
                List<Position> positions = entry.getValue();

                DisjointSet set = new DisjointSet(m + n);
                for(var pos : positions) {
                    set.union(pos.x, pos.y + n);
                }

                Map<Integer, Integer> representativeRanks = new HashMap<>();
                for(var pos : positions) {
                    var representative = set.find(pos.x);

                    int rank = representativeRanks.containsKey(representative) ? representativeRanks.get(representative) : 0;
                    rank = Math.max(rank, Math.max(rowRanks[pos.x], colRanks[pos.y]) + 1);

                    representativeRanks.put(representative ,rank);
                }

                for(var pos : positions) {
                    var representative = set.find(pos.x);
                    int rank = representativeRanks.get(representative);

                    results[pos.x][pos.y] = rank;
                    rowRanks[pos.x] = rank;
                    colRanks[pos.y] = rank;
                }
            }

            return results;
        }

        private class DisjointSet {
            int[] parent;
            int n;

            public DisjointSet(int n) {
                this.n  = n;
                this.parent = new int[n];
                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int a, int b) {
                int ap = find(a);
                int bp = find(b);

                parent[ap] = bp;
            }

            public int find(int a) {
                if(a != parent[a]) {
                    parent[a] = find(parent[a]);
                }

                return parent[a];
            }
        }

        private class Position {
            int x;
            int y;

            public Position(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    class Solution_IncorrectForEquals {
        public int[][] matrixRankTransform(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return null;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            PriorityQueue<Element> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    heap.offer(new Element(matrix[r][c], r, c));
                }
            }

            int[][] rowRanks = new int[m][2];
            int[][] colRanks = new int[n][2];

            int[][] results = new int[m][n];
            while(!heap.isEmpty()) {
                Element element = heap.poll();
                int val = element.val;
                int row = element.row;
                int col = element.col;

                int[] dimension = (rowRanks[row][0] > colRanks[col][0]) ? rowRanks[row] : colRanks[col];
                int rank = dimension[0];
                if(rank == 0 || dimension[1] < val) {
                    rank++;
                }

                results[row][col] = rank;

                rowRanks[row][0] = rank;
                rowRanks[row][1] = val;

                colRanks[col][0] = rank;
                colRanks[col][1] = val;
            }

            return results;
        }

        public class Element {
            int val;
            int row;
            int col;

            public Element(int val, int row, int col) {
                this.val = val;
                this.row = row;
                this.col = col;
            }
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                System.out.println(row[0] + ", " + row[1]);
            }

            System.out.println();
        }
    }


// [[1,2],[3,4]]
// [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
// [[-2,-35,-32,-5,-30,33,-12],[7,2,-43,4,-49,14,17],[4,23,-6,-15,-24,-17,6],[-47,20,39,-26,9,-44,39],[-50,-47,44,43,-22,33,-36],[-13,34,49,24,23,-2,-35],[-40,43,-22,-19,-4,23,-18]]
}

//    1632. Rank Transform of a Matrix
//    Hard
//    Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
//
//    The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
//
//    The rank is an integer starting from 1.
//    If two elements p and q are in the same row or column, then:
//    If p < q then rank(p) < rank(q)
//    If p == q then rank(p) == rank(q)
//    If p > q then rank(p) > rank(q)
//    The rank should be as small as possible.
//    It is guaranteed that answer is unique under the given rules.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,2],[3,4]]
//    Output: [[1,2],[2,3]]
//    Explanation:
//    The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
//    The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
//    The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
//    The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
//    Example 2:
//
//
//    Input: matrix = [[7,7],[7,7]]
//    Output: [[1,1],[1,1]]
//    Example 3:
//
//
//    Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
//    Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
//    Example 4:
//
//
//    Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
//    Output: [[5,1,4],[1,2,3],[6,3,1]]
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 500
//    -109 <= matrix[row][col] <= 109