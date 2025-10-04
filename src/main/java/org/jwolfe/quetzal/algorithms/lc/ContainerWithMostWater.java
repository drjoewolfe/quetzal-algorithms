package org.jwolfe.quetzal.algorithms.lc;

public class ContainerWithMostWater {
    class Solution {
        public int maxArea(int[] height) {
            if (height == null || height.length < 2) {
                return 0;
            }

            int answer = 0;
            int n = height.length;

            int left = 0;
            int right = n - 1;

            while (left < right) {
                int leftHeight = height[left];
                int rightHeight = height[right];

                int area = Math.min(leftHeight, rightHeight) * (right - left);
                answer = Math.max(answer, area);

                if (leftHeight > rightHeight) {
                    right--;
                } else {
                    left++;
                }
            }

            return answer;
        }
    }

    public class Solution_1 {
        public int maxArea(int[] height) {
            if (height == null || height.length < 2) {
                return 0;
            }

            int n = height.length;

            int max = 0;
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int area = Math.min(height[left], height[right]) * (right - left);
                max = Math.max(max, area);

                if (height[left] > height[right]) {
                    right--;
                } else {
                    left++;
                }
            }

            return max;
        }
    }
}

//    11. Container With Most Water
//    Medium
//    Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
//
//    Notice that you may not slant the container.
//
//
//
//    Example 1:
//
//
//    Input: height = [1,8,6,2,5,4,8,3,7]
//    Output: 49
//    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//    Example 2:
//
//    Input: height = [1,1]
//    Output: 1
//    Example 3:
//
//    Input: height = [4,3,2,1,4]
//    Output: 16
//    Example 4:
//
//    Input: height = [1,2,1]
//    Output: 2
//
//
//    Constraints:
//
//    n == height.length
//    2 <= n <= 3 * 104
//    0 <= height[i] <= 3 * 104