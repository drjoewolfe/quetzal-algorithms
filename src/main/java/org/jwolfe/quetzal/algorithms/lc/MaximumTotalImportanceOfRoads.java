package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumTotalImportanceOfRoads {
    class Solution {
        public long maximumImportance(int n, int[][] roads) {
            if (n < 1) {
                return 0;
            }

            int[] degrees = new int[n];
            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];

                degrees[u]++;
                degrees[v]++;
            }

            Arrays.sort(degrees);
            long maxTotalImportance = 0;
            for (int i = 0; i < n; i++) {
                maxTotalImportance += 1L * degrees[i] * (i + 1);
            }

            return maxTotalImportance;
        }
    }

    class Solution_Correct_2 {
        public long maximumImportance(int n, int[][] roads) {
            if (n < 1) {
                return 0;
            }

            int[] degrees = new int[n];
            for (int[] road : roads) {
                degrees[road[0]]++;
                degrees[road[1]]++;
            }

            Arrays.sort(degrees);
            long total = 0;
            for (int i = 0; i < n; i++) {
                total += 1L * degrees[i] * (i + 1);
            }

            return total;
        }
    }

    class Solution_Correct_1 {
        public long maximumImportance(int n, int[][] roads) {
            if (n < 1) {
                return 0;
            }

            int[] edgeCounts = new int[n];
            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];

                edgeCounts[u]++;
                edgeCounts[v]++;
            }

            List<int[]> list = new ArrayList<>();
            for (int u = 0; u < n; u++) {
                int c = edgeCounts[u];

                list.add(new int[]{u, c});
            }

            Collections.sort(list, (a, b) -> a[1] - b[1]);
            int[] importances = new int[n];
            int importance = 1;
            for (int i = 0; i < n; i++) {
                int u = list.get(i)[0];
                importances[u] = importance++;
            }

            long total = 0;
            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];

                total += importances[u];
                total += importances[v];
            }

            return total;
        }
    }

// 5
// [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
}

//    2285. Maximum Total Importance of Roads
//    Medium
//    You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.
//
//    You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
//
//    You need to assign each city with an integer value from 1 to n, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.
//
//    Return the maximum total importance of all roads possible after assigning the values optimally.
//
//
//
//    Example 1:
//
//
//    Input: n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
//    Output: 43
//    Explanation: The figure above shows the country and the assigned values of [2,4,5,3,1].
//    - The road (0,1) has an importance of 2 + 4 = 6.
//    - The road (1,2) has an importance of 4 + 5 = 9.
//    - The road (2,3) has an importance of 5 + 3 = 8.
//    - The road (0,2) has an importance of 2 + 5 = 7.
//    - The road (1,3) has an importance of 4 + 3 = 7.
//    - The road (2,4) has an importance of 5 + 1 = 6.
//    The total importance of all roads is 6 + 9 + 8 + 7 + 7 + 6 = 43.
//    It can be shown that we cannot obtain a greater total importance than 43.
//    Example 2:
//
//
//    Input: n = 5, roads = [[0,3],[2,4],[1,3]]
//    Output: 20
//    Explanation: The figure above shows the country and the assigned values of [4,3,2,5,1].
//    - The road (0,3) has an importance of 4 + 5 = 9.
//    - The road (2,4) has an importance of 2 + 1 = 3.
//    - The road (1,3) has an importance of 3 + 5 = 8.
//    The total importance of all roads is 9 + 3 + 8 = 20.
//    It can be shown that we cannot obtain a greater total importance than 20.
//
//
//    Constraints:
//
//    2 <= n <= 5 * 104
//    1 <= roads.length <= 5 * 104
//    roads[i].length == 2
//    0 <= ai, bi <= n - 1
//    ai != bi
//    There are no duplicate roads.