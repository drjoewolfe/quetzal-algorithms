package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class MinimumCostToHireKWorkers {
    class Solution {
        public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
            if(quality == null || quality.length == 0 || wage == null || wage.length != quality.length || k < 1 || k > quality.length) {
                return 0;
            }

            int n = quality.length;
            List<Pair<Double, Integer>> w2QRationdQualityPairs = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                int q = quality[i];
                int w = wage[i];

                double wToQRatio = (double) 1d * w / q;
                w2QRationdQualityPairs.add(new Pair<>(wToQRatio, q));
            }

            Collections.sort(w2QRationdQualityPairs, Comparator.comparingDouble(Pair::getKey));

            PriorityQueue<Integer> highestQualityWorkers = new PriorityQueue<>(Collections.reverseOrder());
            double currentTotalQuality = 0;
            double minTotalCost = Double.MAX_VALUE;

            for(int worker = 0; worker < n; worker++) {
                double wToQRatio = w2QRationdQualityPairs.get(worker).getKey();
                int q = w2QRationdQualityPairs.get(worker).getValue();

                highestQualityWorkers.add(q);
                currentTotalQuality += q;

                if(highestQualityWorkers.size() > k) {
                    currentTotalQuality -= highestQualityWorkers.poll();
                }

                if(highestQualityWorkers.size() == k) {
                    double currentCost = currentTotalQuality * wToQRatio;
                    minTotalCost = Math.min(minTotalCost,
                            currentCost);
                }
            }

            return minTotalCost;
        }
    }
}

//    857. Minimum Cost to Hire K Workers
//    Hard
//    There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
//
//    We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
//
//    Every worker in the paid group must be paid at least their minimum wage expectation.
//    In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
//    Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.
//
//
//
//    Example 1:
//
//    Input: quality = [10,20,5], wage = [70,50,30], k = 2
//    Output: 105.00000
//    Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
//    Example 2:
//
//    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
//    Output: 30.66667
//    Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
//
//
//    Constraints:
//
//    n == quality.length == wage.length
//    1 <= k <= n <= 104
//    1 <= quality[i], wage[i] <= 104
