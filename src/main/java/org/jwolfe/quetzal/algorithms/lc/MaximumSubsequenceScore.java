package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {
    class Solution {
        public long maxScore(int[] nums1, int[] nums2, int k) {
            if(nums1 == null || nums1.length == 0 || nums1.length != nums2.length || k < 1 || k > nums1.length) {
                return 0;
            }

            int n = nums1.length;

            List<int[]> list = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                list.add(new int[] {nums1[i], nums2[i]});
            }

            Collections.sort(list, (a, b) -> a[1] - b[1]);
            PriorityQueue<Integer> heap = new PriorityQueue<>();

            long maxScore = Integer.MIN_VALUE;
            long sum = 0;
            for(int i = n - 1; i >= 0; i--) {
                int[] pair = list.get(i);
                int val1 = pair[0];
                int val2 = pair[1];

                sum += val1;
                heap.offer(val1);

                if(heap.size() == k) {
                    long score = sum * val2;
                    maxScore = Math.max(maxScore, score);

                    sum -= heap.poll();
                }
            }

            return maxScore;
        }
    }

    class Solution_TLE {
        public long maxScore(int[] nums1, int[] nums2, int k) {
            if(nums1 == null || nums1.length == 0 || nums1.length != nums2.length || k < 1 || k > nums1.length) {
                return 0;
            }

            List<List<Integer>> subsequenceIndices = new ArrayList<>();
            int n = nums1.length;
            generateSubsequences(n, 0, k, new ArrayList<>(), subsequenceIndices);

            int maxScore = Integer.MIN_VALUE;
            for(var subsequence : subsequenceIndices) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                for(int i : subsequence) {
                    sum += nums1[i];
                    min = Math.min(min, nums2[i]);
                }

                int score = sum * min;
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        }

        private void generateSubsequences(int n, int index, int k, List<Integer> current, List<List<Integer>> subsequenceIndices) {
            if(current.size() == k) {
                subsequenceIndices.add(new ArrayList<>(current));
                return;
            }

            if(index == n) {
                return;
            }

            // include current
            current.add(index);
            generateSubsequences(n, index + 1, k, current, subsequenceIndices);
            current.remove(current.size() - 1);

            // exclude current
            generateSubsequences(n, index + 1, k, current, subsequenceIndices);
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [1,3,3,2]
// [2,1,3,4]
// 3

// [93,463,179,2488,619,2006,1561,137,53,1765,2304,1459,1768,450,1938,2054,466,331,670,1830,1550,1534,2164,1280,2277,2312,1509,867,2223,1482,2379,1032,359,1746,966,232,67,1203,2474,944,1740,1775,1799,1156,1982,1416,511,1167,1334,2344]
// [345,229,976,2086,567,726,1640,2451,1829,77,1631,306,2032,2497,551,2005,2009,1855,1685,729,2498,2204,588,474,693,30,2051,1126,1293,1378,1693,1995,2188,1284,1414,1618,2005,1005,1890,30,895,155,526,682,2454,278,999,1417,1682,995]
// 42
}

//    2542. Maximum Subsequence Score
//    Medium
//    You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.
//
//    For chosen indices i0, i1, ..., ik - 1, your score is defined as:
//
//    The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
//    It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
//    Return the maximum possible score.
//
//    A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
//    Output: 12
//    Explanation:
//    The four possible subsequence scores are:
//    - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
//    - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
//    - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
//    - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
//    Therefore, we return the max score, which is 12.
//    Example 2:
//
//    Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
//    Output: 30
//    Explanation:
//    Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
//
//
//    Constraints:
//
//    n == nums1.length == nums2.length
//    1 <= n <= 105
//    0 <= nums1[i], nums2[j] <= 105
//    1 <= k <= n