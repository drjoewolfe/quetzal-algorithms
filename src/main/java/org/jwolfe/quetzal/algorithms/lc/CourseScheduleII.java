package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class CourseScheduleII {
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses <= 0) {
                return new int[0];
            }

            Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
            for(int i = 0; i < numCourses; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }

            for(int[] pair : prerequisites) {
                int v = pair[0];
                int u = pair[1];

                adjacencyList.get(u).add(v);
            }

            if(hasCycle(adjacencyList, numCourses)) {
                return new int[0];
            }

            return topologicalSort(adjacencyList, numCourses);
        }

        private boolean hasCycle(Map<Integer, List<Integer>> adjacencyList, int numCourses) {
            boolean[] visited = new boolean[numCourses];
            boolean[] stack = new boolean[numCourses];

            for(int u = 0; u < numCourses; u++) {
                if(visited[u]) {
                    continue;
                }

                if(hasCycle(adjacencyList, u, visited, stack)) {
                    return true;
                }
            }

            return false;
        }

        private boolean hasCycle(Map<Integer, List<Integer>> adjacencyList, int u, boolean[] visited, boolean[] stack) {
            visited[u] = true;
            stack[u] = true;

            for(Integer v : adjacencyList.get(u)) {
                if(stack[v]) {
                    return true;
                }

                if(!visited[v]
                        && hasCycle(adjacencyList, v, visited, stack)) {
                    return true;
                }
            }

            stack[u] = false;
            return false;
        }

        private int[] topologicalSort(Map<Integer, List<Integer>> adjacencyList, int numCourses) {
            boolean[] visited = new boolean[numCourses];
            List<Integer> reverseSortResults = new ArrayList<>();
            for(int u = 0; u < numCourses; u++) {
                topologicalSort(adjacencyList, u, visited, reverseSortResults);
            }

            int[] results = new int[numCourses];
            for(int i = 0; i < numCourses; i++) {
                results[i] = reverseSortResults.get(numCourses - i - 1);
            }

            return results;
        }

        private void topologicalSort(Map<Integer, List<Integer>> adjacencyList, int u, boolean[] visited, List<Integer> reverseSortResults) {
            if(visited[u]) {
                return;
            }

            visited[u] = true;
            for(Integer v : adjacencyList.get(u)) {
                topologicalSort(adjacencyList, v, visited, reverseSortResults);
            }

            reverseSortResults.add(u);
        }

        private void print(boolean[] arr) {
            for(boolean a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses < 1)  {
                return new int[0];
            }

            Graph graph = new Graph(numCourses);
            for(int[] dependency : prerequisites) {
                if(dependency.length != 2) {
                    return new int[0];
                }

                graph.addDirectedEdge(dependency[1], dependency[0]);
            }

            return graph.getTopologicalSort();
        }

        class Graph {
            int vertexCount;
            Map<Integer, List<Integer>> adjacencyList;

            public Graph(int vertexCount) {
                this.vertexCount = vertexCount;

                this.adjacencyList = new HashMap<>();
                for(int i = 0; i < vertexCount; i++) {
                    this.adjacencyList.put(i, new LinkedList<>());
                }
            }

            public void addDirectedEdge(int u, int v) {
                adjacencyList.get(u).add(v);
            }

            public boolean hasCycle() {
                Set<Integer> visited = new HashSet<>();
                for(int u = 0; u < vertexCount; u++) {
                    if(!visited.contains(u)) {
                        if(hasCycle(u, new HashSet<>(), visited)) {
                            return true;
                        }
                    }
                }

                return false;
            }

            private boolean hasCycle(int u, Set<Integer> currentStack, Set<Integer> visited) {
                visited.add(u);
                currentStack.add(u);

                for(Integer v : adjacencyList.get(u)) {
                    if(currentStack.contains(v)) {
                        return true;
                    }

                    if(!visited.contains(v)
                            && hasCycle(v, currentStack, visited)) {
                        return true;
                    }
                }

                currentStack.remove(u);
                return false;
            }

            public int[] getTopologicalSort() {
                if(hasCycle()) {
                    return new int[0];
                }

                List<Integer> reverseTopologicalSort = new ArrayList<>();
                Set<Integer> visited = new HashSet<>();

                for(int u = 0; u < vertexCount; u++) {
                    if(!visited.contains(u)) {
                        generateTopologicalSort(u, visited, reverseTopologicalSort);
                    }
                }

                int[] sortResults = new int[vertexCount];
                for(int i = 0; i < vertexCount; i++) {
                    sortResults[i] = reverseTopologicalSort.get(vertexCount - i - 1);
                }

                return sortResults;
            }

            private void generateTopologicalSort(int u, Set<Integer> visited, List<Integer> reverseTopologicalSort) {
                visited.add(u);

                for(Integer v : adjacencyList.get(u)) {
                    if(!visited.contains(v)) {
                        generateTopologicalSort(v, visited, reverseTopologicalSort);
                    }
                }

                reverseTopologicalSort.add(u);
            }
        }
    }
}

//    210. Course Schedule II
//    Medium
//    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//    Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//
//
//
//    Example 1:
//
//    Input: numCourses = 2, prerequisites = [[1,0]]
//    Output: [0,1]
//    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//    Example 2:
//
//    Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//    Output: [0,2,1,3]
//    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//    So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//    Example 3:
//
//    Input: numCourses = 1, prerequisites = []
//    Output: [0]
//
//
//    Constraints:
//
//    1 <= numCourses <= 2000
//    0 <= prerequisites.length <= numCourses * (numCourses - 1)
//    prerequisites[i].length == 2
//    0 <= ai, bi < numCourses
//    ai != bi
//    All the pairs [ai, bi] are distinct.
