package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }

            Set<Integer> set = new HashSet<>();
            for(int val : nums1) {
                set.add(val);
            }

            Set<Integer> intersection = new HashSet<>();
            for(int val : nums2) {
                if(set.contains(val)) {
                    intersection.add(val);
                }
            }

            int size = intersection.size();
            int[] results = new int[size];
            int index = 0;
            for(int val : intersection) {
                results[index++] = val;
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public int[] intersection(int[] nums1, int[] nums2) {
            if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }

            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < nums1.length; i++) {
                set.add(nums1[i]);
            }

            Set<Integer> intersection = new HashSet<>();
            for(int j = 0; j < nums2.length; j++) {
                if(set.contains(nums2[j])) {
                    intersection.add(nums2[j]);
                }
            }

            int[] results = new int[intersection.size()];
            int k = 0;
            for(int v : intersection) {
                results[k++] = v;
            }

            return results;
        }
    }
}

//    349. Intersection of Two Arrays
//    Easy
//    Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,2,2,1], nums2 = [2,2]
//    Output: [2]
//    Example 2:
//
//    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//    Output: [9,4]
//    Explanation: [4,9] is also accepted.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 1000
//    0 <= nums1[i], nums2[i] <= 1000