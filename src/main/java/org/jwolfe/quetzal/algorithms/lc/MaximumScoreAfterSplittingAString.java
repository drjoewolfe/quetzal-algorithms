package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumScoreAfterSplittingAString {
    class Solution {
        public int maxScore(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int totalOnes = 0;
            int leftZeros = 0;
            int maxLeftZeroOneDiff = Integer.MIN_VALUE;

            for (int i = 0; i < n - 1; i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    totalOnes++;
                } else {
                    leftZeros++;
                }

                int leftZeroOneDiff = leftZeros - totalOnes;
                maxLeftZeroOneDiff = Math.max(maxLeftZeroOneDiff, leftZeroOneDiff);
            }

            if (s.charAt(n - 1) == '1') {
                totalOnes++;
            }

            return maxLeftZeroOneDiff + totalOnes;
        }
    }

    class Solution_Correct_2 {
        public int maxScore(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int zeros = 0;
            int ones = 0;
            int n = s.length();

            int zeroOneDiff = Integer.MIN_VALUE;
            for (int i = 0; i < n - 1; i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    ones++;
                } else {
                    zeros++;
                }

                zeroOneDiff = Math.max(zeroOneDiff, zeros - ones);
            }

            if (s.charAt(n - 1) == '1') {
                ones++;
            }

            return zeroOneDiff + ones;
        }
    }

    class Solution_Correct_1 {
        public int maxScore(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] left = new int[n];
            int[] right = new int[n];

            left[0] = s.charAt(0) == '0' ? 1 : 0;
            right[n - 1] = s.charAt(n - 1) == '1' ? 1 : 0;

            for (int i = 1; i < n; i++) {
                char lc = s.charAt(i);
                char rc = s.charAt(n - i - 1);

                left[i] = left[i - 1] + ((lc == '0') ? 1 : 0);
                right[n - i - 1] = right[n - i] + ((rc == '1') ? 1 : 0);
            }

            int maxScore = 0;
            for (int i = 0; i < n - 1; i++) {
                int leftZeros = left[i];
                int rightOnes = right[i + 1];

                maxScore = Math.max(maxScore, leftZeros + rightOnes);
            }

            return maxScore;
        }

        private void print(int[] arr) {
            Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
            System.out.println();
        }
    }
}

//    1422. Maximum Score After Splitting a String
//    Easy
//    Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
//
//    The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
//
//
//
//    Example 1:
//
//    Input: s = "011101"
//    Output: 5
//    Explanation:
//    All possible ways of splitting s into two non-empty substrings are:
//    left = "0" and right = "11101", score = 1 + 4 = 5
//    left = "01" and right = "1101", score = 1 + 3 = 4
//    left = "011" and right = "101", score = 1 + 2 = 3
//    left = "0111" and right = "01", score = 1 + 1 = 2
//    left = "01110" and right = "1", score = 2 + 1 = 3
//    Example 2:
//
//    Input: s = "00111"
//    Output: 5
//    Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
//    Example 3:
//
//    Input: s = "1111"
//    Output: 3
//
//
//    Constraints:
//
//    2 <= s.length <= 500
//    The string s consists of characters '0' and '1' only.