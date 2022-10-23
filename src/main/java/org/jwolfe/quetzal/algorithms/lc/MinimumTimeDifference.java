package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {
    class Solution {
        public int findMinDifference(List<String> timePoints) {
            if(timePoints == null || timePoints.size() < 2) {
                return 0;
            }

            int[] timeline = new int[1440];
            for(String timePoint : timePoints) {
                int hour = Integer.valueOf(timePoint.substring(0, 2));
                int minutes = Integer.valueOf(timePoint.substring(3));

                int point = hour * 60 + minutes;
                timeline[point]++;
            }

            int minDifference = Integer.MAX_VALUE;
            int prev = -1;
            int first = -1;
            int last = -1;

            for(int curr = 0; curr < 1440; curr++) {
                if(timeline[curr] == 0) {
                    continue;
                }

                if(timeline[curr] > 1) {
                    return 0;
                }

                if(prev != -1) {
                    minDifference = Math.min(minDifference, curr - prev);
                }

                if(first == -1) {
                    first = curr;
                }

                last = curr;
                prev = curr;
            }

            minDifference = Math.min(minDifference, 1440 - last + first);
            return minDifference;
        }
    }

    class Solution_Correct_1 {
        public int findMinDifference(List<String> timePoints) {
            if(timePoints == null || timePoints.size() < 2) {
                return 0;
            }

            List<Integer> netMinutes = new ArrayList<>();
            for(String timePoint : timePoints) {
                int hour = Integer.valueOf(timePoint.substring(0, 2));
                int minutes = Integer.valueOf(timePoint.substring(3));

                netMinutes.add(hour * 60 + minutes);
            }

            Collections.sort(netMinutes);
            int minDifference = Integer.MAX_VALUE;
            for(int i = 1; i < netMinutes.size(); i++) {
                minDifference = Math.min(minDifference, (netMinutes.get(i) - netMinutes.get(i - 1)));
            }

            minDifference = Math.min(minDifference, 1440 - netMinutes.get(netMinutes.size() - 1) + netMinutes.get(0));

            return minDifference;
        }
    }

// ["23:59","00:00"]
}

//    539. Minimum Time Difference
//    Medium
//    Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
//
//
//    Example 1:
//
//    Input: timePoints = ["23:59","00:00"]
//    Output: 1
//    Example 2:
//
//    Input: timePoints = ["00:00","23:59","00:00"]
//    Output: 0
//
//
//    Constraints:
//
//    2 <= timePoints.length <= 2 * 104
//    timePoints[i] is in the format "HH:MM".