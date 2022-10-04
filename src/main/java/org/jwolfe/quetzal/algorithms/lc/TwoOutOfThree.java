package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoOutOfThree {
    class Solution {
        public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
            if(nums1 == null || nums2 == null || nums3 == null) {
                return null;
            }

            Set<Integer> set1 = new HashSet<>();
            for(int a : nums1) {
                set1.add(a);
            }

            Set<Integer> set2 = new HashSet<>();
            for(int b : nums2) {
                set2.add(b);
            }

            Set<Integer> set3 = new HashSet<>();
            for(int c : nums3) {
                set3.add(c);
            }

            Set<Integer> intersection = new HashSet<>();
            for(int a : set1) {
                if(set2.contains(a) || set3.contains(a)) {
                    intersection.add(a);
                }
            }

            for(int b : set2) {
                if(set1.contains(b) || set3.contains(b)) {
                    intersection.add(b);
                }
            }

            for(int c : set3) {
                if(set1.contains(c) || set2.contains(c)) {
                    intersection.add(c);
                }
            }

            return new ArrayList<>(intersection);
        }
    }
}

//    2032. Two Out of Three
//    Easy
//    Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.
//
//
//    Example 1:
//
//    Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
//    Output: [3,2]
//    Explanation: The values that are present in at least two arrays are:
//    - 3, in all three arrays.
//    - 2, in nums1 and nums2.
//    Example 2:
//
//    Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
//    Output: [2,3,1]
//    Explanation: The values that are present in at least two arrays are:
//    - 2, in nums2 and nums3.
//    - 3, in nums1 and nums2.
//    - 1, in nums1 and nums3.
//    Example 3:
//
//    Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
//    Output: []
//    Explanation: No value is present in at least two arrays.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length, nums3.length <= 100
//    1 <= nums1[i], nums2[j], nums3[k] <= 100
