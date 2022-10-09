package org.jwolfe.quetzal.algorithms.lc;

public class FindCenterOfStarGraph {
    class Solution {
        public int findCenter(int[][] edges) {
            if(edges == null || edges.length == 0) {
                return -1;
            }

            int a = edges[0][0];
            int b = edges[0][1];

            if(edges[1][0] == a || edges[1][1] == a) {
                return a;
            }

            return b;
        }
    }

    class Solution_Approach_1 {
        public int findCenter(int[][] edges) {
            if(edges == null || edges.length == 0) {
                return -1;
            }

            int n = edges.length + 1;
            int[] counts = new int[n];
            for(int[] edge : edges) {
                counts[edge[0] - 1]++;
                counts[edge[1] - 1]++;
            }

            for(int i = 0; i < n; i++) {
                if(counts[i] == n - 1) {
                    return i + 1;
                }
            }

            return -1;
        }
    }
}

//    1791. Find Center of Star Graph
//    Easy
//    There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
//
//    You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
//
//
//
//    Example 1:
//
//
//    Input: edges = [[1,2],[2,3],[4,2]]
//    Output: 2
//    Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
//    Example 2:
//
//    Input: edges = [[1,2],[5,1],[1,3],[1,4]]
//    Output: 1
//
//
//    Constraints:
//
//    3 <= n <= 105
//    edges.length == n - 1
//    edges[i].length == 2
//    1 <= ui, vi <= n
//    ui != vi
//    The given edges represent a valid star graph.
