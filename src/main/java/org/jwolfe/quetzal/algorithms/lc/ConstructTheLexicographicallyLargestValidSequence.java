package org.jwolfe.quetzal.algorithms.lc;

public class ConstructTheLexicographicallyLargestValidSequence {
    class Solution {
        public int[] constructDistancedSequence(int n) {
            if (n < 1) {
                return new int[0];
            }

            int[] results = new int[2 * n - 1];
            boolean[] isNumberSlotted = new boolean[n + 1];

            constructDistancedSequence(results, 0, isNumberSlotted, n);
            return results;
        }

        private boolean constructDistancedSequence(int[] results, int index, boolean[] isNumberSlotted, int n) {
            if (index == results.length) {
                return true;
            }

            if (results[index] != 0) {
                return constructDistancedSequence(results, index + 1, isNumberSlotted, n);
            }

            for (int number = n; number >= 1; number--) {
                if (isNumberSlotted[number]) {
                    continue;
                }

                isNumberSlotted[number] = true;
                results[index] = number;

                if (number == 1) {
                    if (constructDistancedSequence(results, index + 1, isNumberSlotted, n)) {
                        return true;
                    }
                } else {
                    if (number + index < results.length
                            && results[number + index] == 0) {
                        results[number + index] = number;
                        if (constructDistancedSequence(results, index + 1, isNumberSlotted, n)) {
                            return true;
                        }

                        results[number + index] = 0;
                    }
                }

                isNumberSlotted[number] = false;
                results[index] = 0;
            }

            return false;
        }
    }
}

//    1718. Construct the Lexicographically Largest Valid Sequence
//    Medium
//    Given an integer n, find a sequence that satisfies all of the following:
//
//    The integer 1 occurs once in the sequence.
//    Each integer between 2 and n occurs twice in the sequence.
//    For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
//    The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
//
//    Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
//
//    A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.
//
//
//
//    Example 1:
//
//    Input: n = 3
//    Output: [3,1,2,3,2]
//    Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
//    Example 2:
//
//    Input: n = 5
//    Output: [5,3,1,4,3,5,2,4,2]
//
//
//    Constraints:
//
//    1 <= n <= 20