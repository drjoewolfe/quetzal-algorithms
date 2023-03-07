package org.jwolfe.quetzal.algorithms.lc;

public class MinimumTimeToCompleteTrips {
    class Solution {
        public long minimumTime(int[] time, int totalTrips) {
            if(time == null || time.length == 0 || totalTrips < 1) {
                return 0;
            }

            int n = time.length;

            int maxBusTripTime = 0;
            for(int val : time) {
                maxBusTripTime = Math.max(maxBusTripTime, val);
            }

            long min = 1;
            long max = 1L * maxBusTripTime * totalTrips;

            long minTime = max;
            while(min < max) {
                long mid = min + (max - min) / 2;

                long trips = getTrips(time, mid);
                // System.out.println(min + ", " + max + ", " + mid + ", " + trips + ", " + minTime);
                if(trips >= totalTrips) {
                    minTime = mid;
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }

            return minTime;
        }

        private long getTrips(int[] time, long currentTime) {
            long trips = 0;
            for(int i = 0; i < time.length; i++) {
                trips += currentTime / time[i];
            }

            return trips;
        }
    }

    class Solution_TLE {
        public long minimumTime(int[] time, int totalTrips) {
            if(time == null || time.length == 0 || totalTrips < 1) {
                return 0;
            }

            int n = time.length;

            int currentTime = 0;
            int currentTrips = 0;
            int[] trips = new int[n];

            while(currentTrips < totalTrips) {
                currentTime++;
                currentTrips = 0;
                for(int i = 0; i < n; i++) {
                    trips[i] = currentTime / time[i];
                    currentTrips += trips[i];
                }
            }

            return currentTime;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }


// [1,2,3]
// 5

// [5,10,10]
// 9

// [39,82,69,37,78,14,93,36,66,61,13,58,57,12,70,14,67,75,91,1,34,68,73,50,13,40,81,21,79,12,35,18,71,43,5,50,37,16,15,6,61,7,87,43,27,62,95,45,82,100,15,74,33,95,38,88,91,47,22,82,51,19,10,24,87,38,5,91,10,36,56,86,48,92,10,26,63,2,50,88,9,83,20,42,59,55,8,15,48,25]
// 4187
}

//    2187. Minimum Time to Complete Trips
//    Medium
//    You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
//
//    Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
//
//    You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the minimum time required for all buses to complete at least totalTrips trips.
//
//
//
//    Example 1:
//
//    Input: time = [1,2,3], totalTrips = 5
//    Output: 3
//    Explanation:
//    - At time t = 1, the number of trips completed by each bus are [1,0,0].
//    The total number of trips completed is 1 + 0 + 0 = 1.
//    - At time t = 2, the number of trips completed by each bus are [2,1,0].
//    The total number of trips completed is 2 + 1 + 0 = 3.
//    - At time t = 3, the number of trips completed by each bus are [3,1,1].
//    The total number of trips completed is 3 + 1 + 1 = 5.
//    So the minimum time needed for all buses to complete at least 5 trips is 3.
//    Example 2:
//
//    Input: time = [2], totalTrips = 1
//    Output: 2
//    Explanation:
//    There is only one bus, and it will complete its first trip at t = 2.
//    So the minimum time needed to complete 1 trip is 2.
//
//
//    Constraints:
//
//    1 <= time.length <= 105
//    1 <= time[i], totalTrips <= 107
