package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceOfGivenDifference {
    class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int maxLength = 1;
            Map<Integer, Integer> dp = new HashMap<>();
            for(int b : arr) {
                int a = b - difference;
                int currentLength = dp.getOrDefault(a, 0) + 1;
                dp.put(b, currentLength);

                maxLength = Math.max(maxLength, currentLength);
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int longestSubsequence(int[] arr, int difference) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int max = 1;

            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int b = arr[i];
                int a = b - difference;

                if(map.containsKey(a)) {
                    int asl = map.get(a) + 1;
                    if(map.containsKey(b)) {
                        if(map.get(b) < asl) {
                            map.put(b, asl);
                        }

                    } else {
                        map.put(b, asl);
                    }

                    max = Math.max(max, map.get(b));
                } else {
                    map.put(b, 1);
                }
            }

            return max;
        }
    }

    class Solution_LIS_Variant {
        public int longestSubsequence(int[] arr, int difference) {
            if(arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int[] las = new int[n];
            Arrays.fill(las, 1);

            int max = 1;
            for(int i = 1; i < n; i++) {
                int b = arr[i];
                for(int j = 0; j < i; j++) {
                    int a = arr[j];

                    if(b - a == difference
                            && las[j] + 1 > las[i]) {
                        las[i] = las[j] + 1;
                        max = Math.max(max, las[i]);
                    }
                }
            }

            return max;
        }
    }

// [1,2,3,4]
// 1
}

//    1218. Longest Arithmetic Subsequence of Given Difference
//    Medium
//    Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
//
//    A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: arr = [1,2,3,4], difference = 1
//    Output: 4
//    Explanation: The longest arithmetic subsequence is [1,2,3,4].
//    Example 2:
//
//    Input: arr = [1,3,5,7], difference = 1
//    Output: 1
//    Explanation: The longest arithmetic subsequence is any single element.
//    Example 3:
//
//    Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
//    Output: 4
//    Explanation: The longest arithmetic subsequence is [7,5,3,1].
//
//
//    Constraints:
//
//    1 <= arr.length <= 105
//    -104 <= arr[i], difference <= 104