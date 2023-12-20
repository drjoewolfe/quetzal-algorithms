package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumHeightTrees {
    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> minHeightTrees = new ArrayList<>();
            if(n == 0) {
                return minHeightTrees;
            }

            if(edges == null || edges.length == 0) {
                for(int u = 0; u < n; u++) {
                    minHeightTrees.add(u);
                }

                return minHeightTrees;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] indegrees = new int[n];
            boolean[] seen = new boolean[n];
            Queue<Integer> leaves = new LinkedList<>();

            for(var entry : graph.entrySet()) {
                int u = entry.getKey();
                var neighbours = entry.getValue();

                indegrees[u] = neighbours.size();
                if(indegrees[u] == 1) {
                    leaves.offer(u);
                    seen[u] = true;
                }
            }

            while(n > 2) {
                int size = leaves.size();
                n -= size;
                for(int i = 0; i < size; i++) {
                    int u = leaves.poll();

                    for(int v : graph.get(u)) {
                        if(seen[v]) {
                            continue;
                        }

                        indegrees[v]--;
                        if(indegrees[v] == 1) {
                            leaves.offer(v);
                            seen[v] = true;
                        }
                    }
                }
            }

            while(!leaves.isEmpty()) {
                minHeightTrees.add(leaves.poll());
            }

            return minHeightTrees;
        }
    }

    class Solution_TLE {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> minHeightRoots = new ArrayList<>();
            if(n == 0) {
                return minHeightRoots;
            }

            if(edges == null || edges.length == 0) {
                for(int u = 0; u < n; u++) {
                    minHeightRoots.add(u);
                }

                return minHeightRoots;
            }

            Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
            for(int u = 0; u < n; u++) {
                adjacencyList.put(u, new ArrayList<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                adjacencyList.get(u).add(v);
                adjacencyList.get(v).add(u);
            }

            int[] heights = new int[n];
            int minHeight = Integer.MAX_VALUE;
            for(int u = 0; u < n; u++) {
                int height = getHeight(adjacencyList, u, new HashSet<>());
                minHeight = Math.min(height, minHeight);
                heights[u] = height;
            }

            for(int u = 0; u < n; u++) {
                if(heights[u] == minHeight) {
                    minHeightRoots.add(u);
                }
            }

            return minHeightRoots;
        }

        private int getHeight(Map<Integer, List<Integer>> adjacencyList, int u, Set<Integer> visited) {
            if(visited.contains(u)) {
                return 0;
            }

            visited.add(u);
            int maxHeight = 0;
            for(Integer v : adjacencyList.get(u)) {
                maxHeight = Math.max(maxHeight, getHeight(adjacencyList, v, visited));
            }

            return maxHeight + 1;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> mhtRoots = new ArrayList<>();

            if(n < 0) {
                return mhtRoots;
            }

            if(edges == null || edges.length == 0) {
                for(int u = 0; u < n; u++) {
                    mhtRoots.add(u);
                }

                return mhtRoots;
            }

            Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
            for(int u= 0; u < n; u++) {
                adjacencyList.put(u, new ArrayList<>());
            }

            for(int[] e : edges) {
                int u = e[0];
                int v = e[1];
                adjacencyList.get(u).add(v);
                adjacencyList.get(v).add(u);
            }

            int[] heights = new int[n];
            int minHeight = Integer.MAX_VALUE;
            for(int u = 0; u < n; u++) {
                int h = getHeight(u, adjacencyList, new HashSet<>());
                heights[u] = h;
                minHeight = Math.min(minHeight, h);
            }

            for(int u = 0; u < n; u++) {
                if(heights[u] == minHeight) {
                    mhtRoots.add(u);
                }
            }

            return mhtRoots;
        }

        private int getHeight(int u, Map<Integer, List<Integer>> adjacencyList, Set<Integer> visited) {
            visited.add(u);

            var children = adjacencyList.get(u);
            int maxChildHeight = 0;
            for(int v : children) {
                if(visited.contains(v)) {
                    continue;
                }

                int vHeight = getHeight(v, adjacencyList, visited);
                maxChildHeight = Math.max(maxChildHeight, vHeight);
            }

            return maxChildHeight + 1;
        }
    }

// 4
// [[1,0],[1,2],[1,3]]

}

//    310. Minimum Height Trees
//    Medium
//    A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
//
//    Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
//
//    Return a list of all MHTs' root labels. You can return the answer in any order.
//
//    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
//
//
//
//    Example 1:
//
//
//    Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//    Output: [1]
//    Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
//    Example 2:
//
//
//    Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//    Output: [3,4]
//
//
//    Constraints:
//
//    1 <= n <= 2 * 104
//    edges.length == n - 1
//    0 <= ai, bi < n
//    ai != bi
//    All the pairs (ai, bi) are distinct.
//    The given input is guaranteed to be a tree and there will be no repeated edges.