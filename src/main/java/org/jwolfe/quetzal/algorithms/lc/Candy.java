package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class Candy {
    class Solution {
        public int candy(int[] ratings) {
            if(ratings == null || ratings.length == 0) {
                return 0;
            }

            int numChildren = ratings.length;

            int[] candies = new int[numChildren];

            // printArray(ratings);
            Arrays.fill(candies, 1);
            for(int i = 1; i < numChildren; i++) {
                if(ratings[i] > ratings[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }

            // printArray(candies);
            int totalCandies = candies[numChildren - 1];
            for(int i = numChildren - 2; i >= 0; i--) {
                if(ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                }

                totalCandies += candies[i];
            }

            // printArray(candies);
            return totalCandies;
        }

        private void printArray(int[] arr) {
            Arrays.stream(arr).forEach(x -> System.out.print(" " + x));
            System.out.println();
        }
    }

    class Solution_Correct2 {
        public int candy(int[] ratings) {
            if(ratings == null || ratings.length == 0) {
                return 0;
            }

            int n = ratings.length;
            int[] candies = new int[n];
            Arrays.fill(candies, 1);

            for(int i = 1; i < n; i++) {
                if(ratings[i] > ratings[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }

            int count = candies[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                if(ratings[i] > ratings[i + 1]
                        && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                }

                count += candies[i];
            }

            return count;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    135. Candy
//    Hard
//    There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
//
//    You are giving candies to these children subjected to the following requirements:
//
//    Each child must have at least one candy.
//    Children with a higher rating get more candies than their neighbors.
//    Return the minimum number of candies you need to have to distribute the candies to the children.
//
//
//
//    Example 1:
//
//    Input: ratings = [1,0,2]
//    Output: 5
//    Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
//    Example 2:
//
//    Input: ratings = [1,2,2]
//    Output: 4
//    Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//    The third child gets 1 candy because it satisfies the above two conditions.
//
//
//    Constraints:
//
//    n == ratings.length
//    1 <= n <= 2 * 104
//    0 <= ratings[i] <= 2 * 104
