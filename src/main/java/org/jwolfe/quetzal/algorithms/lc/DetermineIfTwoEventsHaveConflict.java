package org.jwolfe.quetzal.algorithms.lc;

public class DetermineIfTwoEventsHaveConflict {
    class Solution {
        public boolean haveConflict(String[] event1, String[] event2) {
            if(event1 == null || event2 == null || event1.length != 2 || event2.length != 2) {
                return false;
            }


            int event1Start = getNumber(event1[0]);
            int event1End = getNumber(event1[1]);

            int event2Start = getNumber(event2[0]);
            int event2End = getNumber(event2[1]);

            if(event2Start > event1Start) {
                return !(event2Start > event1End);
            } else {
                return !(event1Start > event2End);
            }
        }

        private int getNumber(String event) {
            String[] eventParts = event.split(":");
            return Integer.valueOf(eventParts[0]) * 60 + Integer.valueOf(eventParts[1]);
        }
    }
}

//    2446. Determine if Two Events Have Conflict
//    Easy
//    You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
//
//    event1 = [startTime1, endTime1] and
//    event2 = [startTime2, endTime2].
//    Event times are valid 24 hours format in the form of HH:MM.
//
//    A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
//
//    Return true if there is a conflict between two events. Otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
//    Output: true
//    Explanation: The two events intersect at time 2:00.
//    Example 2:
//
//    Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
//    Output: true
//    Explanation: The two events intersect starting from 01:20 to 02:00.
//    Example 3:
//
//    Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
//    Output: false
//    Explanation: The two events do not intersect.
//
//
//    Constraints:
//
//    evnet1.length == event2.length == 2.
//    event1[i].length == event2[i].length == 5
//    startTime1 <= endTime1
//    startTime2 <= endTime2
//    All the event times follow the HH:MM format.
