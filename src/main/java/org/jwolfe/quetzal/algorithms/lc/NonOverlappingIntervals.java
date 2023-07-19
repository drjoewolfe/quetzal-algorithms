package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NonOverlappingIntervals {
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, (a, b) -> {
                if(a[1] == b[1]) {
                    return a[0] - b[0];
                }

                return a[1] - b[1];
            });

            int intervalsToRemove = 0;
            int lastIntervalEnd = Integer.MIN_VALUE;
            for(int[] interval : intervals) {
                if(lastIntervalEnd > interval[0]) {
                    intervalsToRemove++;
                } else {
                    lastIntervalEnd = interval[1];
                }
            }

            return intervalsToRemove;
        }
    }

    class Solution_Correct_1 {
        public int eraseOverlapIntervals(int[][] intervals) {
            if(intervals == null || intervals.length == 0 || intervals[0].length == 0) {
                return 0;
            }

            Arrays.sort(intervals, (i1, i2) -> {
                if(i1[1] != i2[1]) {
                    return i1[1] - i2[1];
                }

                return i2[0] - i1[0];
            });

            int removeCount = 0;
            int lastEnd = Integer.MIN_VALUE;
            for(int[] iv : intervals) {
                if(lastEnd > iv[0]) {
                    removeCount++;
                } else {
                    lastEnd = iv[1];
                }
            }

            return removeCount;
        }

        private void print(int[][] intervals) {
            for(int[] iv : intervals) {
                System.out.print("[" + iv[0] + ", " + iv[1] + "]" + " ");
            }

            System.out.println();
        }
    }
}

//    435. Non-overlapping Intervals
//    Medium
//    Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
//
//
//
//    Example 1:
//
//    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
//    Output: 1
//    Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
//    Example 2:
//
//    Input: intervals = [[1,2],[1,2],[1,2]]
//    Output: 2
//    Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
//    Example 3:
//
//    Input: intervals = [[1,2],[2,3]]
//    Output: 0
//    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
//
//
//    Constraints:
//
//    1 <= intervals.length <= 105
//    intervals[i].length == 2
//    -5 * 104 <= starti < endi <= 5 * 104