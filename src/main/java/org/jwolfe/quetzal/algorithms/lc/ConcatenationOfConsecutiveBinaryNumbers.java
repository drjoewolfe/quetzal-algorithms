package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcatenationOfConsecutiveBinaryNumbers {
    class Solution {
        private int MOD = 1_000_000_007;

        public int concatenatedBinary(int n) {
            if(n < 1) {
                return 0;
            }

            long result = 1;
            int width = 1;

            for(int i = 2; i <= n; i++) {
                if((i & (i - 1)) == 0) {
                    width++;
                }

                result <<= width;
                result += i;

                result %= MOD;
            }

            return (int) result;
        }
    }

    class Solution_Optimal_1 {
        final int MOD = 1_000_000_007;

        public int concatenatedBinary(int n) {
            if(n < 1) {
                return 0;
            }

            long result = 1;
            int width = 1;
            for(int i = 2; i <= n; i++) {
                if((i & (i - 1)) == 0) {
                    width++;
                }

                result <<= width;
                result += i;

                result %= MOD;
            }

            return (int) result;
        }
    }

    class Solution_Cache {
        final int MOD = 1_000_000_007;

        public int concatenatedBinary(int n) {
            Map<Integer, List<Integer>> cache = new HashMap<>();

            return concatenatedBinaryHelper(n, cache);
        }

        public int concatenatedBinaryHelper(int n, Map<Integer, List<Integer>> cache) {
            if(n < 1) {
                return 0;
            }

            if(n == 1) {
                return 1;
            }

            int result = concatenatedBinary(n - 1);

            List<Integer> binary = new ArrayList<>();
            int j = n;
            while(j > 0) {
                binary.add(j % 2);
                j /= 2;

                if(cache.containsKey(j)) {
                    binary.addAll(cache.get(j));
                    break;
                }
            }

            for(int k = binary.size() - 1; k >= 0; k--) {
                result = result * 2 + binary.get(k);
                result %= MOD;
            }

            cache.put(n, binary);
            return result;
        }
    }

    class Solution_Recursive {
        final int MOD = 1_000_000_007;

        public int concatenatedBinary(int n) {
            if(n < 1) {
                return 0;
            }

            if(n == 1) {
                return 1;
            }

            int result = concatenatedBinary(n - 1);

            List<Integer> binary = new ArrayList<>();
            int j = n;
            while(j > 0) {
                binary.add(j % 2);
                j /= 2;
            }

            for(int k = binary.size() - 1; k >= 0; k--) {
                result = result * 2 + binary.get(k);
                result %= MOD;
            }

            return result;
        }
    }

    class Solution_Brute {
        public int concatenatedBinary(int n) {
            if(n < 1) {
                return 0;
            }

            int mod = 1_000_000_007;

            int result = 0;
            for(int i = 1; i <= n; i++) {
                int j = i;

                List<Integer> binary = new ArrayList<>();
                while(j > 0) {
                    binary.add(j % 2);
                    j /= 2;
                }

                for(int k = binary.size() - 1; k >= 0; k--) {
                    result = result * 2 + binary.get(k);
                    result %= mod;
                }
            }

            return result;
        }
    }
}

//    1680. Concatenation of Consecutive Binary Numbers
//    Medium
//    Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 1
//    Output: 1
//    Explanation: "1" in binary corresponds to the decimal value 1.
//    Example 2:
//
//    Input: n = 3
//    Output: 27
//    Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
//    After concatenating them, we have "11011", which corresponds to the decimal value 27.
//    Example 3:
//
//    Input: n = 12
//    Output: 505379714
//    Explanation: The concatenation results in "1101110010111011110001001101010111100".
//    The decimal value of that is 118505380540.
//    After modulo 109 + 7, the result is 505379714.
//
//
//    Constraints:
//
//    1 <= n <= 105