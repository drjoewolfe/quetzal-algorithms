package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    class Solution {
        int MOD = 1_000_000_007;
        public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
            if(h < 1 || w < 1) {
                return 0;
            }

            if((horizontalCuts == null || horizontalCuts.length == 0)
                    && (verticalCuts == null || verticalCuts.length == 0)) {
                return 0;
            }

            Arrays.sort(horizontalCuts);
            Arrays.sort(verticalCuts);

            int maxHorizontalDiff = getMaxDifference(horizontalCuts, h);
            int maxVerticalDiff = getMaxDifference(verticalCuts, w);

            long area = 1L * maxHorizontalDiff * maxVerticalDiff;
            area %= MOD;

            return (int) area;
        }

        private int getMaxDifference(int[] cuts, int rightVal) {
            int maxDiff = cuts[0];
            int prev = cuts[0];
            for(int i = 1; i < cuts.length; i++) {
                int curr = cuts[i];
                maxDiff = Math.max(maxDiff, curr - prev);

                prev = curr;
            }

            maxDiff = Math.max(maxDiff, rightVal - prev);
            return maxDiff;
        }
    }

    class Solution_Brute {
        int MOD = 1_000_000_007;
        public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
            if(h < 1 || w < 1) {
                return 0;
            }

            if((horizontalCuts == null || horizontalCuts.length == 0)
                    && (verticalCuts == null || verticalCuts.length == 0)) {
                return 0;
            }

            Arrays.sort(horizontalCuts);
            Arrays.sort(verticalCuts);

            int hc = horizontalCuts.length;
            int vc = verticalCuts.length;

            int h1 = 0;
            int h2 = 0;
            int hi = 0;

            int maxArea = 0;
            while(h2 < h) {
                h2 = hi < hc ? horizontalCuts[hi] : h;

                int v1 = 0;
                int v2 = 0;
                int vi = 0;

                while(v2 < w) {
                    v2 = vi < vc ? verticalCuts[vi] : w;
                    long area = (h2 - h1) * (v2 - v1);
                    area %= MOD;

                    if(maxArea < area) {
                        maxArea = (int) area;
                    }

                    vi++;
                    v1 = v2;
                }

                hi++;
                h1 = h2;

            }

            return maxArea;
        }
    }

// 5
// 4
// [1,2,4]
// [1,3]

}

//    1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
//    Medium
//    Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
//
//    Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
//
//
//
//    Example 1:
//
//
//
//    Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
//    Output: 4
//    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
//    Example 2:
//
//
//
//    Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
//    Output: 6
//    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
//    Example 3:
//
//    Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
//    Output: 9
//
//
//    Constraints:
//
//    2 <= h, w <= 10^9
//    1 <= horizontalCuts.length < min(h, 10^5)
//    1 <= verticalCuts.length < min(w, 10^5)
//    1 <= horizontalCuts[i] < h
//    1 <= verticalCuts[i] < w
//    It is guaranteed that all elements in horizontalCuts are distinct.
//    It is guaranteed that all elements in verticalCuts are distinct.