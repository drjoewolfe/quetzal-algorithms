package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ValidArrangementOfPairs {
    class Solution {
        public int[][] validArrangement(int[][] pairs) {
            if (pairs == null || pairs.length == 0) {
                return new int[0][0];
            }

            Map<Integer, Queue<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> inDegrees = new HashMap<>();
            Map<Integer, Integer> outDegrees = new HashMap<>();

            for (int[] pair : pairs) {
                int u = pair[0];
                int v = pair[1];

                if (!graph.containsKey(u)) {
                    graph.put(u, new LinkedList<>());
                }

                graph.get(u).add(v);
                outDegrees.put(u, outDegrees.getOrDefault(u, 0) + 1);
                inDegrees.put(v, inDegrees.getOrDefault(v, 0) + 1);
            }

            int start = -1;
            for (var entry : outDegrees.entrySet()) {
                var u = entry.getKey();
                if (entry.getValue() == inDegrees.getOrDefault(u, 0) + 1) {
                    start = u;
                    break;
                }
            }

            if (start == -1) {
                start = pairs[0][0];
            }

            List<Integer> ordering = new ArrayList<>();
            dfs(graph, start, ordering);

            Collections.reverse(ordering);
            int[][] results = new int[pairs.length][2];
            for (int i = 1; i < ordering.size(); i++) {
                results[i - 1] = new int[]{ordering.get(i - 1), ordering.get(i)};
            }

            return results;
        }

        private void dfs(Map<Integer, Queue<Integer>> graph, int u, List<Integer> ordering) {
            if (graph.containsKey(u)) {
                var neighbours = graph.get(u);
                while (!neighbours.isEmpty()) {
                    var v = neighbours.poll();
                    dfs(graph, v, ordering);
                }
            }

            ordering.add(u);
        }
    }
}

//    2097. Valid Arrangement of Pairs
//    Hard
//    You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
//
//    Return any valid arrangement of pairs.
//
//    Note: The inputs will be generated such that there exists a valid arrangement of pairs.
//
//
//
//    Example 1:
//
//    Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
//    Output: [[11,9],[9,4],[4,5],[5,1]]
//    Explanation:
//    This is a valid arrangement since endi-1 always equals starti.
//    end0 = 9 == 9 = start1
//    end1 = 4 == 4 = start2
//    end2 = 5 == 5 = start3
//    Example 2:
//
//    Input: pairs = [[1,3],[3,2],[2,1]]
//    Output: [[1,3],[3,2],[2,1]]
//    Explanation:
//    This is a valid arrangement since endi-1 always equals starti.
//    end0 = 3 == 3 = start1
//    end1 = 2 == 2 = start2
//    The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
//    Example 3:
//
//    Input: pairs = [[1,2],[1,3],[2,1]]
//    Output: [[1,2],[2,1],[1,3]]
//    Explanation:
//    This is a valid arrangement since endi-1 always equals starti.
//    end0 = 2 == 2 = start1
//    end1 = 1 == 1 = start2
//
//
//    Constraints:
//
//    1 <= pairs.length <= 105
//    pairs[i].length == 2
//    0 <= starti, endi <= 109
//    starti != endi
//    No two pairs are exactly the same.
//    There exists a valid arrangement of pairs.