package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class RabbitsInForest {
    class Solution {
        public int numRabbits(int[] answers) {
            if (answers == null || answers.length == 0) {
                return 0;
            }

            int rabbitCount = 0;
            Map<Integer, Integer> groupFrequency = new HashMap<>();
            for (int group : answers) {
                if (group == 0) {
                    rabbitCount++;
                } else {
                    groupFrequency.put(group, groupFrequency.getOrDefault(group, 0) + 1);
                    if (groupFrequency.get(group) == group + 1) {
                        rabbitCount += (group + 1);
                        groupFrequency.put(group, 0);
                    }
                }
            }

            for (var entry : groupFrequency.entrySet()) {
                if (entry.getValue() > 0) {
                    int group = entry.getKey();
                    rabbitCount += (group + 1);
                }
            }

            return rabbitCount;
        }
    }

    class Solution_Correct_1 {
        public int numRabbits(int[] answers) {
            if (answers == null || answers.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int a : answers) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            int total = 0;
            for (var entry : map.entrySet()) {
                int size = entry.getKey();
                int count = entry.getValue();

                if (size > count) {
                    total += size + 1;
                } else if (count % (size + 1) == 0) {
                    total += count;
                } else {
                    total += (count / (size + 1)) * (size + 1) + (size + 1);
                }
            }

            return total;
        }
    }

// [1,0,1,0,0]
}

//    781. Rabbits in Forest
//    Medium
//    There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same color as you?" and collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.
//
//    Given the array answers, return the minimum number of rabbits that could be in the forest.
//
//
//
//    Example 1:
//
//    Input: answers = [1,1,2]
//    Output: 5
//    Explanation:
//    The two rabbits that answered "1" could both be the same color, say red.
//    The rabbit that answered "2" can't be red or the answers would be inconsistent.
//    Say the rabbit that answered "2" was blue.
//    Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
//    The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
//    Example 2:
//
//    Input: answers = [10,10,10]
//    Output: 11
//
//
//    Constraints:
//
//    1 <= answers.length <= 1000
//    0 <= answers[i] < 1000