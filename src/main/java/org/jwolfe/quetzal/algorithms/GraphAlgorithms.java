package org.jwolfe.quetzal.algorithms;

import java.util.Arrays;

public class GraphAlgorithms {
    public static int[] dijkstrasShortestPaths(int[][] graph, int numVertices, int startVertex) {
        int[] distances = new int[numVertices];
        boolean[] sptSet = new boolean[numVertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);

        distances[startVertex] = 0;

        for (int i = 0; i < numVertices; i++) {
            int u = getShortestDistanceVertexForSPTSet(distances, sptSet);

            sptSet[u] = true;
            for(int v = 0; v < numVertices; v++) {
                if (!sptSet[v]
                        && graph[u][v] != 0
                        && distances[u] != Integer.MAX_VALUE
                        && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        return distances;
    }

    private static int getShortestDistanceVertexForSPTSet(int[] distances, boolean[] sptSet) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < sptSet.length; i++) {
            if (!sptSet[i]) {
                if (distances[i] <= minValue) {
                    minValue = distances[i];
                    minIndex = i;
                }
            }
        }

        return minIndex;
    }

    public static void primMST(int[][] graph) {
        int vertices = graph.length;
        int[] parent = new int[vertices];
        int[] keys = new int[vertices];
        boolean[] inMst = new boolean[vertices];

        Arrays.fill(keys, Integer.MAX_VALUE);
        Arrays.fill(inMst, false);

        keys[0] = 0;
        parent[0] = -1;

        for(int i = 0; i < vertices; i++) {
            int u = getMinIndexFromKeys(keys, inMst);
            inMst[u] = true;

            for(int v = 0; v < vertices; v++) {
                if(graph[u][v] != 0
                        && inMst[v] == false
                        && graph[u][v] < keys[v]) {
                    parent[v] = u;
                    keys[v] = graph[u][v];
                }
            }
        }

        for(int i = 1; i < vertices; i++) {
            System.out.println((i) + ":\t" + parent[i] + "\t-\t" + i + "\t(" + graph[i][parent[i]] + ")");
        }
    }

    private static int getMinIndexFromKeys(int[] keys, boolean[] inMst) {
        int minKey = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] < minKey
                    && inMst[i] == false) {
                minKey = keys[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}

