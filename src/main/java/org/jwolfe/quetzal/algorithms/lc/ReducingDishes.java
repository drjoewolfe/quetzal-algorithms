package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ReducingDishes {
    class Solution {
        public int maxSatisfaction(int[] satisfaction) {
            if(satisfaction == null || satisfaction.length == 0) {
                return 0;
            }

            int n = satisfaction.length;
            int[][] memo = new int[n][n + 1];
            for(int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }

            Arrays.sort(satisfaction);
            return maxSatisfaction(satisfaction, 0, 1, memo);
        }

        private int maxSatisfaction(int[] satisfaction, int index, int time, int[][] memo) {
            if(index == satisfaction.length) {
                return 0;
            }

            if(memo[index][time] != -1) {
                return memo[index][time];
            }

            int likeTimeCoefficient = 0;

            // cook this dish
            likeTimeCoefficient = (satisfaction[index] * time) + maxSatisfaction(satisfaction, index + 1, time + 1, memo);

            // skip this dish
            likeTimeCoefficient = Math.max(likeTimeCoefficient,
                    maxSatisfaction(satisfaction, index + 1, time, memo));

            memo[index][time] = likeTimeCoefficient;
            return likeTimeCoefficient;
        }
    }

    class Solution_Recursive {
        public int maxSatisfaction(int[] satisfaction) {
            if(satisfaction == null || satisfaction.length == 0) {
                return 0;
            }

            Arrays.sort(satisfaction);
            return maxSatisfaction(satisfaction, 0, 1);
        }

        private int maxSatisfaction(int[] satisfaction, int index, int time) {
            if(index == satisfaction.length) {
                return 0;
            }

            int likeTimeCoefficient = 0;

            // cook this dish
            likeTimeCoefficient = (satisfaction[index] * time) + maxSatisfaction(satisfaction, index + 1, time + 1);

            // skip this dish
            likeTimeCoefficient = Math.max(likeTimeCoefficient,
                    maxSatisfaction(satisfaction, index + 1, time));

            return likeTimeCoefficient;
        }
    }

// [-1,-8,0,5,-7]
}

//    1402. Reducing Dishes
//    Hard
//    A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
//
//    Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
//
//    Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
//
//    Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
//
//
//
//    Example 1:
//
//    Input: satisfaction = [-1,-8,0,5,-9]
//    Output: 14
//    Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
//    Each dish is prepared in one unit of time.
//    Example 2:
//
//    Input: satisfaction = [4,3,2]
//    Output: 20
//    Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
//    Example 3:
//
//    Input: satisfaction = [-1,-4,-5]
//    Output: 0
//    Explanation: People do not like the dishes. No dish is prepared.
//
//
//    Constraints:
//
//    n == satisfaction.length
//    1 <= n <= 500
//    -1000 <= satisfaction[i] <= 1000
