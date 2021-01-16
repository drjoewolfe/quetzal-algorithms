package org.jwolfe.quetzal.algorithms.lc;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0 || s <= 0) {
            return 0;
        }

        int n = nums.length;
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            while(sum >= s) {
                minLength = Math.min(minLength, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
