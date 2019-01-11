package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.graph.AdjacencyGraph;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
			for (int v = 0; v < numVertices; v++) {
				if (!sptSet[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE
						&& distances[u] + graph[u][v] < distances[v]) {
					distances[v] = distances[u] + graph[u][v];
				}
			}
		}

		return distances;
	}

	public static int[] dialsShortestPaths(int[][] graph, int numVertices, int startVertex) {
		if (graph == null || graph.length > numVertices || startVertex >= numVertices) {
			return null;
		}

		int maxWeight = Utilities.getMaxValue(graph);
		int numBuckets = maxWeight * numVertices + 1;

		int[] distances = new int[numVertices];
		List<LinkedList<Integer>> buckets = new ArrayList<>();

		Arrays.fill(distances, Integer.MAX_VALUE);
		for (int i = 0; i < numBuckets; i++) {
			buckets.add(new LinkedList<>());
		}

		distances[startVertex] = 0;
		buckets.get(0).offer(startVertex);

		int bucketIndex = 0;
		while (true) {
			// Find first non-empty bucket
			for (bucketIndex = 0; bucketIndex < numBuckets; bucketIndex++) {
				if (buckets.get(bucketIndex).size() > 0) {
					break;
				}
			}

			if (bucketIndex == numBuckets) {
				// All buckets are empty
				break;
			}

			var u = buckets.get(bucketIndex).poll();
			for (Integer v = 0; v < numVertices; v++) {
				if (u == v || graph[u][v] == 0) {
					continue;
				}

				// v is adjacent to u
				int w = graph[u][v];

				int du = distances[u];
				int dv = distances[v];

				if (du + w < dv) {
					if (dv != Integer.MAX_VALUE) {
						// Remove v from its old bucket
						buckets.get(dv).remove(v);
					}

					// Add v to the new weight bucket
					dv = du + w;
					distances[v] = dv;
					buckets.get(dv).add(v);
				}
			}
		}

		return distances;
	}

	public static int[][] floydWarshall(int[][] graph, int numVertices, int startVertex) {
		// Shortest paths from every vertex to every other vertex. O(V^3) 

		int[][] shortestPaths = new int[numVertices][numVertices];

		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				if (i == j) {
					shortestPaths[i][i] = 0;
				} else if (graph[i][j] != 0) {
					shortestPaths[i][j] = graph[i][j];
				} else {
					shortestPaths[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int k = 0; k < numVertices; k++) {
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					if (shortestPaths[i][k] != Integer.MAX_VALUE
							&& shortestPaths[k][j] != Integer.MAX_VALUE
							&& shortestPaths[i][k] + shortestPaths[k][j] < shortestPaths[i][j]) {
						shortestPaths[i][j] = shortestPaths[i][k] + shortestPaths[k][j];
					}
				}
			}
		}

		return shortestPaths;
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

		for (int i = 0; i < vertices; i++) {
			int u = getMinIndexFromKeys(keys, inMst);
			inMst[u] = true;

			for (int v = 0; v < vertices; v++) {
				if (graph[u][v] != 0 && inMst[v] == false && graph[u][v] < keys[v]) {
					parent[v] = u;
					keys[v] = graph[u][v];
				}
			}
		}

		for (int i = 1; i < vertices; i++) {
			System.out.println((i) + ":\t" + parent[i] + "\t-\t" + i + "\t(" + graph[i][parent[i]] + ")");
		}
	}

	private static int getMinIndexFromKeys(int[] keys, boolean[] inMst) {
		int minKey = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] < minKey && inMst[i] == false) {
				minKey = keys[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

	public static int[] topologicalSort(AdjacencyGraph graph) {
		// Works only for a Directed Acyclic Graph

		if (graph == null) {
			return null;
		}

		boolean[] visited = new boolean[graph.getVertexCount()];
		Arrays.fill(visited, false);

		Stack<Integer> stack = new Stack<>();

		for (int u = 0; u < graph.getVertexCount(); u++) {
			if (!visited[u]) {
				topologicalSort(graph, u, visited, stack);
			}
		}

		int[] sortedTopology = new int[graph.getVertexCount()];
		int index = 0;
		while (!stack.isEmpty()) {
			sortedTopology[index++] = stack.pop();
		}

		return sortedTopology;
	}

	private static void topologicalSort(AdjacencyGraph graph, int u, boolean[] visited, Stack<Integer> stack) {
		visited[u] = true;

		for (var v : graph.getAdjacencyList()[u]) {
			if (!visited[v]) {
				topologicalSort(graph, v, visited, stack);
			}
		}

		stack.push(u);
	}

	public static int[] getHamiltonianCycle(int[][] graph, int startVertex) {
		if (graph == null || graph.length == 0) {
			return null;
		}

		int numVertices = graph.length;
		for (var list : graph) {
			if (list == null || list.length != numVertices) {
				return null;
			}
		}

		if (startVertex >= numVertices) {
			return null;
		}

		int[] path = new int[numVertices + 1];
		Arrays.fill(path, -1);
		path[0] = startVertex;

		boolean hamiltonianCycleFound = constructHamiltonianCycle(graph, startVertex, numVertices, path, 1);
		if (!hamiltonianCycleFound) {
			return null;
		}

		return path;
	}

	private static boolean constructHamiltonianCycle(int[][] graph, int startVertex, int numVertices, int[] path, int pathIndex) {
		if (pathIndex == numVertices
				&& graph[path[pathIndex - 1]][path[startVertex]] != 0) {
			path[pathIndex] = startVertex;
			return true;
		}


		for (int vertex = 0; vertex < numVertices; vertex++) {
			if (isValildForHamiltonianPath(graph, path, pathIndex, vertex)) {
				path[pathIndex] = vertex;
				if (constructHamiltonianCycle(graph, startVertex, numVertices, path, pathIndex + 1)) {
					return true;
				}

				// Didn't work out. Backtrack
				path[pathIndex] = -1;
			}
		}

		return false;
	}

	private static boolean isValildForHamiltonianPath(int[][] graph, int[] path, int pathIndex, int vertex) {
		// Ensure vertex not seen in path till now.
		for (int i = 0; i < pathIndex; i++) {
			if (path[i] == vertex) {
				return false;
			}
		}

		// Ensure there is a path from the last vertex in the path to this vertex
		if (graph[path[pathIndex - 1]][vertex] == 0) {
			return false;
		}

		return true;
	}
}
