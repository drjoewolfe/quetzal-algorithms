package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class IntegerBreak {
    class Solution {
        public int integerBreak(int n) {
            int[] cache = new int[n + 1];
            cache[1] = 1;
            return integerBreak(n, cache);
        }

        private int integerBreak(int n, int[] cache) {
            if(cache[n] != 0) {
                return cache[n];
            }

            int maxProduct = 1;
            for(int a = 1; a < n; a++) {
                int b = n - a;

                maxProduct = Math.max(maxProduct,
                        Math.max(a * integerBreak(b, cache),
                                a * b));
            }

            cache[n] = maxProduct;
            return maxProduct;
        }
    }

    class Solution_Recursive_2 {
        public int integerBreak(int n) {
            if( n == 1) {
                return 1;
            }

            int maxProduct = 1;
            for(int a = 1; a < n; a++) {
                int b = n - a;

                maxProduct = Math.max(maxProduct,
                        Math.max(a * integerBreak(b),
                                a * b));
            }

            return maxProduct;
        }
    }


    class Solution_Correct_1 {
        public int integerBreak(int n) {
            Map<Integer, Integer> cache = new HashMap<>();
            return integerBreak(n, cache);
        }

        public int integerBreak(int n, Map<Integer, Integer> cache) {
            if(n < 2) {
                return 1;
            }

            if(cache.containsKey(n)) {
                return cache.get(n);
            }

            int maxProduct = 1;
            for(int i = 1; i < n; i++) {
                maxProduct = Math.max(maxProduct,
                        Math.max(i * integerBreak(n - i, cache),
                                i * (n - i)));
            }

            cache.put(n, maxProduct);
            return maxProduct;
        }
    }

    class Solution_Recursive {
        public int integerBreak(int n) {
            if(n < 2) {
                return 1;
            }

            int maxProduct = 1;
            for(int i = 1; i < n; i++) {
                maxProduct = Math.max(maxProduct,
                        Math.max(i * integerBreak(n - i),
                                i * (n - i)));
            }

            return maxProduct;
        }
    }

    class Solution_Recursive1 {
        int maxProduct = 1;

        public int integerBreak(int n) {
            if(n < 2 || n > 58) {
                return 0;
            }

            integerBreak(n, 0, 1, 0);
            return maxProduct;
        }

        private void integerBreak(int n, int sum, int product, int count) {
            if(sum == n) {
                if(count > 1) {
                    maxProduct = Math.max(maxProduct, product);
                }

                return;
            }

            if(sum > n) {
                return;
            }

            for(int a = 1; a <= n - sum; a++) {
                integerBreak(n, sum + a, product * a, count + 1);
            }
        }
    }
}

//    343. Integer Break
//    Medium
//    Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
//
//    Return the maximum product you can get.
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: 1
//    Explanation: 2 = 1 + 1, 1 × 1 = 1.
//    Example 2:
//
//    Input: n = 10
//    Output: 36
//    Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
//
//
//    Constraints:
//
//    2 <= n <= 58