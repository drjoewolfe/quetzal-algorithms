package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class CheapestFlightsWithinKStops {
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(n < 1 || flights == null || flights.length == 0 || src < 0 || dst < 0 || src >= n || dst >= n || K < 0) {
                return 0;
            }

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashMap<>());
            }

            for(int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int c = flight[2];

                graph.get(u).put(v, c);
            }

            Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
            queue.offer(new Pair<>(src, 0));

            int round = 0;
            int[] cost = new int[n];
            Arrays.fill(cost, Integer.MAX_VALUE);
            cost[src] = 0;

            while(!queue.isEmpty() && round <= K) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    var node = queue.poll();
                    var u = node.getKey();
                    var c = node.getValue();

                    for(var childNode : graph.get(u).entrySet()) {
                        var v = childNode.getKey();
                        var vc = childNode.getValue();

                        if(c + vc < cost[v]) {
                            cost[v] = c + vc;
                            queue.offer(new Pair<>(v, cost[v]));
                        }
                    }
                }

                round++;
            }

            return cost[dst] == Integer.MAX_VALUE ?  -1 : cost[dst];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_2 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(n < 1 || flights == null || flights.length == 0 || src < 0 || dst < 0 || src >= n || dst >= n || K < 0) {
                return -1;
            }

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashMap<>());
            }

            for(int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int c = flight[2];

                graph.get(u).put(v, c);
            }

            int[] costs = new int[n];
            Arrays.fill(costs, Integer.MAX_VALUE);
            costs[src] = 0;

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {src, 0});
            int iterations = 0;

            while(!queue.isEmpty() && iterations <= K) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int[] node = queue.poll();
                    int u = node[0];
                    int c = node[1];

                    for(var neighbourNode : graph.get(u).entrySet()) {
                        int v = neighbourNode.getKey();
                        int vc = neighbourNode.getValue();

                        if(c + vc < costs[v]) {
                            costs[v] = c + vc;
                            queue.offer(new int[] {v, c + vc});
                        }
                    }
                }

                iterations++;
            }

            return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(flights == null || flights.length == 0) {
                return 0;
            }

            Map<Integer, List<int[]>> map = new HashMap<>();
            for(int[] f : flights) {
                int u = f[0];
                int v = f[1];
                int c = f[2];

                if(!map.containsKey(u)) {
                    map.put(u, new ArrayList<>());
                }

                List<int[]> list = map.get(u);
                list.add(new int[] {v, c});
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
            heap.offer(new int[] {src, 0, K});

            while(!heap.isEmpty()) {
                int[] current = heap.poll();
                int u = current[0];
                int c = current[1];
                int s = current[2];

                if(u == dst) {
                    return c;
                }

                if(s >= 0 && map.containsKey(u)) {
                    for(int[] conn : map.get(u)) {
                        int v = conn[0];
                        int vc = conn[1];

                        heap.offer(new int[] {v, c + vc, s - 1});
                    }
                }
            }

            return -1;
        }
    }

    class Solution_Better {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(flights == null || flights.length == 0) {
                return 0;
            }

            Graph graph = new Graph(n);
            for(int[] flight : flights) {
                if(flight.length != 3) {
                    return 0;
                }

                int u = flight[0];
                int v = flight[1];
                int c = flight[2];

                if(u == v || c < 1) {
                    return 0;
                }

                graph.addDirectedEdge(u, v, c);
            }

            return graph.getLowestCostPath(src, dst, K);
        }

        class Graph {
            int vertexCount;
            int[][] adjacencyGraph;

            public Graph(int vertexCount) {
                this.vertexCount = vertexCount;

                this.adjacencyGraph = new int[vertexCount][vertexCount];
                for(int[] row : adjacencyGraph) {
                    Arrays.fill(row, -1);
                }
            }

            public void addDirectedEdge(int u, int v, int c) {
                adjacencyGraph[u][v] = c;
            }

            public int getLowestCostPath(int src, int dst, int k) {
                PriorityQueue<int[]> heap = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
                heap.offer(new int[] {0, src, k});

                while(!heap.isEmpty()) {
                    int[] current = heap.poll();
                    int u = current[1];
                    int c = current[0];
                    int s = current[2];

                    if(u == dst) {
                        return c;
                    }

                    if(s >= 0) {
                        for(int v = 0; v < this.adjacencyGraph.length; v++) {
                            int vc = this.adjacencyGraph[u][v];
                            if(vc == -1) {
                                continue;
                            }

                            heap.offer(new int[] {c + vc, v, s - 1});
                        }
                    }
                }

                return -1;
            }
        }
    }

    class Solution_BruteDFS {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(flights == null || flights.length == 0) {
                return 0;
            }

            Graph graph = new Graph(n);
            for(int[] flight : flights) {
                if(flight.length != 3) {
                    return 0;
                }

                int u = flight[0];
                int v = flight[1];
                int c = flight[2];

                if(u == v || c < 1) {
                    return 0;
                }

                graph.addDirectedEdge(u, v, c);
            }

            return graph.getLowestCostPath(src, dst, K);
        }

        class Graph {
            int vertexCount;
            int[][] adjacencyGraph;

            public Graph(int vertexCount) {
                this.vertexCount = vertexCount;

                this.adjacencyGraph = new int[vertexCount][vertexCount];
                for(int[] row : adjacencyGraph) {
                    Arrays.fill(row, -1);
                }
            }

            public void addDirectedEdge(int u, int v, int c) {
                adjacencyGraph[u][v] = c;
            }

            public int getLowestCostPath(int src, int dst, int k) {
                List<Integer> visited = new ArrayList<>();
                int minCost = dfsPath(src, dst, k, visited, 0);

                return minCost == Integer.MAX_VALUE ? -1 : minCost;
            }

            private int dfsPath(int u, int dst, int k, List<Integer> visited, int runningCost) {
                if(u == dst) {
                    return runningCost;
                }

                if(visited.size() == k + 1) {
                    return Integer.MAX_VALUE;
                }

                int minCostPath = Integer.MAX_VALUE;
                for(int v = 0; v < this.adjacencyGraph[u].length; v++) {
                    if(visited.contains(v)) {
                        continue;
                    }

                    int c = this.adjacencyGraph[u][v];
                    if(c == -1) {
                        continue;
                    }

                    visited.add(v);
                    minCostPath = Math.min(minCostPath,
                            dfsPath(v, dst, k, visited, runningCost + c));
                    visited.remove(visited.size() - 1);
                }

                return minCostPath;
            }

            private void print() {
                for(int u = 0; u < vertexCount; u++) {
                    for(int v = 0; v < vertexCount; v++) {
                        System.out.print(adjacencyGraph[u][v] + " ");
                    }

                    System.out.println();
                }

                System.out.println();
            }
        }
    }

