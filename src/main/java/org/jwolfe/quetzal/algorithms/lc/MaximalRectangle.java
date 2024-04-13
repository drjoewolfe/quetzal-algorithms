package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class MaximalRectangle {
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int maxArea = 0;

            int m = matrix.length;
            int n = matrix[0].length;

            int[] histogram = new int[n + 1];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    char c = matrix[i][j];

                    if(c == '0') {
                        histogram[j] = 0;
                    } else {
                        histogram[j]++;
                    }
                }

                int maxAreaInCurrentHistogram = getMaxArea(histogram);
                maxArea = Math.max(maxArea, maxAreaInCurrentHistogram);
            }

            return maxArea;
        }

        private int getMaxArea(int[] histogram) {
            int maxArea = 0;

            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < histogram.length; i++) {
                int currentHeight = histogram[i];

                while(!stack.isEmpty()
                        && histogram[stack.peek()] >= currentHeight) {
                    int j = stack.pop();

                    int minHeight = histogram[j];
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    int area = minHeight * width;

                    maxArea = Math.max(maxArea, area);
                }

                stack.push(i);
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

    class Solution_Correct_1 {
        public int maximalRectangle(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int maxArea = 0;

            int[] histogram = new int[n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '0') {
                        histogram[j] = 0;
                    } else {
                        histogram[j] += 1;
                    }
                }

                maxArea = Math.max(maxArea,
                        maxAreaInHistogram(histogram));
            }

            return maxArea;
        }

        private int maxAreaInHistogram(int[] histogram) {
            int n = histogram.length;

            Stack<Integer> stack = new Stack<>();

            int[] leftMins = new int[n];
            for(int i = 0; i < n; i++) {
                while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                    stack.pop();
                }

                int left = -1;
                if(!stack.isEmpty()) {
                    left = stack.peek();
                }

                leftMins[i] = left;

                stack.push(i);
            }

            stack.clear();
            int[] rightMins = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                    stack.pop();
                }

                int right = n;
                if(!stack.isEmpty()) {
                    right = stack.peek();
                }

                rightMins[i] = right;

                stack.push(i);
            }

            int maxArea = 0;
            for(int i = 0; i < n; i++) {
                maxArea = Math.max(maxArea,
                        (rightMins[i] - leftMins[i] - 1) * histogram[i]);
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

// [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
// [["1"]]
}

//    85. Maximal Rectangle
//    Hard
//    Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//    Output: 6
//    Explanation: The maximal rectangle is shown in the above picture.
//    Example 2:
//
//    Input: matrix = [["0"]]
//    Output: 0
//    Example 3:
//
//    Input: matrix = [["1"]]
//    Output: 1
//
//
//    Constraints:
//
//    rows == matrix.length
//    cols == matrix[i].length
//    1 <= row, cols <= 200
//    matrix[i][j] is '0' or '1'.