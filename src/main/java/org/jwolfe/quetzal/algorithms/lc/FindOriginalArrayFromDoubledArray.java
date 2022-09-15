package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindOriginalArrayFromDoubledArray {
    class Solution {
        public int[] findOriginalArray(int[] changed) {
            if(changed == null || changed.length == 0 || changed.length % 2 != 0) {
                return new int[0];
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int a : changed) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            Arrays.sort(changed);
            int n = changed.length;

            int[] original = new int[n / 2];
            int index = 0;

            for(int i = 0; i < n; i++) {
                int a = changed[i];
                if(map.get(a) == 0) {
                    continue;
                }

                original[index++] = a;
                map.put(a, map.get(a) - 1);

                int d = 2 * a;
                if(!map.containsKey(d) || map.get(d) == 0) {
                    return new int[0];
                }

                map.put(d, map.get(d) - 1);
            }

            return original;
        }
    }

// [1,3,4,2,6,8]

// [0,0,0,0]

// [1,4,2,1]

// [0,1,0,0]
}

//    2007. Find Original Array From Doubled Array
//    Medium
//    An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
//
//    Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
//
//
//
//    Example 1:
//
//    Input: changed = [1,3,4,2,6,8]
//    Output: [1,3,4]
//    Explanation: One possible original array could be [1,3,4]:
//    - Twice the value of 1 is 1 * 2 = 2.
//    - Twice the value of 3 is 3 * 2 = 6.
//    - Twice the value of 4 is 4 * 2 = 8.
//    Other original arrays could be [4,3,1] or [3,1,4].
//    Example 2:
//
//    Input: changed = [6,3,0,1]
//    Output: []
//    Explanation: changed is not a doubled array.
//    Example 3:
//
//    Input: changed = [1]
//    Output: []
//    Explanation: changed is not a doubled array.
//
//
//    Constraints:
//
//    1 <= changed.length <= 105
//    0 <= changed[i] <= 105