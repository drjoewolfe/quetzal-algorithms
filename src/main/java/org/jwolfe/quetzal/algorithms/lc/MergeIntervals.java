package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return new int[0][0];
            }

            Arrays.sort(intervals, (a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            List<int[]> mergedIntervals = new ArrayList<>();
            int start = intervals[0][0];
            int end = intervals[0][1];
            for(int i = 1; i < intervals.length; i++) {
                int[] interval = intervals[i];
                int a = interval[0];
                int b = interval[1];

                if(a > end) {
                    // No overlap
                    mergedIntervals.add(new int[] {start, end});
                    start = a;
                    end = b;
                } else {
                    // Overlap
                    end = Math.max(end, b);
                }
            }

            mergedIntervals.add(new int[] {start, end});

            int[][] results = new int[mergedIntervals.size()][2];
            for(int i = 0; i < mergedIntervals.size(); i++) {
                results[i][0] = mergedIntervals.get(i)[0];
                results[i][1] = mergedIntervals.get(i)[1];
            }

            return results;
        }
    }

    class Solution_Correct_2 {
        public int[][] merge(int[][] intervals) {
            if(intervals == null || intervals.length == 0 || intervals[0].length != 2) {
                return new int[0][0];
            }

            Arrays.sort(intervals, (a, b) -> {if(a[0] != b[0]) {return a[0] - b[0];} else {return a[1] - b[1];}});

            List<int[]> mergedIntervals = new ArrayList<>();
            int[] curr = intervals[0];
            for(int i = 1; i < intervals.length; i++) {
                int[] next = intervals[i];

                if(curr[1] < next[0]) {
                    // no overlap
                    mergedIntervals.add(curr);
                    curr = next;
                } else {
                    curr[1] = Math.max(curr[1], next[1]);
                }
            }

            mergedIntervals.add(curr);

            int[][] results = new int[mergedIntervals.size()][2];
            int index = 0;
            for(var interval : mergedIntervals) {
                results[index++] = interval;
            }

            return results;
        }
    }
}

//    56. Merge Intervals
//    Medium
//    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//
//
//    Example 1:
//
//    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//    Output: [[1,6],[8,10],[15,18]]
//    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//    Example 2:
//
//    Input: intervals = [[1,4],[4,5]]
//    Output: [[1,5]]
//    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//    Constraints:
//
//    1 <= intervals.length <= 104
//    intervals[i].length == 2
//    0 <= starti <= endi <= 104