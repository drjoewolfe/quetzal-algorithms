package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class MyCalendarIII {
    class MyCalendarThree {
        TreeMap<Integer, Integer> map;

        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int kBooking = 0;
            int counter = 0;

            for(var entry : map.entrySet()) {
                counter += entry.getValue();

                kBooking = Math.max(kBooking, counter);
            }

            return kBooking;
        }
    }

    class MyCalendarThree_Correct_1 {
        List<EventTime> eventTimes;

        public MyCalendarThree_Correct_1() {
            eventTimes = new ArrayList<>();
        }

        public int book(int start, int end) {
            eventTimes.add(new EventTime(start, true));
            eventTimes.add(new EventTime(end, false));

            Collections.sort(eventTimes, (a, b) -> {
                if(a.time == b.time) {
                    if(a.isStart) {
                        return 1;
                    } else if(b.isStart) {
                        return -1;
                    }
                }

                return a.time - b.time;
            });

            int kBooking = 0;
            int eventCounter = 0;
            for(int i = 0; i < eventTimes.size(); i++) {
                EventTime eventTime = eventTimes.get(i);
                if(eventTime.isStart) {
                    eventCounter++;
                } else {
                    eventCounter--;
                }

                kBooking = Math.max(kBooking, eventCounter);
            }

            return kBooking;
        }

        private class EventTime {
            int time;
            boolean isStart;

            public EventTime(int time, boolean isStart) {
                this.time = time;
                this.isStart = isStart;
            }

            public String toString() {
                return "(" + time + ", " + isStart + ")";
            }
        }
    }

    class MyCalendarThree_Incorrect {
        List<Event> calendar;

        public MyCalendarThree_Incorrect() {
            calendar = new ArrayList<>();
        }

        public int book(int start, int end) {
            Event event = new Event(start, end);
            calendar.add(event);

            return kBooking();
        }

        private int kBooking() {
            System.out.println("\nK-Booking");
            int k = 1;
            for(int i = 0; i < calendar.size(); i++) {
                Event event1 = calendar.get(i);
                int ek = 1;
                System.out.println(event1);
                for(int j = 0; j < calendar.size(); j++) {
                    if(i == j) {
                        continue;
                    }

                    Event event2 = calendar.get(j);
                    if(overlap(event1, event2)) {
                        System.out.println(event1 + " overlaps with " + event2);
                        ek++;
                        k = Math.max(k, ek);
                    } else {
                        System.out.println(event1 + " does not overlap with " + event2);
                    }
                }
            }

            return k;
        }

        private boolean overlap(Event event1, Event event2) {
            if(event1.start < event2.start) {
                if(event1.end > event2.start) {
                    return true;
                }

                return false;
            } else {
                if(event2.end > event1.start) {
                    return true;
                }

                return false;
            }
        }

        private class Event {
            int start;
            int end;

            public Event(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "(" + start + ", " + end + ")";
            }
        }
    }


/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
}

//    732. My Calendar III
//    Hard
//    A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
//
//    You are given some events [start, end), after each given event, return an integer k representing the maximum k-booking between all the previous events.
//
//    Implement the MyCalendarThree class:
//
//    MyCalendarThree() Initializes the object.
//    int book(int start, int end) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
//
//
//    Example 1:
//
//    Input
//    ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
//    [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
//    Output
//    [null, 1, 1, 2, 3, 3, 3]
//
//    Explanation
//    MyCalendarThree myCalendarThree = new MyCalendarThree();
//    myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
//    myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
//    myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
//    myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
//    myCalendarThree.book(5, 10); // return 3
//    myCalendarThree.book(25, 55); // return 3
//
//
//    Constraints:
//
//    0 <= start < end <= 109
//    At most 400 calls will be made to book.