package org.jwolfe.quetzal.algorithms.lc;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null) {
            return 0;
        }

        if(nums1 == null) {
            return getMedian(nums2);
        }

        if(nums2 == null) {
            return getMedian(nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int [m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < m && j < n) {
            if(nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        while(i < m) {
            merged[k++] = nums1[i++];
        }

        while(j < n) {
            merged[k++] = nums2[j++];
        }

        return getMedian(merged);
    }

    private double getMedian(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }

        if(n % 2 == 0) {
            return (nums[n / 2 - 1] + nums[ n / 2]) / (1d * 2);
        } else {
            return nums[n / 2];
        }
    }
}

//    4. Median of Two Sorted Arrays
//    Hard
//
//    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//    Follow up: The overall run time complexity should be O(log (m+n)).
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,3], nums2 = [2]
//    Output: 2.00000
//    Explanation: merged array = [1,2,3] and median is 2.
//    Example 2:
//
//    Input: nums1 = [1,2], nums2 = [3,4]
//    Output: 2.50000
//    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//    Example 3:
//
//    Input: nums1 = [0,0], nums2 = [0,0]
//    Output: 0.00000
//    Example 4:
//
//    Input: nums1 = [], nums2 = [1]
//    Output: 1.00000
//    Example 5:
//
//    Input: nums1 = [2], nums2 = []
//    Output: 2.00000
//
//
//    Constraints:
//
//    nums1.length == m
//    nums2.length == n
//    0 <= m <= 1000
//    0 <= n <= 1000
//    1 <= m + n <= 2000
//    -106 <= nums1[i], nums2[i] <= 106