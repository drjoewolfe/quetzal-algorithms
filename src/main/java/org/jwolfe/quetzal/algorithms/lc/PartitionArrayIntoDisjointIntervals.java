package org.jwolfe.quetzal.algorithms.lc;

public class PartitionArrayIntoDisjointIntervals {
    class Solution {
        public int partitionDisjoint(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int n = A.length;
            int[] leftMaxs = new int[n];
            int[] rightMins = new int[n];

            leftMaxs[0] = A[0];
            for(int i = 1; i < n; i++) {
                leftMaxs[i] = Math.max(leftMaxs[i - 1], A[i]);
            }

            rightMins[n - 1] = A[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                rightMins[i] = Math.min(rightMins[i + 1], A[i]);
            }

            for(int i = 0; i < n - 1; i++) {
                if(leftMaxs[i] <= rightMins[i + 1]) {
                    return i + 1;
                }
            }

            return 0;
        }
    }

    class Solution_N2 {
        public int partitionDisjoint(int[] A) {
            if(A == null || A.length == 0) {
                return 0;
            }

            int n = A.length;

            int maxLeft = Integer.MIN_VALUE;
            for(int i = 0; i < n - 1; i++) {
                maxLeft = Math.max(maxLeft, A[i]);

                boolean disjoint = true;
                for(int j = i + 1; j < n; j++) {
                    if(A[j] < maxLeft) {
                        disjoint = false;
                        break;
                    }
                }

                if(disjoint) {
                    return i + 1;
                }
            }

            return 0;
        }
    }

    class Solution_Correct_2 {
        public int partitionDisjoint(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] maxFromLeft = new int[n];
            int[] minFromRight = new int[n];

            maxFromLeft[0] = nums[0];
            for(int i = 1; i < n; i++) {
                maxFromLeft[i] = Math.max(maxFromLeft[i - 1], nums[i]);
            }

            minFromRight[n - 1] = nums[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                minFromRight[i] = Math.min(minFromRight[i + 1], nums[i]);
            }

            for(int i = 0; i < n - 1; i++) {
                if(maxFromLeft[i] <= minFromRight[i + 1]) {
                    return i + 1;
                }
            }

            return n;
        }
    }

// [5,0,3,8,6]
// [1,1]
}

//    915. Partition Array into Disjoint Intervals
//    Medium
//    Given an array nums, partition it into two (contiguous) subarrays left and right so that:
//
//    Every element in left is less than or equal to every element in right.
//    left and right are non-empty.
//    left has the smallest possible size.
//    Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
//
//
//
//    Example 1:
//
//    Input: nums = [5,0,3,8,6]
//    Output: 3
//    Explanation: left = [5,0,3], right = [8,6]
//    Example 2:
//
//    Input: nums = [1,1,1,0,6,12]
//    Output: 4
//    Explanation: left = [1,1,1,0], right = [6,12]
//
//
//    Note:
//
//    2 <= nums.length <= 30000
//    0 <= nums[i] <= 106
//    It is guaranteed there is at least one way to partition nums as described.
