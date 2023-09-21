package org.jwolfe.quetzal.algorithms.lc;

public class MedianOfTwoSortedArrays {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1 == null && nums2 == null) {
                return 0d;
            }

            int n1 = nums1.length;
            int n2 = nums2.length;

            int n = n1 + n2;
            if(n % 2 == 0) {
                return (solve(nums1, nums2, n / 2, 0, n1 - 1, 0, n2 - 1)
                        + solve(nums1, nums2, n / 2 - 1, 0, n1 - 1, 0, n2 - 1)) / 2;
            } else {
                return solve(nums1, nums2, n / 2, 0, n1 - 1, 0, n2 - 1);
            }
        }

        private double solve(int[] nums1, int[] nums2, int k, int start1, int end1, int start2, int end2) {
            if(end1 < start1) {
                return nums2[k - start1];
            }

            if(end2 < start2) {
                return nums1[k - start2];
            }

            int mid1 = start1 + (end1 - start1) / 2;
            int mid2 = start2 + (end2 - start2) / 2;

            int val1 = nums1[mid1];
            int val2 = nums2[mid2];

            if(mid1 + mid2 >= k) {
                // cut right half of array with larger mid
                if(val1 <= val2) {
                    return solve(nums1, nums2, k, start1, end1, start2, end2 - 1);
                } else {
                    return solve(nums1, nums2, k, start1, mid1 - 1, start2, end2);
                }
            } else {
                // cut left half of array with smaller mid
                if(val1 <= val2) {
                    return solve(nums1, nums2, k, mid1 + 1, end1, start2, end2);
                } else {
                    return solve(nums1, nums2, k, start1, end1, mid2 + 1, end2);
                }
            }
        }
    }

    class Solution_Incorrect {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1 == null && nums2 == null) {
                return 0;
            }

            if(nums1 == null || nums1.length == 0) {
                return getMedian(nums2);
            }

            if(nums2 == null || nums2.length == 0) {
                return getMedian(nums1);
            }

            int m = nums1.length;
            int n = nums2.length;

            int length = m + n;
            int low = 0;
            int high = m - 1;

            while(low <= high) {
                int mid1 = low + (high - low) / 2;
                // int mid2 = length / 2 - mid1 - 1 + (n % 2 == 0 ? -1 : 0);
                int mid2 = (length + 1) / 2 - mid1 - 2;

                System.out.println(low + ", " + high + ", " + mid1 + ", " + mid2);
                int l1 = mid1 >= 0 ? nums1[mid1] : Integer.MIN_VALUE;
                int l2 = mid2 >= 0 ? nums2[mid2] : Integer.MIN_VALUE;

                int r1 = mid1 < (m - 1) ? nums1[mid1 + 1] : Integer.MAX_VALUE;
                int r2 = mid2 < (n - 1) ? nums2[mid2 + 1] : Integer.MAX_VALUE;

                System.out.println(l1 + ", " + l2 + ", " + r1 + ", " + r2);
                if(l1 <= r2 && l2 <= r1) {
                    if(length % 2 == 0) {
                        return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                    } else {
                        return Math.max(l1, l2);
                    }
                }

                if(l1 > r2) {
                    high = mid1 - 1;
                } else {
                    low = mid1 + 1;
                }

                System.out.println(low + ", " + high);
            }

            return -1;
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

    class Solution_Brute {
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

// [1,3]
// [2]

// [1,2]
// [3,4]

// [1]
// [1]

// [100001]
// [100000]
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