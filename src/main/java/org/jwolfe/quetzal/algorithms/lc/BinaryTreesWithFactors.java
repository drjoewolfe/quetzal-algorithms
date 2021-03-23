package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreesWithFactors {
    class Solution {
        public int numFactoredBinaryTrees(int[] arr) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int mod = 1_000_000_007;

            Arrays.sort(arr);
            Map<Integer, Long> map = new HashMap<>();

            long count = 0;
            int n = arr.length;

            for(int i = 0; i < n; i++) {
                int a = arr[i];
                long ac = 1;

                for(int j = 0; j < i; j++) {
                    int b = arr[j];
                    if(a % b == 0) {
                        int c = a / b;

                        if(map.containsKey(c)) {
                            ac += map.get(b) * map.get(c);
                            ac %= mod;
                        }
                    }
                }

                map.put(a, ac);
                count += ac;
                count %= mod;
            }

            return (int) count;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [2,4]
// [18,3,6,2]
// [45,42,2,18,23,1170,12,41,40,9,47,24,33,28,10,32,29,17,46,11,759,37,6,26,21,49,31,14,19,8,13,7,27,22,3,36,34,38,39,30,43,15,4,16,35,25,20,44,5,48]
}

//    823. Binary Trees With Factors
//    Medium
//    Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
//
//    We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
//
//    Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: arr = [2,4]
//    Output: 3
//    Explanation: We can make these trees: [2], [4], [4, 2, 2]
//    Example 2:
//
//    Input: arr = [2,4,5,10]
//    Output: 7
//    Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
//
//
//    Constraints:
//
//    1 <= arr.length <= 1000
//    2 <= arr[i] <= 109
