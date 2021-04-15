package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
    class Solution {
        public int fib(int N) {
            if(N < 2) {
                return N;
            }

            int n2 = 0;
            int n1 = 1;
            int n0 = 1;

            for(int i = 2; i <= N; i++) {
                n0 = n1 + n2;
                n2 = n1;
                n1 = n0;
            }

            return n0;
        }
    }

    class Solution_Memoized {
        public int fib(int n) {
            if(n < 0) {
                throw new RuntimeException("Fibonacci series not defined for negative numbers");
            }

            Map<Integer, Integer> memo = new HashMap<>();
            return fib(n, memo);
        }

        private int fib(int n, Map<Integer, Integer> memo) {
            if(n < 2) {
                return n;
            }

            if(memo.containsKey(n)) {
                return memo.get(n);
            }

            int val = fib(n -1) + fib(n - 2);
            memo.put(n, val);
            return val;
        }
    }

    class Solution_Classic {
        public int fib(int N) {
            if(N < 2) {
                return N;
            }

            int[] numbers = new int[N + 1];
            numbers[0] = 0;
            numbers[1] = 1;

            for(int i = 2; i <= N; i++) {
                numbers[i] = numbers[i - 1] + numbers[i - 2];
            }

            return numbers[N];
        }
    }

    class Solution_Brute {
        public int fib(int N) {
            if(N == 0) {
                return 0;
            }

            if(N == 1) {
                return 1;
            }

            return fib(N - 1) + fib(N - 2);
        }
    }
}

//    509. Fibonacci Number
//    Easy
//    The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
//
//    F(0) = 0, F(1) = 1
//    F(n) = F(n - 1) + F(n - 2), for n > 1.
//    Given n, calculate F(n).
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: 1
//    Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
//    Example 2:
//
//    Input: n = 3
//    Output: 2
//    Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
//    Example 3:
//
//    Input: n = 4
//    Output: 3
//    Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
//
//
//    Constraints:
//
//    0 <= n <= 30
