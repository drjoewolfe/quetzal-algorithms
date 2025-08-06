package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class FruitsIntoBasketsIII {
    class Solution {
        int[] segmentTree = new int[400000];

        public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
            if (fruits == null || fruits.length == 0) {
                return 0;
            }

            if (baskets == null || baskets.length == 0) {
                return fruits.length;
            }

            int n = fruits.length;
            int m = baskets.length;

            Arrays.fill(segmentTree, Integer.MIN_VALUE);
            buildSegmentTree(1, baskets, 0, m - 1);

            int count = 0;
            for (int i = 0; i < n; i++) {
                int left = 0;
                int right = m - 1;
                int basketIndex = -1;

                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    int maxValue = querySegmentTree(1, baskets, 0, m - 1, 0, mid);
                    if (maxValue >= fruits[i]) {
                        basketIndex = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                if (basketIndex != -1 && baskets[basketIndex] >= fruits[i]) {
                    updateSegmentTree(1, 0, m - 1, basketIndex, Integer.MIN_VALUE);
                } else {
                    count++;
                }
            }

            return count;
        }

        private void buildSegmentTree(int treeIndex, int[] baskets, int left, int right) {
            if (left == right) {
                segmentTree[treeIndex] = baskets[left];
                return;
            }

            int treeLeftChildIndex = treeIndex * 2;
            int treeRightChildIndex = treeIndex * 2 + 1;

            int mid = left + (right - left) / 2;

            buildSegmentTree(treeLeftChildIndex, baskets, left, mid);
            buildSegmentTree(treeRightChildIndex, baskets, mid + 1, right);

            segmentTree[treeIndex] = Math.max(segmentTree[treeLeftChildIndex], segmentTree[treeRightChildIndex]);
        }

        private int querySegmentTree(int treeIndex, int[] baskets, int left, int right, int queryLeft, int queryRight) {
            if (queryLeft > right || queryRight < left) {
                return Integer.MIN_VALUE;
            }

            if (queryLeft <= left && right <= queryRight) {
                return segmentTree[treeIndex];
            }

            int mid = left + (right - left) / 2;

            int treeLeftChildIndex = treeIndex * 2;
            int treeRightChildIndex = treeIndex * 2 + 1;

            return Math.max(
                    querySegmentTree(treeLeftChildIndex, baskets, left, mid, queryLeft, queryRight),
                    querySegmentTree(treeRightChildIndex, baskets, mid + 1, right, queryLeft, queryRight)
            );
        }

        private void updateSegmentTree(int treeIndex, int left, int right, int position, int value) {
            if (left == right) {
                segmentTree[treeIndex] = value;
                return;
            }

            int mid = left + (right - left) / 2;

            int treeLeftChildIndex = treeIndex * 2;
            int treeRightChildIndex = treeIndex * 2 + 1;

            if (position <= mid) {
                updateSegmentTree(treeLeftChildIndex, left, mid, position, value);
            } else {
                updateSegmentTree(treeRightChildIndex, mid + 1, right, position, value);
            }

            segmentTree[treeIndex] = Math.max(segmentTree[treeLeftChildIndex], segmentTree[treeRightChildIndex]);
        }
    }
}

//    3479. Fruits Into Baskets III
//    Medium
//    You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
//
//    From left to right, place the fruits according to these rules:
//
//    Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
//    Each basket can hold only one type of fruit.
//    If a fruit type cannot be placed in any basket, it remains unplaced.
//    Return the number of fruit types that remain unplaced after all possible allocations are made.
//
//
//
//    Example 1:
//
//    Input: fruits = [4,2,5], baskets = [3,5,4]
//
//    Output: 1
//
//    Explanation:
//
//    fruits[0] = 4 is placed in baskets[1] = 5.
//    fruits[1] = 2 is placed in baskets[0] = 3.
//    fruits[2] = 5 cannot be placed in baskets[2] = 4.
//    Since one fruit type remains unplaced, we return 1.
//
//    Example 2:
//
//    Input: fruits = [3,6,1], baskets = [6,4,7]
//
//    Output: 0
//
//    Explanation:
//
//    fruits[0] = 3 is placed in baskets[0] = 6.
//    fruits[1] = 6 cannot be placed in baskets[1] = 4 (insufficient capacity) but can be placed in the next available basket, baskets[2] = 7.
//    fruits[2] = 1 is placed in baskets[1] = 4.
//    Since all fruits are successfully placed, we return 0.
//
//
//
//    Constraints:
//
//    n == fruits.length == baskets.length
//    1 <= n <= 105
//    1 <= fruits[i], baskets[i] <= 109