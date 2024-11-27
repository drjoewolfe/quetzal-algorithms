package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ShortestDistanceAfterRoadAdditionQueriesI {
    class Solution {
        public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
            if (n < 1 || queries == null || queries.length == 0) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int u = 0; u < n - 1; u++) {
                graph.get(u).add(u + 1);
            }

            int count = queries.length;
            int[] results = new int[count];
            for (int i = 0; i < count; i++) {
                int[] query = queries[i];

                int u = query[0];
                int v = query[1];

                graph.get(u).add(v);
                results[i] = bfs(graph, 0, n - 1);
            }

            return results;
        }

        private int bfs(Map<Integer, Set<Integer>> graph, int start, int target) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            Set<Integer> visited = new HashSet<>();
            visited.add(start);

            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int u = queue.poll();
                    if (u == target) {
                        return level;
                    }

                    for (int v : graph.get(u)) {
                        if (visited.contains(v)) {
                            continue;
                        }

                        queue.offer(v);
                        visited.add(v);
                    }
                }

                level++;
            }

            return -1;
        }
    }
}

//    3243. Shortest Distance After Road Addition Queries I
//    Medium
//    You are given an integer n and a 2D integer array queries.
//
//    There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
//
//    queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.
//
//    Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
//
//
//
//    Example 1:
//
//    Input: n = 5, queries = [[2,4],[0,2],[0,4]]
//
//    Output: [3,2,1]
//
//    Explanation:
//
//
//
//    After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
//
//
//
//    After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
//
//
//
//    After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
//
//    Example 2:
//
//    Input: n = 4, queries = [[0,3],[0,2]]
//
//    Output: [1,1]
//
//    Explanation:
//
//
//
//    After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
//
//
//
//    After the addition of the road from 0 to 2, the length of the shortest path remains 1.
//
//
//
//    Constraints:
//
//    3 <= n <= 500
//    1 <= queries.length <= 500
//    queries[i].length == 2
//    0 <= queries[i][0] < queries[i][1] < n
//    1 < queries[i][1] - queries[i][0]
//    There are no repeated roads among the queries.