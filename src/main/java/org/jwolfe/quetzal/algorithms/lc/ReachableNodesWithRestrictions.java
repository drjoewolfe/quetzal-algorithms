package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ReachableNodesWithRestrictions {
    class Solution {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            if(n < 0 || edges == null || edges.length == 0) {
                return 0;
            }

            if(restricted == null || restricted.length == 0) {
                return n;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Set<Integer> restrictedSet = new HashSet<>();
            for(int u : restricted) {
                restrictedSet.add(u);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            int count = 0;
            Set<Integer> visited = new HashSet<>();
            while(!queue.isEmpty()) {
                int u = queue.poll();
                visited.add(u);
                count++;

                for(int v : graph.get(u)) {
                    if(!visited.contains(v)
                            && ! restrictedSet.contains(v)) {
                        queue.offer(v);
                    }
                }
            }

            return count;
        }
    }
}

//    2368. Reachable Nodes With Restrictions
//    Medium
//    There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
//
//    You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.
//
//    Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
//
//    Note that node 0 will not be a restricted node.
//
//
//
//    Example 1:
//
//
//    Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
//    Output: 4
//    Explanation: The diagram above shows the tree.
//    We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.
//    Example 2:
//
//
//    Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
//    Output: 3
//    Explanation: The diagram above shows the tree.
//    We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    edges represents a valid tree.
//    1 <= restricted.length < n
//    1 <= restricted[i] < n
//    All the values of restricted are unique.