package org.jwolfe.quetzal.algorithms.lc;

public class GCDOfOddAndEvenSums {
    class Solution {
        public int gcdOfOddEvenSums(int n) {
            int sumOdd = 0;
            int sumEven = 0;

            int odd = 1;
            int even = 2;
            for (int x = 1; x <= n; x++) {
                sumOdd += odd;
                sumEven += even;

                odd += 2;
                even += 2;
            }

            System.out.println(sumOdd + ", " + sumEven);
            return gcd(sumOdd, sumEven);
        }

        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = a;
                a = b;
                b = temp % b;
            }

            return a;
        }
    }
}

//    3658. GCD of Odd and Even Sums
//    Easy
//    You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:
//
//    sumOdd: the sum of the smallest n positive odd numbers.
//
//    sumEven: the sum of the smallest n positive even numbers.
//
//    Return the GCD of sumOdd and sumEven.
//
//
//
//    Example 1:
//
//    Input: n = 4
//
//    Output: 4
//
//    Explanation:
//
//    Sum of the first 4 odd numbers sumOdd = 1 + 3 + 5 + 7 = 16
//    Sum of the first 4 even numbers sumEven = 2 + 4 + 6 + 8 = 20
//    Hence, GCD(sumOdd, sumEven) = GCD(16, 20) = 4.
//
//    Example 2:
//
//    Input: n = 5
//
//    Output: 5
//
//    Explanation:
//
//    Sum of the first 5 odd numbers sumOdd = 1 + 3 + 5 + 7 + 9 = 25
//    Sum of the first 5 even numbers sumEven = 2 + 4 + 6 + 8 + 10 = 30
//    Hence, GCD(sumOdd, sumEven) = GCD(25, 30) = 5.
//
//
//
//    Constraints:
//
//    1 <= n <= 10​​​​​​​00