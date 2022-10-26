package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinimumSumOfSquaredDifference {
    class Solution {
        public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
            if(nums1 == null || nums2 == null || nums1.length != nums2.length || k1 < 0 || k2 < 0) {
                return 0;
            }

            int n = nums1.length;
            int sum = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int a = nums1[i];
                int b = nums2[i];

                int diff = Math.abs(a - b);
                if(diff != 0) {
                    map.put(diff, map.getOrDefault(diff, 0) + 1);
                }
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            for(var key : map.keySet()) {
                maxHeap.offer(key);
            }

            int k = k1 + k2;
            while(k > 0 && !maxHeap.isEmpty()) {
                int diff = maxHeap.poll();
                int count = map.get(diff);

                if(count <= k) {
                    map.remove(diff);

                    int newDiff = diff - 1;
                    if(newDiff != 0) {
                        if(!map.containsKey(newDiff)) {
                            maxHeap.offer(newDiff);
                        }

                        map.put(newDiff, map.getOrDefault(newDiff, 0) + count);
                    }

                    k -= count;
                } else {
                    map.put(diff, count - k);
                    maxHeap.offer(diff);

                    int newDiff = diff - 1;
                    if(newDiff != 0) {
                        if(!map.containsKey(newDiff)) {
                            maxHeap.offer(newDiff);
                        }

                        map.put(newDiff, map.getOrDefault(newDiff, 0) + k);
                    }

                    k = 0;
                }
            }

            long sumOfSquares = 0;
            while(!maxHeap.isEmpty()) {
                int a = maxHeap.poll();
                int count = map.get(a);
                sumOfSquares += (1l * a * a * count);
            }

            return sumOfSquares;
        }
    }

    class Solution_TLE {
        public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
            if(nums1 == null || nums2 == null || nums1.length != nums2.length || k1 < 0 || k2 < 0) {
                return 0;
            }

            int n = nums1.length;
            int sum = 0;

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            for(int i = 0; i < n; i++) {
                int a = nums1[i];
                int b = nums2[i];

                int diff = Math.abs(a - b);
                maxHeap.offer(diff);
            }

            int k = k1 + k2;
            while(k > 0) {
                int a = maxHeap.poll();
                if(a <= 0) {
                    break;
                }

                maxHeap.offer(a - 1);
                k--;
            }

            long sumOfSquares = 0;
            while(!maxHeap.isEmpty()) {
                int a = maxHeap.poll();
                sumOfSquares += (1l * a * a);
            }

            return sumOfSquares;
        }
    }

// [1,2,3,4]
// [2,10,20,19]
// 0
// 0

// [10,10,10,11,5]
// [1,0,6,6,1]
// 11
// 27

// [1,4,10,12]
// [5,8,6,9]
// 1
// 1
}

//    2333. Minimum Sum of Squared Difference
//    Medium
//    You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
//
//    The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.
//
//    You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
//
//    Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.
//
//    Note: You are allowed to modify the array elements to become negative integers.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
//    Output: 579
//    Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0.
//    The sum of square difference will be: (1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579.
//    Example 2:
//
//    Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
//    Output: 43
//    Explanation: One way to obtain the minimum sum of square difference is:
//    - Increase nums1[0] once.
//    - Increase nums2[2] once.
//    The minimum of the sum of square difference will be:
//    (2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
//    Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.
//
//
//    Constraints:
//
//    n == nums1.length == nums2.length
//    1 <= n <= 105
//    0 <= nums1[i], nums2[i] <= 105
//    0 <= k1, k2 <= 109