package org.jwolfe.quetzal.algorithms.lc;

public class SuperPow {
    class Solution {
        private int MOD = 1337;

        public int superPow(int a, int[] b) {
            return superPow(a, b, b.length);
        }

        private int superPow(int a, int[] b, int length) {
            if(length == 1) {
                return powMod(a, b[0]);
            }

            return (powMod(superPow(a, b, length - 1), 10) * powMod(a, b[length - 1])) % MOD;
        }

        private int powMod(int a, int b) {
            long pow = 1;
            while(b > 0) {
                pow *= a;
                pow %= MOD;
                b--;
            }

            return (int) pow;
        }
    }

// 2
// [3]

// 2147483647
// [2,0,0]
}

//    372. Super Pow
//    Medium
//    Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
//
//
//
//    Example 1:
//
//    Input: a = 2, b = [3]
//    Output: 8
//    Example 2:
//
//    Input: a = 2, b = [1,0]
//    Output: 1024
//    Example 3:
//
//    Input: a = 1, b = [4,3,3,8,5,2]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= a <= 231 - 1
//    1 <= b.length <= 2000
//    0 <= b[i] <= 9
//    b does not contain leading zeros.