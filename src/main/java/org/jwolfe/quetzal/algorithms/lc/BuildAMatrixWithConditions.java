package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class BuildAMatrixWithConditions {
    class Solution {
        public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
            if (k < 1 || rowConditions == null || colConditions == null) {
                return new int[0][0];
            }

            List<Integer> rowOrdering = getTopologicalSort(k, rowConditions);
            if (rowOrdering.isEmpty()) {
                return new int[0][0];
            }

            List<Integer> colOrdering = getTopologicalSort(k, colConditions);
            if (colOrdering.isEmpty()) {
                return new int[0][0];
            }

            int[][] matrix = new int[k][k];
            for (int r = 0; r < k; r++) {
                for (int c = 0; c < k; c++) {
                    if (rowOrdering.get(r).equals(colOrdering.get(c))) {
                        matrix[r][c] = rowOrdering.get(r);
                    }
                }
            }

            return matrix;
        }

        private List<Integer> getTopologicalSort(int n, int[][] edges) {
            List<Integer> sorting = new ArrayList<>();

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int u = 1; u <= n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
            }

            if (hasCycle(graph, n)) {
                return sorting;
            }

            Set<Integer> visited = new HashSet<>();
            for (int u = 1; u <= n; u++) {
                if (!visited.contains(u)) {
                    dfsForTopogicalSort(graph, u, visited, sorting);
                }
            }

            Collections.reverse(sorting);
            return sorting;
        }

        private void dfsForTopogicalSort(Map<Integer, List<Integer>> graph, int u, Set<Integer> visited, List<Integer> sorting) {
            visited.add(u);

            for (var v : graph.get(u)) {
                if (!visited.contains(v)) {
                    dfsForTopogicalSort(graph, v, visited, sorting);
                }
            }

            sorting.add(u);
        }

        private boolean hasCycle(Map<Integer, List<Integer>> graph, int n) {
            Set<Integer> stack = new HashSet<>();
            Set<Integer> visited = new HashSet<>();

            for (int u = 1; u <= n; u++) {
                if (dfsForCycle(graph, u, visited, stack)) {
                    return true;
                }
            }

            return false;
        }

        private boolean dfsForCycle(Map<Integer, List<Integer>> graph, int u, Set<Integer> visited, Set<Integer> stack) {
            stack.add(u);
            visited.add(u);

            for (var v : graph.get(u)) {
                if (stack.contains(v)) {
                    return true;
                }

                if (!visited.contains(v)) {
                    if (dfsForCycle(graph, v, visited, stack)) {
                        return true;
                    }
                }
            }

            stack.remove(u);
            return false;
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(List<Integer> arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 3
// [[1,2],[3,2]]
// [[2,1],[3,2]]

// 3
// [[1,2],[2,3],[3,1],[2,3]]
// [[2,1]]
}

//    2392. Build a Matrix With Conditions
//    Hard
//    You are given a positive integer k. You are also given:
//
//    a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
//    a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
//    The two arrays contain integers from 1 to k.
//
//    You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
//
//    The matrix should also satisfy the following conditions:
//
//    The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
//    The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
//    Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
//
//
//
//    Example 1:
//
//
//    Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
//    Output: [[3,0,0],[0,0,1],[0,2,0]]
//    Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
//    The row conditions are the following:
//    - Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
//    - Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
//    The column conditions are the following:
//    - Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
//    - Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
//    Note that there may be multiple correct answers.
//    Example 2:
//
//    Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
//    Output: []
//    Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
//    No matrix can satisfy all the conditions, so we return the empty matrix.
//
//
//    Constraints:
//
//    2 <= k <= 400
//    1 <= rowConditions.length, colConditions.length <= 104
//    rowConditions[i].length == colConditions[i].length == 2
//    1 <= abovei, belowi, lefti, righti <= k
//    abovei != belowi
//    lefti != righti