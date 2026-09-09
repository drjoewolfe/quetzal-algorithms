package org.jwolfe.quetzal.algorithms.lc;

public class CountCommasInRangeII {
    class Solution {
        public long countCommas(long n) {
            long p = 1000, res = 0;
            while (p <= n) {
                res += n - p + 1;
                p *= 1000;
            }
            return res;
        }
    }

    class Solution_Incorrect {
        public long countCommas(long n) {
            long commas = 0;

            int zeros = 4;
            for (int exp = 12; exp >= 3; exp -= 3) {
                long base = (long) Math.pow(10, exp) - 1;
                long range = Math.max(n - base, 0);
                commas += range * zeros;

                n -= range;
                zeros--;
            }

            return commas;
        }
    }
}

//    3871. Count Commas in Range II
//    Medium
//    You are given an integer n.
//
//    Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number formatting.
//
//    In standard formatting:
//
//    A comma is inserted after every three digits from the right.
//    Numbers with fewer than 4 digits contain no commas.
//
//
//    Example 1:
//
//    Input: n = 1002
//
//    Output: 3
//
//    Explanation:
//
//    The numbers "1,000", "1,001", and "1,002" each contain one comma, giving a total of 3.
//
//    Example 2:
//
//    Input: n = 998
//
//    Output: 0
//
//    Explanation:
//
//    ​​​​​​​All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.
//
//
//
//    Constraints:
//
//    1 <= n <= 1015