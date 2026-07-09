package org.jwolfe.quetzal.algorithms.lc;

public class PathExistenceQueriesInAGraphI {
    class Solution {
        public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
            if (n < 1 || maxDiff < 0 || queries == null || queries.length == 0) {
                return new boolean[0];
            }

            DisjointSets sets = new DisjointSets(n);

            for (int u = 0; u < n - 1; u++) {
                int v = u + 1;

                if (Math.abs(nums[u] - nums[v]) <= maxDiff) {
                    sets.union(u, v);
                }
            }

            int m = queries.length;
            boolean[] result = new boolean[m];

            for (int i = 0; i < m; i++) {
                int[] query = queries[i];
                int u = query[0];
                int v = query[1];

                if (sets.find(u) == sets.find(v)) {
                    result[i] = true;
                }
            }

            return result;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;
            int n;

            public DisjointSets(int n) {
                this.n = n;
                parent = new int[n];
                rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int u) {
                if (u != parent[u]) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if (rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else if (rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }
        }
    }

    class Solution_TLE {
        public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
            if (n < 1 || maxDiff < 0 || queries == null || queries.length == 0) {
                return new boolean[0];
            }

            DisjointSets sets = new DisjointSets(n);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(nums[i] - nums[j]) <= maxDiff) {
                        sets.union(i, j);
                    }
                }
            }

            int m = queries.length;
            boolean[] result = new boolean[m];

            for (int i = 0; i < m; i++) {
                int[] query = queries[i];
                int u = query[0];
                int v = query[1];

                if (sets.find(u) == sets.find(v)) {
                    result[i] = true;
                }
            }

            return result;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;
            int n;

            public DisjointSets(int n) {
                this.n = n;
                parent = new int[n];
                rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int u) {
                if (u != parent[u]) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if (rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else if (rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }
        }
    }
}

//    3532. Path Existence Queries in a Graph I
//    Medium
//    You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
//
//    You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.
//
//    An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
//
//    You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a path between nodes ui and vi.
//
//    Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query and false otherwise.
//
//
//
//    Example 1:
//
//    Input: n = 2, nums = [1,3], maxDiff = 1, queries = [[0,0],[0,1]]
//
//    Output: [true,false]
//
//    Explanation:
//
//    Query [0,0]: Node 0 has a trivial path to itself.
//    Query [0,1]: There is no edge between Node 0 and Node 1 because |nums[0] - nums[1]| = |1 - 3| = 2, which is greater than maxDiff.
//    Thus, the final answer after processing all the queries is [true, false].
//    Example 2:
//
//    Input: n = 4, nums = [2,5,6,8], maxDiff = 2, queries = [[0,1],[0,2],[1,3],[2,3]]
//
//    Output: [false,false,true,true]
//
//    Explanation:
//
//    The resulting graph is:
//
//
//
//    Query [0,1]: There is no edge between Node 0 and Node 1 because |nums[0] - nums[1]| = |2 - 5| = 3, which is greater than maxDiff.
//    Query [0,2]: There is no edge between Node 0 and Node 2 because |nums[0] - nums[2]| = |2 - 6| = 4, which is greater than maxDiff.
//    Query [1,3]: There is a path between Node 1 and Node 3 through Node 2 since |nums[1] - nums[2]| = |5 - 6| = 1 and |nums[2] - nums[3]| = |6 - 8| = 2, both of which are within maxDiff.
//    Query [2,3]: There is an edge between Node 2 and Node 3 because |nums[2] - nums[3]| = |6 - 8| = 2, which is equal to maxDiff.
//    Thus, the final answer after processing all the queries is [false, false, true, true].
//
//
//    Constraints:
//
//    1 <= n == nums.length <= 105
//    0 <= nums[i] <= 105
//    nums is sorted in non-decreasing order.
//    0 <= maxDiff <= 105
//    1 <= queries.length <= 105
//    queries[i] == [ui, vi]
//    0 <= ui, vi < n