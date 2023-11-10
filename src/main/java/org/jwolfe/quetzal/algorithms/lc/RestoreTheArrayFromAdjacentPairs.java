package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RestoreTheArrayFromAdjacentPairs {
    class Solution {
        public int[] restoreArray(int[][] adjacentPairs) {
            if(adjacentPairs == null || adjacentPairs.length == 0) {
                return new int[0];
            }

            int n = adjacentPairs.length + 1;

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int[] edge : adjacentPairs) {
                int u = edge[0];
                int v = edge[1];

                if(!graph.containsKey(u)) {
                    graph.put(u, new HashSet<>());
                }

                if(!graph.containsKey(v)) {
                    graph.put(v, new HashSet<>());
                }

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int first = -1;
            for(var entry : graph.entrySet()) {
                if(entry.getValue().size() == 1) {
                    first = entry.getKey();
                    break;
                }
            }

            int[] result = new int[n];
            dfs(graph, first, result, 0, new HashSet<>());

            return result;
        }

        private void dfs(Map<Integer, Set<Integer>> graph, int u, int[] result, int index, Set<Integer> visited) {
            result[index] = u;
            visited.add(u);

            for(var v : graph.get(u)) {
                if(!visited.contains(v)) {
                    dfs(graph, v, result, ++index, visited);
                }
            }
        }
    }
}

//    1743. Restore the Array From Adjacent Pairs
//    Medium
//    There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
//
//    You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
//
//    It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
//
//    Return the original array nums. If there are multiple solutions, return any of them.
//
//
//
//    Example 1:
//
//    Input: adjacentPairs = [[2,1],[3,4],[3,2]]
//    Output: [1,2,3,4]
//    Explanation: This array has all its adjacent pairs in adjacentPairs.
//    Notice that adjacentPairs[i] may not be in left-to-right order.
//    Example 2:
//
//    Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
//    Output: [-2,4,1,-3]
//    Explanation: There can be negative numbers.
//    Another solution is [-3,1,4,-2], which would also be accepted.
//    Example 3:
//
//    Input: adjacentPairs = [[100000,-100000]]
//    Output: [100000,-100000]
//
//
//    Constraints:
//
//    nums.length == n
//    adjacentPairs.length == n - 1
//    adjacentPairs[i].length == 2
//    2 <= n <= 105
//    -105 <= nums[i], ui, vi <= 105
//    There exists some nums that has adjacentPairs as its pairs.