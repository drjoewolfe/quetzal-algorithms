package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindBuildingWhereAliceAndBobCanMeet {
    class Solution {
        public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
            if (heights == null || heights.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = heights.length;
            int qn = queries.length;

            // queriesBySI -> queriesBySecondIndex
            Map<Integer, List<int[]>> queriesBySI = new HashMap<>();

            int[] results = new int[qn];
            Arrays.fill(results, -1);

            for (int index = 0; index < qn; index++) {
                int[] query = queries[index];
                int i = query[0];
                int j = query[1];

                if (i > j) {
                    // Swap
                    int t = i;
                    i = j;
                    j = t;
                }

                if (i == j || heights[j] > heights[i]) {
                    results[index] = j;
                } else {
                    queriesBySI.computeIfAbsent(j, k -> new ArrayList<>());
                    queriesBySI.get(j).add(new int[]{heights[i], index, i, j});
                }
            }

            // mono-stack contains [height, index] combinations
            List<int[]> monoStack = new ArrayList<>();
            for (int j = n - 1; j >= 0; j--) {
                int hj = heights[j];

                if (queriesBySI.containsKey(j)) {
                    for (int[] query : queriesBySI.get(j)) {
                        int hi = query[0];
                        int index = query[1];

                        int pos = binarySearch(monoStack, hi);
                        if (pos >= 0 && pos < monoStack.size()) {
                            results[index] = monoStack.get(pos)[1];
                        }
                    }
                }

                while (!monoStack.isEmpty()
                        && monoStack.get(monoStack.size() - 1)[0] <= hj) {
                    monoStack.remove(monoStack.size() - 1);
                }

                monoStack.add(new int[]{hj, j});
            }

            return results;
        }

        private int binarySearch(List<int[]> monoStack, int height) {
            int left = 0;
            int right = monoStack.size() - 1;
            int pos = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int[] element = monoStack.get(mid);
                int hm = element[0];
                int im = element[1];

                if (hm > height) {
                    pos = Math.max(pos, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return pos;
        }

        private void printStack(List<int[]> monoStack) {
            for (int[] element : monoStack) {
                System.out.print("[" + element[0] + ", " + element[1] + "] ");
            }

            System.out.println();
        }

        private void printQueries(Map<Integer, List<int[]>> queriesBySI) {
            for (var entry : queriesBySI.entrySet()) {
                int key = entry.getKey();
                var value = entry.getValue();
                System.out.println("Key: " + key);
                for (int[] element : value) {
                    System.out.println("\t{" + element[0] + ", " + element[1] + ", " + element[2] + ", " + element[3] + "}");
                }
            }

            System.out.println();
        }
    }

    class Solution_Debug {
        public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
            if (heights == null || heights.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = heights.length;
            int qn = queries.length;

            // queriesBySI -> queriesBySecondIndex
            Map<Integer, List<int[]>> queriesBySI = new HashMap<>();

            int[] results = new int[qn];
            Arrays.fill(results, -1);

            for (int index = 0; index < qn; index++) {
                int[] query = queries[index];
                int i = query[0];
                int j = query[1];

                if (i > j) {
                    // Swap
                    int t = i;
                    i = j;
                    j = t;
                }

                if (i == j || heights[j] > heights[i]) {
                    results[index] = j;
                } else {
                    queriesBySI.computeIfAbsent(j, k -> new ArrayList<>());
                    queriesBySI.get(j).add(new int[]{heights[i], index, i, j});
                }
            }
            printQueries(queriesBySI);

            // mono-stack contains [height, index] combinations
            List<int[]> monoStack = new ArrayList<>();
            for (int j = n - 1; j >= 0; j--) {
                System.out.println("Processing " + j);
                System.out.print("Stack: ");
                printStack(monoStack);
                int hj = heights[j];
                System.out.println("HJ : " + hj);

                if (queriesBySI.containsKey(j)) {
                    for (int[] query : queriesBySI.get(j)) {
                        int hi = query[0];
                        int index = query[1];

                        int pos = binarySearch(monoStack, hi);
                        System.out.println("\t" + j + " -> " + pos);
                        if (pos >= 0 && pos < monoStack.size()) {
                            results[index] = monoStack.get(pos)[1];
                        }
                    }
                }

                while (!monoStack.isEmpty()
                        && monoStack.get(monoStack.size() - 1)[0] <= hj) {
                    monoStack.remove(monoStack.size() - 1);
                }

                monoStack.add(new int[]{hj, j});
            }

            return results;
        }

        private int binarySearch(List<int[]> monoStack, int height) {
            System.out.println("\tSearch -> Height: " + height);
            int left = 0;
            int right = monoStack.size() - 1;
            int pos = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int[] element = monoStack.get(mid);
                int hm = element[0];
                int im = element[1];

                if (hm > height) {
                    pos = Math.max(pos, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return pos;
        }

        private void printStack(List<int[]> monoStack) {
            for (int[] element : monoStack) {
                System.out.print("[" + element[0] + ", " + element[1] + "] ");
            }

            System.out.println();
        }

        private void printQueries(Map<Integer, List<int[]>> queriesBySI) {
            for (var entry : queriesBySI.entrySet()) {
                int key = entry.getKey();
                var value = entry.getValue();
                System.out.println("Key: " + key);
                for (int[] element : value) {
                    System.out.println("\t{" + element[0] + ", " + element[1] + ", " + element[2] + ", " + element[3] + "}");
                }
            }

            System.out.println();
        }
    }
}

//    2940. Find Building Where Alice and Bob Can Meet
//    Hard
//    You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.
//
//    If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].
//
//    You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.
//
//    Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
//
//
//
//    Example 1:
//
//    Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
//    Output: [2,5,-1,5,2]
//    Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
//    In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
//    In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
//    In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
//    In the fifth query, Alice and Bob are already in the same building.
//    For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//    For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
//    Example 2:
//
//    Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
//    Output: [7,6,-1,4,6]
//    Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
//    In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
//    In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
//    In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
//    In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
//    For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//    For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
//
//
//
//    Constraints:
//
//    1 <= heights.length <= 5 * 104
//    1 <= heights[i] <= 109
//    1 <= queries.length <= 5 * 104
//    queries[i] = [ai, bi]
//    0 <= ai, bi <= heights.length - 1