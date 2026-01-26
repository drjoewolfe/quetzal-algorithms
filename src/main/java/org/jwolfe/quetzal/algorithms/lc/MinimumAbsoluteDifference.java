package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference {
    class Solution {
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            List<List<Integer>> results = new ArrayList<>();
            if (arr == null || arr.length < 2) {
                return results;
            }

            int n = arr.length;
            Arrays.sort(arr);
            int minDiff = arr[n - 1] - arr[0];
            for (int i = 0; i < n - 1; i++) {
                int a = arr[i];
                int b = arr[i + 1];

                int diff = b - a;
                minDiff = Math.min(minDiff, diff);
            }

            for (int i = 0; i < n - 1; i++) {
                int a = arr[i];
                int b = arr[i + 1];

                int diff = b - a;
                if (diff == minDiff) {
                    List<Integer> pair = List.of(a, b);
                    results.add(pair);
                }
            }

            return results;
        }
    }

    class Solution_Correct_2 {
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            List<List<Integer>> results = new ArrayList<>();
            if (arr == null || arr.length < 2) {
                return results;
            }

            Arrays.sort(arr);
            int minAbsDiff = arr[1] - arr[0];
            for (int i = 2; i < arr.length; i++) {
                int a = arr[i - 1];
                int b = arr[i];

                int absDiff = Math.abs(a - b);
                minAbsDiff = Math.min(minAbsDiff, absDiff);
            }

            for (int i = 1; i < arr.length; i++) {
                int a = arr[i - 1];
                int b = arr[i];

                int absDiff = Math.abs(a - b);
                if (absDiff == minAbsDiff) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(a);
                    pair.add(b);

                    results.add(pair);
                }
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            List<List<Integer>> results = new ArrayList<>();
            if (arr == null || arr.length < 2) {
                return results;
            }

            Arrays.sort(arr);
            int minDifference = arr[1] - arr[0];
            for (int i = 1; i < arr.length - 1; i++) {
                int difference = arr[i + 1] - arr[i];
                minDifference = Math.min(minDifference, difference);
            }


            for (int i = 0; i < arr.length - 1; i++) {
                int difference = arr[i + 1] - arr[i];
                if (difference == minDifference) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(arr[i]);
                    pair.add(arr[i + 1]);

                    results.add(pair);
                }
            }

            return results;
        }
    }
}

//    1200. Minimum Absolute Difference
//    Easy
//    Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
//
//    Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
//
//    a, b are from arr
//    a < b
//    b - a equals to the minimum absolute difference of any two elements in arr
//
//
//    Example 1:
//
//    Input: arr = [4,2,1,3]
//    Output: [[1,2],[2,3],[3,4]]
//    Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
//    Example 2:
//
//    Input: arr = [1,3,6,10,15]
//    Output: [[1,3]]
//    Example 3:
//
//    Input: arr = [3,8,-10,23,19,-4,-14,27]
//    Output: [[-14,-10],[19,23],[23,27]]
//
//
//    Constraints:
//
//    2 <= arr.length <= 10^5
//    -10^6 <= arr[i] <= 10^6