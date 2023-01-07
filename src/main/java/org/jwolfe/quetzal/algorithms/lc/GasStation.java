package org.jwolfe.quetzal.algorithms.lc;

public class GasStation {
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if(gas == null || cost == null || gas.length == 0 || gas.length != cost.length) {
                return -1;
            }

            int n = gas.length;

            int tank = 0;
            int total = 0;

            int start = 0;

            for(int i = 0; i < n; i++) {
                int gasConsumed = gas[i] - cost[i];
                tank += gasConsumed;
                total += gasConsumed;

                if(tank < 0) {
                    start = i + 1;
                    tank = 0;
                }
            }

            return total >= 0 ? start : -1;
        }
    }

    class Solution_Correct_2_TLE {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if(gas == null || cost == null || gas.length == 0 || gas.length != cost.length) {
                return -1;
            }

            int n = gas.length;
            for(int start = 0; start < n; start++) {
                int tank = 0;

                // Try start from this index
                for(int i = 0; i < n; i++) {
                    int station = (start + i) % n;

                    // fill up
                    tank += gas[station];

                    // go to next
                    tank -= cost[station];

                    if(tank < 0) {
                        break;
                    }
                }

                if(tank >= 0) {
                    return start;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if(gas == null || gas.length == 0 || cost == null || cost.length != gas.length) {
                return -1;
            }

            int n = gas.length;

            int start = 0;
            int tank = 0;
            int total = 0;
            for(int i = 0; i < n; i++) {
                int gasConsumed = gas[i] - cost[i];
                tank += gasConsumed;
                total += gasConsumed;

                if(tank < 0) {
                    start = i + 1;
                    tank = 0;
                }
            }

            return total >= 0 ? start : -1;
        }
    }

    class Solution_Classic {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if(gas == null || gas.length == 0 || cost == null || cost.length != gas.length) {
                return -1;
            }

            int n = gas.length;
            for(int i = 0; i < n; i++) {
                if(canCompleteCircuit(gas, cost, i)) {
                    return i;
                }
            }

            return -1;
        }

        private boolean canCompleteCircuit(int[] gas, int[] cost, int startIndex) {
            int n = gas.length;
            int tank = gas[startIndex];

            for(int i = startIndex + 1; i <= n + startIndex; i++) {
                int index = i % n;
                int prevIndex = (index == 0) ? n - 1 : index - 1;

                tank = tank - cost[prevIndex];
                if(tank < 0) {
                    return false;
                }

                tank += gas[index];
            }

            return true;
        }
    }

// [1,2,3,4,5]
// [3,4,5,1,2]
}

//    134. Gas Station
//    Medium
//    There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
//
//    You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
//
//    Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
//
//
//
//    Example 1:
//
//    Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//    Output: 3
//    Explanation:
//    Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//    Travel to station 4. Your tank = 4 - 1 + 5 = 8
//    Travel to station 0. Your tank = 8 - 2 + 1 = 7
//    Travel to station 1. Your tank = 7 - 3 + 2 = 6
//    Travel to station 2. Your tank = 6 - 4 + 3 = 5
//    Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
//    Therefore, return 3 as the starting index.
//    Example 2:
//
//    Input: gas = [2,3,4], cost = [3,4,3]
//    Output: -1
//    Explanation:
//    You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
//    Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//    Travel to station 0. Your tank = 4 - 3 + 2 = 3
//    Travel to station 1. Your tank = 3 - 3 + 3 = 3
//    You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
//    Therefore, you can't travel around the circuit once no matter where you start.
//
//
//    Constraints:
//
//    n == gas.length == cost.length
//    1 <= n <= 105
//    0 <= gas[i], cost[i] <= 104