// 4
// [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
// 0
// 3
// 1

// 13
// [[11,12,74],[1,8,91],[4,6,13],[7,6,39],[5,12,8],[0,12,54],[8,4,32],[0,11,4],[4,0,91],[11,7,64],[6,3,88],[8,5,80],[11,10,91],[10,0,60],[8,7,92],[12,6,78],[6,2,8],[4,3,54],[3,11,76],[3,12,23],[11,6,79],[6,12,36],[2,11,100],[2,5,49],[7,0,17],[5,8,95],[3,9,98],[8,10,61],[2,12,38],[5,7,58],[9,4,37],[8,6,79],[9,0,1],[2,3,12],[7,10,7],[12,10,52],[7,2,68],[12,2,100],[6,9,53],[7,4,90],[0,5,43],[11,2,52],[11,8,50],[12,4,38],[7,9,94],[2,7,38],[3,7,88],[9,12,20],[12,0,26],[10,5,38],[12,8,50],[0,2,77],[11,0,13],[9,10,76],[2,6,67],[5,6,34],[9,7,62],[5,3,67]]
// 10
// 1
// 10

// 11
// [[0,3,3],[3,4,3],[4,1,3],[0,5,1],[5,1,100],[0,6,2],[6,1,100],[0,7,1],[7,8,1],[8,9,1],[9,1,1],[1,10,1],[10,2,1],[1,2,100]]
// 0
// 2
// 4

// 2
// [[0,1,2]]
// 1
// 0
// 0

// 3
// [[0,1,100],[1,2,100],[0,2,500]]
// 0
// 2
// 0
}

//    787. Cheapest Flights Within K Stops
//    Medium
//    There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
//
//    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
//
//
//
//    Example 1:
//
//
//    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//    Output: 700
//    Explanation:
//    The graph is shown above.
//    The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
//    Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
//    Example 2:
//
//
//    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//    Output: 200
//    Explanation:
//    The graph is shown above.
//    The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
//    Example 3:
//
//
//    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//    Output: 500
//    Explanation:
//    The graph is shown above.
//    The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
//
//
//    Constraints:
//
//    1 <= n <= 100
//    0 <= flights.length <= (n * (n - 1) / 2)
//    flights[i].length == 3
//    0 <= fromi, toi < n
//    fromi != toi
//    1 <= pricei <= 104
//    There will not be any multiple flights between two cities.
//    0 <= src, dst, k < n
//    src != dst