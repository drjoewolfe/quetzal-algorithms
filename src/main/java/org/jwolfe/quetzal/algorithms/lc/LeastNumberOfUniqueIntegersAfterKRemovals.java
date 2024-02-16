package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    class Solution {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int val : arr) {
                map.put(val, map.getOrDefault(val, 0) + 1);
            }

            List<Integer> counts = new ArrayList<>(map.values());
            Collections.sort(counts);

            int i = 0;
            int n = counts.size();
            while(k > 0 && i < n) {
                if(counts.get(i) <= k) {
                    k -= counts.get(i);
                    i++;
                } else {
                    k = 0;
                }
            }

            return n - i;
        }
    }

    class Solution_Correct_1 {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            if(arr == null || arr.length < k) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int a : arr) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            PriorityQueue<Pair<Integer>> minHeap = new PriorityQueue<>((a, b) -> a.count - b.count);
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                minHeap.offer(new Pair<>(entry.getKey(), entry.getValue()));
            }

            while(k > 0) {
                Pair<Integer> p = minHeap.poll();
                if(p.count > k) {
                    p.count -= k;
                    k = 0;

                    minHeap.offer(p);
                } else {
                    k -= p.count;
                }
            }

            return minHeap.size();
        }

        class Pair<T> {
            T number;
            T count;

            public Pair(T number, T count) {
                this.number = number;
                this.count = count;
            }
        }
    }

// [5,5,4]
// 1

// [1]
// 0
}

//    1481. Least Number of Unique Integers after K Removals
//    Medium
//    Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
//
//
//
//    Example 1:
//
//    Input: arr = [5,5,4], k = 1
//    Output: 1
//    Explanation: Remove the single 4, only 5 is left.
//    Example 2:
//    Input: arr = [4,3,1,1,3,3,2], k = 3
//    Output: 2
//    Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
//
//
//    Constraints:
//
//    1 <= arr.length <= 10^5
//    1 <= arr[i] <= 10^9
//    0 <= k <= arr.length