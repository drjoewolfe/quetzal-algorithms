package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountOfSmallerNumbersAfterSelf {
    class Solution {
        public List<Integer> countSmaller(int[] nums) {
            if(nums == null || nums.length == 0) {
                return null;
            }

            int n = nums.length;
            // int max = nums[0];
            // for(int i = 1; i < n; i++) {
            //     max = Math.max(max, nums[i]);
            // }

            int max = 10000 * 2 + 1;

            int[] counters = new int[max + 1];
            SegmentTree tree = new SegmentTree(counters);

            int[] results = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                int val = nums[i] + 10001;
                int count = tree.getSum(0, val - 1);

                results[i] = count;
                tree.increment(val);
            }

            return Arrays.stream(results)
                    .boxed()
                    .collect(Collectors.toList());
        }
    }

    public static class SegmentTree {
        int[] arr;
        int[] tree;
        int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            this.arr = arr;

            int h = (int) Math.ceil(Math.log(n) / Math.log(2));
            int size = (int) Math.pow(2, h + 1) - 1;

            this.tree = new int[size];
            construct(0, n - 1, 0);
        }

        public int getSum(int queryStart, int queryEnd) {
            return getSum(0, n - 1, queryStart, queryEnd, 0);
        }

        public void update(int index, int value) {
            int diff = value - arr[index];
            arr[index] = value;

            update(0, n - 1, index, diff, 0);
        }

        public void increment(int index) {
            update(index, arr[index] + 1);
        }

        private int construct(int low, int high, int treeIndex) {
            if(low == high) {
                tree[treeIndex] = arr[low];
            } else {
                int mid = getMid(low, high);
                tree[treeIndex] = construct(low, mid, 2 * treeIndex + 1)
                        + construct(mid + 1, high, 2 * treeIndex + 2);
            }

            return tree[treeIndex];
        }

        private void update(int low, int high, int index, int diff, int treeIndex) {
            if(low != high) {
                int mid = getMid(low, high);
                if(index <= mid) {
                    update(low, mid, index, diff, 2 * treeIndex + 1);
                } else {
                    update(mid + 1, high, index, diff, 2 * treeIndex + 2);
                }
            }

            tree[treeIndex] += diff;
        }

        private int getSum(int low, int high, int queryStart, int queryEnd, int treeIndex) {
            if(low >= queryStart && high <= queryEnd) {
                // Interval within query limits
                return tree[treeIndex];
            }

            if(queryEnd < low || queryStart > high) {
                // No overlap with query
                return 0;
            }

            int mid = getMid(low, high);
            return getSum(low, mid, queryStart, queryEnd, 2 * treeIndex + 1)
                    + getSum(mid + 1, high, queryStart, queryEnd, 2 * treeIndex + 2);
        }

        private int getMid(int low, int high) {
            return low + (high - low) / 2;
        }

        private void print() {
            print(arr);
            print(tree);

            System.out.println();
        }

        private void print(int[] arr) {
            int n = arr.length;
            for(int i = 0; i < n; i++) {
                System.out.print(i + "\t");
            }

            System.out.println();
            for(int a : arr) {
                System.out.print(a + "\t");
            }

            System.out.println();
        }
    }

    class Solution_Brute {
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> counts = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return counts;
            }

            for(int i = 0; i < nums.length; i++) {
                int smallerCount = 0;
                for(int j = i + 1; j < nums.length; j++) {
                    if(nums[i] > nums[j]) {
                        smallerCount++;
                    }
                }

                counts.add(smallerCount);
            }

            return counts;
        }
    }

// [5,2,6,1]
// [-1]
}

//    315. Count of Smaller Numbers After Self
//    Hard
//    You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
//
//
//    Example 1:
//
//    Input: nums = [5,2,6,1]
//    Output: [2,1,1,0]
//    Explanation:
//    To the right of 5 there are 2 smaller elements (2 and 1).
//    To the right of 2 there is only 1 smaller element (1).
//    To the right of 6 there is 1 smaller element (1).
//    To the right of 1 there is 0 smaller element.
//    Example 2:
//
//    Input: nums = [-1]
//    Output: [0]
//    Example 3:
//
//    Input: nums = [-1,-1]
//    Output: [0,0]
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
