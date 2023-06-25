package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CountAllPossibleRoutes {
    class Solution {
        private int MOD = 1_000_000_007;
        public int countRoutes(int[] locations, int start, int finish, int fuel) {
            if(locations == null || locations.length == 0 || start < 0 || start >= locations.length || finish < 0 || finish >= locations.length || fuel < 0) {
                return 0;
            }

            int n = locations.length;
            int[][] memo = new int[n][fuel + 1];
            for(int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }

            return countRoutesHelper(locations, start, finish, fuel, memo);
        }

        private int countRoutesHelper(int[] locations, int curr, int finish, int fuel, int[][] memo) {
            if(fuel < 0) {
                return 0;
            }

            if(memo[curr][fuel] != -1) {
                return memo[curr][fuel];
            }

            int routes = (curr == finish) ? 1 : 0;
            for(int next = 0; next < locations.length; next++) {
                if(next == curr) {
                    continue;
                }

                routes = (routes + (countRoutesHelper(locations, next, finish, fuel - Math.abs(locations[curr] - locations[next]), memo))) % MOD;
            }

            memo[curr][fuel] = routes;
            return routes;
        }
    }

    class Solution_Recursive_TLE {
        private int MOD = 1_000_000_007;
        public int countRoutes(int[] locations, int start, int finish, int fuel) {
            if(locations == null || locations.length == 0 || start < 0 || start >= locations.length || finish < 0 || finish >= locations.length || fuel < 0) {
                return 0;
            }

            return countRoutesHelper(locations, start, finish, fuel);
        }

        private int countRoutesHelper(int[] locations, int curr, int finish, int fuel) {
            if(fuel < 0) {
                return 0;
            }

            int routes = (curr == finish) ? 1 : 0;
            for(int next = 0; next < locations.length; next++) {
                if(next == curr) {
                    continue;
                }

                routes += (countRoutesHelper(locations, next, finish, fuel - Math.abs(locations[curr] - locations[next])) % MOD);
            }

            return routes;
        }
    }

// [2,3,6,8,4]
// 1
// 3
// 5
}

//    1575. Count All Possible Routes
//    Hard
//    You are given an array of distinct positive integers locations where locations[i] represents the position of city i. You are also given integers start, finish and fuel representing the starting city, ending city, and the initial amount of fuel you have, respectively.
//
//    At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and move to city j. Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|. Please notice that |x| denotes the absolute value of x.
//
//    Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more than once (including start and finish).
//
//    Return the count of all possible routes from start to finish. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
//    Output: 4
//    Explanation: The following are all possible routes, each uses 5 units of fuel:
//    1 -> 3
//    1 -> 2 -> 3
//    1 -> 4 -> 3
//    1 -> 4 -> 2 -> 3
//    Example 2:
//
//    Input: locations = [4,3,1], start = 1, finish = 0, fuel = 6
//    Output: 5
//    Explanation: The following are all possible routes:
//    1 -> 0, used fuel = 1
//    1 -> 2 -> 0, used fuel = 5
//    1 -> 2 -> 1 -> 0, used fuel = 5
//    1 -> 0 -> 1 -> 0, used fuel = 3
//    1 -> 0 -> 1 -> 0 -> 1 -> 0, used fuel = 5
//    Example 3:
//
//    Input: locations = [5,2,1], start = 0, finish = 2, fuel = 3
//    Output: 0
//    Explanation: It is impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.
//
//
//    Constraints:
//
//    2 <= locations.length <= 100
//    1 <= locations[i] <= 109
//    All integers in locations are distinct.
//    0 <= start, finish < locations.length
//    1 <= fuel <= 200