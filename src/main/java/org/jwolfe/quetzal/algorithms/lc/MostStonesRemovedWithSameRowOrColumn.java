package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MostStonesRemovedWithSameRowOrColumn {
    class Solution {
        public int removeStones(int[][] stones) {
            if(stones == null || stones.length < 2) {
                return 0;
            }

            var uf = new UnionFind();
            for(int[] stone : stones) {
                int u = stone[0];
                int v = 10001 + stone[1];

                uf.union(u, v);
            }

            return stones.length - uf.getSetCount();
        }

        private class UnionFind {
            Map<Integer, Integer> parent;

            public UnionFind() {
                parent = new HashMap<>();
            }

            public void union(Integer u, Integer v) {
                Integer pu = find(u);
                Integer pv = find(v);

                parent.put(pu, pv);
            }

            public Integer find(Integer u) {
                if(!parent.containsKey(u)) {
                    parent.put(u, u);
                }

                var pu = parent.get(u);
                if(u != pu) {
                    return find(pu);
                }

                return u;
            }

            public int getSetCount() {
                Set<Integer> set = new HashSet<>();
                for(var entry : parent.entrySet()) {
                    var u = entry.getKey();
                    var pu = find(u);

                    if(!set.contains(pu)) {
                        set.add(pu);
                    }
                }


                return set.size();
            }
        }
    }

    class Solution_BFS {
        public int removeStones(int[][] stones) {
            if(stones == null || stones.length < 1) {
                return 0;
            }

            int n = stones.length;
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for(int u = 0; u < n; u++) {
                for(int v = 0; v < n; v++) {
                    if(u == v) {
                        continue;
                    }

                    if(stones[u][0] == stones[v][0]
                            || stones[u][1] == stones[v][1]) {
                        graph.get(u).add(v);
                    }
                }
            }

            Set<Integer> visited = new HashSet<>();
            int components = 0;

            for(int i = 0; i < n; i++) {
                if(!visited.contains(i)) {
                    components++;
                }

                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while(!queue.isEmpty()) {
                    var u = queue.poll();
                    visited.add(u);

                    for(var v : graph.get(u)) {
                        if(!visited.contains(v)) {
                            queue.offer(v);
                        }
                    }
                }
            }

            return n - components;
        }
    }

    class Solution_DFS_Array {
        public int removeStones(int[][] stones) {
            if(stones == null || stones.length < 1) {
                return 0;
            }

            int n = stones.length;
            boolean[][] graph = new boolean[n][n];

            for(int u = 0; u < n; u++) {
                for(int v = 0; v < n; v++) {
                    if(u == v) {
                        continue;
                    }

                    if(stones[u][0] == stones[v][0]
                            || stones[u][1] == stones[v][1]) {
                        graph[u][v] = true;
                    }
                }
            }

            boolean[] visited = new boolean[n];
            int components = 0;
            for(int u = 0; u < n; u++) {
                if(!visited[u]) {
                    components++;
                }

                dfs(graph, u, visited);
            }

            return n - components;
        }

        private void dfs(boolean[][] graph, int u, boolean[] visited) {
            visited[u] = true;

            for(int v = 0; v < graph.length; v++) {
                if(v == u) {
                    continue;
                }

                if(graph[u][v]
                        && !visited[v]) {
                    dfs(graph, v, visited);
                }
            }
        }
    }


    class Solution_DFS {
        public int removeStones(int[][] stones) {
            if(stones == null || stones.length < 1) {
                return 0;
            }

            int n = stones.length;
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for(int u = 0; u < n; u++) {
                for(int v = 0; v < n; v++) {
                    if(u == v) {
                        continue;
                    }

                    if(stones[u][0] == stones[v][0]
                            || stones[u][1] == stones[v][1]) {
                        graph.get(u).add(v);
                    }
                }
            }

            Set<Integer> visited = new HashSet<>();
            int components = 0;
            for(int u = 0; u < n; u++) {
                if(!visited.contains(u)) {
                    components++;
                }

                dfs(graph, u, visited);
            }

            return n - components;
        }

        private void dfs(Map<Integer, List<Integer>> graph, int u, Set<Integer> visited) {
            visited.add(u);

            for(var v : graph.get(u)) {
                if(!visited.contains(v)) {
                    dfs(graph, v, visited);
                }
            }
        }
    }

// [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
}

//    947. Most Stones Removed with Same Row or Column
//    Medium
//    On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
//
//    A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
//
//    Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
//
//
//
//    Example 1:
//
//    Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//    Output: 5
//    Explanation: One way to remove 5 stones is as follows:
//    1. Remove stone [2,2] because it shares the same row as [2,1].
//    2. Remove stone [2,1] because it shares the same column as [0,1].
//    3. Remove stone [1,2] because it shares the same row as [1,0].
//    4. Remove stone [1,0] because it shares the same column as [0,0].
//    5. Remove stone [0,1] because it shares the same row as [0,0].
//    Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
//    Example 2:
//
//    Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//    Output: 3
//    Explanation: One way to make 3 moves is as follows:
//    1. Remove stone [2,2] because it shares the same row as [2,0].
//    2. Remove stone [2,0] because it shares the same column as [0,0].
//    3. Remove stone [0,2] because it shares the same row as [0,0].
//    Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
//    Example 3:
//
//    Input: stones = [[0,0]]
//    Output: 0
//    Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
//
//
//    Constraints:
//
//    1 <= stones.length <= 1000
//    0 <= xi, yi <= 104
//    No two stones are at the same coordinate point.