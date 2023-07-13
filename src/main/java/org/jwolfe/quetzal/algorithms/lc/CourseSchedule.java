package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class CourseSchedule {
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) {
                return false;
            }

            if(prerequisites == null || prerequisites.length < 2) {
                return true;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < numCourses; u++) {
                graph.put(u, new HashSet<>());
            }

            int[] inDegrees = new int[numCourses];
            for(int[] edge : prerequisites) {
                int u = edge[1];
                int v = edge[0];

                inDegrees[v]++;
                graph.get(u).add(v);
            }

            Queue<Integer> queue = new LinkedList<>();
            for(int u = 0; u < numCourses; u++) {
                if(inDegrees[u] == 0) {
                    queue.offer(u);
                }
            }

            int completedCourses = 0;
            while(!queue.isEmpty()) {
                var u = queue.poll();
                completedCourses++;

                for(var v : graph.get(u)) {
                    inDegrees[v]--;

                    if(inDegrees[v] == 0) {
                        queue.offer(v);
                    }
                }
            }

            return completedCourses == numCourses;
        }
    }

    class Solution_Correct_1 {
        class Graph {
            int vertexCount;
            List<LinkedList<Integer>> adjacencyList;

            public Graph(int vertexCount) {
                this.vertexCount = vertexCount;

                adjacencyList = new ArrayList<>();
                for(int i = 0; i < vertexCount; i++) {
                    adjacencyList.add(new LinkedList<>());
                }
            }

            public void addDirectedEdge(int u, int v) {
                adjacencyList.get(u).add(v);
            }

            public void print() {
                for(int u = 0; u < vertexCount; u++) {
                    System.out.print(u + "[ ");
                    for(int v : adjacencyList.get(u)) {
                        System.out.print(v + " ");
                    }

                    System.out.println("]");
                }
            }
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses < 2 || prerequisites == null || prerequisites.length == 0) {
                return true;
            }

            Graph graph = new Graph(numCourses);
            for(int i = 0; i < prerequisites.length; i++) {
                int[] dependency = prerequisites[i];

                if(dependency.length != 2) {
                    return false;
                }

                int u = dependency[0];
                int v = dependency[1];

                if(u == v || u < 0 || u >= numCourses || v < 0 || v >= numCourses) {
                    return false;
                }

                graph.addDirectedEdge(u, v);
            }

            boolean[] visited = new boolean[numCourses];
            List<Integer> stack = new ArrayList<>();

            for(int u = 0; u < numCourses; u++) {
                if(!visited[u]
                        && hasCycle(graph, u, visited, stack)) {
                    return false;
                }
            }

            return true;
        }

        private boolean hasCycle(Graph graph, int u, boolean[] visited, List<Integer> stack) {
            if(stack.contains(u)) {
                return true;
            }

            if(visited[u]) {
                return false;
            }

            visited[u] = true;
            stack.add(u);

            for(int v : graph.adjacencyList.get(u)) {
                if(hasCycle(graph, v, visited, stack)) {
                    return true;
                }
            }

            stack.remove(stack.size() - 1);
            return false;
        }
    }
}

//    207. Course Schedule
//    Medium
//    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//    Return true if you can finish all courses. Otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: numCourses = 2, prerequisites = [[1,0]]
//    Output: true
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0. So it is possible.
//    Example 2:
//
//    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//    Output: false
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//
//
//    Constraints:
//
//    1 <= numCourses <= 2000
//    0 <= prerequisites.length <= 5000
//    prerequisites[i].length == 2
//    0 <= ai, bi < numCourses
//    All the pairs prerequisites[i] are unique.