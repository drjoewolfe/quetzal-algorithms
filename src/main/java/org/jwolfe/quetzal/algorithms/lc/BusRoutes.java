package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class BusRoutes {
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if(routes == null || routes.length == 0) {
                return -1;
            }

            if(source == target) {
                return 0;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int r = 0; r < routes.length; r++) {
                for(int stop : routes[r]) {
                    if(!graph.containsKey(stop)) {
                        graph.put(stop, new HashSet<>());
                    }

                    graph.get(stop).add(r);
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();

            for(var r : graph.get(source)) {
                queue.offer(r);
                visited.add(r);
            }

            int count = 1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    var r = queue.poll();

                    for(int stop : routes[r]) {
                        if(stop == target) {
                            return count;
                        }

                        for(int nr : graph.get(stop)) {
                            if(visited.contains(nr)) {
                                continue;
                            }

                            visited.add(nr);
                            queue.offer(nr);
                        }
                    }
                }

                count++;
            }

            return -1;
        }
    }

    class Solution_TLE {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if(routes == null || routes.length == 0) {
                return -1;
            }

            if(source == target) {
                return 0;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int[] route : routes) {
                Set<Integer> set = new HashSet<>();
                for(int u : route) {
                    set.add(u);
                    if(!graph.containsKey(u)) {
                        graph.put(u, new HashSet<>());
                    }
                }

                for(var u : set) {
                    graph.get(u).addAll(set);
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            Set<Integer> visited = new HashSet<>();
            visited.add(source);

            int count = 1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    var u = queue.poll();

                    for(var v : graph.get(u)) {
                        if(v == target) {
                            return count;
                        }

                        if(visited.contains(v)) {
                            continue;
                        }

                        visited.add(v);
                        queue.offer(v);
                    }
                }

                count++;
            }

            return -1;
        }
    }

// [[1,2,7],[3,6,7]]
// 1
// 6

// [[7,12],[4,5,15],[6],[15,19],[9,12,13]]
// 15
// 12

// [[1,7],[3,5]]
// 5
// 5
}

//    815. Bus Routes
//    Hard
//    You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
//
//    For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
//    You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
//
//    Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
//
//
//
//    Example 1:
//
//    Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//    Output: 2
//    Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
//    Example 2:
//
//    Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//    Output: -1
//
//
//    Constraints:
//
//    1 <= routes.length <= 500.
//    1 <= routes[i].length <= 105
//    All the values of routes[i] are unique.
//    sum(routes[i].length) <= 105
//    0 <= routes[i][j] < 106
//    0 <= source, target < 106