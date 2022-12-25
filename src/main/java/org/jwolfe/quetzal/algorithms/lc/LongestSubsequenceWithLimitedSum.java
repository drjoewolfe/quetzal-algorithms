package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.TreeMap;

public class LongestSubsequenceWithLimitedSum {
    class Solution {
        public int[] answerQueries(int[] nums, int[] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            Arrays.sort(nums);

            int[] prefixSums = new int[n];
            prefixSums[0] = nums[0];
            for(int i = 1; i < n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i];
            }

            int m = queries.length;
            int[] results = new int[m];
            for(int i = 0; i < m; i++) {
                int query = queries[i];
                int index = binarySearchForLesserOrEqual(prefixSums, query);

                if(index != -1) {
                    results[i] = index + 1;
                }
            }

            return results;
        }

        private int binarySearchForLesserOrEqual(int[] arr, int number) {
            int left = 0;
            int right = arr.length - 1;
            int index = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int val = arr[mid];

                if(val <= number) {
                    index = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }
    }

    class Solution_Correct_1 {
        public int[] answerQueries(int[] nums, int[] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            Arrays.sort(nums);

            int n = nums.length;
            int m = queries.length;

            int[] sums = new int[n];
            sums[0] = nums[0];

            for(int i = 1; i < n; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }

            int[] answers = new int[m];

            for(int i = 0; i < m; i++) {
                int query = queries[i];
                int index = binarySearch(sums, query);

                if(index != -1) {
                    answers[i] = index + 1;
                } else {
                    answers[i] = 0;
                }
            }

            return answers;
        }

        private int binarySearch(int[] arr, int n) {
            int left = 0;
            int right = arr.length - 1;

            int index = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] == n) {
                    index = mid;
                    break;
                } else if(arr[mid] < n) {
                    index = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Map {
        public int[] answerQueries(int[] nums, int[] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            Arrays.sort(nums);

            int n = nums.length;
            int m = queries.length;

            TreeMap<Integer, Integer> map = new TreeMap<>();
            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += nums[i];
                map.put(sum, i + 1);
            }

            int[] answers = new int[m];

            for(int i = 0; i < m; i++) {
                int query = queries[i];
                var key = map.floorKey(query);
                if(key != null) {
                    answers[i] = map.get(map.floorKey(query));
                } else {
                    answers[i] = 0;
                }
            }

            return answers;
        }
    }

    class Solution_Brute {
        public int[] answerQueries(int[] nums, int[] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            Arrays.sort(nums);

            int n = nums.length;
            int m = queries.length;

            int[] answers = new int[m];

            for(int i = 0; i < m; i++) {
                int query = queries[i];

                int sum = 0;
                int maxLength = 0;
                for(int j = 0; j < n; j++) {
                    sum += nums[j];
                    if(sum > query) {
                        break;
                    }

                    maxLength++;
                }

                answers[i] = maxLength;
            }

            return answers;
        }
    }

// [4,5,2,1]
// [3,10,21]

// [736411,184882,914641,37925,214915]
// [331244,273144,118983,118252,305688,718089,665450]
}

//    2389. Longest Subsequence With Limited Sum
//    Easy
//    You are given an integer array nums of length n, and an integer array queries of length m.
//
//    Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].
//
//    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: nums = [4,5,2,1], queries = [3,10,21]
//    Output: [2,3,4]
//    Explanation: We answer the queries as follows:
//    - The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
//    - The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
//    - The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
//    Example 2:
//
//    Input: nums = [2,3,4,5], queries = [1]
//    Output: [0]
//    Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
//
//
//    Constraints:
//
//    n == nums.length
//    m == queries.length
//    1 <= n, m <= 1000
//    1 <= nums[i], queries[i] <= 106