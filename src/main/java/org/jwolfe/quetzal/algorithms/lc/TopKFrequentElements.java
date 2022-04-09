package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class TopKFrequentElements {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int val : nums) {
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
            }

            int n = frequencies.size();
            int[] keys = new int[n];
            int i = 0;
            for(int val : frequencies.keySet()) {
                keys[i++] = val;
            }

            quickSelect(keys, n, 0, n - 1, k, frequencies);

            int[] results = new int[k];
            for(i = 0; i < k; i++) {
                results[i] = keys[n - i - 1];
            }

            return results;
        }

        private void quickSelect(int[] arr, int n, int left, int right, int k, Map<Integer, Integer> frequencies) {
            if(left == right) {
                return;
            }

            Random random = new Random();
            int pivotIndex = left + random.nextInt(right - left);
            pivotIndex = partition(arr, left, right, pivotIndex, frequencies);

            if(pivotIndex == n - k) {
                return;
            } else if(pivotIndex < n - k) {
                quickSelect(arr, n, pivotIndex + 1, right, k, frequencies);
            } else {
                quickSelect(arr, n, left, pivotIndex - 1, k, frequencies);
            }
        }

        private int partition(int[] arr, int left, int right, int pivotIndex, Map<Integer, Integer> frequencies) {
            int pivot = arr[pivotIndex];
            int pivotFrequency = frequencies.get(pivot);

            swap(arr, pivotIndex, right);
            int index = left;

            for(int i = left; i <= right; i++) {
                int val = arr[i];
                int valFrequency = frequencies.get(val);
                if(valFrequency < pivotFrequency) {
                    swap(arr, index, i);
                    index++;
                }
            }

            swap(arr, index, right);
            return index;
        }

        private void swap(int[] arr, int left, int right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

    class Solution_Heap {
        public int[] topKFrequent(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int val : nums) {
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>( (a, b) -> frequencies.get(a) - frequencies.get(b) );
            for(int val : frequencies.keySet()) {
                heap.offer(val);

                if(heap.size() > k) {
                    heap.poll();
                }
            }

            int[] results = new int[k];
            for(int i = 0; i < k; i++) {
                results[i] = heap.poll();
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public int[] topKFrequent(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || nums.length < k) {
                return new int[0];
            }

            Map<Integer, Integer> frequency = new HashMap<>();
            for(int n : nums) {
                frequency.put(n, frequency.getOrDefault(n, 0) + 1);
            }

            // PriorityQueue<Element> heap = new PriorityQueue<>((e1, e2) -> e2.frequency - e1.frequency);
            PriorityQueue<Element> heap = new PriorityQueue<>((e1, e2) -> e1.frequency - e2.frequency);
            for(Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
                heap.offer(new Element(entry.getKey(), entry.getValue()));

                if(heap.size() > k) {
                    heap.poll();
                }
            }

            int[] results = new int[k];
            for(int i = 0; i < k; i++) {
                results[i] = heap.poll().number;
            }

            return results;
        }

        class Element {
            int number;
            int frequency;

            public Element(int number, int frequency) {
                this.number = number;
                this.frequency = frequency;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof Element) {
                    return this.number == ((Element) o).number;
                }

                return false;
            }

            public int hashCode() {
                int hash = 7;
                hash *= 31;
                hash *= number;

                return hash;
            }
        }
    }

// [1,1,1,2,2,3]
// 2

// [3,0,1,0]
// 1
}

//    347. Top K Frequent Elements
//    Medium
//    Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,1,2,2,3], k = 2
//    Output: [1,2]
//    Example 2:
//
//    Input: nums = [1], k = 1
//    Output: [1]
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    k is in the range [1, the number of unique elements in the array].
//    It is guaranteed that the answer is unique.
//
//
//    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
