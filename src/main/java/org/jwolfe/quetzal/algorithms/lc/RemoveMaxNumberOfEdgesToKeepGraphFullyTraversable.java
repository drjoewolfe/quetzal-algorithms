package org.jwolfe.quetzal.algorithms.lc;

public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    class Solution {
        public int maxNumEdgesToRemove(int n, int[][] edges) {
            if (n < 1 || edges == null || edges.length == 0) {
                return 0;
            }

            int requiredEdges = 0;

            DisjointSets bobSets = new DisjointSets(n + 1);
            DisjointSets aliceSets = new DisjointSets(n + 1);

            for (int[] edge : edges) {
                int t = edge[0];
                int u = edge[1];
                int v = edge[2];

                if (t == 3) {
                    boolean requiredForBob = bobSets.union(u, v);
                    boolean requiredForAlice = aliceSets.union(u, v);

                    if (requiredForBob || requiredForAlice) {
                        requiredEdges++;
                    }
                }
            }

            for (int[] edge : edges) {
                int t = edge[0];
                int u = edge[1];
                int v = edge[2];

                if (t == 1) {
                    boolean requiredForAlice = aliceSets.union(u, v);
                    if (requiredForAlice) {
                        requiredEdges++;
                    }
                } else if (t == 2) {
                    boolean requiredForBob = bobSets.union(u, v);
                    if (requiredForBob) {
                        requiredEdges++;
                    }
                }
            }

            if (!bobSets.isConnected() || !aliceSets.isConnected()) {
                return -1;
            }

            return edges.length - requiredEdges;
        }

        public class DisjointSets {
            int[] representatives;
            int[] rank;
            int length;

            public DisjointSets(int n) {
                length = n;
                representatives = new int[n];
                rank = new int[n];

                for (int u = 0; u < n; u++) {
                    representatives[u] = u;
                }
            }

            private int find(int u) {
                if (representatives[u] != u) {
                    representatives[u] = find(representatives[u]);
                }

                return representatives[u];
            }

            private boolean union(int u, int v) {
                int ur = find(u);
                int vr = find(v);

                if (ur == vr) {
                    return false;
                }

                if (rank[ur] < rank[vr]) {
                    representatives[ur] = vr;
                } else if (rank[ur] > rank[vr]) {
                    representatives[vr] = ur;
                } else {
                    representatives[ur] = vr;
                    rank[vr]++;
                }

                return true;
            }

            private boolean isConnected() {
                int r = find(1);
                for (int u = 2; u < length; u++) {
                    if (r != find(u)) {
                        return false;
                    }
                }

                return true;
            }

            private void print() {
                for (int r : representatives) {
                    System.out.print(r + " ");
                }

                System.out.println();
            }
        }
    }
}

//    1579. Remove Max Number of Edges to Keep Graph Fully Traversable
//    Hard
//    Alice and Bob have an undirected graph of n nodes and three types of edges:
//
//    Type 1: Can be traversed by Alice only.
//    Type 2: Can be traversed by Bob only.
//    Type 3: Can be traversed by both Alice and Bob.
//    Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
//
//    Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.
//
//
//
//    Example 1:
//
//
//
//    Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
//    Output: 2
//    Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
//    Example 2:
//
//
//
//    Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
//    Output: 0
//    Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
//    Example 3:
//
//
//
//    Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
//    Output: -1
//    Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
//
//
//
//
//    Constraints:
//
//    1 <= n <= 105
//    1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
//    edges[i].length == 3
//    1 <= typei <= 3
//    1 <= ui < vi <= n
//    All tuples (typei, ui, vi) are distinct.