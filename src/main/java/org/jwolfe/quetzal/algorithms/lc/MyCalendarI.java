package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyCalendarI {
    class MyCalendar {

        TreeMap<Integer, Integer> bookings = new TreeMap<>();

        public MyCalendar() {
            bookings = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            var prev = bookings.floorKey(start);
            var next = bookings.ceilingKey(start);

            if ((prev == null || bookings.get(prev) <= start)
                    && (next == null || next >= end)) {
                bookings.put(start, end);
                return true;
            }

            return false;
        }
    }

    class MyCalendar_Correct_1 {

        TreeMap<Integer, Integer> bookings;

        public MyCalendar_Correct_1() {
            bookings = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prev = bookings.floorKey(start);
            Integer next = bookings.ceilingKey(start);

            if ((prev == null || bookings.get(prev) <= start)
                    && (next == null || next >= end)) {
                bookings.put(start, end);
                return true;
            }

            return false;
        }
    }

    class MyCalendar_Brute {

        List<int[]> bookings;

        public MyCalendar_Brute() {
            bookings = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] booking : bookings) {
                if (checkOverlap(booking, start, end)) {
                    return false;
                }
            }

            bookings.add(new int[]{start, end});
            return true;
        }

        private boolean checkOverlap(int[] booking, int s1, int e1) {
            int s2 = booking[0];
            int e2 = booking[1];

            boolean a = (s2 >= e1);
            boolean b = (s1 >= e2);

            if (!(a || b)) {
                return true;
            }

            return false;
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

// ["MyCalendar","book","book","book"]
// [[],[10,20],[15,25],[20,30]]

// ["MyCalendar","book","book","book","book","book","book","book","book","book","book"]
// [[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]

// ["MyCalendar","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book"]
// [[],[20,29],[13,22],[44,50],[1,7],[2,10],[14,20],[19,25],[36,42],[45,50],[47,50],[39,45],[44,50],[16,25],[45,50],[45,50],[12,20],[21,29],[11,20],[12,17],[34,40],[10,18],[38,44],[23,32],[38,44],[15,20],[27,33],[34,42],[44,50],[35,40],[24,31]]
}

//    729. My Calendar I
//    Medium
//    Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
//
//    Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
//
//    A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
//
//    For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
//
//    Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
//    Example 1:
//
//    MyCalendar();
//    MyCalendar.book(10, 20); // returns true
//    MyCalendar.book(15, 25); // returns false
//    MyCalendar.book(20, 30); // returns true
//    Explanation:
//    The first event can be booked.  The second can't because time 15 is already booked by another event.
//    The third event can be booked, as the first event takes every time less than 20, but not including 20.
//
//
//    Note:
//
//    The number of calls to MyCalendar.book per test case will be at most 1000.
//    In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].