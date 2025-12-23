package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class TwoBestNonOverlappingEvents {
    class Solution {
        public int maxTwoEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            Integer[][] memo = new Integer[events.length][3];
            return maxTwoEvents(events, 0, 0, memo);
        }

        private int maxTwoEvents(int[][] events, int index, int selectedCount, Integer[][] memo) {
            if (selectedCount == 2 || index >= events.length) {
                return 0;
            }

            if (memo[index][selectedCount] != null) {
                return memo[index][selectedCount];
            }

            // Exclude this event
            int excludedValue = maxTwoEvents(events, index + 1, selectedCount, memo);

            // Include this event
            int value = events[index][2];
            int includedValue = value;
            int nextIndex = findNextNonOverlappingInterval(events, index);
            if (nextIndex >= 0 && nextIndex < events.length) {
                includedValue += maxTwoEvents(events, nextIndex, selectedCount + 1, memo);
            }

            int maxValue = Math.max(excludedValue, includedValue);
            return memo[index][selectedCount] = maxValue;
        }

        private int findNextNonOverlappingInterval(int[][] events, int index) {
            int end = events[index][1];
            int left = index + 1;
            int right = events.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][0] > end) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_TLE_3 {
        public int maxTwoEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            return maxTwoEvents(events, 0, 0);
        }

        private int maxTwoEvents(int[][] events, int index, int selectedCount) {
            if (selectedCount == 2 || index >= events.length) {
                return 0;
            }

            // Exclude this event
            int excludedValue = maxTwoEvents(events, index + 1, selectedCount);

            // Include this event
            int value = events[index][2];
            int includedValue = value;
            int nextIndex = findNextNonOverlappingInterval(events, index);
            if (nextIndex >= 0 && nextIndex < events.length) {
                includedValue += maxTwoEvents(events, nextIndex, selectedCount + 1);
            }

            int maxValue = Math.max(excludedValue, includedValue);
            return maxValue;
        }

        private int findNextNonOverlappingInterval(int[][] events, int index) {
            int end = events[index][1];
            int left = index + 1;
            int right = events.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][0] > end) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Correct_1 {
        public int maxTwoEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            int n = events.length;
            int[][] memo = new int[n][3];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            return getMaxFromTwoEvents(events, 0, 0, memo);
        }

        private int getMaxFromTwoEvents(int[][] events, int index, int count, int[][] memo) {
            if (count == 2 || index >= events.length) {
                return 0;
            }

            if (memo[index][count] != -1) {
                return memo[index][count];
            }

            int exclude = getMaxFromTwoEvents(events, index + 1, count, memo);

            int nextIndex = getNextIndex(events, index);
            int include = events[index][2] +
                    ((nextIndex < events.length && events[nextIndex][0] > events[index][1]) ? getMaxFromTwoEvents(events, nextIndex, count + 1, memo) : 0);

            return memo[index][count] = Math.max(include, exclude);
        }

        private int getNextIndex(int[][] events, int index) {
            int left = index + 1;
            int right = events.length - 1;

            int[] event1 = events[index];
            int end1 = event1[1];

            while (left < right) {
                int mid = left + (right - left) / 2;

                int[] event2 = events[mid];
                int start2 = event2[0];

                if (end1 < start2) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_TLE_2 {
        public int maxTwoEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            int n = events.length;
            int[][] memo = new int[n][3];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            return getMaxFromTwoEvents(events, 0, 0, memo);
        }

        private int getMaxFromTwoEvents(int[][] events, int index, int count, int[][] memo) {
            if (count == 2 || index >= events.length) {
                return 0;
            }

            if (memo[index][count] != -1) {
                return memo[index][count];
            }

            int exclude = getMaxFromTwoEvents(events, index + 1, count, memo);

            int[] event = events[index];
            int start1 = event[0];
            int end1 = event[1];
            int value1 = event[2];

            int include = value1;
            for (int i = index + 1; i < events.length; i++) {
                int start2 = events[i][0];
                int value2 = events[i][2];
                if (end1 < start2) {
                    include = Math.max(include,
                            value1 + value2
                    );
                }
            }

            return memo[index][count] = Math.max(include, exclude);
        }
    }

    class Solution_TLE {
        public int maxTwoEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            return getMaxFromTwoEvents(events, 0, 0);
        }

        private int getMaxFromTwoEvents(int[][] events, int index, int count) {
            if (count == 2 || index >= events.length) {
                return 0;
            }

            int exclude = getMaxFromTwoEvents(events, index + 1, count);

            int[] event = events[index];
            int start1 = event[0];
            int end1 = event[1];
            int value1 = event[2];

            int include = value1;
            for (int i = index + 1; i < events.length; i++) {
                int start2 = events[i][0];
                int value2 = events[i][2];
                if (end1 < start2) {
                    include = Math.max(include,
                            value1 + value2
                    );
                }
            }

            return Math.max(include, exclude);
        }
    }
}

//    2054. Two Best Non-Overlapping Events
//    Medium
//    You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
//
//    Return this maximum sum.
//
//    Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
//
//
//
//    Example 1:
//
//
//    Input: events = [[1,3,2],[4,5,2],[2,4,3]]
//    Output: 4
//    Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
//    Example 2:
//
//    Example 1 Diagram
//    Input: events = [[1,3,2],[4,5,2],[1,5,5]]
//    Output: 5
//    Explanation: Choose event 2 for a sum of 5.
//    Example 3:
//
//
//    Input: events = [[1,5,3],[1,5,1],[6,6,5]]
//    Output: 8
//    Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
//
//
//    Constraints:
//
//    2 <= events.length <= 105
//    events[i].length == 3
//    1 <= startTimei <= endTimei <= 109
//    1 <= valuei <= 106