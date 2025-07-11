package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomsIII {
    class Solution {
        public int mostBooked(int n, int[][] meetings) {
            if(n < 1 || meetings == null || meetings.length == 0) {
                return 0;
            }

            int[] meetingsPerRoom = new int[n];
            PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
            PriorityQueue<long[]> blockedRooms = new PriorityQueue<>((a, b) -> {
                if(a[1] != b[1])
                    return Long.compare(a[1], b[1]);

                return Long.compare(a[0], b[0]);
            });

            for(int i = 0; i < n; i++) {
                availableRooms.offer(i);
            }

            Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

            for(int[] meeting : meetings) {
                int start = meeting[0];
                int end = meeting[1];

                while(!blockedRooms.isEmpty()
                        && blockedRooms.peek()[1] <= start) {
                    long[] roomInfo = blockedRooms.poll();
                    int room = (int) roomInfo[0];
                    availableRooms.offer(room);
                }

                if(!availableRooms.isEmpty()) {
                    int room = availableRooms.poll();
                    blockedRooms.offer(new long[] {room, end});

                    meetingsPerRoom[room]++;
                } else {
                    long[] nextAvailableRoomInfo = blockedRooms.poll();
                    int room = (int) nextAvailableRoomInfo[0];
                    long nextAvailableTime = nextAvailableRoomInfo[1];
                    int requiredDuration = end - start;

                    blockedRooms.offer(new long[] {room, nextAvailableTime + requiredDuration});

                    meetingsPerRoom[room]++;
                }
            }

            int maxMeetings = 0;
            int maxMeetingsRoom = 0;

            for(int i = 0; i < n; i++) {
                if(meetingsPerRoom[i] > maxMeetings) {
                    maxMeetings = meetingsPerRoom[i];
                    maxMeetingsRoom = i;
                }
            }

            print(meetingsPerRoom);
            return maxMeetingsRoom;
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int mostBooked(int n, int[][] meetings) {
            if(n < 1 || meetings == null || meetings.length == 0) {
                return 0;
            }

            int[] roomMeetings = new int[n];
            Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

            List<Appointment> appointments = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                appointments.add(new Appointment(i, -1, -1));
            }

            for(int[] meeting : meetings) {
                int start = meeting[0];
                int end = meeting[1];

                for(int i = 0; i < n; i++) {
                    Appointment appointment = appointments.get(i);

                    if(appointment.end != -1 && appointment.end < start) {
                        appointment.start = -1;
                        appointment.end = -1;
                    }
                }

                var appointment = appointments.get(0);
                for(int i = 0; i < n; i++) {
                    if(appointments.get(i).end < appointment.end) {
                        appointment = appointments.get(i);
                    }
                }

                long meetingStart = Math.max(appointment.end + 1, start);
                long meetingEnd = meetingStart + (end - start) - 1;

                roomMeetings[appointment.room]++;
                appointment.start = meetingStart;
                appointment.end = meetingEnd;
            }

            int maxRoomIndex = -1;
            int maxMeetings = 0;
            for(int i = 0; i < n; i++) {
                if(maxMeetings < roomMeetings[i]) {
                    maxRoomIndex = i;
                    maxMeetings = roomMeetings[i];
                }
            }

            return maxRoomIndex;
        }

        private class Appointment {
            int room;
            long start;
            long end;

            public Appointment(int room, int start, int end) {
                this.room = room;
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "(" + room + " : " + start + ", " + end + ")";
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(List<Appointment> arr) {
            for(var a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(int[][] arr) {
            for(int[] a : arr) {
                System.out.print("[");
                for(int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public int mostBooked(int n, int[][] meetings) {
            if(n < 1 || meetings == null || meetings.length == 0) {
                return 0;
            }

            int[] roomMeetings = new int[n];

            Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

            PriorityQueue<Appointment> heap = new PriorityQueue<>((a, b) -> {
                if(a.end == b.end) {
                    return a.room - b.room;
                }

                return a.end - b.end;
            });

            for(int i = 0; i < n; i++) {
                heap.offer(new Appointment(i, -1, -1));
            }

            print(meetings);
            System.out.println(heap);

            for(int i = 0; i < meetings.length; i++) {
                int[] meeting = meetings[i];
                int start = meeting[0];
                int end = meeting[1];

                while(heap.peek().end != -1 && heap.peek().end + 1 < start) {
                    Appointment appt = heap.poll();
                    appt.start = -1;
                    appt.end = -1;

                    heap.offer(appt);
                }

                Appointment appointment = heap.poll();

                int meetingStart = Math.max(appointment.end + 1, start);
                int meetingEnd = meetingStart + (end - start) - 1;

                roomMeetings[appointment.room]++;
                heap.offer(new Appointment(appointment.room, meetingStart, meetingEnd));
                System.out.println(heap);
            }

            print(roomMeetings);

            int maxRoomIndex = -1;
            int maxMeetings = 0;
            for(int i = 0; i < n; i++) {
                if(maxMeetings < roomMeetings[i]) {
                    maxRoomIndex = i;
                    maxMeetings = roomMeetings[i];
                }
            }

            return maxRoomIndex;
        }

        private class Appointment {
            int room;
            int start;
            int end;

            public Appointment(int room, int start, int end) {
                this.room = room;
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "(" + room + " : " + start + ", " + end + ")";
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(int[][] arr) {
            for(int[] a : arr) {
                System.out.print("[");
                for(int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

// 2
// [[0,10],[1,5],[2,7],[3,4]]

// 4
// [[18,19],[3,12],[17,19],[2,13],[7,10]]

// 3
// [[1,20],[2,10],[3,5],[4,9],[6,8]]
}

//    2402. Meeting Rooms III
//    Hard
//    You are given an integer n. There are n rooms numbered from 0 to n - 1.
//
//    You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
//
//    Meetings are allocated to rooms in the following manner:
//
//    Each meeting will take place in the unused room with the lowest number.
//    If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
//    When a room becomes unused, meetings that have an earlier original start time should be given the room.
//    Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
//
//    A half-closed interval [a, b) is the interval between a and b including a and not including b.
//
//
//
//    Example 1:
//
//    Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
//    Output: 0
//    Explanation:
//    - At time 0, both rooms are not being used. The first meeting starts in room 0.
//    - At time 1, only room 1 is not being used. The second meeting starts in room 1.
//    - At time 2, both rooms are being used. The third meeting is delayed.
//    - At time 3, both rooms are being used. The fourth meeting is delayed.
//    - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
//    - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
//    Both rooms 0 and 1 held 2 meetings, so we return 0.
//    Example 2:
//
//    Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
//    Output: 1
//    Explanation:
//    - At time 1, all three rooms are not being used. The first meeting starts in room 0.
//    - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
//    - At time 3, only room 2 is not being used. The third meeting starts in room 2.
//    - At time 4, all three rooms are being used. The fourth meeting is delayed.
//    - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
//    - At time 6, all three rooms are being used. The fifth meeting is delayed.
//    - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
//    Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
//
//
//    Constraints:
//
//    1 <= n <= 100
//    1 <= meetings.length <= 105
//    meetings[i].length == 2
//    0 <= starti < endi <= 5 * 105
//    All the values of starti are unique.