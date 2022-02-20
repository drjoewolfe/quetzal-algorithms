package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class RemoveCoveredIntervals {
    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, (a, b) -> {
                if(a[0] == b[0]) {
                    return b[1] - a[1];
                }

                return a[0] - b[0];
            });

            int[] prev = intervals[0];
            int count = 1;
            for(int i = 1; i < intervals.length; i++) {
                int[] curr = intervals[i];

                if(!isCovered(curr, prev)) {
                    count++;
                    prev = curr;
                }
            }

            return count;
        }

        private boolean isCovered(int[] candidate, int[] parent) {
            return (candidate[0] >= parent[0] && candidate[1] <= parent[1]);
        }
    }

    class Solution_Correct_1 {
        public int removeCoveredIntervals(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }

            for(int i = 0; i < intervals.length; i++) {
                if(intervals[i].length != 2) {
                    return 0;
                }
            }

            Arrays.sort(intervals, (x, y) -> {
                if(x[0] != y[0]) {
                    return x[0] - y[0];
                }
                return y[1] - x[1];
            });

            int count = 0;
            int[] prev = intervals[0];
            for(int i = 1; i < intervals.length; i++) {
                int[] curr = intervals[i];

                if(prev[0] <= curr[0] && prev[1] >= curr[1]) {
                    // curr is a covered interval
                    continue;
                } else {
                    count++;
                    prev = curr;
                }
            }

            return count + 1;
        }
    }

// [[1,4],[3,6],[2,8]]
}

//    1288. Remove Covered Intervals
//    Medium
//    Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
//
//    The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
//
//    Return the number of remaining intervals.
//
//
//
//    Example 1:
//
//    Input: intervals = [[1,4],[3,6],[2,8]]
//    Output: 2
//    Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
//    Example 2:
//
//    Input: intervals = [[1,4],[2,3]]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= intervals.length <= 1000
//    intervals[i].length == 2
//    0 <= li <= ri <= 105
//    All the given intervals are unique.