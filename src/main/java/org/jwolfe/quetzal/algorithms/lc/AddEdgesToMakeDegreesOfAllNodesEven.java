package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AddEdgesToMakeDegreesOfAllNodesEven {
    class Solution {
        public boolean isPossible(int n, List<List<Integer>> edges) {
            if(n < 1) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 1; u <= n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(var edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            List<Integer> oddNodes = new ArrayList<>();
            for(var entry : graph.entrySet()) {
                var u = entry.getKey();
                var neighbours = entry.getValue();

                if(neighbours.size() % 2 != 0) {
                    oddNodes.add(u);
                }
            }

            if(oddNodes.size() == 0) {
                return true;
            }

            if(oddNodes.size() == 2) {
                var u = oddNodes.get(0);
                var v = oddNodes.get(1);

                if(!graph.get(u).contains(v)) {
                    return true;
                }

                for(int w = 1; w <= n; w++) {
                    if(w == u || w == v) {
                        continue;
                    }

                    if(!graph.get(u).contains(w) && !graph.get(v).contains(w)) {
                        return true;
                    }
                }

                return false;
            }

            if(oddNodes.size() == 4) {
                var u1 = oddNodes.get(0);
                var u2 = oddNodes.get(1);
                var u3 = oddNodes.get(2);
                var u4 = oddNodes.get(3);


                if((!graph.get(u1).contains(u2) && !graph.get(u3).contains(u4))
                        || (!graph.get(u1).contains(u3) && !graph.get(u2).contains(u4))
                        || (!graph.get(u1).contains(u4) && !graph.get(u2).contains(u3))) {
                    return true;
                }

                return false;
            }

            return false;
        }
    }

// 5
// [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]

// 6
// [[1,2],[1,6],[1,4],[4,5],[5,3]]

// 11
// [[5,9],[8,1],[2,3],[7,10],[3,6],[6,7],[7,8],[5,1],[5,7],[10,11],[3,7],[6,11],[8,11],[3,4],[8,9],[9,1],[2,10],[9,11],[5,11],[2,5],[8,10],[2,7],[4,1],[3,10],[6,1],[4,9],[4,6],[4,5],[2,4],[2,11],[5,8],[6,9],[4,10],[3,11],[4,7],[3,5],[7,1],[2,9],[6,10],[10,1],[5,6],[3,9],[2,6],[7,9],[4,11],[4,8],[6,8],[3,8],[9,10],[5,10],[2,8],[7,11]]
}

//    2508. Add Edges to Make Degrees of All Nodes Even
//    Hard
//    There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
//
//    You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
//
//    Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
//
//    The degree of a node is the number of edges connected to it.
//
//
//
//    Example 1:
//
//
//    Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
//    Output: true
//    Explanation: The above diagram shows a valid way of adding an edge.
//    Every node in the resulting graph is connected to an even number of edges.
//    Example 2:
//
//
//    Input: n = 4, edges = [[1,2],[3,4]]
//    Output: true
//    Explanation: The above diagram shows a valid way of adding two edges.
//    Example 3:
//
//
//    Input: n = 4, edges = [[1,2],[1,3],[1,4]]
//    Output: false
//    Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
//
//
//    Constraints:
//
//    3 <= n <= 105
//    2 <= edges.length <= 105
//    edges[i].length == 2
//    1 <= ai, bi <= n
//    ai != bi
//    There are no repeated edges.