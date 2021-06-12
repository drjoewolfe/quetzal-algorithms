package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumNumberOfRefuelingStops {
    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(target < 0 || startFuel < 0) {
                return -1;
            }

            if(target == 0 || startFuel >= target) {
                return 0;
            }

            if(stations == null || stations.length == 0) {
                return -1;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int tank = startFuel;
            int stationCount = 0;
            int previousLocation = 0;
            for(int i = 0; i < stations.length; i++) {
                int[] station = stations[i];
                int location = station[0];
                int fuel = station[1];

                tank -= (location - previousLocation);
                while(tank < 0 && ! maxHeap.isEmpty()) {
                    tank += maxHeap.poll();
                    stationCount++;
                }

                if(tank < 0) {
                    return -1;
                }

                maxHeap.offer(fuel);
                previousLocation = location;
            }

            tank -= (target - previousLocation);
            while(tank < 0 && ! maxHeap.isEmpty()) {
                tank += maxHeap.poll();
                stationCount++;
            }

            if(tank < 0) {
                return -1;
            }

            return stationCount;
        }
    }

    class Solution_DP {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(target < 0 || startFuel < 0) {
                return -1;
            }

            if(target == 0 || startFuel >= target) {
                return 0;
            }

            if(stations == null || stations.length == 0) {
                return -1;
            }

            int n = stations.length;
            int[] dp = new int[n + 1];
            dp[0] = startFuel;
            for(int i = 0; i < n; i++) {
                for(int j = i; j >= 0; j--) {
                    if(dp[j] >= stations[i][0]) {
                        dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                    }
                }
            }

            for(int i = 0; i <= n; i++) {
                if(dp[i] >= target) {
                    return i;
                }
            }

            return -1;
        }
    }

    class Solution_Memoized_TLE {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(target < 0 || startFuel < 0) {
                return -1;
            }

            if(target == 0 || startFuel >= target) {
                return 0;
            }

            if(stations == null || stations.length == 0) {
                return -1;
            }

            Arrays.sort(stations, (s1, s2) -> s1[0] - s2[0]);
            int minStationCount = minRefuelStops(stations, 0, target, startFuel, -1, new HashMap<>());
            return minStationCount == Integer.MAX_VALUE ? -1 : minStationCount;
        }

        private int minRefuelStops(int[][] stations, int current, int target, int fuel, int lastStation, Map<String, Integer> memo) {
            int span = current + fuel;
            if(span >= target) {
                return 0;
            }

            String hash = span + "_" + lastStation;
            if(memo.containsKey(hash)) {
                return memo.get(hash);
            }

            int minStationCount = Integer.MAX_VALUE;
            for(int i = lastStation + 1; i < stations.length; i++) {
                int[] station = stations[i];

                // Can reach this station ?
                int distanceToStation = station[0] - current;
                if(fuel < distanceToStation) {
                    // Nopes
                    break;
                }

                // Try with refuelling at this station
                int stationCount = minRefuelStops(stations, station[0], target, fuel - distanceToStation + station[1], i, memo);
                if(stationCount != Integer.MAX_VALUE) {
                    minStationCount = Math.min(minStationCount, stationCount + 1);
                }
            }

            memo.put(hash, minStationCount);
            return minStationCount;
        }
    }

    class Solution_Recursive_BU {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(target < 0 || startFuel < 0) {
                return -1;
            }

            if(target == 0 || startFuel >= target) {
                return 0;
            }

            if(stations == null || stations.length == 0) {
                return -1;
            }

            Arrays.sort(stations, (s1, s2) -> s1[0] - s2[0]);
            int minStationCount = minRefuelStops(stations, 0, target, startFuel, -1);
            return minStationCount == Integer.MAX_VALUE ? -1 : minStationCount;
        }

        private int minRefuelStops(int[][] stations, int current, int target, int fuel, int lastStation) {
            if(current + fuel >= target) {
                return 0;
            }

            int minStationCount = Integer.MAX_VALUE;
            for(int i = lastStation + 1; i < stations.length; i++) {
                int[] station = stations[i];

                // Can reach this station ?
                int distanceToStation = station[0] - current;
                if(fuel < distanceToStation) {
                    // Nopes
                    break;
                }

                // Try with refuelling at this station
                int stationCount = minRefuelStops(stations, station[0], target, fuel - distanceToStation + station[1], i);
                if(stationCount != Integer.MAX_VALUE) {
                    minStationCount = Math.min(minStationCount, stationCount + 1);
                }
            }

            return minStationCount;
        }
    }

    class Solution_Recursive_TD {
        int minStops;

        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(target < 0 || startFuel < 0) {
                return -1;
            }

            if(target == 0 || startFuel >= target) {
                return 0;
            }

            if(stations == null || stations.length == 0) {
                return -1;
            }

            Arrays.sort(stations, (s1, s2) -> s1[0] - s2[0]);

            minStops = Integer.MAX_VALUE;
            minRefuelStops(stations, 0, target, startFuel, -1, 0);

            return minStops == Integer.MAX_VALUE ? -1 : minStops;
        }

        private void minRefuelStops(int[][] stations, int current, int target, int fuel, int lastStation, int stationCount) {
            if(current + fuel >= target) {
                minStops = Math.min(minStops, stationCount);
                return;
            }

            for(int i = lastStation + 1; i < stations.length; i++) {
                int[] station = stations[i];

                // Can reach this station ?
                int distanceToStation = station[0] - current;
                if(fuel < distanceToStation) {
                    // Nopes
                    break;
                }

                // Try with refuelling at this station
                minRefuelStops(stations, station[0], target, fuel - distanceToStation + station[1], i, stationCount + 1);
            }
        }
    }

