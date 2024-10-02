package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RankTransformOfAnArray {
    class Solution {
        public int[] arrayRankTransform(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }

            Set<Integer> set = new HashSet<>();
            for (int val : arr) {
                set.add(val);
            }

            int[] sortedArr = new int[set.size()];
            int index = 0;
            for (int val : set) {
                sortedArr[index++] = val;
            }

            Arrays.sort(sortedArr);

            Map<Integer, Integer> rankMap = new HashMap<>();
            int rank = 1;
            for (int val : sortedArr) {
                rankMap.put(val, rank++);
            }

            for (int i = 0; i < arr.length; i++) {
                arr[i] = rankMap.get(arr[i]);
            }

            return arr;
        }
    }

    class Solution_Correct_1 {
        public int[] arrayRankTransform(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }

            Set<Integer> set = new HashSet<>();
            for (int num : arr) {
                set.add(num);
            }

            int[] sortedArr = new int[set.size()];
            int index = 0;
            for (int num : set) {
                sortedArr[index++] = num;
            }

            Arrays.sort(sortedArr);

            for (int i = 0; i < arr.length; i++) {
                int num = arr[i];
                int rank = binarySearch(sortedArr, num) + 1;
                arr[i] = rank;
            }

            return arr;
        }

        private int binarySearch(int[] arr, int num) {
            int left = 0;
            int right = arr.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (arr[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }
    }

    class Solution_Brute {
        public int[] arrayRankTransform(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }

            int[] tmp = new int[arr.length];
            int[] out = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                tmp[i] = arr[i];
            }

            Arrays.sort(tmp);

            int element = tmp[0];
            int rank = 1;
            setRank(arr, out, element, rank);
            for (int i = 1; i < tmp.length; i++) {
                if (tmp[i] != element) {
                    element = tmp[i];
                    rank++;

                    setRank(arr, out, element, rank);
                }
            }

            return out;
        }

        private void setRank(int[] arr, int[] out, int element, int rank) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == element) {
                    out[i] = rank;
                }
            }
        }
    }
}

//    1331. Rank Transform of an Array
//    Easy
//
//    1995
//
//    99
//
//    Add to List
//
//    Share
//    Given an array of integers arr, replace each element with its rank.
//
//    The rank represents how large the element is. The rank has the following rules:
//
//    Rank is an integer starting from 1.
//    The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
//    Rank should be as small as possible.
//
//
//    Example 1:
//
//    Input: arr = [40,10,20,30]
//    Output: [4,1,2,3]
//    Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
//    Example 2:
//
//    Input: arr = [100,100,100]
//    Output: [1,1,1]
//    Explanation: Same elements share the same rank.
//    Example 3:
//
//    Input: arr = [37,12,28,9,100,56,80,5,12]
//    Output: [5,3,4,2,8,6,7,1,3]
//
//
//    Constraints:
//
//    0 <= arr.length <= 105
//    -109 <= arr[i] <= 109
