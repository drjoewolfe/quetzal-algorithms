package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DivideIntervalsIntoMinimumNumberOfGroups {
    class Solution {
        public int minGroups(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, (a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            PriorityQueue<Integer> heap = new PriorityQueue<>();
            heap.offer(intervals[0][1]);

            for(int i = 1; i < intervals.length; i++) {
                int[] interval = intervals[i];
                if(heap.peek() >= interval[0]) {
                    // Overlap
                    heap.offer(interval[1]);
                } else {
                    // No overlap
                    heap.poll();
                    heap.offer(interval[1]);
                }
            }

            return heap.size();
        }
    }

    class Solution_TLE {
        public int minGroups(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, (a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            List<int[]> groups = new ArrayList<>();
            for(int i = 0; i < intervals.length; i++) {
                int[] interval = intervals[i];

                boolean addedToGroup = false;
                for(int j = 0; j < groups.size(); j++) {
                    int[] candidate = groups.get(j);
                    if(candidate[1] < interval[0]) {
                        // Can add to this group
                        groups.set(j, interval);
                        addedToGroup = true;
                        break;
                    }
                }

                if(!addedToGroup) {
                    groups.add(interval);
                }
            }

            return groups.size();
        }
    }

// [[5,10],[6,8],[1,5],[2,3],[1,10]]
// [[1,3],[5,6],[8,10],[11,13]]
// [[441459,446342],[801308,840640],[871890,963447],[228525,336985],[807945,946787],[479815,507766],[693292,944029],[751962,821744]]
}

//    2406. Divide Intervals Into Minimum Number of Groups
//    Medium
//    You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].
//
//    You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.
//
//    Return the minimum number of groups you need to make.
//
//    Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.
//
//
//
//    Example 1:
//
//    Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
//    Output: 3
//    Explanation: We can divide the intervals into the following groups:
//    - Group 1: [1, 5], [6, 8].
//    - Group 2: [2, 3], [5, 10].
//    - Group 3: [1, 10].
//    It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
//    Example 2:
//
//    Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
//    Output: 1
//    Explanation: None of the intervals overlap, so we can put all of them in one group.
//
//
//    Constraints:
//
//    1 <= intervals.length <= 105
//    intervals[i].length == 2
//    1 <= lefti <= righti <= 106