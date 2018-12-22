package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.graph.AdjacencyGraph;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {
    @Test
    void dijkstrasShortestPaths() {
        int[][] graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        var distances = GraphAlgorithms.dijkstrasShortestPaths(graph, 9, 0);
        var expectedDistances = Utilities.constructArray(0, 4, 12, 19, 21, 11, 9, 8, 14);
        Utilities.printArray(distances);
        assertArrayEquals(expectedDistances, distances);
    }
    
    @Test
    void dialsShortestPaths() {
        int[][] graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        
        var distances = GraphAlgorithms.dialsShortestPaths(graph, 9, 0);
        var expectedDistances = Utilities.constructArray(0, 4, 12, 19, 21, 11, 9, 8, 14);
        Utilities.printArray(distances);
        assertArrayEquals(expectedDistances, distances);
    }

    @Test
    void primMST() {
        int graph[][] = new int[][] {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        GraphAlgorithms.primMST(graph);
    }

    @Test
    void topologicalSort() {
        AdjacencyGraph graph;
        int[] topologicalSort;
        int[] expectedTopologicalSort;

        graph = new AdjacencyGraph(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        expectedTopologicalSort = Utilities.constructArray(5, 4, 2, 3, 1, 0);
        topologicalSort = GraphAlgorithms.topologicalSort(graph);
        Utilities.printArray(topologicalSort);
        assertArrayEquals(expectedTopologicalSort, topologicalSort);
    }
}