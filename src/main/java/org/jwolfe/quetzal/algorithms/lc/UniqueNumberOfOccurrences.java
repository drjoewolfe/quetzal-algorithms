package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueNumberOfOccurrences {
    class Solution {
        public boolean uniqueOccurrences(int[] arr) {
            if(arr == null || arr.length < 2) {
                return true;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int a : arr) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            Set<Integer> set = new HashSet<>();
            for(var entry : map.entrySet()) {
                int count = entry.getValue();
                if(set.contains(count)) {
                    return false;
                }

                set.add(count);
            }

            return true;
        }
    }

    class Solution_Corrrect_1 {
        public boolean uniqueOccurrences(int[] arr) {
            if(arr == null || arr.length < 2) {
                return true;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : arr) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            Set<Integer> set = new HashSet<>();
            for(var entry : frequencies.entrySet()) {
                int frequency = entry.getValue();
                if(set.contains(frequency)) {
                    return false;
                }

                set.add(frequency);
            }

            return true;
        }
    }
}

//    1207. Unique Number of Occurrences
//    Easy
//    Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: arr = [1,2,2,1,1,3]
//    Output: true
//    Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
//    Example 2:
//
//    Input: arr = [1,2]
//    Output: false
//    Example 3:
//
//    Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
//    Output: true
//
//
//    Constraints:
//
//    1 <= arr.length <= 1000
//    -1000 <= arr[i] <= 1000