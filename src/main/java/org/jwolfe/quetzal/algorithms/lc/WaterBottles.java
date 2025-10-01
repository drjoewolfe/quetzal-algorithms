package org.jwolfe.quetzal.algorithms.lc;

public class WaterBottles {
    class Solution {
        public int numWaterBottles(int numBottles, int numExchange) {
            if (numBottles < 1) {
                return 0;
            }

            int drunkBottles = numBottles;
            int emptyBottles = numBottles;

            while (emptyBottles / numExchange > 0) {
                int newBottles = emptyBottles / numExchange;
                drunkBottles += newBottles;

                emptyBottles = newBottles + (emptyBottles % numExchange);
            }

            return drunkBottles;
        }
    }

    class Solution_Correct_2 {
        public int numWaterBottles(int numBottles, int numExchange) {
            if (numBottles < 0) {
                return 0;
            }

            if (numExchange < 0) {
                return numBottles;
            }

            int drunkBottles = 0;
            int fullBottles = numBottles;
            int emptyBottles = 0;

            while (fullBottles > 0 || emptyBottles > numExchange) {
                drunkBottles += fullBottles;
                emptyBottles += fullBottles;


                fullBottles = emptyBottles / numExchange;
                emptyBottles = emptyBottles % numExchange;
            }


            return drunkBottles;
        }
    }

    class Solution_Correct_1 {
        public int numWaterBottles(int numBottles, int numExchange) {
            if (numBottles < 0) {
                return 0;
            }

            int fullBottles = numBottles;
            int emptyBottles = 0;
            int drunkBottles = 0;

            while (fullBottles > 0 || emptyBottles > numExchange) {
                drunkBottles += fullBottles;
                emptyBottles += fullBottles;
                fullBottles = 0;

                fullBottles = emptyBottles / numExchange;
                emptyBottles = emptyBottles % numExchange;
            }

            return drunkBottles;
        }
    }
}

//    1518. Water Bottles
//    Easy
//    There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.
//
//    The operation of drinking a full water bottle turns it into an empty bottle.
//
//    Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
//
//
//
//    Example 1:
//
//
//    Input: numBottles = 9, numExchange = 3
//    Output: 13
//    Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
//    Number of water bottles you can drink: 9 + 3 + 1 = 13.
//    Example 2:
//
//
//    Input: numBottles = 15, numExchange = 4
//    Output: 19
//    Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
//    Number of water bottles you can drink: 15 + 3 + 1 = 19.
//
//
//    Constraints:
//
//    1 <= numBottles <= 100
//    2 <= numExchange <= 100