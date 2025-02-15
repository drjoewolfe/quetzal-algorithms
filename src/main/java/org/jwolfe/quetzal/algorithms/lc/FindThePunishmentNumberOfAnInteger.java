package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class FindThePunishmentNumberOfAnInteger {
    class Solution {
        public int punishmentNumber(int n) {
            if (n < 1) {
                return 0;
            }

            int punishmentNumber = 0;
            for (int a = 1; a <= n; a++) {
                int sa = a * a;
                String strSA = Integer.toString(sa);

                int[][] memo = new int[strSA.length()][a + 1];
                for (int[] row : memo) {
                    Arrays.fill(row, -1);
                }

                boolean partitioned = canPartition(strSA, 0, 0, a, memo);
                if (partitioned) {
                    punishmentNumber += sa;
                }
            }

            return punishmentNumber;
        }

        private boolean canPartition(String str, int index, int sum, int target, int[][] memo) {
            if (index == str.length()) {
                return sum == target;
            }

            if (sum > target) {
                return false;
            }

            if (memo[index][sum] != -1) {
                return memo[index][sum] == 1;
            }

            for (int i = index; i < str.length(); i++) {
                String partitionString = str.substring(index, i + 1);
                int partitionValue = Integer.parseInt(partitionString);

                if (canPartition(str, i + 1, sum + partitionValue, target, memo)) {
                    memo[index][sum] = 1;
                    return true;
                }
            }

            memo[index][sum] = 0;
            return false;
        }
    }

    class Solution_Recursive {
        public int punishmentNumber(int n) {
            if (n < 1) {
                return 0;
            }

            int punishmentNumber = 0;
            for (int a = 1; a <= n; a++) {
                int sa = a * a;
                String strSA = Integer.toString(sa);

                boolean partitioned = canPartition(strSA, 0, 0, a);
                if (partitioned) {
                    punishmentNumber += sa;
                }
            }

            return punishmentNumber;
        }

        private boolean canPartition(String str, int index, int sum, int target) {
            if (index == str.length()) {
                return sum == target;
            }

            if (sum > target) {
                return false;
            }

            for (int i = index; i < str.length(); i++) {
                String partitionString = str.substring(index, i + 1);
                int partitionValue = Integer.parseInt(partitionString);

                if (canPartition(str, i + 1, sum + partitionValue, target)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    2698. Find the Punishment Number of an Integer
//    Medium
//    Given a positive integer n, return the punishment number of n.
//
//    The punishment number of n is defined as the sum of the squares of all integers i such that:
//
//    1 <= i <= n
//    The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals i.
//
//
//    Example 1:
//
//    Input: n = 10
//    Output: 182
//    Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy the conditions in the statement:
//    - 1 since 1 * 1 = 1
//    - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to 8 + 1 == 9.
//    - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum equal to 10 + 0 == 10.
//    Hence, the punishment number of 10 is 1 + 81 + 100 = 182
//    Example 2:
//
//    Input: n = 37
//    Output: 1478
//    Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy the conditions in the statement:
//    - 1 since 1 * 1 = 1.
//    - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
//    - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
//    - 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
//    Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478
//
//
//    Constraints:
//
//    1 <= n <= 1000