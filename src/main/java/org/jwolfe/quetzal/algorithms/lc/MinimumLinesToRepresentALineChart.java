package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumLinesToRepresentALineChart {
    class Solution {
        public int minimumLines(int[][] stockPrices) {
            if(stockPrices == null || stockPrices.length < 2) {
                return 0;
            }

            if(stockPrices.length < 3) {
                return 1;
            }

            Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
            int n = stockPrices.length;
            int lines = n - 1;
            for(int i = 2; i < n; i++) {
                int[] p1 = stockPrices[i - 2];
                int[] p2 = stockPrices[i - 1];
                int[] p3 = stockPrices[i];

                if(1L * (p3[1] - p2[1]) * (p2[0] - p1[0]) == 1L * (p2[1] - p1[1]) * (p3[0] - p2[0])) {
                    lines--;
                }
            }

            return lines;
        }
    }

    class Solution_Incorrect {
        public int minimumLines(int[][] stockPrices) {
            if(stockPrices == null || stockPrices.length < 2) {
                return 0;
            }

            if(stockPrices.length < 3) {
                return 1;
            }

            Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
            int lines = 1;

            int[] firstPoint = stockPrices[0];
            int[] prevPoint = stockPrices[1];
            double prevSlope = 1d * (prevPoint[1] - firstPoint[1]) / (prevPoint[0] - firstPoint[0]);
            for(int i = 2; i < stockPrices.length; i++) {
                int[] point = stockPrices[i];
                double slope = 1d * (point[1] - prevPoint[1]) / (point[0] - prevPoint[0]);
                System.out.println(prevSlope + ", " + slope);
                if(slope != prevSlope) {
                    lines++;
                    prevSlope = slope;
                    prevPoint = point;
                }

                System.out.println("\t" + lines);
            }

            return lines;
        }
    }

// [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
// [[3,4],[1,2],[7,8],[2,3]]
// [[72,98],[62,27],[32,7],[71,4],[25,19],[91,30],[52,73],[10,9],[99,71],[47,22],[19,30],[80,63],[18,15],[48,17],[77,16],[46,27],[66,87],[55,84],[65,38],[30,9],[50,42],[100,60],[75,73],[98,53],[22,80],[41,61],[37,47],[95,8],[51,81],[78,79],[57,95]]
// [[1,1],[2,2],[3,3],[4,5],[5,7],[6,6],[7,5],[8,4],[9,4],[10,4]]
}

//    2280. Minimum Lines to Represent a Line Chart
//    Medium
//    You are given a 2D integer array stockPrices where stockPrices[i] = [dayi, pricei] indicates the price of the stock on day dayi is pricei. A line chart is created from the array by plotting the points on an XY plane with the X-axis representing the day and the Y-axis representing the price and connecting adjacent points. One such example is shown below:
//
//
//    Return the minimum number of lines needed to represent the line chart.
//
//
//
//    Example 1:
//
//
//    Input: stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
//    Output: 3
//    Explanation:
//    The diagram above represents the input, with the X-axis representing the day and Y-axis representing the price.
//    The following 3 lines can be drawn to represent the line chart:
//    - Line 1 (in red) from (1,7) to (4,4) passing through (1,7), (2,6), (3,5), and (4,4).
//    - Line 2 (in blue) from (4,4) to (5,4).
//    - Line 3 (in green) from (5,4) to (8,1) passing through (5,4), (6,3), (7,2), and (8,1).
//    It can be shown that it is not possible to represent the line chart using less than 3 lines.
//    Example 2:
//
//
//    Input: stockPrices = [[3,4],[1,2],[7,8],[2,3]]
//    Output: 1
//    Explanation:
//    As shown in the diagram above, the line chart can be represented with a single line.
//
//
//    Constraints:
//
//    1 <= stockPrices.length <= 105
//    stockPrices[i].length == 2
//    1 <= dayi, pricei <= 109
//    All dayi are distinct.