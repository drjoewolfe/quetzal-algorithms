package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countPaths(int n, int[][] roads) {
            if (n < 1 || roads == null || roads.length == 0) {
                return 1;
            }

            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];
                int c = road[2];

                graph.get(u).add(new int[]{v, c});
                graph.get(v).add(new int[]{u, c});
            }

            long[] shortestTime = new long[n];
            Arrays.fill(shortestTime, Long.MAX_VALUE);
            shortestTime[0] = 0;

            int[] pathCount = new int[n];
            pathCount[0] = 1;

            // PQ holds (node, time) tuplets
            PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> (int) (a[1] - b[1]));
            minHeap.offer(new long[]{0, 0});
            while (!minHeap.isEmpty()) {
                var entry = minHeap.poll();
                int u = (int) entry[0];
                long t = entry[1];

                if (t > shortestTime[u]) {
                    continue;
                }

                for (var neighbour : graph.get(u)) {
                    int v = neighbour[0];
                    int vt = neighbour[1];

                    long totalTime = t + vt;

                    if (totalTime < shortestTime[v]) {
                        shortestTime[v] = totalTime;
                        pathCount[v] = pathCount[u];
                        minHeap.offer(new long[]{v, totalTime});
                    } else if (totalTime == shortestTime[v]) {
                        pathCount[v] = (pathCount[v] + pathCount[u]) % MOD;
                    }
                }
            }

            return pathCount[n - 1];
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    1976. Number of Ways to Arrive at Destination
//    Medium
//    You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
//
//    You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
//
//    Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//
//    Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
//    Output: 4
//    Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
//    The four ways to get there in 7 minutes are:
//    - 0 ➝ 6
//    - 0 ➝ 4 ➝ 6
//    - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
//    - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
//    Example 2:
//
//    Input: n = 2, roads = [[1,0,10]]
//    Output: 1
//    Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
//
//
//    Constraints:
//
//    1 <= n <= 200
//    n - 1 <= roads.length <= n * (n - 1) / 2
//    roads[i].length == 3
//    0 <= ui, vi <= n - 1
//    1 <= timei <= 109
//    ui != vi
//    There is at most one road connecting any two intersections.
//    You can reach any intersection from any other intersection.