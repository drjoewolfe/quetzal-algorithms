package org.jwolfe.quetzal.algorithms.lc;

public class ConcatenationOfConsecutiveBinaryNumbers {
    final int MOD = 1_000_000_007;

    public int concatenatedBinary(int n) {
        if(n < 1) {
            return 0;
        }

        long result = 1;
        int width = 1;
        for(int i = 2; i <= n; i++) {
            if((i & (i - 1)) == 0) {
                width++;
            }

            result <<= width;
            result += i;

            result %= MOD;
        }

        return (int) result;
    }
}
