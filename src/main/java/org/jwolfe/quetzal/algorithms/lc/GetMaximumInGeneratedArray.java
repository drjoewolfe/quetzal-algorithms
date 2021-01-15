package org.jwolfe.quetzal.algorithms.lc;

public class GetMaximumInGeneratedArray {
    public int getMaximumGenerated(int n) {
        if(n < 2) {
            return n;
        }

        int max = 1;

        int[] series = new int[n + 1];
        series[0] = 0;
        series[1] = 1;
        for(int i = 2; i <= n; i++) {
            if(i % 2 == 0) {
                // even
                series[i] = series[i / 2];
            } else {
                // odd
                series[i] = series[i / 2] + series[i / 2 + 1];
                max = Math.max(max, series[i]);
            }
        }

        return max;
    }
}
