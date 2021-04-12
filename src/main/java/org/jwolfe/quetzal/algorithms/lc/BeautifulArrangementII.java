package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeautifulArrangementII {
    class Solution {
        public int[] constructArray(int n, int k) {
            if(n < 1 || k < 1 || n <= k) {
                return new int[0];
            }

            int[] numbers = new int[n];
            int index = 0;
            for(int i = 1; i < n - k; i++) {
                numbers[index++] = i;
            }

            int left = n - k;
            int right = n;
            for(int i = 0; i <= k; i++) {
                numbers[index++] = (i % 2 == 0) ? left++ : right--;
            }

            return numbers;
        }
    }

    class Solution_TLE {
        public int[] constructArray(int n, int k) {
            if(n < 1 || k < 1) {
                return new int[0];
            }

            int[] numbers = new int[n];
            for(int i = 0; i < n; i++) {
                numbers[i] = i + 1;
            }

            int[] beautifulArray = searchForBeautifulPermutation(numbers, n, k, 0, numbers.clone());

            if(beautifulArray == null) {
                return new int[0];
            }

            return beautifulArray;
        }

        private int[] searchForBeautifulPermutation(int[] numbers, int n, int k, int index, int[] current) {
            if(index == n) {
                if(uniqueDeltaSize(current) == k) {
                    return current;
                }

                return null;
            }

            for(int i = index; i < numbers.length; i++) {
                swap(current, i, index);
                int[] p = searchForBeautifulPermutation(numbers, n, k, index + 1, current);
                if(p != null) {
                    return p;
                }

                swap(current, i, index);
            }

            return null;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private int uniqueDeltaSize(int[] p) {
            Set<Integer> set = new HashSet<>();
            for(int i = 1; i < p.length; i++) {
                set.add(Math.abs(p[i] - p[i - 1]));
            }

            return set.size();
        }
    }


    class Solution_MLE {
        public int[] constructArray(int n, int k) {
            if(n < 1 || k < 1) {
                return new int[0];
            }

            int[] numbers = new int[n];
            for(int i = 0; i < n; i++) {
                numbers[i] = i + 1;
            }

            List<int[]> permutations = generatePermutations(numbers, n);
            for(var p : permutations) {
                if(uniqueDeltaSize(p) == k) {
                    return p;
                }
            }

            return new int[0];
        }

        private List<int[]> generatePermutations(int[] numbers, int n) {
            List<int[]> permutations = new ArrayList<>();
            generatePermutations(numbers, 0, numbers.clone(), permutations);

            return permutations;
        }

        private void generatePermutations(int[] numbers, int index, int[] current, List<int[]> permutations) {
            if(index == numbers.length) {
                permutations.add(current.clone());
                return;
            }

            for(int i = index; i < numbers.length; i++) {
                swap(current, i, index);
                generatePermutations(numbers, index + 1, current, permutations);

                swap(current, i, index);
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private int uniqueDeltaSize(int[] p) {
            Set<Integer> set = new HashSet<>();
            for(int i = 1; i < p.length; i++) {
                set.add(Math.abs(p[i] - p[i - 1]));
            }

            return set.size();
        }

        private void print(List<int[]> permutations) {
            for(var p : permutations) {
                for(int a : p) {
                    System.out.print(a + " ");
                }

                System.out.println();
            }
        }
    }

// 3
// 1

// 3
// 2

// 5
// 2

// 10
// 4

// 92
// 80
}

//    667. Beautiful Arrangement II
//    Medium
//    Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
//    Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
//
//    If there are multiple answers, print any of them.
//
//    Example 1:
//    Input: n = 3, k = 1
//    Output: [1, 2, 3]
//    Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
//    Example 2:
//    Input: n = 3, k = 2
//    Output: [1, 3, 2]
//    Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
//    Note:
//    The n and k are in the range 1 <= k < n <= 104.
