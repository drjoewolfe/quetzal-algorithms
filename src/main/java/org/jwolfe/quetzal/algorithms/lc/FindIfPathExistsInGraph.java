package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindIfPathExistsInGraph {
    class Solution {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || destination < 0 || source >= n || destination >= n) {
                return false;
            }

            if(source == destination) {
                return true;
            }

            if(edges == null || edges.length == 0) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return dfs(graph, source, destination, new HashSet<>());
        }

        private boolean dfs(Map<Integer, Set<Integer>> graph, int u, int target, Set<Integer> visited) {
            if(u == target) {
                return true;
            }

            visited.add(u);
            for(var v : graph.get(u)) {
                if(!visited.contains(v)
                        && dfs(graph, v, target, visited)) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            if(source == destination) {
                return true;
            }

            if(edges == null || edges.length == 0) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return dfs(graph, source, destination, new HashSet<Integer>());
        }

        private boolean dfs(Map<Integer, Set<Integer>> graph, Integer u, Integer destination, Set<Integer> visited) {
            if(u.equals(destination)) {
                return true;
            }

            visited.add(u);
            for(Integer v : graph.get(u)) {
                if(!visited.contains(v)) {
                    if(dfs(graph, v, destination, visited)) {
                        return true;
                    }
                }

            }

            return false;
        }
    }

    class Solution_BFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            Set<Integer> visited = new HashSet<>();
            while(!queue.isEmpty()) {
                Integer u = queue.poll();
                if(u == destination) {
                    return true;
                }

                visited.add(u);
                for(Integer v : graph.get(u)) {
                    if(!visited.contains(v)) {
                        queue.offer(v);
                    }
                }
            }

            return false;
        }
    }

    class Solution_Iterative_DFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(source);

            Set<Integer> visited = new HashSet<>();
            while(!stack.isEmpty()) {
                Integer u = stack.pop();
                if(u == destination) {
                    return true;
                }

                visited.add(u);
                for(Integer v : graph.get(u)) {
                    if(!visited.contains(v)) {
                        stack.push(v);
                    }
                }
            }

            return false;
        }
    }

    class Solution_Recursive_DFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return dfs(graph, source, destination, new HashSet<>());
        }

        private boolean dfs(Map<Integer, Set<Integer>> graph, Integer u, int destination, Set<Integer> visited) {
            if(u == destination) {
                return true;
            }

            visited.add(u);
            for(Integer v : graph.get(u)) {
                if(!visited.contains(v)
                        && dfs(graph, v, destination, visited)) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Brute_MLE {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            if(source == destination) {
                return true;
            }

            if(edges == null || edges.length == 0) {
                return false;
            }

            boolean[][] graph = new boolean[n][n];
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph[u][v] = true;
                graph[v][u] = true;
            }

            return dfs(graph, n, source, destination, new boolean[n]);
        }

        private boolean dfs(boolean[][] graph, int n, int u, int destination, boolean[] visited) {
            if(u == destination) {
                return true;
            }

            visited[u] = true;
            for(int v = 0; v < n; v++) {
                if(graph[u][v]
                        && !visited[v]) {
                    if(dfs(graph, n, v, destination, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// 3
// [[0,1],[1,2],[2,0]]
// 0
// 2

// 500
// [[266,314],[35,276],[144,79],[242,397],[474,405],[496,110],[288,342],[480,149],[420,495],[52,385],[4,378],[490,7],[491,32],[433,479],[193,209],[342,258],[292,439],[287,281],[453,417],[422,126],[237,439],[340,325],[342,74],[129,84],[355,206],[256,161],[242,334],[114,242],[190,126],[190,455],[476,20],[209,439],[405,252],[345,231],[328,204],[405,365],[393,426],[10,488],[152,486],[170,208],[93,407],[499,439],[486,337],[473,285],[203,422],[181,474],[223,148],[273,100],[313,179],[358,290],[439,314],[75,187],[26,17],[209,137],[53,183],[197,447],[161,423],[447,368],[338,495],[117,192],[113,490],[150,423],[60,102],[447,43],[94,11],[423,334],[485,101],[155,380],[352,390],[380,206],[449,95],[345,456],[256,342],[270,124],[409,422],[107,246],[2,200],[12,58],[213,62],[429,439],[363,439],[342,174],[204,439],[211,402],[369,479],[59,49],[272,210],[449,474],[70,191],[444,265],[117,266],[439,467],[439,26],[487,495],[369,295],[273,439],[488,439],[310,405],[331,210],[356,133],[16,44],[353,439],[282,454],[4...
// 217
// 308
}

//    1971. Find if Path Exists in Graph
//    Easy
//    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
//
//    You want to determine if there is a valid path that exists from vertex source to vertex destination.
//
//    Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//    Output: true
//    Explanation: There are two paths from vertex 0 to vertex 2:
//    - 0 → 1 → 2
//    - 0 → 2
//    Example 2:
//
//
//    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
//    Output: false
//    Explanation: There is no path from vertex 0 to vertex 5.
//
//
//    Constraints:
//
//    1 <= n <= 2 * 105
//    0 <= edges.length <= 2 * 105
//    edges[i].length == 2
//    0 <= ui, vi <= n - 1
//    ui != vi
//    0 <= source, destination <= n - 1
//    There are no duplicate edges.
//    There are no self edges.