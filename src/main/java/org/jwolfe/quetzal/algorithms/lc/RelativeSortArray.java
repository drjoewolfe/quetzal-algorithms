package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RelativeSortArray {
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            if (arr1 == null || arr1.length == 0) {
                return arr1;
            }

            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> heap = new PriorityQueue<>();

            for (int val : arr2) {
                map.put(val, 0);
            }

            for (int val : arr1) {
                if (map.containsKey(val)) {
                    map.put(val, map.get(val) + 1);
                } else {
                    heap.offer(val);
                }
            }

            int index = 0;
            int[] sortedArray = new int[arr1.length];

            for (int val : arr2) {
                int count = map.get(val);
                while (count > 0) {
                    sortedArray[index++] = val;
                    count--;
                }
            }

            while (!heap.isEmpty()) {
                sortedArray[index++] = heap.poll();
            }

            return sortedArray;
        }
    }

    class Solution_Correct_1 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            if (arr1 == null || arr1.length == 0) {
                return arr1;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i = 0; i < arr2.length; i++) {
                frequencies.put(arr2[i], 0);
            }

            for (int i = 0; i < arr1.length; i++) {
                int a = arr1[i];
                if (frequencies.containsKey(a)) {
                    frequencies.put(a, frequencies.get(a) + 1);
                } else {
                    minHeap.offer(a);
                }
            }

            int[] output = new int[arr1.length];
            int index = 0;
            for (int b : arr2) {
                for (int i = 0; i < frequencies.get(b); i++) {
                    output[index++] = b;
                }
            }

            while (!minHeap.isEmpty()) {
                output[index++] = minHeap.poll();
            }

            return output;
        }
    }
}

//    1122. Relative Sort Array
//    Easy
//    Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
//
//    Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
//
//
//
//    Example 1:
//
//    Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//    Output: [2,2,2,1,4,3,3,9,6,7,19]
//    Example 2:
//
//    Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
//    Output: [22,28,8,6,17,44]
//
//
//    Constraints:
//
//    1 <= arr1.length, arr2.length <= 1000
//    0 <= arr1[i], arr2[i] <= 1000
//    All the elements of arr2 are distinct.
//    Each arr2[i] is in arr1.