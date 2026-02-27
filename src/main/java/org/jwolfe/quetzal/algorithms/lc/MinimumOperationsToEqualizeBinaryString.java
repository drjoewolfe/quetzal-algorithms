package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumOperationsToEqualizeBinaryString {
    class Solution {
        public int minOperations(String s, int k) {
            int n = s.length();

            int startZeros = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    startZeros++;
                }
            }

            if (startZeros == 0) {
                return 0;
            }

            int[] operations = new int[n + 1];
            Arrays.fill(operations, -1);

            TreeSet<Integer> evenSet = new TreeSet<>();
            TreeSet<Integer> oddSet = new TreeSet<>();

            for (int count = 0; count <= n; count++) {
                if (count % 2 == 0) {
                    evenSet.add(count);
                } else {
                    oddSet.add(count);
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startZeros);
            operations[startZeros] = 0;

            if (startZeros % 2 == 0) {
                evenSet.remove(startZeros);
            } else {
                oddSet.remove(startZeros);
            }

            while (!queue.isEmpty()) {
                int z = queue.poll();

                int minF = Math.max(0, k - n + z);
                int maxF = Math.min(k, z);

                int minNewZ = z + k - 2 * minF;
                int maxNewZ = z + k - 2 * maxF;

                Set<Integer> currentSet = (minNewZ % 2 == 0) ? evenSet : oddSet;

                int low = Math.min(minNewZ, maxNewZ);
                int high = Math.max(minNewZ, maxNewZ);

                Integer next = ((TreeSet<Integer>) currentSet).ceiling(low);

                while (next != null && next <= high) {
                    int newZ = next;

                    operations[newZ] = operations[z] + 1;

                    if (newZ == 0) {
                        return operations[newZ];
                    }

                    queue.offer(newZ);

                    currentSet.remove(newZ);

                    next = ((TreeSet<Integer>) currentSet).ceiling(low);
                }


            }

            return -1;
        }
    }

    class Solution_TLE {
        public int minOperations(String s, int k) {
            int n = s.length();

            int startZeros = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    startZeros++;
                }
            }

            if (startZeros == 0) {
                return 0;
            }

            int[] operations = new int[n + 1];
            Arrays.fill(operations, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startZeros);
            operations[startZeros] = 0;

            while (!queue.isEmpty()) {
                int z = queue.poll();

                int minF = Math.max(0, k - n + z);
                int maxF = Math.min(k, z);

                for (int f = minF; f <= maxF; f++) {
                    int newZ = z + k - 2 * f;

                    if (operations[newZ] == -1) {
                        operations[newZ] = operations[z] + 1;

                        if (newZ == 0) {
                            return operations[newZ];
                        }

                        queue.offer(newZ);
                    }
                }
            }

            return -1;
        }
    }
}

//    3666. Minimum Operations to Equalize Binary String
//    Hard
//    You are given a binary string s, and an integer k.
//
//    In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.
//
//    Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not possible, return -1.
//
//
//
//    Example 1:
//
//    Input: s = "110", k = 1
//
//    Output: 1
//
//    Explanation:
//
//    There is one '0' in s.
//    Since k = 1, we can flip it directly in one operation.
//    Example 2:
//
//    Input: s = "0101", k = 3
//
//    Output: 2
//
//    Explanation:
//
//    One optimal set of operations choosing k = 3 indices in each operation is:
//
//    Operation 1: Flip indices [0, 1, 3]. s changes from "0101" to "1000".
//    Operation 2: Flip indices [1, 2, 3]. s changes from "1000" to "1111".
//    Thus, the minimum number of operations is 2.
//
//    Example 3:
//
//    Input: s = "101", k = 2
//
//    Output: -1
//
//    Explanation:
//
//    Since k = 2 and s has only one '0', it is impossible to flip exactly k indices to make all '1'. Hence, the answer is -1.
//
//
//
//    Constraints:
//
//    1 <= s.length <= 10​​​​​​​5
//    s[i] is either '0' or '1'.
//    1 <= k <= s.length