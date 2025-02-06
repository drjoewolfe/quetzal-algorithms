package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TupleWithSameProduct {
    class Solution {
        public int tupleSameProduct(int[] nums) {
            if (nums == null || nums.length < 4) {
                return 0;
            }

            Arrays.sort(nums);
            int n = nums.length;

            int tuples = 0;
            for (int i = 0; i < n; i++) {
                int a = nums[i];
                for (int j = n - 1; j > i; j--) {
                    int b = nums[j];

                    int p = a * b;
                    Set<Integer> seen = new HashSet<>();

                    for (int k = i + 1; k < j; k++) {
                        int c = nums[k];
                        if (p % c != 0) {
                            continue;
                        }

                        int d = p / c;
                        if (seen.contains(d)) {
                            tuples += 8;
                        }

                        seen.add(c);
                    }
                }
            }

            return tuples;
        }
    }

    class Solution_TLE {
        public int tupleSameProduct(int[] nums) {
            if (nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;

            int tuples = 0;
            for (int i = 0; i < n; i++) {
                int a = nums[i];

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    int b = nums[j];

                    for (int k = 0; k < n; k++) {
                        if (i == k || j == k) {
                            continue;
                        }

                        int c = nums[k];

                        for (int l = 0; l < n; l++) {
                            if (i == l || j == l || k == l) {
                                continue;
                            }

                            int d = nums[l];
                            if (a * b == c * d) {
                                tuples++;
                            }
                        }
                    }
                }
            }

            return tuples;
        }
    }
}

//    1726. Tuple with Same Product
//    Medium
//    Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,4,6]
//    Output: 8
//    Explanation: There are 8 valid tuples:
//    (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
//    (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
//    Example 2:
//
//    Input: nums = [1,2,4,5,10]
//    Output: 16
//    Explanation: There are 16 valid tuples:
//    (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
//    (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
//    (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
//    (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 104
//    All elements in nums are distinct.