package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximizeTheMinimumPoweredCity {
    class Solution {
        public long maxPower(int[] stations, int r, int k) {
            if (stations == null || stations.length == 0 || r < 0 || k < 0) {
                return 0L;
            }

            int n = stations.length;

            // Difference Array
            long[] difference = new long[n + 1];

            for (int i = 0; i < n; i++) {
                int left = Math.max(0, i - r);
                int right = Math.min(n, i + r + 1);

                int count = stations[i];
                difference[left] += count;
                difference[right] -= count;
            }

            long low = Arrays.stream(stations).min().getAsInt();
            long high = Arrays.stream(stations).asLongStream().sum() + k;

            long maxPower = 0;
            while (low <= high) {
                long mid = low + (high - low) / 2;

                if (isMaxMinFeasible(difference, mid, n, r, k)) {
                    low = mid + 1;
                    maxPower = mid;
                } else {
                    high = mid - 1;
                }
            }

            return maxPower;
        }

        private boolean isMaxMinFeasible(long[] difference, long candidate, int n, int r, int k) {
            long[] tempDifference = difference.clone();
            long sum = 0;

            for (int i = 0; i < n; i++) {
                long delta = tempDifference[i];
                sum += delta;

                if (sum < candidate) {
                    // Try with adding (k - sum) stations
                    // Greedy - best to add (k - sum) to (i + r)th city
                    //      This will provide till (i + 2 * r) th city

                    long shortFall = candidate - sum;
                    if (shortFall > k) {
                        return false;
                    }

                    sum += shortFall;
                    k -= shortFall;

                    int end = Math.min(n, i + (2 * r) + 1);
                    tempDifference[end] -= shortFall;
                }
            }

            return true;
        }
    }
}

//    2528. Maximize the Minimum Powered City
//    Hard
//    You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power stations in the ith city.
//
//    Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
//
//    Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
//    The power of a city is the total number of power stations it is being provided power from.
//
//    The government has sanctioned building k more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.
//
//    Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations are built optimally.
//
//    Note that you can build the k power stations in multiple cities.
//
//
//
//    Example 1:
//
//    Input: stations = [1,2,4,5,0], r = 1, k = 2
//    Output: 5
//    Explanation:
//    One of the optimal ways is to install both the power stations at city 1.
//    So stations will become [1,4,4,5,0].
//    - City 0 is provided by 1 + 4 = 5 power stations.
//    - City 1 is provided by 1 + 4 + 4 = 9 power stations.
//    - City 2 is provided by 4 + 4 + 5 = 13 power stations.
//    - City 3 is provided by 5 + 4 = 9 power stations.
//    - City 4 is provided by 5 + 0 = 5 power stations.
//    So the minimum power of a city is 5.
//    Since it is not possible to obtain a larger power, we return 5.
//    Example 2:
//
//    Input: stations = [4,4,4,4], r = 0, k = 3
//    Output: 4
//    Explanation:
//    It can be proved that we cannot make the minimum power of a city greater than 4.
//
//
//    Constraints:
//
//    n == stations.length
//    1 <= n <= 105
//    0 <= stations[i] <= 105
//    0 <= r <= n - 1
//    0 <= k <= 109