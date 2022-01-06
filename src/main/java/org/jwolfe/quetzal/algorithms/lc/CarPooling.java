package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CarPooling {
    class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            if(trips == null || trips.length == 0) {
                return true;
            }

            if(capacity < 1) {
                return false;
            }

            Arrays.sort(trips, (a, b) -> {
                if(a[1] == b[1]) {
                    return a[2] - b[2];
                }

                return a[1] - b[1];
            });

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

            int occupancy = 0;
            for(int i = 0; i < trips.length; i++) {
                int[] trip = trips[i];
                while(!heap.isEmpty()
                        && heap.peek()[2] <= trip[1]) {
                    int[] prev = heap.poll();
                    occupancy -= prev[0];
                }


                if(occupancy + trip[0] > capacity) {
                    return false;
                }

                occupancy += trip[0];
                heap.offer(trip);
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean carPooling(int[][] trips, int capacity) {
            if(trips == null || trips.length == 0) {
                return true;
            }

            if(capacity < 1) {
                return false;
            }

            Arrays.sort(trips, (t1, t2) -> {
                if(t1[1] == t2[1]) {
                    return t1[2] - t2[2];
                }

                return t1[1] - t2[1];
            });

            PriorityQueue<int[]> currentTrips = new PriorityQueue<>((t1, t2) -> t1[2] - t2[2]);
            int currentOccupancy = 0;

            for(int i = 0; i < trips.length; i++) {
                int[] trip = trips[i];

                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                while(!currentTrips.isEmpty() && currentTrips.peek()[2] <= from) {
                    int[] prevTrip = currentTrips.poll();
                    currentOccupancy -= prevTrip[0];
                }

                if(currentOccupancy + passengers > capacity) {
                    return false;
                }

                currentOccupancy += passengers;
                currentTrips.offer(trip);
            }

            return true;
        }
    }

    class Solution_Wrong {
        public boolean carPooling(int[][] trips, int capacity) {
            if(trips == null || trips.length == 0) {
                return true;
            }

            if(capacity < 1) {
                return false;
            }

            Arrays.sort(trips, (t1, t2) -> {
                if(t1[1] == t2[1]) {
                    return t1[2] - t2[2];
                }

                return t1[1] - t2[1];
            });

            int occupancy = 0;
            int end = 0;

            for(int i = 0; i < trips.length; i++) {
                int[] trip = trips[i];

                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                if(end <= from) {
                    occupancy = passengers;
                    end = to;
                } else {
                    if(occupancy + passengers > capacity) {
                        return false;
                    }

                    occupancy += passengers;
                    end = to;
                }
            }

            return true;
        }
    }

// [[2,1,5],[3,3,7]]
// 4

// [[2,1,5],[3,5,7]]
// 3

// [[3,2,7],[3,7,9],[8,3,9]]
// 11
}

//    1094. Car Pooling
//    Medium
//    There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
//
//    You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
//
//    Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: trips = [[2,1,5],[3,3,7]], capacity = 4
//    Output: false
//    Example 2:
//
//    Input: trips = [[2,1,5],[3,3,7]], capacity = 5
//    Output: true
//
//
//    Constraints:
//
//    1 <= trips.length <= 1000
//    trips[i].length == 3
//    1 <= numPassengersi <= 100
//    0 <= fromi < toi <= 1000
//    1 <= capacity <= 105