package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountLargestGroup {
    class Solution {
        public int countLargestGroup(int n) {
            if (n < 0) {
                return 0;
            }

            if (n < 10) {
                return n;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                int k = getSumOfDigits(i);
                map.put(k, map.getOrDefault(k, 0) + 1);
            }

            int largestGroupSize = 0;
            int largestGroupCount = 0;

            for (var entry : map.entrySet()) {
                int groupSize = entry.getValue();
                if (groupSize > largestGroupSize) {
                    largestGroupSize = groupSize;
                    largestGroupCount = 1;
                } else if (groupSize == largestGroupSize) {
                    largestGroupCount++;
                }
            }

            return largestGroupCount;
        }

        private int getSumOfDigits(int val) {
            int sum = 0;
            while (val > 0) {
                sum += val % 10;
                val /= 10;
            }

            return sum;
        }
    }

    class Solution_Correct_1 {
        public int countLargestGroup(int n) {
            if (n <= 0) {
                return 0;
            }

            if (n < 10) {
                return n;
            }

            Map<Integer, Integer> map = new HashMap<>();
            int largestGroupSize = 0;
            for (int i = 1; i <= n; i++) {
                int sum = getSumOfDigits(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                largestGroupSize = Math.max(largestGroupSize, map.get(sum));
            }

            int groupCount = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == largestGroupSize) {
                    groupCount++;
                }
            }

            return groupCount;
        }

        private void print(Map<Integer, Integer> map) {
            for (var entry : map.entrySet()) {
                System.out.print("[" + entry.getKey() + ", " + entry.getValue() + "] ");
            }

            System.out.println();
        }

        private int getSumOfDigits(int n) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10);
                n /= 10;
            }

            return sum;
        }
    }
}

//    1399. Count Largest Group
//    Easy
//    You are given an integer n.
//
//    Each number from 1 to n is grouped according to the sum of its digits.
//
//    Return the number of groups that have the largest size.
//
//
//
//    Example 1:
//
//    Input: n = 13
//    Output: 4
//    Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
//    [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
//    There are 4 groups with largest size.
//    Example 2:
//
//    Input: n = 2
//    Output: 2
//    Explanation: There are 2 groups [1], [2] of size 1.
//
//
//    Constraints:
//
//    1 <= n <= 104