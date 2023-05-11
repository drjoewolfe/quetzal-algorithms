package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UncrossedLines {
    class Solution {
        public int maxUncrossedLines(int[] A, int[] B) {
            if(A == null || B == null || A.length == 0 || B.length == 0) {
                return 0;
            }

            int m = A.length;
            int n = B.length;

            int[][] dp = new int[m + 1][n + 1];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int a = A[i];
                    int b = B[j];

                    if(a == b) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {
                        dp[i + 1][j + 1] = Math.max(
                                dp[i + 1][j],
                                dp[i][j + 1]
                        );
                    }
                }
            }

            return dp[m][n];
        }
    }

    class Solution_Correct_1 {
        public int maxUncrossedLines(int[] A, int[] B) {
            if(A == null || B == null || A.length == 0 || B.length == 0) {
                return 0;
            }

            int m = A.length;
            int n = B.length;

            int[][] dp = new int[m + 1][n + 1];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(A[i] == B[j]) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {
                        dp[i + 1][j + 1] = Math.max(dp[i + 1][j],
                                dp[i][j + 1]);
                    }
                }
            }

            return dp[m][n];
        }
    }

    class Solution_Classic {
        public int maxUncrossedLines(int[] A, int[] B) {
            if(A == null || B == null || A.length == 0 || B.length == 0) {
                return 0;
            }

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < B.length; i++) {
                int n = B[i];
                if(!map.containsKey(n)) {
                    map.put(n, new ArrayList<>());
                }

                map.get(n).add(i);
            }

            return maxUncrossedLines(A, B, 0, 0, map, 0);
        }

        private int maxUncrossedLines(int[] A, int[] B, int ai, int bi, Map<Integer, List<Integer>> map, int runningCount) {
            if(ai == A.length) {
                return runningCount;
            }

            int n = A[ai];

            int newBI = -1;
            if(map.containsKey(n)) {
                List<Integer> bIndexes = map.get(n);
                for(int i = 0; i < bIndexes.size(); i++) {
                    int canBI = bIndexes.get(i);
                    if(canBI >= bi) {
                        newBI = canBI;
                        break;
                    }
                }
            }

            if(newBI == -1) {
                return maxUncrossedLines(A, B, ai + 1, bi, map, runningCount);
            } else {
                return Math.max(
                        maxUncrossedLines(A, B, ai + 1, newBI + 1, map, runningCount + 1),
                        maxUncrossedLines(A, B, ai + 1, bi, map, runningCount)
                );
            }
        }

        private void print(Map<Integer, List<Integer>> map) {
            for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                int key = entry.getKey();
                List<Integer> value = entry.getValue();

                System.out.print(key + ": ");
                for(int v : value) {
                    System.out.print(v + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}

//    1035. Uncrossed Lines
//    Medium
//    You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
//
//    We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
//
//    nums1[i] == nums2[j], and
//    the line we draw does not intersect any other connecting (non-horizontal) line.
//    Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
//
//    Return the maximum number of connecting lines we can draw in this way.
//
//
//
//    Example 1:
//
//
//    Input: nums1 = [1,4,2], nums2 = [1,2,4]
//    Output: 2
//    Explanation: We can draw 2 uncrossed lines as in the diagram.
//    We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
//    Example 2:
//
//    Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
//    Output: 3
//    Example 3:
//
//    Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
//    Output: 2
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 500
//    1 <= nums1[i], nums2[j] <= 2000