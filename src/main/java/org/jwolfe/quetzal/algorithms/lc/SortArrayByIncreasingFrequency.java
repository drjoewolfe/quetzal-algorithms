package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SortArrayByIncreasingFrequency {
    class Solution {
        public int[] frequencySort(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            int n = nums.length;

            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int val : nums) {
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
            }

            Integer[] numbers = new Integer[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = nums[i];
            }

            Arrays.sort(numbers, (a, b) -> {
                int af = frequencies.get(a);
                int bf = frequencies.get(b);

                if (af == bf) {
                    return Integer.compare(b, a);
                }

                return af - bf;
            });

            for (int i = 0; i < n; i++) {
                nums[i] = numbers[i];
            }

            return nums;
        }
    }

    class Solution_Correct_1 {
        public int[] frequencySort(int[] nums) {
            if (nums == null || nums.length < 2) {
                return nums;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int a : nums) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
            for (var entry : frequencies.entrySet()) {
                int num = entry.getKey();
                int count = entry.getValue();

                if (map.containsKey(count)) {
                    var queue = map.get(count);
                    queue.offer(num);
                } else {
                    PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
                    queue.offer(num);

                    map.put(count, queue);
                }
            }

            int index = 0;
            for (var entry : map.entrySet()) {
                int count = entry.getKey();
                var queue = entry.getValue();

                while (!queue.isEmpty()) {
                    int num = queue.poll();

                    for (int i = 0; i < count; i++) {
                        nums[index++] = num;
                    }
                }
            }

            return nums;
        }
    }
}

//    1636. Sort Array by Increasing Frequency
//    Easy
//    Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
//
//    Return the sorted array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,2,2,2,3]
//    Output: [3,1,1,2,2,2]
//    Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
//    Example 2:
//
//    Input: nums = [2,3,1,3,2]
//    Output: [1,3,3,2,2]
//    Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
//    Example 3:
//
//    Input: nums = [-1,1,-6,4,5,-6,1,4,1]
//    Output: [5,-1,4,4,-6,-6,1,1,1]
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    -100 <= nums[i] <= 100