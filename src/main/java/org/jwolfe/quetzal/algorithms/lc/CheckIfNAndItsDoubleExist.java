package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckIfNAndItsDoubleExist {
    class Solution {
        public boolean checkIfExist(int[] arr) {
            if (arr == null || arr.length < 2) {
                return false;
            }

            Set<Integer> set = new HashSet<>();
            for (int a : arr) {
                int da = a * 2;
                if (set.contains(da)) {
                    return true;
                }

                if (a % 2 == 0) {
                    int ha = a / 2;
                    if (set.contains(ha)) {
                        return true;
                    }
                }

                set.add(a);
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean checkIfExist(int[] arr) {
            if (arr == null || arr.length < 2) {
                return false;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                int n = arr[i];
                map.put(n, map.getOrDefault(n, 0) + 1);
            }

            for (int i = 0; i < arr.length; i++) {
                int n = arr[i];
                int dn = 2 * n;
                if (map.containsKey(dn)) {
                    if (n != 0) {
                        return true;
                    } else if (n == 0 && map.get(dn) > 1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}

//    1346. Check If N and Its Double Exist
//    Easy
//    Given an array arr of integers, check if there exist two indices i and j such that :
//
//    i != j
//    0 <= i, j < arr.length
//    arr[i] == 2 * arr[j]
//
//
//    Example 1:
//
//    Input: arr = [10,2,5,3]
//    Output: true
//    Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
//    Example 2:
//
//    Input: arr = [3,1,7,11]
//    Output: false
//    Explanation: There is no i and j that satisfy the conditions.
//
//
//    Constraints:
//
//    2 <= arr.length <= 500
//    -103 <= arr[i] <= 103