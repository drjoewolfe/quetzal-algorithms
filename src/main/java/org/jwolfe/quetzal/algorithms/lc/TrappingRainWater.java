package org.jwolfe.quetzal.algorithms.lc;

public class TrappingRainWater {
    class Solution {
        public int trap(int[] height) {
            if(height == null || height.length < 3) {
                return 0;
            }

            int n = height.length;
            int[] maxFromLeft = new int[n];
            int[] maxFromRight = new int[n];

            maxFromLeft[0] = height[0];
            for(int i = 1; i < n; i++) {
                maxFromLeft[i] = Math.max(maxFromLeft[i - 1], height[i]);
            }

            maxFromRight[n - 1] = height[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                maxFromRight[i] = Math.max(maxFromRight[i + 1], height[i]);
            }

            int rainWater = 0;
            for(int i = 0; i <n; i++) {
                int column = Math.min(maxFromLeft[i], maxFromRight[i]) - height[i];
                if(column < 0) {
                    column = 0;
                }

                rainWater += column;
            }

            return rainWater;
        }
    }

    class Solution_Correct_3 {
        public int trap(int[] height) {
            if(height == null || height.length < 3) {
                return 0;
            }

            int length = height.length;

            int[] largestToRight = new int[length];
            largestToRight[length - 1] = height[length - 1];
            for(int i = length - 2; i >=0; i--) {
                largestToRight[i] = Math.max(largestToRight[i + 1], height[i]);
            }

            int volume = 0;
            int left = 0;
            int right = 0;
            int current = 0;
            for(int i = 1; i < height.length - 1; i++) {
                left = Math.max(left, height[i - 1]);
                right = largestToRight[i];
                current = height[i];

                int currentVolume = Math.min(left, right) - current;
                volume += Math.max(currentVolume, 0);
            }

            return volume;
        }
    }

    class Solution_Correct_2 {
        public int trap(int[] height) {
            if(height == null || height.length < 2) {
                return 0;
            }

            int n = height.length;
            int[] maxFromRight = new int[n];
            maxFromRight[n - 1] = height[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                maxFromRight[i] = Math.max(height[i], maxFromRight[i + 1]);
            }

            int trappedWater = 0;
            int maxFromLeft = height[0];
            for(int i = 1; i < n - 1; i++) {
                trappedWater += Math.max(
                        Math.min(maxFromLeft, maxFromRight[i + 1]) - height[i],
                        0);

                maxFromLeft = Math.max(maxFromLeft, height[i]);
            }

            return trappedWater;
        }
    }

// Testcases
//  [0,1,0,2,1,0,1,3,2,1,2,1]
//  [2,0,2]
}

//    42. Trapping Rain Water
//    Hard
//    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//
//
//    Example 1:
//
//
//    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//    Output: 6
//    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//    Example 2:
//
//    Input: height = [4,2,0,3,2,5]
//    Output: 9
//
//
//    Constraints:
//
//    n == height.length
//    0 <= n <= 3 * 104
//    0 <= height[i] <= 105