package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class FindMinimumDiameterAfterMergingTwoTrees {
    class Solution {
        public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
            int n = edges1.length + 1;
            int m = edges2.length + 1;

            Map<Integer, Set<Integer>> graph1 = buildGraph(edges1, n);
            Map<Integer, Set<Integer>> graph2 = buildGraph(edges2, m);

            int diameter1 = getDiameter(graph1, n);
            int diameter2 = getDiameter(graph2, m);

            int combinedDiameter = (int) Math.ceil(diameter1 / 2.0) + (int) Math.ceil(diameter2 / 2.0) + 1;

            return Math.max(
                    Math.max(diameter1, diameter2),
                    combinedDiameter
            );
        }

        private Map<Integer, Set<Integer>> buildGraph(int[][] edges, int n) {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return graph;
        }

        private int getDiameter(Map<Integer, Set<Integer>> graph, int n) {
            var pair1 = bfsForFarthest(graph, 0);
            int u = pair1.getKey();

            var pair2 = bfsForFarthest(graph, u);
            int diameter = pair2.getValue();

            return diameter;
        }

        private Pair<Integer, Integer> bfsForFarthest(Map<Integer, Set<Integer>> graph, int start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            Set<Integer> visited = new HashSet<>();
            visited.add(start);

            int maxDistance = 0;
            int farthestNode = start;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int u = queue.poll();
                    farthestNode = u;

                    for (int v : graph.get(u)) {
                        if (visited.contains(v)) {
                            continue;
                        }

                        queue.offer(v);
                        visited.add(v);
                    }
                }

                if (!queue.isEmpty()) {
                    maxDistance++;
                }
            }

            return new Pair<Integer, Integer>(farthestNode, maxDistance);
        }
    }
}

//    3203. Find Minimum Diameter After Merging Two Trees
//    Hard
//    There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
//
//    You must connect one node from the first tree with another node from the second tree with an edge.
//
//    Return the minimum possible diameter of the resulting tree.
//
//    The diameter of a tree is the length of the longest path between any two nodes in the tree.
//
//
//
//    Example 1:
//
//    Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
//
//    Output: 3
//
//    Explanation:
//
//    We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.
//
//    Example 2:
//
//
//    Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
//
//    Output: 5
//
//    Explanation:
//
//    We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.
//
//
//
//    Constraints:
//
//    1 <= n, m <= 105
//    edges1.length == n - 1
//    edges2.length == m - 1
//    edges1[i].length == edges2[i].length == 2
//    edges1[i] = [ai, bi]
//    0 <= ai, bi < n
//    edges2[i] = [ui, vi]
//    0 <= ui, vi < m
//    The input is generated such that edges1 and edges2 represent valid trees.