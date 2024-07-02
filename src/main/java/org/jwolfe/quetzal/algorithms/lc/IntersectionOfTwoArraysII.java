package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }

            int[] frequencies = new int[1001];
            for (int a : nums1) {
                frequencies[a]++;
            }

            List<Integer> intersection = new ArrayList<>();
            for (int a : nums2) {
                if (frequencies[a] > 0) {
                    intersection.add(a);
                    frequencies[a]--;
                }
            }

            int[] results = new int[intersection.size()];
            for (int i = 0; i < intersection.size(); i++) {
                results[i] = intersection.get(i);
            }

            return results;
        }
    }

    class Solution_Correct_3 {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int a : nums1) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            List<Integer> intersection = new ArrayList<>();

            for (int a : nums2) {
                if (frequencies.containsKey(a) && frequencies.get(a) > 0) {
                    intersection.add(a);

                    frequencies.put(a, frequencies.get(a) - 1);
                }
            }

            int n = intersection.size();
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = intersection.get(i);
            }

            return result;
        }
    }

    class Solution_Correct_2 {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int a : nums1) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            List<Integer> intersection = new ArrayList<>();
            for (int a : nums2) {
                if (frequencies.containsKey(a)) {
                    intersection.add(a);

                    if (frequencies.get(a) > 1) {
                        frequencies.put(a, frequencies.get(a) - 1);
                    } else {
                        frequencies.remove(a);
                    }
                }
            }

            int[] rv = new int[intersection.size()];
            for (int i = 0; i < intersection.size(); i++) {
                rv[i] = intersection.get(i);
            }

            return rv;
        }
    }
}

//    350. Intersection of Two Arrays II
//    Easy
//    Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,2,2,1], nums2 = [2,2]
//    Output: [2,2]
//    Example 2:
//
//    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//    Output: [4,9]
//    Explanation: [9,4] is also accepted.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 1000
//    0 <= nums1[i], nums2[i] <= 1000
//
//
//    Follow up:
//
//    What if the given array is already sorted? How would you optimize your algorithm?
//    What if nums1's size is small compared to nums2's size? Which algorithm is better?
//    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?