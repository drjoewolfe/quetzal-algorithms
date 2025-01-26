package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MaximumEmployeesToBeInvitedToAMeeting {
    class Solution {
        public int maximumInvitations(int[] favorite) {
            if (favorite == null || favorite.length == 0) {
                return 0;
            }

            int n = favorite.length;

            // Note: reversed graph. If B is a favourite of A, then A is an admirer of B.
            // Edge then goes from B to A
            List<List<Integer>> graph = new ArrayList(n);
            for (int u = 0; u < n; u++) {
                graph.add(new ArrayList());
            }

            for (int u = 0; u < n; u++) {
                int v = favorite[u];
                graph.get(v).add(u);
            }

            // Find all cycles
            boolean[] visited = new boolean[n];
            int longestCycle = 0;
            int twoCycleChainLength = 0;

            for (int u = 0; u < n; u++) {
                if (visited[u]) {
                    continue;
                }

                // Check for cycle from this person
                int current = u;
                int currentDistance = 0;
                Map<Integer, Integer> distances = new HashMap<>();
                while (true) {
                    if (visited[current]) {
                        break;
                    }

                    visited[current] = true;
                    distances.put(current, currentDistance);
                    currentDistance++;

                    int next = favorite[current];
                    if (distances.containsKey(next)) {
                        // Cycle found at v
                        int currentCycle = currentDistance - distances.get(next);
                        longestCycle = Math.max(longestCycle, currentCycle);

                        if (currentCycle == 2) {
                            Set<Integer> visitedNodes = new HashSet<>();
                            visitedNodes.add(current);
                            visitedNodes.add(next);

                            twoCycleChainLength +=
                                    2 +
                                            getLength(graph, current, visitedNodes) +
                                            getLength(graph, next, visitedNodes);

                        }

                        break;
                    }

                    current = next;
                }
            }

            return Math.max(longestCycle, twoCycleChainLength);
        }

        private int getLength(List<List<Integer>> graph, int u, Set<Integer> visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{u, 0});

            int maxDistance = 0;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int node = curr[0];
                int distance = curr[1];

                for (int neighbour : graph.get(node)) {
                    if (visited.contains(neighbour)) {
                        continue;
                    }

                    visited.add(neighbour);
                    queue.offer(new int[]{neighbour, distance + 1});
                    maxDistance = Math.max(maxDistance, distance + 1);
                }
            }

            return maxDistance;
        }
    }

    class Solution_TLE {
        public int maximumInvitations(int[] favorite) {
            if (favorite == null || favorite.length == 0) {
                return 0;
            }

            int n = favorite.length;

            // Note: reversed graph. If B is a favourite of A, then A is an admirer of B.
            // Edge then goes from B to A
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int u = 0; u < n; u++) {
                int v = favorite[u];
                graph.get(v).add(u);
            }

            // Find all cycles
            boolean[] visited = new boolean[n];
            int longestCycle = 0;
            int twoCycleChainLength = 0;

            for (int u = 0; u < n; u++) {
                if (visited[u]) {
                    continue;
                }

                // Check for cycle from this person
                int currentDistance = 0;
                Map<Integer, Integer> distances = new HashMap<>();
                while (true) {
                    if (visited[u]) {
                        break;
                    }

                    visited[u] = true;
                    distances.put(u, currentDistance);
                    currentDistance++;

                    int v = favorite[u];
                    if (distances.containsKey(v)) {
                        // Cycle found at v
                        int currentCycle = currentDistance - distances.get(v);
                        longestCycle = Math.max(longestCycle, currentCycle);

                        if (currentCycle == 2) {
                            Set<Integer> visitedNodes = new HashSet<>();
                            visitedNodes.add(u);
                            visitedNodes.add(v);

                            twoCycleChainLength += 2 +
                                    getLength(graph, u, visitedNodes) +
                                    getLength(graph, v, visitedNodes);

                        }

                        break;
                    }

                    u = v;
                }
            }

            return Math.max(longestCycle, twoCycleChainLength);
        }

        private int getLength(Map<Integer, Set<Integer>> graph, int u, Set<Integer> visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{u, 0});

            int maxDistance = 0;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int node = curr[0];
                int distance = curr[1];

                for (int neighbour : graph.get(node)) {
                    if (visited.contains(neighbour)) {
                        continue;
                    }

                    visited.add(neighbour);
                    queue.offer(new int[]{neighbour, distance + 1});
                    maxDistance = Math.max(maxDistance, distance + 1);
                }
            }

            return maxDistance;
        }
    }
}

//    2127. Maximum Employees to Be Invited to a Meeting
//    Hard
//    A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.
//
//    The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.
//
//    Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.
//
//
//
//    Example 1:
//
//
//    Input: favorite = [2,2,1,2]
//    Output: 3
//    Explanation:
//    The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
//    All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
//    Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
//    The maximum number of employees that can be invited to the meeting is 3.
//    Example 2:
//
//    Input: favorite = [1,2,0]
//    Output: 3
//    Explanation:
//    Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
//    The seating arrangement will be the same as that in the figure given in example 1:
//    - Employee 0 will sit between employees 2 and 1.
//    - Employee 1 will sit between employees 0 and 2.
//    - Employee 2 will sit between employees 1 and 0.
//    The maximum number of employees that can be invited to the meeting is 3.
//    Example 3:
//
//
//    Input: favorite = [3,0,1,4,1]
//    Output: 4
//    Explanation:
//    The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
//    Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
//    So the company leaves them out of the meeting.
//    The maximum number of employees that can be invited to the meeting is 4.
//
//
//    Constraints:
//
//    n == favorite.length
//    2 <= n <= 105
//    0 <= favorite[i] <= n - 1
//    favorite[i] != i