// 1
// 1
// []

// 100
// 10
// [[10,60],[20,30],[30,30],[60,40]]

// 1000
// 10
// [[7,217],[10,211],[17,146],[21,232],[25,310],[48,175],[53,23],[63,158],[71,292],[96,85],[100,302],[102,295],[116,110],[122,46],[131,20],[132,19],[141,13],[163,85],[169,263],[179,233],[191,169],[215,163],[224,231],[228,282],[256,115],[259,3],[266,245],[283,331],[299,21],[310,224],[315,188],[328,147],[345,74],[350,49],[379,79],[387,276],[391,92],[405,174],[428,307],[446,205],[448,226],[452,275],[475,325],[492,310],[496,94],[499,313],[500,315],[511,137],[515,180],[519,6],[533,206],[536,262],[553,326],[561,103],[564,115],[582,161],[593,236],[599,216],[611,141],[625,137],[626,231],[628,66],[646,197],[665,103],[668,8],[691,329],[699,246],[703,94],[724,277],[729,75],[735,23],[740,228],[761,73],[770,120],[773,82],[774,297],[780,184],[791,239],[801,85],[805,156],[837,157],[844,259],[849,2],[860,115],[874,311],[877,172],[881,109],[888,321],[894,302],[899,321],[908,76],[916,241],[924,301],[933,56],[960,29],[979,319],[983,325],[988,190],[995,299],[996,97]]

// 1000
// 299
// [[42,39],[132,236],[166,142],[434,7],[462,80],[518,103],[545,209],[656,104],[769,137],[811,67]]

// 1000000000
// 145267354
// [[5510987,84329695],[10682248,76273791],[56227783,136858069],[91710087,18854476],[111148380,127134059],[165982642,122930004],[184216180,124802819],[217578071,7123113],[233719001,95862544],[339631786,7676497],[349762649,136128214],[403119403,21487501],[423890164,61095325],[424072629,50415446],[572994480,13561367],[609623597,69207102],[662818314,84432133],[678679727,20403175],[682325369,14288077],[702137485,6426204],[716318901,47662322],[738137702,129579140],[761962118,23765733],[820353983,70497719],[895811889,75533360]]
}

//    871. Minimum Number of Refueling Stops
//    Hard
//    A car travels from a starting position to a destination which is target miles east of the starting position.
//
//    Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
//
//    The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
//
//    When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
//
//    What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
//
//    Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
//
//
//
//    Example 1:
//
//    Input: target = 1, startFuel = 1, stations = []
//    Output: 0
//    Explanation: We can reach the target without refueling.
//    Example 2:
//
//    Input: target = 100, startFuel = 1, stations = [[10,100]]
//    Output: -1
//    Explanation: We can't reach the target (or even the first gas station).
//    Example 3:
//
//    Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
//    Output: 2
//    Explanation:
//    We start with 10 liters of fuel.
//    We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
//    Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
//    and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
//    We made 2 refueling stops along the way, so we return 2.
//
//
//    Note:
//
//    1 <= target, startFuel, stations[i][1] <= 10^9
//    0 <= stations.length <= 500
//    0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
