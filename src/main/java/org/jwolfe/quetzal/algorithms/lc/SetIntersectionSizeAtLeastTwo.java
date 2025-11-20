package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SetIntersectionSizeAtLeastTwo {
    class Solution {
        public int intersectionSizeTwo(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }

            int size = 0;

            Arrays.sort(intervals, (a, b) -> {
                if (a[1] == b[1]) {
                    return b[0] - a[0];
                }

                return a[1] - b[1];
            });

            int p1 = -1;
            int p2 = -1;

            for (int[] interval : intervals) {
                int left = interval[0];
                int right = interval[1];

                if (p2 < left) {
                    // No overlap
                    size += 2;
                    p1 = right - 1;
                    p2 = right;
                } else if (p1 < left) {
                    // Right overlaps, left not
                    size += 1;
                    p1 = p2;
                    p2 = right;
                } else {
                    // Total overlap
                }
            }

            return size;
        }
    }
}

//    757. Set Intersection Size At Least Two
//    Hard
//    You are given a 2D integer array intervals where intervals[i] = [starti, endi] represents all the integers from starti to endi inclusively.
//
//    A containing set is an array nums where each interval from intervals has at least two integers in nums.
//
//    For example, if intervals = [[1,3], [3,7], [8,9]], then [1,2,4,7,8,9] and [2,3,4,8,9] are containing sets.
//    Return the minimum possible size of a containing set.
//
//
//
//    Example 1:
//
//    Input: intervals = [[1,3],[3,7],[8,9]]
//    Output: 5
//    Explanation: let nums = [2, 3, 4, 8, 9].
//    It can be shown that there cannot be any containing array of size 4.
//    Example 2:
//
//    Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
//    Output: 3
//    Explanation: let nums = [2, 3, 4].
//    It can be shown that there cannot be any containing array of size 2.
//    Example 3:
//
//    Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
//    Output: 5
//    Explanation: let nums = [1, 2, 3, 4, 5].
//    It can be shown that there cannot be any containing array of size 4.
//
//
//    Constraints:
//
//    1 <= intervals.length <= 3000
//    intervals[i].length == 2
//    0 <= starti < endi <= 108