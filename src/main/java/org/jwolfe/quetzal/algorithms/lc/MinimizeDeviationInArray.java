package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeSet;

public class MinimizeDeviationInArray {
    public int minimumDeviation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < n; i++) {
            int element = nums[i];
            if(element % 2 == 0) {
                set.add(element);
            } else {
                set.add(element * 2);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        while(true) {
            int min = set.first();
            int max = set.last();

            int diff = max - min;
            minDiff = Math.min(minDiff, diff);


            if(max % 2 == 0) {
                set.remove(max);

                max /= 2;
                set.add(max);
            } else {
                break;
            }
        }

        return minDiff;
    }
}
