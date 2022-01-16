package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximizeDistanceToClosestPerson {
    class Solution {
        public int maxDistToClosest(int[] seats) {
            if(seats == null || seats.length == 0) {
                return 0;
            }

            int n = seats.length;

            int first = -1;
            int prev = -1;
            int maxDistance = 0;

            for(int i = 0; i < n; i++) {
                int v = seats[i];

                if(v == 1) {
                    if(prev == -1) {
                        // first occupied seat
                        first = i;
                    } else {
                        int distance = (i - prev) / 2;
                        maxDistance = Math.max(maxDistance, distance);
                    }

                    prev = i;
                }
            }

            if(first == -1) {
                // empty seats
                return n;
            }

            maxDistance = Math.max(maxDistance,
                    Math.max(first, n - prev - 1));
            return maxDistance;
        }
    }

    class Solution_Correct_1 {
        public int maxDistToClosest(int[] seats) {
            if(seats == null || seats.length < 2) {
                return -1;
            }

            int n = seats.length;
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(left, n);
            Arrays.fill(right, n);

            for(int i = 0; i < n; i++) {
                if(seats[i] == 1) {
                    left[i] = 0;
                } else {
                    if(i > 0) {
                        left[i] = left[i - 1] + 1;
                    }
                }
            }

            for(int i = n - 1; i >= 0; i--) {
                if(seats[i] == 1) {
                    right[i] = 0;
                } else {
                    if(i < n - 1) {
                        right[i] = right[i + 1] + 1;
                    }
                }
            }

            int maxDistance = 0;
            for(int i = 0; i < n; i++) {
                if(seats[i] == 0) {
                    maxDistance = Math.max(maxDistance,
                            Math.min(left[i], right[i]));
                }
            }

            return maxDistance;
        }
    }


    class Solution_Classic {
        public int maxDistToClosest(int[] seats) {
            if(seats == null || seats.length < 2) {
                return -1;
            }

            int emptyAtStart = 0;
            int emptyAtEnd = 0;

            int start = 0;
            int n = seats.length;
            while(start < n && seats[start] == 0) {
                emptyAtStart++;
                start++;
            }

            int end = n - 1;
            while(end > 0 && seats[end] == 0) {
                emptyAtEnd++;
                end--;
            }

            int streak = 0;
            int maxEmptyStreak = 0;
            for(int i = start; i < end; i++) {
                if(seats[i] == 0) {
                    streak++;
                } else {
                    streak = 0;
                }

                maxEmptyStreak = Math.max(maxEmptyStreak, streak);
            }

            int centerRadius = (maxEmptyStreak % 2 == 0) ? maxEmptyStreak / 2 : maxEmptyStreak / 2 + 1;

            return Math.max(emptyAtStart,
                    Math.max(emptyAtEnd, centerRadius));
        }
    }

    class Solution_Incorrect {
        public int maxDistToClosest(int[] seats) {
            if(seats == null || seats.length == 0) {
                return 0;
            }

            int maxGap = 0;
            int maxLeft = 0;
            int maxRight = 0;

            int currentGap = 0;
            int currentLeft = 0;
            int currentRight = 0;

            for(int i = 0; i < seats.length; i++) {
                if(seats[i] == 0) {
                    currentGap++;
                    currentRight++;
                } else {
                    if(currentGap > maxGap) {
                        maxGap = currentGap;
                        maxLeft = currentLeft;
                        maxRight = currentRight;
                    }

                    currentGap = 0;
                    currentLeft = i;
                    currentRight = i;
                }
            }

            // System.out.println(maxLeft + ", " + maxRight + " -> " + maxGap);

            if(maxGap == 0) {
                return 0;
            }

            if(maxLeft == 0 || maxRight == seats.length - 1) {
                return maxGap;
            }

            return maxGap % 2 == 0 ? maxGap / 2 : maxGap / 2 + 1;
        }
    }

// [1,0,0,0,1,0,1]
}

//    849. Maximize Distance to Closest Person
//    Medium
//    You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
//
//    There is at least one empty seat, and at least one person sitting.
//
//    Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
//
//    Return that maximum distance to the closest person.
//
//
//
//    Example 1:
//
//
//    Input: seats = [1,0,0,0,1,0,1]
//    Output: 2
//    Explanation:
//    If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
//    If Alex sits in any other open seat, the closest person has distance 1.
//    Thus, the maximum distance to the closest person is 2.
//    Example 2:
//
//    Input: seats = [1,0,0,0]
//    Output: 3
//    Explanation:
//    If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
//    This is the maximum distance possible, so the answer is 3.
//    Example 3:
//
//    Input: seats = [0,1]
//    Output: 1
//
//
//    Constraints:
//
//    2 <= seats.length <= 2 * 104
//    seats[i] is 0 or 1.
//    At least one seat is empty.
//    At least one seat is occupied.
