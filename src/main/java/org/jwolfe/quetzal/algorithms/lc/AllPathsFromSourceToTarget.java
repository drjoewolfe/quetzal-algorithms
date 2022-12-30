package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    class Solution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> allPaths = new ArrayList<>();
            if(graph == null || graph.length == 0) {
                return allPaths;
            }

            List<Integer> currentPath = new ArrayList<>();
            currentPath.add(0);

            generateAllPathsToTarget(graph, 0, graph.length - 1, currentPath, allPaths);
            return allPaths;
        }

        private void generateAllPathsToTarget(int[][] graph, int u, int target, List<Integer> currentPath, List<List<Integer>> allPaths) {
            if(u == target) {
                List<Integer> path = new ArrayList<>(currentPath);
                allPaths.add(path);

                return;
            }

            for(int v : graph[u]) {
                currentPath.add(v);
                generateAllPathsToTarget(graph, v, target, currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> allPaths = new ArrayList<>();
            if(graph == null || graph.length == 0) {
                return allPaths;
            }

            List<Integer> currentPath = new ArrayList<>();
            currentPath.add(0);
            generatePathsToTarget(graph, 0, currentPath, allPaths);

            return allPaths;
        }

        private void generatePathsToTarget(int[][] graph, int u, List<Integer> currentPath, List<List<Integer>> allPaths) {
            if(u == graph.length - 1) {
                allPaths.add(new ArrayList<>(currentPath));
                return;
            }

            for(int v : graph[u]) {
                if(currentPath.contains(v)) {
                    continue;
                }

                currentPath.add(v);
                generatePathsToTarget(graph, v, currentPath, allPaths);

                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
}

//    797. All Paths From Source to Target
//    Medium
//    Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
//
//    The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//
//
//    Example 1:
//
//
//    Input: graph = [[1,2],[3],[3],[]]
//    Output: [[0,1,3],[0,2,3]]
//    Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
//    Example 2:
//
//
//    Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//    Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//
//
//    Constraints:
//
//    n == graph.length
//    2 <= n <= 15
//    0 <= graph[i][j] < n
//    graph[i][j] != i (i.e., there will be no self-loops).
//    All the elements of graph[i] are unique.
//    The input graph is guaranteed to be a DAG.