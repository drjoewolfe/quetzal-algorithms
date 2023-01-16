package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if(newInterval == null) {
                return intervals;
            }

            if(intervals == null || intervals.length == 0) {
                return new int[][] {newInterval};
            }

            int n = intervals.length;
            List<int[]> mergedIntervals = new ArrayList<>();

            int i = 0;
            while(i < n && intervals[i][1] < newInterval[0]) {
                mergedIntervals.add(intervals[i++]);
            }

            int[] interval = new int[] {newInterval[0], newInterval[1]};
            while(i < n && intervals[i][0] <= newInterval[1]) {
                interval[0] = Math.min(interval[0], intervals[i][0]);
                interval[1] = Math.max(interval[1], intervals[i][1]);

                i++;
            }

            mergedIntervals.add(interval);

            while(i < n) {
                mergedIntervals.add(intervals[i++]);
            }

            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
        }
    }

    class Solution_Correct_1 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if(newInterval == null || newInterval.length != 2) {
                return intervals;
            }

            if(intervals == null || intervals.length == 0) {
                return new int[][] {newInterval};
            }

            List<int[]> mergedIntervals = new ArrayList<>();

            int i = 0;
            int n = intervals.length;
            while(i < n
                    && intervals[i][1] < newInterval[0]) {
                mergedIntervals.add(intervals[i]);
                i++;
            }

            int[] mI = new int[] {newInterval[0], newInterval[1]};
            while(i < n
                    && intervals[i][0] <= newInterval[1]) {
                mI[0] = Math.min(mI[0], intervals[i][0]);
                mI[1] = Math.max(mI[1], intervals[i][1]);
                i++;
            }

            mergedIntervals.add(mI);

            while(i < n) {
                mergedIntervals.add(intervals[i]);
                i++;
            }

            int size = mergedIntervals.size();
            int[][] results = new int[size][2];
            for(i = 0; i < size; i++) {
                results[i] = mergedIntervals.get(i);
            }

            return results;
        }
    }


    class Solution_Attempt1 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if(newInterval == null || newInterval.length != 2) {
                return intervals;
            }

            if(intervals == null || intervals.length == 0) {
                return new int[][] {newInterval};
            }


            int n = intervals.length;
            List<int[]> mergedIntervals = new ArrayList<>();
            boolean inserted = false;

            if(newInterval[1] < intervals[0][0]) {
                mergedIntervals.add(newInterval);
                addIntervals(mergedIntervals, intervals);
                inserted = true;
            } else if(newInterval[0] > intervals[n - 1][1]) {
                addIntervals(mergedIntervals, intervals);
                mergedIntervals.add(newInterval);
                inserted = true;
            } else {
                int[] tempInterval = null;
                for(int i = 0; i < n; i++) {
                    int[] iv = intervals[i];
                    int start = iv[0];
                    int end = iv[1];

                    if(end < newInterval[0] || start > newInterval[1]) {
                        // No overlap
                        if(tempInterval != null) {
                            mergedIntervals.add(tempInterval);
                            tempInterval = null;
                        }

                        mergedIntervals.add(iv);
                    } else {
                        inserted = true;
                        if(tempInterval == null) {
                            tempInterval = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
                        }

                        tempInterval[0] = Math.min(tempInterval[0],
                                Math.min(newInterval[0], start));
                        tempInterval[1] = Math.max(tempInterval[1],
                                Math.max(newInterval[1], end));
                    }
                }

                if(tempInterval != null) {
                    mergedIntervals.add(tempInterval);
                }
            }

            if(!inserted) {
                for(int i = 0; i < mergedIntervals.size(); i++) {
                    if(mergedIntervals.get(i)[0] > newInterval[1]) {
                        mergedIntervals.add(i, newInterval);
                        break;
                    }
                }
            }

            n = mergedIntervals.size();
            int[][] results = new int[n][2];
            for(int i = 0; i < n; i++ ) {
                results[i] = mergedIntervals.get(i);
            }

            return results;
        }

        private void addIntervals(List<int[]> mergedIntervals, int[][] intervals) {
            for(int[] iv : intervals) {
                mergedIntervals.add(iv);
            }
        }
    }

// [[1,3],[6,9]]
// [2,5]

// [[1,2],[3,5],[6,7],[8,10],[12,16]]
// [4,8]

// []
// [5,7]
}

//    57. Insert Interval
//    Medium
//    You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
//
//    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
//
//    Return intervals after the insertion.
//
//
//
//    Example 1:
//
//    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//    Output: [[1,5],[6,9]]
//    Example 2:
//
//    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//    Output: [[1,2],[3,10],[12,16]]
//    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
//
//
//    Constraints:
//
//    0 <= intervals.length <= 104
//    intervals[i].length == 2
//    0 <= starti <= endi <= 105
//    intervals is sorted by starti in ascending order.
//    newInterval.length == 2
//    0 <= start <= end <= 105