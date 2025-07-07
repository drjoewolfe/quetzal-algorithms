package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberOfEventsThatCanBeAttended {
    class Solution {
        public int maxEvents(int[][] events) {
            if (events == null || events.length == 0) {
                return 0;
            }

            int n = events.length;
            Arrays.sort(events, (a, b) -> a[0] - b[0]);

            int lastDay = 0;
            for (int[] event : events) {
                lastDay = Math.max(lastDay, event[1]);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int index = 0;
            int count = 0;
            for (int day = 1; day <= lastDay; day++) {
                while (index < n
                        && events[index][0] <= day) {
                    heap.offer(events[index++][1]);
                }

                while (!heap.isEmpty()
                        && heap.peek() < day) {
                    heap.poll();
                }

                if (!heap.isEmpty()) {
                    heap.poll();
                    count++;
                }
            }

            return count;
        }
    }
}

//    1353. Maximum Number of Events That Can Be Attended
//    Medium
//    You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
//
//    You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.
//
//    Return the maximum number of events you can attend.
//
//
//
//    Example 1:
//
//
//    Input: events = [[1,2],[2,3],[3,4]]
//    Output: 3
//    Explanation: You can attend all the three events.
//    One way to attend them all is as shown.
//    Attend the first event on day 1.
//    Attend the second event on day 2.
//    Attend the third event on day 3.
//    Example 2:
//
//    Input: events= [[1,2],[2,3],[3,4],[1,2]]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= events.length <= 105
//    events[i].length == 2
//    1 <= startDayi <= endDayi <= 105