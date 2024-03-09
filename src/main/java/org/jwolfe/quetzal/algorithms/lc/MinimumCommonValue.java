package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class MinimumCommonValue {
    class Solution {
        public int getCommon(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return -1;
            }

            int i = 0;
            int n1 = nums1.length;

            int j = 0;
            int n2 = nums2.length;

            while(i < n1 && j < n2) {
                if(nums1[i] == nums2[j]) {
                    return nums1[i];
                }

                if(nums1[i] > nums2[j]) {
                    j++;
                } else {
                    i++;
                }
            }


            return -1;
        }
    }

    class Solution_Correct_2 {
        public int getCommon(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return -1;
            }

            int i = 0;
            int j = 0;

            while(i < nums1.length && j < nums2.length) {
                if(nums1[i] == nums2[j]) {
                    return nums1[i];
                } else if(nums1[i] > nums2[j]) {
                    j++;
                } else {
                    i++;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int getCommon(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return -1;
            }

            Set<Integer> set = new HashSet<>();
            for(int val : nums1) {
                set.add(val);
            }

            for(int val : nums2) {
                if(set.contains(val)) {
                    return val;
                }
            }

            return -1;
        }
    }
}

//    2540. Minimum Common Value
//    Easy
//    Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
//
//    Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,2,3], nums2 = [2,4]
//    Output: 2
//    Explanation: The smallest element common to both arrays is 2, so we return 2.
//    Example 2:
//
//    Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
//    Output: 2
//    Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 105
//    1 <= nums1[i], nums2[j] <= 109
//    Both nums1 and nums2 are sorted in non-decreasing order.