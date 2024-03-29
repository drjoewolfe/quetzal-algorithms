package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindKClosestElements {
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> results = new ArrayList<>();
            if(arr == null || arr.length == 0 || k < 1) {
                return results;
            }

            int index = binarySearch(arr, x);

            results.add(arr[index]);
            int left = index - 1;
            int right = index + 1;

            int n = arr.length;
            while(k > 1) {
                int ld = (left >= 0 && left < n) ? Math.abs(arr[left] - x) : Integer.MAX_VALUE;
                int rd = (right >= 0 && right < n) ? Math.abs(arr[right] - x) : Integer.MAX_VALUE;

                if(ld <= rd) {
                    results.add(arr[left--]);
                } else {
                    results.add(arr[right++]);
                }

                k--;
            }

            Collections.sort(results);
            return results;
        }

        private int binarySearch(int[] arr, int x) {
            int left = 0;
            int right = arr.length - 1;
            int n = arr.length;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] == x) {
                    return mid;
                } else if(arr[mid] < x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            int ld = (left >= 0 && left < n) ? Math.abs(arr[left] - x) : Integer.MAX_VALUE;
            int rd = (right >= 0 && right < n) ? Math.abs(arr[right] - x) : Integer.MAX_VALUE;

            if(ld < rd) {
                return left;
            } else {
                return right;
            }
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> results = new ArrayList<>();
            if(arr == null || arr.length == 0 || k < 1) {
                return results;
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
                int ad = Math.abs(a - x);
                int bd = Math.abs(b - x);

                if(ad == bd) {
                    return a - b;
                }

                return ad - bd;
            });

            for(int val : arr) {
                heap.offer(val);
            }

            int n = arr.length;
            for(int i = 0; i < k; i++) {
                results.add(heap.poll());
            }

            Collections.sort(results);
            return results;
        }
    }

    class Solution_Heap {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> results = new ArrayList<>();
            if(arr == null || arr.length == 0 || k == 0) {
                return results;
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
                int da = Math.abs(a - x);
                int db = Math.abs(b - x);

                if(da != db) {
                    return db - da;
                }

                return b - a;
            });

            for(int a : arr) {
                heap.offer(a);

                if(heap.size() > k) {
                    heap.poll();
                }
            }

            while(!heap.isEmpty()) {
                results.add(heap.poll());
            }

            Collections.sort(results);
            return results;
        }
    }

    class Solution_Correct_2 {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            if(arr == null || k <= 0) {
                return null;
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            int n = arr.length;
            for(int i = 0; i < n; i++) {
                int val = arr[i];
                int diff = Math.abs(val - x);

                heap.offer(new int[] {diff, i});
            }

            List<Integer> indexes = new ArrayList<>();
            for(int i = 0; i < k && i < n; i++) {
                indexes.add(heap.poll()[1]);
            }

            Collections.sort(indexes);
            List<Integer> result = new ArrayList<>();
            for(var i : indexes) {
                result.add(arr[i]);
            }

            return result;
        }
    }
}

//    658. Find K Closest Elements
//    Medium
//    Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
//
//    An integer a is closer to x than an integer b if:
//
//    |a - x| < |b - x|, or
//    |a - x| == |b - x| and a < b
//
//
//    Example 1:
//
//    Input: arr = [1,2,3,4,5], k = 4, x = 3
//    Output: [1,2,3,4]
//    Example 2:
//
//    Input: arr = [1,2,3,4,5], k = 4, x = -1
//    Output: [1,2,3,4]
//
//
//    Constraints:
//
//    1 <= k <= arr.length
//    1 <= arr.length <= 104
//    arr is sorted in ascending order.
//    -104 <= arr[i], x <= 104