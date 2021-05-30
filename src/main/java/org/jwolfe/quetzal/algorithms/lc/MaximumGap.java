package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumGap {
    class Solution {
        public int maximumGap(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            if(n == 2) {
                return Math.abs(nums[0] - nums[1]);
            }

            int min = nums[0];
            int max = nums[0];
            for(int a : nums) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int size = Math.max((max - min) / (n - 1), 1);
            int bucketCount = Math.max((max - min) / size, 1);

            int[][] buckets = new int[bucketCount][];
            for(int a : nums) {
                int i = (a - min) / size;
                if(i == bucketCount) {
                    i = bucketCount - 1;
                }

                if(buckets[i] == null) {
                    buckets[i] = new int[] {a, a};
                } else {
                    buckets[i][0] = Math.min(buckets[i][0], a);
                    buckets[i][1] = Math.max(buckets[i][1], a);
                }
            }

            if(bucketCount == 1) {
                return buckets[0][1] - buckets[0][0];
            }

            int maxGap = 0;
            int prev = buckets[0][1];
            for(int i = 1; i < bucketCount; i++) {
                if(buckets[i] == null) {
                    continue;
                }

                int curr = buckets[i][0];
                maxGap = Math.max(maxGap, curr - prev);

                prev = buckets[i][1];
            }

            return maxGap;
        }

        private void print(int[][] arr) {
            for(int i = 0; i < arr.length; i++) {
                int[] row = arr[i];
                if(row == null) {
                    continue;
                }

                for(int j = 0; j < row.length; j++) {
                    System.out.print(row[j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Sorting {
        public int maximumGap(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            Arrays.sort(nums);
            int maxGap = 0;
            int prev = nums[0];
            for(int i = 1; i < nums.length; i++) {
                int curr = nums[i];
                maxGap = Math.max(maxGap, curr - prev);

                prev = curr;
            }

            return maxGap;
        }
    }

// [3,6,9,1]
// [1,10000000]
// [1,1,1,1]
// [1,1,1,1,1,5,5,5,5,5]
// [12115,10639,2351,29639,31300,11245,16323,24899,8043,4076,17583,15872,19443,12887,5286,6836,31052,25648,17584,24599,13787,24727,12414,5098,26096,23020,25338,28472,4345,25144,27939,10716,3830,13001,7960,8003,10797,5917,22386,12403,2335,32514,23767,1868,29882,31738,30157,7950,20176,11748,13003,13852,19656,25305,7830,3328,19092,28245,18635,5806,18915,31639,24247,32269,29079,24394,18031,9395,8569,11364,28701,32496,28203,4175,20889,28943,6495,14919,16441,4568,23111,20995,7401,30298,2636,16791,1662,27367,2563,22169,1607,15711,29277,32386,27365,31922,26142,8792]
// 1, 1, 4, 1, 0
}

//    164. Maximum Gap
//    Hard
//
//    1253
//
//    224
//
//    Add to List
//
//    Share
//    Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
//
//    You must write an algorithm that runs in linear time and uses linear extra space.
//
//
//
//    Example 1:
//
//    Input: nums = [3,6,9,1]
//    Output: 3
//    Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
//    Example 2:
//
//    Input: nums = [10]
//    Output: 0
//    Explanation: The array contains less than 2 elements, therefore return 0.
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    0 <= nums[i] <= 109