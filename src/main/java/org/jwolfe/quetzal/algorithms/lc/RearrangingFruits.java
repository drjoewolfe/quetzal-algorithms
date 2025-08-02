package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RearrangingFruits {
    class Solution {
        public long minCost(int[] basket1, int[] basket2) {
            if (basket1 == null || basket2 == null || basket1.length != basket2.length) {
                return -1;
            }

            Map<Integer, Integer> frequency = new HashMap<>();
            int minValue = Integer.MAX_VALUE;

            for (int fruit : basket1) {
                frequency.put(fruit, frequency.getOrDefault(fruit, 0) + 1);
                minValue = Math.min(minValue, fruit);
            }

            for (int fruit : basket2) {
                frequency.put(fruit, frequency.getOrDefault(fruit, 0) - 1);
                minValue = Math.min(minValue, fruit);
            }

            List<Integer> transferPairs = new ArrayList<>();
            for (var entry : frequency.entrySet()) {
                int fruit = entry.getKey();
                int count = Math.abs(entry.getValue());
                if (count == 0) {
                    // already balanced
                    continue;
                }

                if (count % 2 != 0) {
                    // only even frequencies can provide a solution
                    return -1;
                }

                for (int i = 0; i < count / 2; i++) {
                    transferPairs.add(fruit);
                }
            }

            Collections.sort(transferPairs);

            long totalCost = 0;
            for (int i = 0; i < transferPairs.size() / 2; i++) {
                int fruit = transferPairs.get(i);
                long cost = Math.min(fruit, 2 * minValue);
                totalCost += cost;
            }

            return totalCost;
        }
    }
}

//    2561. Rearranging Fruits
//    Hard
//    You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
//
//    Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
//    The cost of the swap is min(basket1[i],basket2[j]).
//    Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
//
//    Return the minimum cost to make both the baskets equal or -1 if impossible.
//
//
//
//    Example 1:
//
//    Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
//    Output: 1
//    Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
//    Example 2:
//
//    Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
//    Output: -1
//    Explanation: It can be shown that it is impossible to make both the baskets equal.
//
//
//    Constraints:
//
//    basket1.length == basket2.length
//    1 <= basket1.length <= 105
//    1 <= basket1[i],basket2[i] <= 109