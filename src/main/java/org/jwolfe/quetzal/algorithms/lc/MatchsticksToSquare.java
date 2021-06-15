package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MatchsticksToSquare {
    class Solution {
        public boolean makesquare(int[] nums) {
            if(nums == null || nums.length < 4) {
                return false;
            }

            int perimeter = 0;
            for(int a : nums) {
                perimeter += a;
            }

            if(perimeter % 4 != 0) {
                return false;
            }

            int sideLength = perimeter / 4;

            int n = nums.length;
            boolean[] used = new boolean[n];
            Map<String, Boolean> memo = new HashMap<>();

            return makesquare(nums, n, -1, 0, sideLength, used, memo);
        }

        private boolean makesquare(int[] nums, int n, int index, int sideCount, int sideLength, boolean[] used, Map<String, Boolean> memo) {
            int total = 0;
            int mask = 0;
            for(int i = 0; i < n; i++) {
                if(used[i]) {
                    total += nums[i];
                    mask ^= (1 << i);
                }
            }

            if(total > 0 && total % sideLength == 0) {
                sideCount++;
            }

            if(sideCount == 3) {
                return true;
            }

            String key = mask + "_" + sideCount;
            if(memo.containsKey(key)) {
                return memo.get(key);
            }

            boolean result = false;
            // int remaining = (sideLength * 4) - total;
            int remaining = ((total / sideLength + 1) * sideLength) - total;
            for(int i = 0; i < n; i++) {
                if(used[i] || nums[i] > remaining) {
                    continue;
                }

                used[i] = true;
                if(makesquare(nums, n, i, sideCount, sideLength, used, memo)) {
                    result = true;
                    break;
                }

                used[i] = false;
            }

            memo.put(key, result);
            return result;
        }

        private void print(boolean[] arr) {
            for(var b : arr) {
                System.out.print(b ? "T " : "F ");
            }

            // System.out.println();
        }
    }

    class Solution_Brute {
        public boolean makesquare(int[] nums) {
            if(nums == null || nums.length < 4) {
                return false;
            }

            int sum = 0;
            for(int a : nums) {
                sum += a;
            }

            if(sum % 4 != 0) {
                return false;
            }

            int sideLength = sum / 4;
            return makesquare(nums, 0, sideLength, new int[4]);
        }

        private boolean makesquare(int[] nums, int index, int sideLength, int[] sides) {
            if(index == nums.length) {
                return sides[0] == sideLength
                        && sides[1] == sideLength
                        && sides[2] == sideLength
                        && sides[3] == sideLength;
            }

            int a = nums[index];
            for(int i = 0; i < 4; i++) {
                if(sides[i] + a <= sideLength) {
                    sides[i] += a;
                    if(makesquare(nums, index + 1, sideLength, sides)) {
                        return true;
                    }

                    sides[i] -= a;
                }
            }

            return false;
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Brute_Correct_1 {
        public boolean makesquare(int[] matchsticks) {
            if(matchsticks == null || matchsticks.length < 4) {
                return false;
            }

            int perimeter = 0;
            for(int stick : matchsticks) {
                perimeter += stick;
            }

            if(perimeter % 4 != 0) {
                return false;
            }

            int side = perimeter / 4;
            return makesquare(matchsticks, 0, side, new int[4]);
        }

        private boolean makesquare(int[] matchsticks, int index, int side, int[] sides) {
            if(index == matchsticks.length) {
                return (sides[0] == side)
                        && (sides[1] == side)
                        && (sides[2] == side)
                        && (sides[3] == side);
            }

            for(int i = 0; i < 4; i++) {
                if(sides[i] + matchsticks[index] > side) {
                    continue;
                }

                sides[i] += matchsticks[index];
                if(makesquare(matchsticks, index + 1, side, sides)) {
                    return true;
                }

                sides[i] -= matchsticks[index];
            }

            return false;
        }
    }

// [1,1,2,2,2]
// [10,6,5,5,5,3,3,3,2,2,2,2]
// [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
}

//    473. Matchsticks to Square
//    Medium
//    You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
//
//    Return true if you can make this square and false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: matchsticks = [1,1,2,2,2]
//    Output: true
//    Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
//    Example 2:
//
//    Input: matchsticks = [3,3,3,3,4]
//    Output: false
//    Explanation: You cannot find a way to form a square with all the matchsticks.
//
//
//    Constraints:
//
//    1 <= matchsticks.length <= 15
//    0 <= matchsticks[i] <= 109