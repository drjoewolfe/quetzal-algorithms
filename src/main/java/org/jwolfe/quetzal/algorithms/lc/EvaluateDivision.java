package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class EvaluateDivision {
    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            if(queries == null || queries.size() == 0) {
                return new double[0];
            }

            int n = queries.size();
            double[] results = new double[n];
            if(equations == null || equations.size() == 0) {
                Arrays.fill(results, -1);
                return results;
            }

            Map<String, List<Pair<String, Double>>> graph = new HashMap<>();
            for(int i = 0; i < equations.size(); i++) {
                var equation = equations.get(i);
                var from = equation.get(0);
                var to = equation.get(1);
                double val = values[i];
                double invVal = 1.0 / val;

                if(!graph.containsKey(from)) {
                    graph.put(from, new ArrayList<>());
                }

                if(!graph.containsKey(to)) {
                    graph.put(to, new ArrayList<>());
                }

                graph.get(from).add(new Pair<>(to, val));
                graph.get(to).add(new Pair<>(from, invVal));
            }

            for(int i = 0; i < n; i++) {
                var query = queries.get(i);
                var from = query.get(0);
                var to = query.get(1);

                if(!graph.containsKey(from) || !graph.containsKey(to)) {
                    results[i] = -1;
                } else {
                    Set<String> visited = new HashSet<>();
                    visited.add(from);
                    results[i] = dfs(graph, from, to, visited);
                }
            }

            return results;
        }

        private double dfs(Map<String, List<Pair<String, Double>>> graph, String u, String target, Set<String> visited) {
            if(u.equals(target)) {
                return 1.0;
            }

            for(var neighbour : graph.get(u)) {
                var v = neighbour.getKey();
                var c = neighbour.getValue();

                if(!visited.contains(v)) {
                    visited.add(v);
                    double val = dfs(graph, v, target, visited);
                    if(val != -1) {
                        return val * c;
                    }
                }
            }

            return -1;
        }
    }
}

//    399. Evaluate Division
//    Medium
//    You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//    You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//    Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//    Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//    Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
//
//
//
//    Example 1:
//
//    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//    Explanation:
//    Given: a / b = 2.0, b / c = 3.0
//    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//    return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//    note: x is undefined => -1.0
//    Example 2:
//
//    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//    Output: [3.75000,0.40000,5.00000,0.20000]
//    Example 3:
//
//    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//    Output: [0.50000,2.00000,-1.00000,-1.00000]
//
//
//    Constraints:
//
//    1 <= equations.length <= 20
//    equations[i].length == 2
//    1 <= Ai.length, Bi.length <= 5
//    values.length == equations.length
//    0.0 < values[i] <= 20.0
//    1 <= queries.length <= 20
//    queries[i].length == 2
//    1 <= Cj.length, Dj.length <= 5
//    Ai, Bi, Cj, Dj consist of lower case English letters and digits.