package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPathVisitingAllNodes {
    class Solution {
        public int shortestPathLength(int[][] graph) {
            if(graph == null || graph.length == 0) {
                return 0;
            }

            Queue<Element> queue = new LinkedList<>();
            Set<Integer> visitedStates = new HashSet<>();

            int n = graph.length;
            int fullMask = (1 << n) - 1;
            for(int u = 0; u < n; u++) {
                int mask = (1 << u);
                int state = mask * 16 + u;
                var element = new Element(u, mask, 0);

                queue.offer(element);
                visitedStates.add(state);
            }

            while(!queue.isEmpty()) {
                var element = queue.poll();

                for(int v : graph[element.u]) {
                    int mask = element.mask | (1 << v);
                    int state = mask * 16 + v;
                    if(!visitedStates.contains(state)) {
                        if(mask == fullMask) {
                            return element.d + 1;
                        }

                        var nextElement = new Element(v, mask, element.d + 1);
                        queue.offer(nextElement);

                        visitedStates.add(state);
                    }
                }
            }

            return 0;
        }

        class Element {
            int u;
            int mask;
            int d;

            public Element(int u, int mask, int d) {
                this.u = u;
                this.mask = mask;
                this.d = d;
            }
        }
    }

    class Solution_Correct_1 {
        public int shortestPathLength(int[][] graph) {
            if(graph == null || graph.length == 0) {
                return 0;
            }

            Queue<int[]> queue = new LinkedList<>();
            Set<Integer> visitedStates = new HashSet<>();

            int n = graph.length;
            int fullMask = (1 << n) - 1;
            for(int u = 0; u < n; u++) {
                int mask = (1 << u);
                int state = mask * 16 + u;
                int[] element = new int[] {u, mask, 0};

                queue.offer(element);
                visitedStates.add(state);
            }

            while(!queue.isEmpty()) {
                var element = queue.poll();

                for(int v : graph[element[0]]) {
                    int mask = element[1] | (1 << v);
                    int state = mask * 16 + v;
                    if(!visitedStates.contains(state)) {
                        if(mask == fullMask) {
                            return element[2] + 1;
                        }

                        var nextElement = new int[] {v, mask, element[2] + 1};
                        queue.offer(nextElement);

                        visitedStates.add(state);
                    }
                }
            }

            return 0;
        }
    }

// [[1,2,3],[0],[0],[0]]
}

//    847. Shortest Path Visiting All Nodes
//    Hard
//    You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
//
//    Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
//
//
//
//    Example 1:
//
//
//    Input: graph = [[1,2,3],[0],[0],[0]]
//    Output: 4
//    Explanation: One possible path is [1,0,2,0,3]
//    Example 2:
//
//
//    Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//    Output: 4
//    Explanation: One possible path is [0,1,4,2,3]
//
//
//    Constraints:
//
//    n == graph.length
//    1 <= n <= 12
//    0 <= graph[i].length < n
//    graph[i] does not contain i.
//    If graph[a] contains b, then graph[b] contains a.
//    The input graph is always connected.