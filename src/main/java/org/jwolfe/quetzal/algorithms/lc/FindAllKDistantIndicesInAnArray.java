package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindAllKDistantIndicesInAnArray {
    class Solution {
        public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
            List<Integer> keyIndices = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == key) {
                    keyIndices.add(i);
                }
            }

            Set<Integer> set = new TreeSet<>();
            for(int j : keyIndices) {
                for(int i = Math.max(0, j - k); i < Math.min(nums.length, j + k + 1); i++) {
                    set.add(i);
                }
            }

            return new ArrayList<>(set);
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Standard {
        public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
            List<Integer> keyIndices = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == key) {
                    keyIndices.add(i);
                }
            }

            Set<Integer> set = new TreeSet<>();
            for(int i = 0; i < nums.length; i++) {
                for(int j : keyIndices) {
                    int distance = Math.abs(i - j);
                    if(distance <= k) {
                        set.add(i);
                    }
                }
            }

            return new ArrayList<>(set);
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [3,4,9,1,3,9,5]
// 9
// 1

// [222,151,842,244,103,736,219,432,565,216,36,198,10,367,778,111,307,460,92,622,750,36,559,983,782,432,312,111,676,179,44,86,766,371,746,905,850,170,892,80,449,932,295,875,259,556,730,441,153,869]
// 153
// 19
}

//    2200. Find All K-Distant Indices in an Array
//    Easy
//    You are given a 0-indexed integer array nums and two integers key and k. A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
//
//    Return a list of all k-distant indices sorted in increasing order.
//
//
//
//    Example 1:
//
//    Input: nums = [3,4,9,1,3,9,5], key = 9, k = 1
//    Output: [1,2,3,4,5,6]
//    Explanation: Here, nums[2] == key and nums[5] == key.
//    - For index 0, |0 - 2| > k and |0 - 5| > k, so there is no j where |0 - j| <= k and nums[j] == key. Thus, 0 is not a k-distant index.
//    - For index 1, |1 - 2| <= k and nums[2] == key, so 1 is a k-distant index.
//    - For index 2, |2 - 2| <= k and nums[2] == key, so 2 is a k-distant index.
//    - For index 3, |3 - 2| <= k and nums[2] == key, so 3 is a k-distant index.
//    - For index 4, |4 - 5| <= k and nums[5] == key, so 4 is a k-distant index.
//    - For index 5, |5 - 5| <= k and nums[5] == key, so 5 is a k-distant index.
//    - For index 6, |6 - 5| <= k and nums[5] == key, so 6 is a k-distant index.
//    Thus, we return [1,2,3,4,5,6] which is sorted in increasing order.
//    Example 2:
//
//    Input: nums = [2,2,2,2,2], key = 2, k = 2
//    Output: [0,1,2,3,4]
//    Explanation: For all indices i in nums, there exists some index j such that |i - j| <= k and nums[j] == key, so every index is a k-distant index.
//    Hence, we return [0,1,2,3,4].
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 1000
//    key is an integer from the array nums.
//    1 <= k <= nums.length