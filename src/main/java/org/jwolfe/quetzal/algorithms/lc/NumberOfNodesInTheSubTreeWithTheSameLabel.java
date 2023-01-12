package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfNodesInTheSubTreeWithTheSameLabel {
    class Solution {
        public int[] countSubTrees(int n, int[][] edges, String labels) {
            if(n < 1 || edges == null || edges.length != n - 1 || labels == null || labels.length() != n) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> parent = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);

                parent.put(v, u);
            }

            int[] counts = new int[n];
            computeCounts(graph, 0, labels, counts, new HashSet<>());

            return counts;
        }

        private int[] computeCounts(Map<Integer, Set<Integer>> graph, Integer u, String labels, int[] counts, Set<Integer> visited) {
            visited.add(u);

            int[] letterCounts = new int[26];

            for(var v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                int[] childLetterCounts = computeCounts(graph, v, labels, counts, visited);
                merge(letterCounts, childLetterCounts);
            }

            int index = labels.charAt(u) - 'a';
            letterCounts[index]++;
            counts[u] = letterCounts[index];

            return letterCounts;
        }

        private void merge(int[] arr1, int[] arr2) {
            for(int i = 0; i < arr1.length; i++) {
                arr1[i] += arr2[i];
            }
        }
    }

    class Solution_Incorrect {
        public int[] countSubTrees(int n, int[][] edges, String labels) {
            if(n < 1 || edges == null || edges.length != n - 1 || labels == null || labels.length() != n) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> parent = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);

                parent.put(v, u);
            }

            int[] counts = new int[n];
            for(int u = 0; u < n; u++) {
                counts[u] = getCount(graph, u, parent.get(u), labels.charAt(u), labels, new HashSet<>());
            }

            return counts;
        }

        private int getCount(Map<Integer, Set<Integer>> graph, Integer u, Integer parent, char c, String labels, Set<Integer> visited) {
            int count = 0;
            if(labels.charAt(u) == c) {
                count++;
            }

            visited.add(u);

            for(var v : graph.get(u)) {
                if(visited.contains(v)
                        || v.equals(parent)) {
                    continue;
                }

                count += getCount(graph, v, parent, c, labels, visited);
            }

            return count;
        }
    }

// 7
// [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
// "abaedcd"

// 4
// [[0,2],[0,3],[1,2]]
// "aeed"
}

//    1519. Number of Nodes in the Sub-Tree With the Same Label
//    Medium
//    You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
//
//    The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
//
//    Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
//
//    A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
//
//
//
//    Example 1:
//
//
//    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
//    Output: [2,1,1,1,1,1,1]
//    Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that any node is part of its sub-tree.
//    Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
//    Example 2:
//
//
//    Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
//    Output: [4,2,1,1]
//    Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
//    The sub-tree of node 3 contains only node 3, so the answer is 1.
//    The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
//    The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
//    Example 3:
//
//
//    Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
//    Output: [3,2,1,1,1]
//
//
//    Constraints:
//
//    1 <= n <= 105
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    labels.length == n
//    labels is consisting of only of lowercase English letters.