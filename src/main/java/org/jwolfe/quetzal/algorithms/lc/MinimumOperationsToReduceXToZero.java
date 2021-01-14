package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        if(nums == null || nums.length == 0 || x < 1) {
            return -1;
        }

        int n = nums.length;
        Map<Integer, Integer> prefixSums = new HashMap<>();

        int minOperations = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            prefixSums.put(sum, i);

            if(sum == x) {
                minOperations = Math.min(minOperations, i + 1);
            }
        }

        sum = 0;
        for(int i = n - 1; i >= 0; i--) {
            int a = nums[i];
            sum += a;
            int delta = x - sum;

            if(delta < 0) {
                break;
            }

            if(delta == 0) {
                minOperations = Math.min(minOperations, n - i);
                break;
            }

            if(prefixSums.containsKey(delta)) {
                int j = prefixSums.get(delta);
                if(j >= i) {
                    break;
                }
                minOperations = Math.min(minOperations, (n - i) + (j + 1));
            }
        }

        return minOperations != Integer.MAX_VALUE ? minOperations : -1;
    }
}
