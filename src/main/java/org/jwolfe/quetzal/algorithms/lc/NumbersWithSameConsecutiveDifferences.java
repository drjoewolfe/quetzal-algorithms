package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumbersWithSameConsecutiveDifferences {
    class Solution {
        public int[] numsSameConsecDiff(int N, int K) {
            if(N == 0 || K < 0) {
                return new int[0];
            }

            Set<Integer> results = new HashSet<>();
            for(int i = 1; i <= 9; i++) {
                generateNumbersWithSameConsecDiff(N - 1, i, i, K, results);
            }

            int[] output = new int[results.size()];
            int i = 0;
            for(int number : results) {
                output[i++] = number;
            }

            return output;
        }

        private void generateNumbersWithSameConsecDiff(int N, int number, int prevDigit, int K, Set<Integer> results) {
            if(N == 0) {
                results.add(number);
                return;
            }

            int digit = prevDigit - K;
            if(digit >= 0) {
                generateNumbersWithSameConsecDiff(N - 1, number * 10 + digit, digit, K, results);
            }

            digit = prevDigit + K;
            if(digit <= 9) {
                generateNumbersWithSameConsecDiff(N - 1, number * 10 + digit, digit, K, results);
            }
        }
    }

    class Solution_Correct_1 {
        public int[] numsSameConsecDiff(int N, int K) {
            if(N == 0 || K < 0) {
                return new int[0];
            }

            if(N == 1) {
                return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            }

            List<Integer> results = new ArrayList<>();
            generateNumbersWithSameConsecutiveDifferences(N, K, 0, 0, results);

            int[] output = new int[results.size()];
            for(int i = 0; i < results.size(); i++) {
                output[i] = results.get(i);
            }

            return output;
        }

        private void generateNumbersWithSameConsecutiveDifferences(int N, int K, int number, int iteration, List<Integer> results) {
            if(iteration == N) {
                // Number formed
                results.add(number);
                return;
            }

            int previousDigit = number % 10;

            for(int digit = 0; digit < 10; digit++) {
                if(iteration == 0 && digit == 0) {
                    continue;
                }

                int newNumber = number * 10 + digit;
                if(iteration == 0) {
                    generateNumbersWithSameConsecutiveDifferences(N, K, newNumber, iteration + 1, results);
                } else {
                    int diff = Math.abs(digit - previousDigit);
                    if(diff == K) {
                        generateNumbersWithSameConsecutiveDifferences(N, K, newNumber, iteration + 1, results);
                    }
                }
            }
        }
    }

// 3
// 7

// 2
// 0
}

//    967. Numbers With Same Consecutive Differences
//    Medium
//    Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
//
//    Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
//
//    You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: n = 3, k = 7
//    Output: [181,292,707,818,929]
//    Explanation: Note that 070 is not a valid number, because it has leading zeroes.
//    Example 2:
//
//    Input: n = 2, k = 1
//    Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
//
//
//    Constraints:
//
//    2 <= n <= 9
//    0 <= k <= 9