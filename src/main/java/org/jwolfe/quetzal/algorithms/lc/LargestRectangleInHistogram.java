package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class LargestRectangleInHistogram {
    class Solution {
        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0) {
                return 0;
            }

            int n = heights.length;
            Stack<Integer> stack = new Stack<>();

            int maxArea = 0;
            for(int i = 0; i <= n; i++) {
                while(!stack.isEmpty() && (i == n || heights[stack.peek()] > heights[i])) {
                    // For the element denoted by the top of the stack
                    int element = stack.pop();

                    int right = i;
                    int left = !stack.isEmpty() ? stack.peek() : -1;

                    int width = right - left - 1;
                    int height = heights[element];
                    int area = height * width;

                    maxArea = Math.max(maxArea, area);
                }

                stack.push(i);
            }

            return maxArea;
        }
    }

    class Solution_Correct_1 {
        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0) {
                return 0;
            }

            int n = heights.length;

            Stack<Integer> stack = new Stack<>();
            int[] firstLesserLefts = new int[n];
            for(int i = 0; i < n; i++) {
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }

                firstLesserLefts[i] = !stack.isEmpty() ? stack.peek() : -1;
                stack.push(i);
            }

            stack.clear();
            int[] firstLesserRights = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }

                firstLesserRights[i] = !stack.isEmpty() ? stack.peek() : n;
                stack.push(i);
            }

            int maxArea = 0;
            for(int i = 0; i < n; i++) {
                int left = firstLesserLefts[i];
                int right = firstLesserRights[i];

                int width = right - left - 1;
                int height = heights[i];
                int area = width * height;

                maxArea = Math.max(maxArea, area);
            }

            return maxArea;
        }

        private void print(int[] arr) {
            for(int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }

    class Solution_Brute {
        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0) {
                return 0;
            }

            int n = heights.length;

            int maxArea = 0;
            for(int i = 0; i < n; i++) {
                int left = findFirstLesserLeft(heights, i);
                int right = findFirstLesserRight(heights, i);

                int width = right - left - 1;
                int height = heights[i];
                int area = width * height;

                maxArea = Math.max(maxArea, area);
            }

            return maxArea;
        }

        private int findFirstLesserLeft(int[] heights, int index) {
            for(int i = index - 1; i >= 0; i--) {
                if(heights[i] < heights[index]) {
                    return i;
                }
            }

            return -1;
        }

        private int findFirstLesserRight(int[] heights, int index) {
            for(int i = index + 1; i < heights.length; i++) {
                if(heights[i] < heights[index]) {
                    return i;
                }
            }

            return heights.length;
        }
    }

// [2,1,5,6,2,3]
// [2,4]
// [1,1]
}

//    84. Largest Rectangle in Histogram
//    Hard
//    Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
//
//
//
//    Example 1:
//
//
//    Input: heights = [2,1,5,6,2,3]
//    Output: 10
//    Explanation: The above is a histogram where width of each bar is 1.
//    The largest rectangle is shown in the red area, which has an area = 10 units.
//    Example 2:
//
//
//    Input: heights = [2,4]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= heights.length <= 105
//    0 <= heights[i] <= 104
