package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfWaysToReachAPositionAfterExactlyKSteps {
    class Solution {
        int MOD = 1_000_000_007;

        public int numberOfWays(int startPos, int endPos, int k) {
            int n = endPos - startPos;
            Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

            return numberOfWaysHelper(startPos, endPos, startPos, k, cache);
        }

        private int numberOfWaysHelper(int startPos, int endPos, int currentPos, int k, Map<Integer, Map<Integer, Integer>> cache) {
            if(k == 0) {
                if(currentPos == endPos) {
                    return 1;
                }

                return 0;
            }

            if(cache.containsKey(currentPos)) {
                var map = cache.get(currentPos);
                if(map.containsKey(k)) {
                    return map.get(k);
                }
            } else {
                cache.put(currentPos, new HashMap<>());
            }

            int ways = 0;
            ways += numberOfWaysHelper(startPos, endPos, currentPos + 1, k - 1, cache);
            ways %= MOD;
            ways += numberOfWaysHelper(startPos, endPos, currentPos - 1, k - 1, cache);
            ways %= MOD;

            cache.get(currentPos).put(k, ways);

            return ways;
        }
    }

    class Solution_Recursive_TLE {
        int MOD = 1_000_000_007;

        public int numberOfWays(int startPos, int endPos, int k) {
            return numberOfWaysHelper(endPos, startPos, k);
        }

        private int numberOfWaysHelper(int endPos, int currentPos, int k) {
            if(k == 0) {
                if(currentPos == endPos) {
                    return 1;
                }

                return 0;
            }

            int ways = 0;
            ways += numberOfWaysHelper(endPos, currentPos + 1, k - 1);
            ways += numberOfWaysHelper(endPos, currentPos - 1, k - 1);

            return ways;
        }
    }

// 1
// 2
// 3

// 1
// 1000
// 999
}

//    2400. Number of Ways to Reach a Position After Exactly k Steps
//    Medium
//    You are given two positive integers startPos and endPos. Initially, you are standing at position startPos on an infinite number line. With one step, you can move either one position to the left, or one position to the right.
//
//    Given a positive integer k, return the number of different ways to reach the position endPos starting from startPos, such that you perform exactly k steps. Since the answer may be very large, return it modulo 109 + 7.
//
//    Two ways are considered different if the order of the steps made is not exactly the same.
//
//    Note that the number line includes negative integers.
//
//
//
//    Example 1:
//
//    Input: startPos = 1, endPos = 2, k = 3
//    Output: 3
//    Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
//    - 1 -> 2 -> 3 -> 2.
//    - 1 -> 2 -> 1 -> 2.
//    - 1 -> 0 -> 1 -> 2.
//    It can be proven that no other way is possible, so we return 3.
//    Example 2:
//
//    Input: startPos = 2, endPos = 5, k = 10
//    Output: 0
//    Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.
//
//
//    Constraints:
//
//    1 <= startPos, endPos, k <= 1000