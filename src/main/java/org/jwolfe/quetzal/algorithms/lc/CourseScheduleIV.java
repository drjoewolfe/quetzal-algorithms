package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class CourseScheduleIV {
    class Solution {
        public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
            List<Boolean> results = new ArrayList<>();

            boolean[][] dependencies = new boolean[n][n];
            for (int[] dependency : prerequisites) {
                int u = dependency[1];
                int v = dependency[0];

                dependencies[u][v] = true;
            }

            updateTransitiveDependencies(dependencies, n);

            for (int[] query : queries) {
                int u = query[1];
                int v = query[0];
                results.add(dependencies[u][v]);
            }

            return results;
        }

        private void updateTransitiveDependencies(boolean[][] dependencies, int n) {
            for (int u = 0; u < n; u++) {
                updateTransitiveDependencies(dependencies, n, u);
            }
        }

        private void updateTransitiveDependencies(boolean[][] dependencies, int n, int u) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(u);

            Set<Integer> visited = new HashSet<>();
            visited.add(u);

            while (!queue.isEmpty()) {
                int v = queue.poll();

                dependencies[u][v] = true;
                for (int w = 0; w < n; w++) {
                    if (!visited.contains(w) && dependencies[v][w]) {
                        queue.offer(w);
                        visited.add(w);
                    }
                }
            }
        }
    }

    class Solution_Correct_1 {
        public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
            List<Boolean> results = new ArrayList<>();
            if (n < 1) {
                return results;
            }

            boolean[][] dependencies = new boolean[n][n];
            for (int[] pair : prerequisites) {
                dependencies[pair[1]][pair[0]] = true;
            }

            for (int i = 0; i < n; i++) {
                updateDependents(dependencies, n, i);
            }

            for (int[] query : queries) {
                if (dependencies[query[1]][query[0]]) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }

            return results;
        }

        private void updateDependents(boolean[][] dependencies, int n, int i) {
            Set<Integer> visited = new HashSet<>();

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);

            while (!queue.isEmpty()) {
                int j = queue.poll();
                dependencies[i][j] = true;
                visited.add(j);

                for (int k = 0; k < n; k++) {
                    if (!visited.contains(k) && dependencies[j][k]) {
                        queue.offer(k);
                    }
                }
            }
        }

        private void print(boolean[][] matrix) {
            for (boolean[] row : matrix) {
                for (boolean cell : row) {
                    System.out.print((cell ? 'T' : 'F') + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

// 2
// [[1,0]]
// [[0,1],[1,0]]
}

//    1462. Course Schedule IV
//    Medium
//    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
//
//    For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
//    Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
//
//    You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
//
//    Return a boolean array answer, where answer[j] is the answer to the jth query.
//
//
//
//    Example 1:
//
//
//    Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
//    Output: [false,true]
//    Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
//    Course 0 is not a prerequisite of course 1, but the opposite is true.
//    Example 2:
//
//    Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
//    Output: [false,false]
//    Explanation: There are no prerequisites, and each course is independent.
//    Example 3:
//
//
//    Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
//    Output: [true,true]
//
//
//    Constraints:
//
//    2 <= numCourses <= 100
//    0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
//    prerequisites[i].length == 2
//    0 <= ai, bi <= numCourses - 1
//    ai != bi
//    All the pairs [ai, bi] are unique.
//    The prerequisites graph has no cycles.
//    1 <= queries.length <= 104
//    0 <= ui, vi <= numCourses - 1
//    ui != vi