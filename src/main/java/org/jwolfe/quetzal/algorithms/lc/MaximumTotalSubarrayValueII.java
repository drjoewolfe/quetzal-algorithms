package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MaximumTotalSubarrayValueII {
    class Solution {
        public long maxTotalValue(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return 0L;
            }

            int n = nums.length;

            SegmentTree minTree = new SegmentTree(nums, true);
            SegmentTree maxTree = new SegmentTree(nums, false);

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for (int l = 0; l < n; l++) {
                int min = minTree.query(l, n - 1);
                int max = maxTree.query(l, n - 1);
                int val = max - min;

                int[] element = new int[]{val, l, n - 1};
                heap.offer(element);
            }

            long result = 0;
            while (k > 0 && !heap.isEmpty()) {
                int[] element = heap.poll();
                result += element[0];

                int left = element[1];
                int right = element[2];

                if (left < right) {
                    int newRight = right - 1;
                    int min = minTree.query(left, newRight);
                    int max = maxTree.query(left, newRight);
                    int val = max - min;

                    heap.offer(new int[]{val, left, newRight});
                }

                k--;
            }

            return result;
        }

        private class SegmentTree {
            int n;
            int[] tree;
            boolean isMinTree;


            public SegmentTree(int[] arr, boolean minTree) {
                n = arr.length;
                isMinTree = minTree;

                tree = new int[4 * n];
                buildTree(arr);
            }

            public int query(int left, int right) {
                return query(left, right, 0, 0, n - 1);
            }

            private int query(int left, int right, int stIndex, int segStart, int segEnd) {
                // No Overlap
                if (left > segEnd || right < segStart) {
                    return isMinTree ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // Complete Overlap
                if (left <= segStart && segEnd <= right) {
                    return tree[stIndex];
                }

                int stLeftChildIndex = stIndex * 2 + 1;
                int stRightChildIndex = stIndex * 2 + 2;

                int segMid = segStart + (segEnd - segStart) / 2;
                int a = query(left, right, stLeftChildIndex, segStart, segMid);
                int b = query(left, right, stRightChildIndex, segMid + 1, segEnd);

                if (isMinTree) {
                    return Math.min(a, b);
                }

                return Math.max(a, b);
            }

            private void buildTree(int[] arr) {
                buildTree(arr, 0, arr.length - 1, 0);
            }

            private void buildTree(int[] arr, int left, int right, int stIndex) {
                if (left == right) {
                    tree[stIndex] = arr[left];
                    return;
                }

                int stLeftChildIndex = stIndex * 2 + 1;
                int stRightChildIndex = stIndex * 2 + 2;

                int mid = left + (right - left) / 2;
                buildTree(arr, left, mid, stLeftChildIndex);
                buildTree(arr, mid + 1, right, stRightChildIndex);

                if (isMinTree) {
                    tree[stIndex] = Math.min(tree[stLeftChildIndex], tree[stRightChildIndex]);
                } else {
                    tree[stIndex] = Math.max(tree[stLeftChildIndex], tree[stRightChildIndex]);
                }
            }
        }
    }

    class Solution_TLE {
        public long maxTotalValue(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return 0L;
            }

            int n = nums.length;

            PriorityQueue<Integer> heap = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                int min = nums[i];
                int max = nums[i];

                for (int j = i; j < n; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    int value = max - min;
                    heap.offer(value);

                    if (heap.size() > k) {
                        heap.poll();
                    }
                }
            }

            long totalValue = 0;
            while (!heap.isEmpty()) {
                totalValue += heap.poll();
            }

            return totalValue;
        }
    }
}

//    3691. Maximum Total Subarray Value II
//    Hard
//    You are given an integer array nums of length n and an integer k.
//
//    You must select exactly k distinct subarrays nums[l..r] of nums. Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.
//
//    The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
//
//    The total value is the sum of the values of all chosen subarrays.
//
//    Return the maximum possible total value you can achieve.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,2], k = 2
//
//    Output: 4
//
//    Explanation:
//
//    One optimal approach is:
//
//    Choose nums[0..1] = [1, 3]. The maximum is 3 and the minimum is 1, giving a value of 3 - 1 = 2.
//    Choose nums[0..2] = [1, 3, 2]. The maximum is still 3 and the minimum is still 1, so the value is also 3 - 1 = 2.
//    Adding these gives 2 + 2 = 4.
//
//    Example 2:
//
//    Input: nums = [4,2,5,1], k = 3
//
//    Output: 12
//
//    Explanation:
//
//    One optimal approach is:
//
//    Choose nums[0..3] = [4, 2, 5, 1]. The maximum is 5 and the minimum is 1, giving a value of 5 - 1 = 4.
//    Choose nums[1..3] = [2, 5, 1]. The maximum is 5 and the minimum is 1, so the value is also 4.
//    Choose nums[2..3] = [5, 1]. The maximum is 5 and the minimum is 1, so the value is again 4.
//    Adding these gives 4 + 4 + 4 = 12.
//
//
//
//    Constraints:
//
//    1 <= n == nums.length <= 5 * 10​​​​​​​4
//    0 <= nums[i] <= 109
//    1 <= k <= min(105, n * (n + 1) / 2)