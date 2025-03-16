package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeMap;

public class MinimumTimeToRepairCars {
    class Solution {
        public long repairCars(int[] ranks, int cars) {
            if (ranks == null || ranks.length == 0 || cars < 1) {
                return 0;
            }

            int minRank = Integer.MAX_VALUE;
            int maxRank = Integer.MIN_VALUE;
            TreeMap<Integer, Integer> frequencies = new TreeMap<>();
            for (int rank : ranks) {
                minRank = Math.min(minRank, rank);
                maxRank = Math.max(maxRank, rank);

                frequencies.put(rank, frequencies.getOrDefault(rank, 0) + 1);
            }

            long left = 1;
            long right = 1L * maxRank * cars * cars;

            while (left < right) {
                long mid = left + (right - left) / 2;

                long carsRepaired = getCarsRepaired(frequencies, mid);
                if (carsRepaired >= cars) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private long getCarsRepaired(TreeMap<Integer, Integer> frequencies, long time) {
            long carsRepaired = 0;
            for (var entry : frequencies.entrySet()) {
                int rank = entry.getKey();
                int count = entry.getValue();

                carsRepaired += (count * (long) Math.sqrt(time / (long) rank));
            }

            return carsRepaired;
        }
    }
}

//    2594. Minimum Time to Repair Cars
//    Medium
//    You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
//
//    You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
//
//    Return the minimum time taken to repair all the cars.
//
//    Note: All the mechanics can repair the cars simultaneously.
//
//
//
//    Example 1:
//
//    Input: ranks = [4,2,3,1], cars = 10
//    Output: 16
//    Explanation:
//    - The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
//    - The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
//    - The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
//    - The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
//    It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
//    Example 2:
//
//    Input: ranks = [5,1,8], cars = 6
//    Output: 16
//    Explanation:
//    - The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
//    - The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
//    - The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
//    It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
//
//
//    Constraints:
//
//    1 <= ranks.length <= 105
//    1 <= ranks[i] <= 100
//    1 <= cars <= 106