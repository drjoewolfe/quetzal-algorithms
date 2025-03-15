package org.jwolfe.quetzal.algorithms.lc;

public class HouseRobberIV {
    class Solution {
        public int minCapability(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return 0;
            }

            int n = nums.length;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                int val = nums[i];
                min = Math.min(min, val);
                max = Math.max(max, val);
            }

            while (min < max) {
                int mid = min + (max - min) / 2;

                int count = 0;
                for (int i = 0; i < n; i++) {
                    int val = nums[i];
                    if (val <= mid) {
                        count++;
                        i++;
                    }
                }

                if (count >= k) {
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }

            return min;
        }
    }

    class Solution_Correct_1 {
        public int minCapability(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0) {
                return 0;
            }

            int left = 0;
            int right = 1_000_000_000;

            while (left < right) {
                int mid = left + (right - left) / 2;

                int count = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= mid) {
                        count++;
                        i++;
                    }
                }

                if (count >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_TLE {
        public int minCapability(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0) {
                return 0;
            }

            int minCapability = Integer.MAX_VALUE;
            for (int i = 0; i <= nums.length - k; i++) {
                minCapability = Math.min(minCapability,
                        getMinCapability(nums, k - 1, i, nums[i]));
            }

            return minCapability;
        }

        private int getMinCapability(int[] nums, int k, int index, int capability) {
            if (k == 0) {
                return capability;
            }

            int minCapability = Integer.MAX_VALUE;
            for (int i = index + 2; i < nums.length; i++) {
                minCapability = Math.min(minCapability,
                        getMinCapability(nums, k - 1, i, Math.max(capability, nums[i])));
            }

            return minCapability;
        }
    }

// [2,3,5,9]
// 2

// [2,7,9,3,1]
// 2

// [1]
// 1
}

//    2560. House Robber IV
//    Medium
//    There are several consecutive houses along a street, each of which has some money inside. There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.
//
//    The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.
//
//    You are given an integer array nums representing how much money is stashed in each house. More formally, the ith house from the left has nums[i] dollars.
//
//    You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.
//
//    Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,5,9], k = 2
//    Output: 5
//    Explanation:
//    There are three ways to rob at least 2 houses:
//    - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
//    - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
//    - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
//    Therefore, we return min(5, 9, 9) = 5.
//    Example 2:
//
//    Input: nums = [2,7,9,3,1], k = 2
//    Output: 2
//    Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    1 <= k <= (nums.length + 1)/2