package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            int n = T.length;
            int[] results = new int[n];

            Stack<Integer> stack = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                int temperature = T[i];
                while (!stack.isEmpty()
                        && T[stack.peek()] <= temperature) {
                    stack.pop();
                }

                results[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
                stack.push(i);
            }

            return results;
        }
    }

    class Solution_Correct_3 {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            Stack<Integer> stack = new Stack<>();
            int n = T.length;
            int[] results = new int[n];

            for (int i = n - 1; i >= 0; i--) {
                int val = T[i];

                while (!stack.isEmpty()
                        && T[stack.peek()] <= val) {
                    stack.pop();
                }

                int j = !stack.isEmpty() ? stack.peek() : i;
                results[i] = j - i;

                stack.push(i);
            }

            return results;
        }
    }

    class Solution_Correct_2 {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            int n = T.length;
            int[] days = new int[n];

            Stack<Integer> stack = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                int temp = T[i];

                while (!stack.isEmpty()
                        && T[stack.peek()] <= temp) {
                    stack.pop();
                }

                int j = stack.isEmpty() ? i : stack.peek();
                days[i] = j - i;

                stack.push(i);
            }

            return days;
        }
    }

    class Solution_Correct_1 {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            int n = T.length;
            int[] days = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                    stack.pop();
                }

                days[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }

            return days;
        }
    }

    class Solution_Standard {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            int n = T.length;
            int[] days = new int[n];
            int[] tempIndexes = new int[101];
            Arrays.fill(tempIndexes, Integer.MIN_VALUE);
            tempIndexes[T[n - 1]] = n - 1;

            for (int i = n - 2; i >= 0; i--) {
                int t = T[i];
                int minDays = Integer.MAX_VALUE;

                for (int nt = t + 1; nt <= 100; nt++) {
                    if (tempIndexes[nt] > i) {
                        minDays = Math.min(minDays, tempIndexes[nt] - i);
                    }
                }

                days[i] = minDays == Integer.MAX_VALUE ? 0 : minDays;
                tempIndexes[t] = i;
            }


            return days;
        }
    }


    class Solution_Brute {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }

            int n = T.length;
            int[] days = new int[n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (T[j] > T[i]) {
                        days[i] = j - i;
                        break;
                    }
                }
            }

            return days;
        }
    }


// [73,74,75,71,69,72,76,73]
// [89,62,70,58,47,47,46,76,100,70]
}

//    739. Daily Temperatures
//    Medium
//    Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
//
//
//
//    Example 1:
//
//    Input: temperatures = [73,74,75,71,69,72,76,73]
//    Output: [1,1,4,2,1,1,0,0]
//    Example 2:
//
//    Input: temperatures = [30,40,50,60]
//    Output: [1,1,1,0]
//    Example 3:
//
//    Input: temperatures = [30,60,90]
//    Output: [1,1,0]
//
//
//    Constraints:
//
//    1 <= temperatures.length <= 105
//    30 <= temperatures[i] <= 100