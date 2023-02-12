package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumFuelCostToReportToTheCapital {
    class Solution {
        private long minCost;

        public long minimumFuelCost(int[][] roads, int seats) {
            if(roads == null || roads.length == 0 || seats == 0) {
                return 0;
            }

            int n = roads.length + 1;

            Map<Integer, Set<Integer>> tree = new HashMap<>();
            for(int u = 0; u < n; u++) {
                tree.put(u, new HashSet<>());
            }

            for(int[] road : roads) {
                int u = road[0];
                int v = road[1];

                tree.get(u).add(v);
                tree.get(v).add(u);
            }

            minCost = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(0);

            for(var child : tree.get(0)) {
                visited.add(child);
                computeMinCost(tree, child, seats, visited);
            }

            return minCost;
        }

        private long computeMinCost(Map<Integer, Set<Integer>> tree, Integer node, int seats, Set<Integer> visited) {
            long size = 1;
            for(var child : tree.get(node)) {
                if(visited.contains(child)) {
                    continue;
                }

                visited.add(child);
                size += computeMinCost(tree, child, seats, visited);
            }

            minCost += size / seats;
            if(size % seats != 0) {
                minCost++;
            }

            return size;
        }
    }

// [[0,1],[0,2],[0,3]]
// 5

// [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]
// 2
}

//    2477. Minimum Fuel Cost to Report to the Capital
//    Medium
//    There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
//
//    There is a meeting for the representatives of each city. The meeting is in the capital city.
//
//    There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
//
//    A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
//
//    Return the minimum number of liters of fuel to reach the capital city.
//
//
//
//    Example 1:
//
//
//    Input: roads = [[0,1],[0,2],[0,3]], seats = 5
//    Output: 3
//    Explanation:
//    - Representative1 goes directly to the capital with 1 liter of fuel.
//    - Representative2 goes directly to the capital with 1 liter of fuel.
//    - Representative3 goes directly to the capital with 1 liter of fuel.
//    It costs 3 liters of fuel at minimum.
//    It can be proven that 3 is the minimum number of liters of fuel needed.
//    Example 2:
//
//
//    Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
//    Output: 7
//    Explanation:
//    - Representative2 goes directly to city 3 with 1 liter of fuel.
//    - Representative2 and representative3 go together to city 1 with 1 liter of fuel.
//    - Representative2 and representative3 go together to the capital with 1 liter of fuel.
//    - Representative1 goes directly to the capital with 1 liter of fuel.
//    - Representative5 goes directly to the capital with 1 liter of fuel.
//    - Representative6 goes directly to city 4 with 1 liter of fuel.
//    - Representative4 and representative6 go together to the capital with 1 liter of fuel.
//    It costs 7 liters of fuel at minimum.
//    It can be proven that 7 is the minimum number of liters of fuel needed.
//    Example 3:
//
//
//    Input: roads = [], seats = 1
//    Output: 0
//    Explanation: No representatives need to travel to the capital city.
//
//
//    Constraints:
//
//    1 <= n <= 105
//    roads.length == n - 1
//    roads[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    roads represents a valid tree.
//    1 <= seats <= 105