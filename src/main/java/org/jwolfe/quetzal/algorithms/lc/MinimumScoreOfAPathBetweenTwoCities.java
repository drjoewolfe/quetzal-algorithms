package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumScoreOfAPathBetweenTwoCities {
    class Solution {
        public int minScore(int n, int[][] roads) {
            if(n < 1 || roads == null || roads.length == 0) {
                return -1;
            }

            Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
            for(int i = 1; i <= n; i++) {
                graph.put(i, new ArrayList<>());
            }

            for(int i = 0; i < roads.length; i++) {
                int[] road = roads[i];
                int u = road[0];
                int v = road[1];
                int d = road[2];

                graph.get(u).add(new Pair<Integer, Integer>(v, d));
                graph.get(v).add(new Pair<Integer, Integer>(u, d));

            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);

            boolean[] visited = new boolean[n + 1];
            visited[1] = true;

            int shortestRoad = Integer.MAX_VALUE;

            while(!queue.isEmpty()) {
                int u = queue.poll();

                for(var pair : graph.get(u)) {
                    int v = pair.key;
                    int d = pair.value;

                    shortestRoad = Math.min(shortestRoad, d);
                    if(!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }

            }

            return shortestRoad;
        }

        private class Pair<K, V> {
            K key;
            V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}

//    2492. Minimum Score of a Path Between Two Cities
//    Medium
//    You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
//
//    The score of a path between two cities is defined as the minimum distance of a road in this path.
//
//    Return the minimum possible score of a path between cities 1 and n.
//
//    Note:
//
//    A path is a sequence of roads between two cities.
//    It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
//    The test cases are generated such that there is at least one path between 1 and n.
//
//
//    Example 1:
//
//
//    Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
//    Output: 5
//    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
//    It can be shown that no other path has less score.
//    Example 2:
//
//
//    Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
//    Output: 2
//    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= roads.length <= 105
//    roads[i].length == 3
//    1 <= ai, bi <= n
//    ai != bi
//    1 <= distancei <= 104
//    There are no repeated edges.
//    There is at least one path between 1 and n.