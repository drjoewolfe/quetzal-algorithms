package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindClosestNodeToGivenTwoNodes {
    class Solution {
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            if(edges == null || edges.length == 0) {
                return 0;
            }

            int n = edges.length;
            var traversal1 = traverse(edges, node1);
            var traversal2 = traverse(edges, node2);

            int minMaxDistance = Integer.MAX_VALUE;
            int minMaxNode = -1;
            for(int i = 0; i < n; i++) {
                int distance1 = traversal1.containsKey(i) ? traversal1.get(i) : Integer.MAX_VALUE;
                int distance2 = traversal2.containsKey(i) ? traversal2.get(i) : Integer.MAX_VALUE;

                int maxDistance = Math.max(distance1, distance2);
                if(maxDistance < minMaxDistance) {
                    minMaxDistance = maxDistance;
                    minMaxNode = i;
                }
            }

            return minMaxNode;
        }

        private Map<Integer, Integer> traverse(int[] edges, int node) {
            Map<Integer, Integer> traversal = new HashMap<>();
            Set<Integer> visited = new HashSet<>();
            int distance = 0;
            while(node != -1 && !visited.contains(node)) {
                traversal.put(node, distance);
                visited.add(node);

                node = edges[node];
                distance++;
            }

            return traversal;
        }
    }

    class Solution_Incorrect_Again {
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            if(edges == null || edges.length == 0) {
                return 0;
            }

            int n = edges.length;

            Set<Integer> set = new HashSet<>();
            set.add(node1);
            while(edges[node1] != -1 && !set.contains(edges[node1])) {
                set.add(edges[node1]);
                node1 = edges[node1];
            }

            while(edges[node2] != -1) {
                if(set.contains(node2)) {
                    break;
                }

                node2 = edges[node2];
            }

            return node2;
        }
    }

    class Solution_Incorrect {
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            if(edges == null || edges.length == 0) {
                return 0;
            }

            int n = edges.length;

            Map<Integer, Integer> map = new HashMap<>();
            int lengthFromNode1 = 0;
            map.put(node1, lengthFromNode1++);
            while(edges[node1] != -1 && !map.containsKey(edges[node1])) {
                map.put(edges[node1], lengthFromNode1++);
                node1 = edges[node1];
            }

            int lengthFromNode2 = 0;
            while(edges[node2] != -1) {
                if(map.containsKey(node2)) {
                    break;
                }

                node2 = edges[node2];
                lengthFromNode2++;
            }

            return node2;
        }
    }

// [2,2,3,-1]
// 0
// 1

// [4,4,4,5,1,2,2]
// 1
// 1

// [5,3,1,0,2,4,5]
// 3
// 2
}

//    2359. Find Closest Node to Given Two Nodes
//    Medium
//    You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
//
//    The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
//
//    You are also given two integers node1 and node2.
//
//    Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.
//
//    Note that edges may contain cycles.
//
//
//
//    Example 1:
//
//
//    Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
//    Output: 2
//    Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
//    The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
//    Example 2:
//
//
//    Input: edges = [1,2,-1], node1 = 0, node2 = 2
//    Output: 2
//    Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
//    The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
//
//
//    Constraints:
//
//    n == edges.length
//    2 <= n <= 105
//    -1 <= edges[i] < n
//    edges[i] != i
//    0 <= node1, node2 < n