package org.jwolfe.quetzal.algorithms.lc;

public class LongestAlternatingSubarray {
    class Solution {
        public int alternatingSubarray(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int maxLength = -1;

            int n = nums.length;
            int left = 0;
            int right = 1;
            boolean zag = false;

            while(right < n) {
                int diff = nums[right] - nums[right - 1];
                if(!zag) {
                    if(diff == 1) {
                        zag = true;
                        maxLength = Math.max(maxLength, right - left + 1);
                    } else {
                        left = right;
                    }
                } else {
                    if(diff == -1) {
                        zag = false;
                        maxLength = Math.max(maxLength, right - left + 1);
                    } else if(diff == 1) {
                        left = right - 1;
                        maxLength = Math.max(maxLength, right - left + 1);
                    } else {
                        zag = false;
                        left = right;
                    }
                }

                right++;
            }

            return maxLength;
        }
    }

    class Solution_Correct_N2 {
        public int alternatingSubarray(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int maxLength = -1;

            int n = nums.length;
            for(int i = 0; i < n - 1; i++) {
                for(int j = i + 1; j < n; j++) {
                    if(isAlternating(nums, i, j)) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }

            return maxLength;
        }

        private boolean isAlternating(int[] nums, int start, int end) {
            if(nums[start] + 1 != nums[start + 1]) {
                return false;
            }

            for(int i = start + 2; i <= end; i++) {
                if(nums[i] != nums[i - 2]){
                    return false;
                }
            }

            return true;
        }
    }
}

//    2765. Longest Alternating Subarray
//    Easy
//    You are given a 0-indexed integer array nums. A subarray s of length m is called alternating if:
//
//    m is greater than 1.
//    s1 = s0 + 1.
//    The 0-indexed subarray s looks like [s0, s1, s0, s1,...,s(m-1) % 2]. In other words, s1 - s0 = 1, s2 - s1 = -1, s3 - s2 = 1, s4 - s3 = -1, and so on up to s[m - 1] - s[m - 2] = (-1)m.
//    Return the maximum length of all alternating subarrays present in nums or -1 if no such subarray exists.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,4,3,4]
//    Output: 4
//    Explanation: The alternating subarrays are [3,4], [3,4,3], and [3,4,3,4]. The longest of these is [3,4,3,4], which is of length 4.
//    Example 2:
//
//    Input: nums = [4,5,6]
//    Output: 2
//    Explanation: [4,5] and [5,6] are the only two alternating subarrays. They are both of length 2.
//
//
//    Constraints:
//
//    2 <= nums.length <= 100
//    1 <= nums[i] <= 104