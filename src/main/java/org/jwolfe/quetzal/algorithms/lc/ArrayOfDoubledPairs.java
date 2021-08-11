package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayOfDoubledPairs {
    class Solution {
        public boolean canReorderDoubled(int[] A) {
            if(A == null || A.length == 0 || A.length % 2 != 0) {
                return true;
            }

            Integer[] B = new Integer[A.length];
            for(int i = 0; i < A.length; i++) {
                B[i] = A[i];
            }

            Arrays.sort(B, (a, b) -> Math.abs(a) - Math.abs(b));
            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int b : B) {
                frequencies.put(b, frequencies.getOrDefault(b, 0) + 1);
            }

            for(int b : B) {
                int count = frequencies.get(b);
                if(count == 0) {
                    // Count is zero only if it has been completely paired with numbers before it
                    continue;
                }

                int pair = b * 2;
                if(!frequencies.containsKey(pair)
                        || frequencies.get(pair) == 0) {
                    return false;
                }

                frequencies.put(b, frequencies.get(b) - 1);
                frequencies.put(pair, frequencies.get(pair) - 1);
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public boolean canReorderDoubled(int[] arr) {
            if(arr == null || arr.length == 0) {
                return true;
            }

            int n = arr.length;
            Integer[] A = new Integer[n];
            for(int i = 0; i < n; i++) {
                A[i] = arr[i];
            }

            Arrays.sort(A, (a, b) -> Math.abs(a) - Math.abs(b));

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : arr) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            for(int a : A) {
                if(frequencies.get(a) == 0) {
                    continue;
                }

                int b = a * 2;
                if(!frequencies.containsKey(b)
                        || frequencies.get(b) == 0) {
                    return false;
                }

                frequencies.put(a, frequencies.get(a) - 1);
                frequencies.put(b, frequencies.get(b) - 1);
            }

            return true;
        }
    }
}

//    954. Array of Doubled Pairs
//    Medium
//    Given an array of integers arr of even length, return true if and only if it is possible to reorder it such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2.
//
//
//
//    Example 1:
//
//    Input: arr = [3,1,3,6]
//    Output: false
//    Example 2:
//
//    Input: arr = [2,1,2,6]
//    Output: false
//    Example 3:
//
//    Input: arr = [4,-2,2,-4]
//    Output: true
//    Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
//    Example 4:
//
//    Input: arr = [1,2,4,16,8,4]
//    Output: false
//
//
//    Constraints:
//
//    0 <= arr.length <= 3 * 104
//    arr.length is even.
//    -105 <= arr[i] <= 105