package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SlidingWindowMaximum {
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1) {
                return new int[0];
            }

            int n = nums.length;
            int[] results = new int[n - k + 1];
            int index = 0;

            Deque<Integer> deque = new ArrayDeque<>();
            for(int i = 0; i < n; i++) {
                while(deque.size() > 0 && deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }

                while(deque.size() > 0 && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }

                deque.offerLast(i);
                if(i >= k - 1) {
                    results[index++] = nums[deque.peekFirst()];
                }
            }

            return results;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1) {
                return new int[0];
            }

            int n = nums.length;
            int[] results = new int[n - k + 1];
            int index = 0;

            TreeMap<Integer, Integer> map = new TreeMap<>();

            int left = 0;
            for(int right = 0; right < n; right++) {
                int a = nums[right];
                map.put(a, map.getOrDefault(a, 0) + 1);

                if(right - left + 1 == k) {
                    results[index++] = map.lastKey();

                    int b = nums[left];
                    if(map.get(b) == 1) {
                        map.remove(b);
                    } else {
                        map.put(b, map.get(b) - 1);
                    }

                    left++;
                }
            }

            return results;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_PQ_TLE {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1) {
                return new int[0];
            }

            int n = nums.length;
            int[] results = new int[n - k + 1];
            int index = 0;

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

            int left = 0;
            for(int right = 0; right < n; right++) {
                int a = nums[right];
                maxHeap.offer(a);

                if(right - left + 1 == k) {
                    results[index++] = maxHeap.peek();

                    int b = nums[left];
                    maxHeap.remove(b);

                    left++;
                }
            }

            return results;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || k > nums.length) {
                return new int[0];
            }

            int n = nums.length;

            int windowStart = 0;
            int windowEnd = 0;

            int currentMax = Integer.MIN_VALUE;
            int currentMaxIndex = -1;

            int currentSecondMax = Integer.MIN_VALUE;
            int currentSecondMaxIndex = -1;

            while(windowEnd < k) {
                if(nums[windowEnd] > currentMax) {
                    currentSecondMax = currentMax;
                    currentSecondMaxIndex = currentMaxIndex;

                    currentMax = nums[windowEnd];
                    currentMaxIndex = windowEnd;
                } else if(nums[windowEnd] > currentSecondMax) {
                    currentSecondMax = nums[windowEnd];
                    currentSecondMaxIndex = windowEnd;
                }
            }

            int[] maxWindow = new int[n - k + 1];
            while(windowEnd < n) {
                maxWindow[windowStart] = currentMax;

                windowStart++;
                windowEnd++;

            }

            return maxWindow;
        }
    }

    class Solution_Brute {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || k > nums.length) {
                return new int[0];
            }

            int n = nums.length;

            int windowStart = 0;
            int windowEnd = k - 1;
            int[] maxWindow = new int[n - k + 1];
            while(windowEnd < n) {
                maxWindow[windowStart] = getMax(nums, windowStart, windowEnd);

                windowStart++;
                windowEnd++;
            }

            return maxWindow;
        }

        private int getMax(int[] nums, int start, int end) {
            int max = Integer.MIN_VALUE;
            for(int i = start; i <= end; i++) {
                max = Math.max(max, nums[i]);
            }

            return max;
        }
    }

// [1,3,-1,-3,5,3,6,7]
// 3

// [1,-1]
// 1
}

//    239. Sliding Window Maximum
//    Hard
//    You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//    Return the max sliding window.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//    Output: [3,3,5,5,6,7]
//    Explanation:
//    Window position                Max
//    ---------------               -----
//    [1  3  -1] -3  5  3  6  7       3
//    1 [3  -1  -3] 5  3  6  7       3
//    1  3 [-1  -3  5] 3  6  7       5
//    1  3  -1 [-3  5  3] 6  7       5
//    1  3  -1  -3 [5  3  6] 7       6
//    1  3  -1  -3  5 [3  6  7]      7
//    Example 2:
//
//    Input: nums = [1], k = 1
//    Output: [1]
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
//    1 <= k <= nums.length