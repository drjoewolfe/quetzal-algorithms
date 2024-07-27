package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumCostToConvertStringI {
    class Solution {
        public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
            if (source == null || target == null || source.length() != target.length() || original == null || original.length == 0 || changed == null || original.length != changed.length || cost == null || original.length != cost.length) {
                return -1;
            }

            Map<Character, Map<Character, Long>> graph = new HashMap<>();
            for (int i = 0; i < 26; i++) {
                char u = (char) ('a' + i);
                graph.put(u, new HashMap<>());
            }

            for (int i = 0; i < original.length; i++) {
                char u = original[i];
                char v = changed[i];
                long c = cost[i];

                if (!graph.containsKey(u)) {
                    graph.put(u, new HashMap<>());
                }

                var map = graph.get(u);
                if (map.containsKey(v)) {
                    map.put(v, Math.min(c, map.get(v)));
                } else {
                    map.put(v, c);
                }
            }

            Map<Character, Map<Character, Long>> shortestDistanceMap = new HashMap<>();
            for (int i = 0; i < 26; i++) {
                char u = (char) ('a' + i);
                shortestDistanceMap.put(u, dijkstra(graph, u));
            }

            int n = source.length();
            long minCost = 0;
            for (int i = 0; i < n; i++) {
                char u = source.charAt(i);
                char v = target.charAt(i);

                if (u == v) {
                    continue;
                }

                Map<Character, Long> distances = shortestDistanceMap.get(u);
                if (!distances.containsKey(v)) {
                    return -1;
                }

                minCost += distances.get(v);
            }

            return minCost;
        }

        private Map<Character, Long> dijkstra(Map<Character, Map<Character, Long>> graph, char s) {
            int n = graph.keySet().size();

            Map<Character, Long> shortestDistances = new HashMap<>();

            Map<Character, Long> distances = new HashMap<>();
            distances.put(s, 0L);

            Set<Character> done = new HashSet<>();
            for (int i = 0; i < graph.size(); i++) {
                var u = getShortestDistanceVertex(distances, done);
                if (u == null) {
                    return shortestDistances;
                }

                shortestDistances.put(u, distances.get(u));
                done.add(u);

                // Relax
                for (var neighbour : graph.get(u).entrySet()) {
                    char v = neighbour.getKey();
                    long c = neighbour.getValue();

                    if (!distances.containsKey(v)) {
                        distances.put(v, distances.get(u) + c);
                    } else {
                        distances.put(v, Math.min(distances.get(v), distances.get(u) + c));
                    }
                }
            }

            return shortestDistances;
        }

        private Character getShortestDistanceVertex(Map<Character, Long> distances, Set<Character> done) {
            Character u = null;
            long minDistance = Long.MAX_VALUE;

            for (var entry : distances.entrySet()) {
                var v = entry.getKey();

                if (done.contains(v)) {
                    continue;
                }

                long c = entry.getValue();
                if (c < minDistance) {
                    minDistance = c;
                    u = v;
                }
            }

            return u;
        }

        private void print(Map<Character, Map<Character, Integer>> graph) {
            for (var entry : graph.entrySet()) {
                var key = entry.getKey();
                System.out.print("[" + key + "] -> [");
                for (var neighbour : entry.getValue().entrySet()) {
                    var subKey = neighbour.getKey();
                    var cost = neighbour.getValue();

                    System.out.print("(" + subKey + ", " + cost + ")");
                }

                System.out.print("]");
                System.out.println();
            }

            System.out.println();
        }

        private void printDistances(Map<Character, Integer> graph) {
            for (var entry : graph.entrySet()) {
                var key = entry.getKey();
                var cost = entry.getValue();

                System.out.print("(" + key + ", " + cost + ")");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
            if (source == null || target == null || source.length() != target.length() || original == null || original.length == 0 || changed == null || original.length != changed.length || cost == null || original.length != cost.length) {
                return -1;
            }

            Map<Character, Map<Character, Long>> graph = new HashMap<>();
            for (int i = 0; i < 26; i++) {
                char u = (char) ('a' + i);
                graph.put(u, new HashMap<>());
            }

            for (int i = 0; i < original.length; i++) {
                char u = original[i];
                char v = changed[i];
                long c = cost[i];

                if (!graph.containsKey(u)) {
                    graph.put(u, new HashMap<>());
                }

                var map = graph.get(u);
                if (map.containsKey(v)) {
                    map.put(v, Math.min(c, map.get(v)));
                } else {
                    map.put(v, c);
                }
            }

            int n = source.length();
            long minCost = 0;
            for (int i = 0; i < n; i++) {
                char u = source.charAt(i);
                char v = target.charAt(i);

                if (u == v) {
                    continue;
                }

                Map<Character, Long> distances = dijkstra(graph, u);
                if (!distances.containsKey(v)) {
                    return -1;
                }

                minCost += distances.get(v);
            }

            return minCost;
        }

        private Map<Character, Long> dijkstra(Map<Character, Map<Character, Long>> graph, char s) {
            int n = graph.keySet().size();

            Map<Character, Long> shortestDistances = new HashMap<>();

            Map<Character, Long> distances = new HashMap<>();
            distances.put(s, 0L);

            Set<Character> done = new HashSet<>();
            for (int i = 0; i < graph.size(); i++) {
                var u = getShortestDistanceVertex(distances, done);
                if (u == null) {
                    return shortestDistances;
                }

                shortestDistances.put(u, distances.get(u));
                done.add(u);

                // Relax
                for (var neighbour : graph.get(u).entrySet()) {
                    char v = neighbour.getKey();
                    long c = neighbour.getValue();

                    if (!distances.containsKey(v)) {
                        distances.put(v, distances.get(u) + c);
                    } else {
                        distances.put(v, Math.min(distances.get(v), distances.get(u) + c));
                    }
                }
            }

            return shortestDistances;
        }

        private Character getShortestDistanceVertex(Map<Character, Long> distances, Set<Character> done) {
            Character u = null;
            long minDistance = Long.MAX_VALUE;

            for (var entry : distances.entrySet()) {
                var v = entry.getKey();

                if (done.contains(v)) {
                    continue;
                }

                long c = entry.getValue();
                if (c < minDistance) {
                    minDistance = c;
                    u = v;
                }
            }

            return u;
        }

        private void print(Map<Character, Map<Character, Integer>> graph) {
            for (var entry : graph.entrySet()) {
                var key = entry.getKey();
                System.out.print("[" + key + "] -> [");
                for (var neighbour : entry.getValue().entrySet()) {
                    var subKey = neighbour.getKey();
                    var cost = neighbour.getValue();

                    System.out.print("(" + subKey + ", " + cost + ")");
                }

                System.out.print("]");
                System.out.println();
            }

            System.out.println();
        }

        private void printDistances(Map<Character, Integer> graph) {
            for (var entry : graph.entrySet()) {
                var key = entry.getKey();
                var cost = entry.getValue();

                System.out.print("(" + key + ", " + cost + ")");
            }

            System.out.println();
        }
    }

// "abcd"
// "acbe"
// ["a","b","c","c","e","d"]
// ["b","c","b","e","b","e"]
// [2,5,5,1,2,20]

// "aaaa"
// "bbbb"
// ["a","c"]
// ["c","b"]
// [1,2]
}

//    2976. Minimum Cost to Convert String I
//    Medium
//    You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].
//
//    You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.
//
//    Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.
//
//    Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
//
//
//
//    Example 1:
//
//    Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
//    Output: 28
//    Explanation: To convert the string "abcd" to string "acbe":
//    - Change value at index 1 from 'b' to 'c' at a cost of 5.
//    - Change value at index 2 from 'c' to 'e' at a cost of 1.
//    - Change value at index 2 from 'e' to 'b' at a cost of 2.
//    - Change value at index 3 from 'd' to 'e' at a cost of 20.
//    The total cost incurred is 5 + 1 + 2 + 20 = 28.
//    It can be shown that this is the minimum possible cost.
//    Example 2:
//
//    Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
//    Output: 12
//    Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.
//    Example 3:
//
//    Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
//    Output: -1
//    Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.
//
//
//    Constraints:
//
//    1 <= source.length == target.length <= 105
//    source, target consist of lowercase English letters.
//    1 <= cost.length == original.length == changed.length <= 2000
//    original[i], changed[i] are lowercase English letters.
//    1 <= cost[i] <= 106
//    original[i] != changed[i]